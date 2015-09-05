package main;

import java.time.LocalDateTime;

/**
 * Hauptklasse
 * 
 * @author Colin Dömer
 */
public class Main {

	public static boolean fullscreen = true, vsync = true, shortcut = false, mute = false, debug = false, tutorial = true, fpsCounter = false;
	public static boolean compressedSounds = false, compressedTextures = false;
	public static int width = 1280, height = 720;
	public static float resolution = (float) width / height;
	public static int frames, updates;
	public static int fps, ups;
	public static FoxTales game;

	/**
	 * Hauptmethode
	 * 
	 * @param args
	 *            irrelevant
	 */
	public static void main(String[] args) {

		System.out.println("Inari started " + LocalDateTime.now());
		game = new FoxTales();
	}

	/**
	 * ändert den angezeigten Screen
	 * 
	 * @param screen
	 *            Screen
	 */
	public static void setScreen(Screen screen) {

		game.setScreen(screen);
	}

	/**
	 * beendet das Spiel
	 */
	public static void destroy() {

		System.out.println("Inari stopped " + LocalDateTime.now());
		game.destroy();
	}

	/**
	 * gibt einen zufälligen Float wieder
	 * 
	 * @return Zufallszahl
	 */
	public static float random() {

		return (float) Math.random();
	}

	/**
	 * gibt einen zufälligen Integer zwischen dem Minumum und Maximum wieder
	 * 
	 * @param min
	 *            Minimum
	 * @param max
	 *            Maximum
	 * @return Zufallszahl
	 */
	public static float random(float min, float max) {

		return (float) ((max - min) * Math.random()) + min;
	}

	/**
	 * gibt einen zufälligen Integer zwischen 0 und i - 1 wieder
	 * 
	 * @param i
	 *            Limit
	 * @return Zufallszahl
	 */
	public static int randomInt(int i) {

		return (int) (i * Math.random());
	}

	/**
	 * Sinus von r als Float
	 * 
	 * @param r
	 *            Winkel in Grad
	 * @return Sinus
	 */
	public static float sin(float r) {

		return (float) Math.sin(Math.toRadians(r));
	}

	/**
	 * Cosinus von r als Float
	 * 
	 * @param r
	 *            Winkel in Grad
	 * @return Cosinus
	 */
	public static float cos(float r) {

		return (float) Math.cos(Math.toRadians(r));
	}

	/**
	 * Quadratwurzel von a als Float
	 * 
	 * @param a
	 * @return Wurzel
	 */
	public static float sqrt(float a) {

		return (float) Math.sqrt(a);
	}

	/**
	 * Arcus Tangens von x und y in Grad und als Float
	 * 
	 * @param y
	 * @param x
	 * @return Arcus Tangens
	 */
	public static float atan2(float y, float x) {

		return (float) Math.toDegrees(Math.atan2(y, x));
	}

	/**
	 * Betrag von a als Float
	 * 
	 * @param a
	 * @return Betrag
	 */
	public static float abs(float a) {

		return (float) Math.abs(a);
	}

	/**
	 * Rückgabe des Maximums von a und b als Float
	 * 
	 * @param a
	 * @param b
	 * @return Maximum
	 */
	public static float max(float a, float b) {

		return (float) Math.max(a, b);
	}

	/**
	 * Rückgabe des Minimums von a und b als Float
	 * 
	 * @param a
	 * @param b
	 * @return Minimum
	 */
	public static float min(float a, float b) {

		return (float) Math.min(a, b);
	}
}
