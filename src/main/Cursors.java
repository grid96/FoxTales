package main;

import org.lwjgl.input.Cursor;
import org.newdawn.slick.opengl.CursorLoader;

/**
 * Cursor Verwaltung
 * 
 * @author Colin D�mer
 */
public class Cursors {

	public static Cursor standard, take, talk, look;

	/**
	 * L�dt Cursor.
	 */
	public static void load() {

		try {
			standard = CursorLoader.get().getCursor("cursor.png", 1, 1);
			take = CursorLoader.get().getCursor("cursorTake.png", 1, 1);
			talk = CursorLoader.get().getCursor("cursorTalk.png", 1, 1);
			look = CursorLoader.get().getCursor("cursorLook.png", 1, 1);
		} catch (Exception e) {
		}
	}
}
