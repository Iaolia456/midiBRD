package midiBRD;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class Instrument extends TimerTask {
	public static int octiveModifier = 0;
	
	private Queue<Integer> noteQueue = new LinkedList<>();
	
	Robot player;
	
	public Instrument() {
		try {
			player = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(this, 0, 1);;
	}

	public void queueNote(int noteToBePlayed) {
		noteQueue.add(noteToBePlayed);
	}
	
	@Override
	public void run() {
		if (noteQueue.isEmpty())
			return;
				
		int note = noteQueue.remove() - 48 + (octiveModifier * 12);
		List<Integer> keyToPress = FFxivNoteToKeyPress.noteToKeyboardMap.get(note);
		
		for (int key : keyToPress) {
			//System.out.println("press " + key);
			player.keyPress(key);
			player.delay(8);
		}
		
		for (int i=keyToPress.size() - 1; i>=0; i--) {
			//System.out.println("release " + keyToPress.get(i));
			player.keyRelease(keyToPress.get(i));
			player.delay(8);
		}
	}
}


