package main;

import org.lwjgl.input.Cursor;
import org.newdawn.slick.opengl.CursorLoader;

/**
 * Cursor Verwaltung
 * 
 * @author Colin D�mer
 */
public class Cursors {

	public static Cursor standard;

	/**
	 * L�dt Cursor.
	 */
	public static void load() {

		try {
			standard = CursorLoader.get().getCursor("cursor.png", 1, 1);
		} catch (Exception e) {
		}
	}
}
