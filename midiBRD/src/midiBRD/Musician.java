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
		System.out.println("Press enter to start playback");
		betweenTrackWait();
		System.out.println("Start playing track 0");
		
		//reset the timing and get the first note track
		long lastLoopTime = System.nanoTime();
		int currentTrack = 0;
		Map<Long, Integer> notes = dmidi.getNotesAtTrack(0);
		
		//player loop
		while (true) {
			//calculate delta time
			long now = System.nanoTime();
			long deltaTime = now - lastLoopTime;
			lastLoopTime = now;
			
			//end of the track
			if (currentTimeMS > duration) {
				System.out.println("End of track " + currentTrack);
				
				if (isPlayAllTrack) {
					if (currentTrack < dmidi.getTrackCount() - 1) {
						//get the next track
						currentTrack++;
						notes = dmidi.getNotesAtTrack(currentTrack);
						
						//wait for enter key pressed, play metronome sound, and wait 1 second
						System.out.println("Press Enter key to play the next track...");
						betweenTrackWait();
						
						//reset timing clock and start playing the next track
						System.out.println("Start playing track " + currentTrack);
						currentTimeMS = 0;
						lastPlayAtTick = -1;
						lastLoopTime = System.nanoTime();
						continue;
					}
					else {
						//all track played
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
			instrument.queueNote(currentTickNote);
			lastPlayAtTick = ticks;
		}
	}

	private void betweenTrackWait() {
		waitForKeyPress();
		waitForMilSecond(3000);
		playVideoStartGuideSound();
		waitForMilSecond(1000);
	}
	
	private void waitForMilSecond(long milSecond) {
		try {
			Thread.sleep(milSecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void playVideoStartGuideSound() {
		metronomeClip.setFramePosition(0);
        metronomeClip.start();
		waitForMilSecond(metronomeClip.getMicrosecondLength() / 1000);
	}
	
	private void waitForKeyPress() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public long currentMsToTick() {
		return Math.round((currentTimeMS * 1000) / ((float)dmidi.getTempo() / dmidi.getResolution()));
	}
}
