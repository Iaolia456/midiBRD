package midiBRD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Musician {
	public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	
	private DecodedMidi dmidi;
	private long duration;
	
	private long lastPlayAtTick = -1;
	private double currentTimeMS = 0;
	
	private Instrument instrument;
	private Clip metronomeClip;
	
	public Musician(Instrument instrument, DecodedMidi dmidi) {
		this.instrument = instrument;
		this.dmidi = dmidi;
		duration = dmidi.getDuration();
		
    	try {
			metronomeClip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(Musician.class.getResourceAsStream("takeStartGuideSound.wav"));
			metronomeClip.open(inputStream);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	}

	public void start(boolean isPlayAllTrack) {
		//wait for enter key pressed, play metronome sound, and wait 1 second
		System.out.println("Press enter to start playback from the beginning or input starting track number (starting from 0)");
		int currentTrack = 0;
		try {
			currentTrack = Integer.parseInt(betweenTrackWait());
		} catch (NumberFormatException e) {
			currentTrack = 0;
		}
		System.out.println("Start playing track " + currentTrack);
		
		//play custom emote/skill on the last slot of 4th bar
		playCustomEmote();
		
		//reset the timing and get the first note track
		long lastLoopTime = System.nanoTime();
		Map<Long, Integer> notes = dmidi.getNotesAtTrack(currentTrack);
		
		//player loop
		while (true) {
			//calculate delta time
			long now = System.nanoTime();
			long deltaTime = now - lastLoopTime;
			lastLoopTime = now;
			
			//end of the track
			if (currentTimeMS > duration) {
				System.out.println("End of track " + currentTrack);
				
				//multitrack mode
				if (isPlayAllTrack) {
					//not the last track, still has some tracks left to play
					if (currentTrack < dmidi.getTrackCount() - 1) {
						playCustomEmote();
						currentTrack++;
						
						//if 'r' was input. repeat the same track
						if(promptForNextTrack()) {
							currentTrack--;
							System.out.println("Repeating track " + currentTrack);
						}
						
						System.out.println("Start playing track " + currentTrack);
						
						//play custom emote
						playCustomEmote();
						
						//reset timing clock and start playing the next track
						notes = dmidi.getNotesAtTrack(currentTrack);
						currentTimeMS = 0;
						lastPlayAtTick = -1;
						lastLoopTime = System.nanoTime();
						
						continue;
					}
					else {
						//all track played
						playCustomEmote();
						
						System.out.println("End of file. Press enter to exit");
						waitForKeyPress();
						System.exit(0);
					}
				}
				else {
					if (dmidi.getTrackCount() > 1)
						System.out.println("Musician only have been told to play one track! (out of " + dmidi.getTrackCount() + " tracks");
					
					System.out.println("End of file. Press enter to exit");
					waitForKeyPress();
					System.exit(0);
				}
			}
			
			//not end of the track
			int currentTickNote;
			currentTimeMS += deltaTime / 1000000.0;
			long ticks = currentMsToTick();
			
			//make sure it not play same note more than once, the loop is running really fast
			if (ticks == lastPlayAtTick)
				continue;
			
			//there is some note to play here
			if (notes.get(ticks) != null)
				currentTickNote = notes.get(ticks);
			else // no note to play
				continue;
			
			//queue the note for the instrument to play
	        int octave = (currentTickNote / 12)-5;
	        int note = currentTickNote % 12;
			System.out.println("@" + ticks + "(" + currentTimeMS + ") " + NOTE_NAMES[note] + " octave " + octave);
			instrument.queueNote(currentTickNote, ticks);
			lastPlayAtTick = ticks;
		}
	}

	private void waitForChangeWindowFocus() {
		waitForMilSecond(3000);
	}

	private boolean promptForNextTrack() {
		//wait for enter key pressed, play metronome sound, and wait 1 second
		System.out.println("Press Enter key to play the next track or enter 'r' to repeat this track");
		String input = betweenTrackWait();
		
		//repeat the same track
		if (input.equals("r") || input.equals("R")) {
			return true;
		}
		
		return false;
	}

	private void playCustomEmote() {
		/*waitForMilSecond(2000);
		instrument.queueNote(999);
		waitForMilSecond(5000);*/
	}
	
	private String betweenTrackWait() {
		String input = waitForKeyPress();
		waitForChangeWindowFocus();
		playVideoStartGuideSound();
		waitForMilSecond(1000);
		return input;
	}
	
	private void waitForMilSecond(long milSecond) {
		try {
			Thread.sleep(milSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void playVideoStartGuideSound() {
		metronomeClip.setFramePosition(0);
        metronomeClip.start();
		waitForMilSecond(metronomeClip.getMicrosecondLength() / 1000);
	}
	
	private String waitForKeyPress() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		
		try {
			input = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return input;
	}
	
	public long currentMsToTick() {
		return Math.round((currentTimeMS * 1000) / ((float)dmidi.getTempo() / dmidi.getResolution()));
	}
}
