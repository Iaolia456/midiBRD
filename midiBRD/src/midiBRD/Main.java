package midiBRD;

public class Main {
	public static void main(String[] args) {
		String filename = "365-nichi-no-kami-hikougi.mid";
		MidiReader reader = new MidiReader("D:\\Eclipse-workspace\\midiBRD\\midi\\" + filename);
		DecodedMidi dmidi = reader.decode();
		
		Instrument ins = new Instrument();
		Musician m = new Musician(ins, dmidi);
		m.start();
	}
}
