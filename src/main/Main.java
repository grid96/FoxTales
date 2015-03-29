package main;

import java.time.LocalDateTime;

/**
 * Hauptklasse
 * 
 * @author Colin Dömer
 */
public class Main {

	public static boolean fullscreen = false, vsync = false, antialiasing = true, shortcut = true, mute = true;
	public static boolean hd = false, compressedSounds = false, compressedTextures = false;
	public static int width = 1280, height = 720;
	public static float resolution = (float) width / height;
	public static int frames, updates;
	public static int fps, ups;
	public static FoxTales game;
	public static String version = "1.0.0";

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

	public static void setScreen(Screen screen) {

		game.setScreen(screen);
	}

	public static void destroy() {

		game.destroy();
	}

	public static float random() {

		return (float) Math.random();
	}

	public static float random(float min, float max) {

		return (float) ((max - min) * Math.random()) + min;
	}

	public static int randomInt(int i) {

		return (int) (i * Math.random());
	}

	public static float sin(float r) {

		return (float) Math.sin(Math.toRadians(r));
	}

	public static float cos(float r) {

		return (float) Math.cos(Math.toRadians(r));
	}

	public static float sqrt(float a) {

		return (float) Math.sqrt(a);
	}

	public static float atan2(float y, float x) {

		return (float) Math.toDegrees(Math.atan2(y, x));
	}

	public static float abs(float a) {

		return (float) Math.abs(a);
	}

	public static float max(float a, float b) {

		return (float) Math.max(a, b);
	}

	public static float min(float a, float b) {

		return (float) Math.min(a, b);
	}
}
