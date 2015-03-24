package main;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluOrtho2D;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL12;

import static main.Main.*;

/**
 * Level Darstellung
 * 
 * @author Colin Dömer
 */
public class Game extends Screen {

	public Level level;
	public Fox fox;
	public float x, y;
	public float width, height;

	/**
	 * OpenGL Einstellungen vornehmen und Level laden
	 * 
	 * @author Colin Dömer
	 */
	public Game() {

		resize();

		level = new Level1();
		fox = new Fox();

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
	}

	/**
	 * Updatet das Level.
	 */
	public void update() {

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			fox.y -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			fox.x -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			fox.y += 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			fox.x += 0.1f;
		}
		x = fox.x;
		y = fox.y - 2.5f;
		y += 5f * ((float) (Main.height - Mouse.getY()) / Main.height - 0.5f);
		x += 5f * resolution * ((float) Mouse.getX() / Main.width - 0.5f);
		if (x - width / 2 < 0) {
			x = width / 2;
		}
		if (y - height / 2 < 0) {
			y = height / 2;
		}
		if (x + width / 2 > level.width) {
			x = level.width - width / 2;
		}
		if (y + height / 2 > level.height) {
			y = level.height - height / 2;
		}
	}

	/**
	 * Rendert das Level.
	 */
	public void render() {

		glClearColor(0.8f, 0.9f, 1.0f, 1f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(x - width / 2, x + width / 2, y + height / 2, y - height / 2);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		level.render();
		fox.render();

		Display.update();
	}

	/**
	 * Wird aufgerufen, wenn sich die Auflösung ändert.
	 */
	public void resize() {

		height = 10f;
		width = height * resolution;
	}
}
