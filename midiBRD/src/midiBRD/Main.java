package midiBRD;

public class Main {
	public static void main(String[] args) {
		String filename = "timing-test.mid";
		MidiReader reader = new MidiReader("D:\\Eclipse-workspace\\midiBRD\\midi\\" + filename);
		DecodedMidi dmidi = reader.decode();
		Instrument ins = new Instrument();
		
		Runnable r = new Musician(ins, dmidi);
		new Thread(r).start();
	}
}
