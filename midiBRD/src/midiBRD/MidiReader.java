package midiBRD;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MidiReader {
	public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    private static final int SET_TEMPO = 0x51;
    
	
	private Sequence midi;

	public MidiReader(String midiFile) {
		try {
			midi = MidiSystem.getSequence(new File(midiFile));
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	
	public DecodedMidi decode() {
		List<HashMap<Long, Integer>> noteTracks = new ArrayList<HashMap<Long, Integer>>();

		int bpm;
		int tempo = 0;
		
		for (Track track : midi.getTracks()) {
			HashMap<Long,Integer> notes = new HashMap<>();
			for (int i=0; i<track.size(); i++) {
				MidiEvent event = track.get(i);
                MidiMessage message = event.getMessage();
                
                if (message instanceof MetaMessage) {
                    MetaMessage mm = (MetaMessage) message;
                    if(mm.getType()== SET_TEMPO){
                        // now what?
                        mm.getData();
                        byte[] data = mm.getData();
                        tempo = (data[0] & 0xff) << 16 | (data[1] & 0xff) << 8 | (data[2] & 0xff);
                        bpm = 60000000 / tempo;
                        
                        System.out.println(bpm + " BPM (tempo " + tempo + " microsec per beat, " + midi.getResolution() + " ticks per beat)");
                    }
                }
                
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    
                    //a note is detected
                    //we only care about "note on" event
                    if (sm.getCommand() == NOTE_ON) {
	                   int key = sm.getData1();
	                   long tick = event.getTick();
	                   int velocity = sm.getData2();
	                   
	                   System.out.print("@" + event.getTick() + " ");
	                   System.out.print("Channel: " + sm.getChannel() + " ");
	                   System.out.println("Note on, " + " key=" + key + " velocity: " + velocity);
	                    
	                   notes.put(tick, key);
                    }
                }
			}
			
			if (notes.size() != 0)
				noteTracks.add(notes);
		}
		
		System.out.println("found " + noteTracks.size() + " tracks");
		long duration = (long)Math.ceil(((midi.getTickLength() / 96.0) * tempo) / 1000.0);
		return new DecodedMidi(noteTracks, duration, midi.getResolution(), tempo);
	}
}
