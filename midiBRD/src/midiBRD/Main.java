package midiBRD;

public class Main {
	public static void main(String[] args) {
		String filename = "multitrack-test.mid";
		MidiReader reader = new MidiReader("D://Leo Aiolia//Project//Programming//Java//NORMAL//midiBRD//midi//" + filename);
		DecodedMidi dmidi = reader.decode();
		
		Instrument ins = new Instrument();
		Musician m = new Musician(ins, dmidi);
		m.start(true);
	}
}
