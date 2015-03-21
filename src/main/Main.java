package main;

import java.time.LocalDateTime;

import org.lwjgl.LWJGLException;

public class Main {

	public static boolean fullscreen = false, vsync = false, antialiasing = true, shortcut = true, mute = true;
	public static int width = 1280, height = 720;
	public static float resolution = (float) width / height;
	public static int frames, updates;
	public static int fps, ups;
	public static Game game;
	public static String version = "1.0.0";
	
	public static void main(String[] args) throws LWJGLException {

		System.out.println("Fox Tales started " + LocalDateTime.now());
		game = new Game();
	}
	
	public static void setScreen(Screen screen) {
		
		game.screen = screen;
	}
	
	public static void destroy() {
		
		game.destroy();
	}
	
	public static float random() {
		
		return (float) Math.random();
	}
	
	public static float random(float f0, float f1) {
		
		return (float) ((f1 - f0) * Math.random()) + f0;
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
