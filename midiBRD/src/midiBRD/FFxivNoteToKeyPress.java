package midiBRD;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FFxivNoteToKeyPress {
	public static Map<Integer, List<Integer>> noteToKeyboardMap;
	static {
		noteToKeyboardMap = new HashMap<>();
		noteToKeyboardMap.put(0, Arrays.asList(KeyEvent.VK_1));
		noteToKeyboardMap.put(1, Arrays.asList(KeyEvent.VK_2));
		noteToKeyboardMap.put(2, Arrays.asList(KeyEvent.VK_3));
		noteToKeyboardMap.put(3, Arrays.asList(KeyEvent.VK_4));
		noteToKeyboardMap.put(4, Arrays.asList(KeyEvent.VK_5));
		noteToKeyboardMap.put(5, Arrays.asList(KeyEvent.VK_6));
		noteToKeyboardMap.put(6, Arrays.asList(KeyEvent.VK_7));
		noteToKeyboardMap.put(7, Arrays.asList(KeyEvent.VK_8));
		noteToKeyboardMap.put(8, Arrays.asList(KeyEvent.VK_9));
		noteToKeyboardMap.put(9, Arrays.asList(KeyEvent.VK_0));
		noteToKeyboardMap.put(10, Arrays.asList(KeyEvent.VK_MINUS));
		noteToKeyboardMap.put(11, Arrays.asList(KeyEvent.VK_EQUALS));
		
		noteToKeyboardMap.put(12, Arrays.asList(KeyEvent.VK_SHIFT, KeyEvent.VK_1));
		noteToKeyboardMap.put(13, Arrays.asList(KeyEvent.VK_SHIFT, KeyEvent.VK_2));
		noteToKeyboardMap.put(14, Arrays.asList(KeyEvent.VK_SHIFT, KeyEvent.VK_3));
		noteToKeyboardMap.put(15, Arrays.asList(KeyEvent.VK_SHIFT, KeyEvent.VK_4));
		noteToKeyboardMap.put(16, Arrays.asList(KeyEvent.VK_SHIFT, KeyEvent.VK_5));
		noteToKeyboardMap.put(17, Arrays.asList(KeyEvent.VK_SHIFT, KeyEvent.VK_6));
		noteToKeyboardMap.put(18, Arrays.asList(KeyEvent.VK_SHIFT, KeyEvent.VK_7));
		noteToKeyboardMap.put(19, Arrays.asList(KeyEvent.VK_SHIFT, KeyEvent.VK_8));
		noteToKeyboardMap.put(20, Arrays.asList(KeyEvent.VK_SHIFT, KeyEvent.VK_9));
		noteToKeyboardMap.put(21, Arrays.asList(KeyEvent.VK_SHIFT, KeyEvent.VK_0));
		noteToKeyboardMap.put(22, Arrays.asList(KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS));
		noteToKeyboardMap.put(23, Arrays.asList(KeyEvent.VK_SHIFT, KeyEvent.VK_EQUALS));
		
		noteToKeyboardMap.put(24, Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_1));
		noteToKeyboardMap.put(25, Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_2));
		noteToKeyboardMap.put(26, Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_3));
		noteToKeyboardMap.put(27, Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_4));
		noteToKeyboardMap.put(28, Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_5));
		noteToKeyboardMap.put(29, Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_6));
		noteToKeyboardMap.put(30, Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_7));
		noteToKeyboardMap.put(31, Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_8));
		noteToKeyboardMap.put(32, Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_9));
		noteToKeyboardMap.put(33, Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_0));
		noteToKeyboardMap.put(34, Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_MINUS));
		noteToKeyboardMap.put(35, Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_EQUALS));
		
		noteToKeyboardMap.put(36, Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_1));
		noteToKeyboardMap.put(37, Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_2));
		noteToKeyboardMap.put(38, Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_3));
		noteToKeyboardMap.put(39, Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_4));
		noteToKeyboardMap.put(40, Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_5));
		noteToKeyboardMap.put(41, Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_6));
		noteToKeyboardMap.put(42, Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_7));
		noteToKeyboardMap.put(43, Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_8));
		noteToKeyboardMap.put(44, Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_9));
		noteToKeyboardMap.put(45, Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_0));
		noteToKeyboardMap.put(46, Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_MINUS));
		noteToKeyboardMap.put(47, Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_EQUALS));
	}
}
