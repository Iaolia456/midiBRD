package midiBRD;

public class Main {
	public static void main(String[] args) {
		MidiReader reader = new MidiReader(args[0]);
		DecodedMidi dmidi = reader.decode();
		
		Instrument ins = new Instrument();
		Musician m = new Musician(ins, dmidi);
		m.start(true);
	}
}
