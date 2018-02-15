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
	
	//one action use 3x DELAY_BETWEEN_KEY_ACTION and 1x KEY_HOLD_DELAY
	private final int DELAY_BETWEEN_KEY_ACTION = 10;
	private final int KEY_HOLD_DELAY = 15;
	
	private Queue<Integer> noteQueue = new LinkedList<>();
	private Queue<Long> debugNoteTick = new LinkedList<>();
	
	private Robot player;
	private int lastKeyModifierPress = -999;
	
	public Instrument() {
		try {
			player = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(this, 0, 1);
	}

	public void queueNote(int noteToBePlayed, long tick) {
		noteQueue.add(noteToBePlayed);
		debugNoteTick.add(tick);
	}
	
	@Override
	public void run() {
		if (noteQueue.isEmpty())
			return;
				
		int noteInQueue = noteQueue.remove();
		long tick = debugNoteTick.remove();
		int note = 0;
		
		if (noteInQueue == 999) {
			note = 999;
		}
		else {
			note = noteInQueue - 48 + (octiveModifier * 12);
		}
		
		List<Integer> keyToPress = FFxivNoteToKeyPress.noteToKeyboardMap.get(note);
		
		//this modifier key is differ from the last one
		if ((keyToPress.size() > 1 && lastKeyModifierPress != keyToPress.get(0))) {
			if (lastKeyModifierPress != -999)
				stopHoldKey(lastKeyModifierPress);
			else
				//make sure every condition has same delay
				player.delay(DELAY_BETWEEN_KEY_ACTION);
		
			lastKeyModifierPress = keyToPress.get(0);
			
			startHoldKey(keyToPress.get(0));
			
			//press the note key
			pressKey(keyToPress.get(1), tick);
		}
		//only one key to press
		else if (keyToPress.size() == 1) {
			if (lastKeyModifierPress != -999)
				stopHoldKey(lastKeyModifierPress);
			else
				//make sure every condition has same delay
				player.delay(DELAY_BETWEEN_KEY_ACTION);
			
			//make sure every condition has same delay
			player.delay(DELAY_BETWEEN_KEY_ACTION);
			
			pressKey(keyToPress.get(0), tick);
			
			lastKeyModifierPress = -999;
		}
		//same key modifier
		else {
			//make sure we every condition has same delay
			if (lastKeyModifierPress != -999)
				player.delay(DELAY_BETWEEN_KEY_ACTION * 2);
			
			pressKey(keyToPress.get(1), tick);
		}
	}
	
	private void pressKey(int keyCode, long tick) {
		System.out.println(" pressed " + tick);
		player.keyPress(keyCode);
		player.delay(KEY_HOLD_DELAY);
		player.keyRelease(keyCode);
		player.delay(DELAY_BETWEEN_KEY_ACTION);
	}
	
	private void startHoldKey(int keyCode) {
		//System.out.println("start " + lastKeyModifierPress);
		player.keyPress(keyCode);
		player.delay(DELAY_BETWEEN_KEY_ACTION);
	}
	
	private void stopHoldKey(int keyCode) {
		//System.out.println("stop " + lastKeyModifierPress);
		player.keyRelease(keyCode);
		player.delay(DELAY_BETWEEN_KEY_ACTION);
	}
}


