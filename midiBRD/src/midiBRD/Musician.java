package midiBRD;

import java.util.Map;

public class Musician {
	public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	
	private DecodedMidi dmidi;
	private Map<Long, Integer> notes;
	private long duration;
	private long lastPlayAtTick = -1;
	
	private double currentTimeMS = 0;
	
	private Instrument instrument;
	
	public Musician(Instrument instrument, DecodedMidi dmidi) {
		this.instrument = instrument;
		this.dmidi = dmidi;
		notes = dmidi.getNotes();
		duration = dmidi.getDuration();
	}

	public void start() {
		System.out.println("Starting playback in 3 seconds");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Starting...");
		long lastLoopTime = System.nanoTime();
		
		while (true) {
			long now = System.nanoTime();
			long deltaTime = now - lastLoopTime;
			lastLoopTime = now;
			
			if (currentTimeMS > duration) {
				System.out.println("End of track");
				break;
			}
			
			int currentTickNote;
			currentTimeMS += deltaTime / 1000000.0;
			//System.out.println(currentTimeMS);
			long ticks = currentMsToTick();
			
			if (ticks == lastPlayAtTick)
				continue;
			
			if (notes.get(ticks) != null)
				currentTickNote = notes.get(ticks);
			else
				continue;
			
	        int octave = (currentTickNote / 12)-5;
	        int note = currentTickNote % 12;
			System.out.println("@" + ticks + "(" + currentTimeMS + ") " + NOTE_NAMES[note] + " octave " + octave);
			instrument.queueNote(currentTickNote);
			lastPlayAtTick = ticks;
		}
	}
	
	public long currentMsToTick() {
		return Math.round((currentTimeMS * 1000) / ((float)dmidi.getTempo() / dmidi.getResolution()));
	}
}
