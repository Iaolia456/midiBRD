package midiBRD;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Musician extends TimerTask {
	public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	
	private DecodedMidi dmidi;
	private Map<Long, Integer> notes;
	private long duration;
	private long lastPlayAtTick = -1;
	
	private long currentTimeMS;
	
	private Instrument instrument;
	
	public Musician(Instrument instrument, DecodedMidi dmidi) {
		this.instrument = instrument;
		this.dmidi = dmidi;
		notes = dmidi.getNotes();
		duration = dmidi.getDuration();
		
		start();
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
		Timer music = new Timer();
		
		music.schedule(this, 0, 1);
	}
	
	@Override
	public void run() {
		if (currentTimeMS > duration) {
			cancel();
			System.out.println("End of track");
		}
		
		int currentTickNote;
		long ticks = currentMsToTick();
		currentTimeMS++;
		
		if (ticks == lastPlayAtTick)
			return;
		
		if (notes.get(ticks) != null)
			currentTickNote = notes.get(ticks);
		else
			return;
		
        int octave = (currentTickNote / 12)-5;
        int note = currentTickNote % 12;
		System.out.print("@" + ticks + " " + NOTE_NAMES[note] + " octave " + octave);
		instrument.queueNote(currentTickNote);
		lastPlayAtTick = ticks;
	}
	
	public long currentMsToTick() {
		return Math.round((float)(currentTimeMS * 1000) / ((float)dmidi.getTempo() / dmidi.getResolution()));
	}
}
