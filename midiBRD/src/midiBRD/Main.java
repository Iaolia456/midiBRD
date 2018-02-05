package midiBRD;

public class Main {
	public static void main(String[] args) {
		String filename = "";
		MidiReader reader = new MidiReader("" + filename);
		DecodedMidi dmidi = reader.decode();
		
		Instrument ins = new Instrument();
		Musician m = new Musician(ins, dmidi);
		m.start(true);
	}
}
