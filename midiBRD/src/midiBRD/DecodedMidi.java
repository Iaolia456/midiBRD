package midiBRD;

import java.util.HashMap;
import java.util.List;

public class DecodedMidi {
	private List<HashMap<Long, Integer>> notes;
	private long duration;
	private int resolution;
	private int tempo;
	
	public DecodedMidi(List<HashMap<Long, Integer>> notes, long duration, int resolution, int tempo) {
		this.notes = notes;
		this.duration = duration;
		this.resolution = resolution;
		this.tempo = tempo;
	}

	public long getDuration() {
		return duration;
	}
	
	public HashMap<Long, Integer> getFirstTrackNotes() {
		return notes.get(0);
	}
	
	public HashMap<Long, Integer> getNotesAtTrack(int trackNo) {
		return notes.get(trackNo);
	}
	
	public int getResolution() {
		return resolution;
	}
	
	public int getTempo() {
		return tempo;
	}
	
	public int getTrackCount() {
		return notes.size();
	}
}
