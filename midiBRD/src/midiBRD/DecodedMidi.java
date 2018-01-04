package midiBRD;

import java.util.Map;

public class DecodedMidi {
	private Map<Long, Integer> notes;
	private long duration;
	private int resolution;
	private int tempo;
	
	public DecodedMidi(Map<Long, Integer> notes, long duration, int resolution, int tempo) {
		this.notes = notes;
		this.duration = duration;
		this.resolution = resolution;
		this.tempo = tempo;
	}

	public long getDuration() {
		return duration;
	}
	
	public Map<Long, Integer> getNotes() {
		return notes;
	}
	
	public int getResolution() {
		return resolution;
	}
	
	public int getTempo() {
		return tempo;
	}
}
