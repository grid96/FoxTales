package main;

import org.lwjgl.input.Cursor;
import org.newdawn.slick.opengl.CursorLoader;

/**
 * Cursor Verwaltung
 * 
 * @author Colin Dömer
 */
public class Cursors {

	public static Cursor standard;

	/**
	 * Lädt Cursor.
	 */
	public static void load() {

		try {
			standard = CursorLoader.get().getCursor("cursor.png", 1, 1);
		} catch (Exception e) {
		}
	}
}
