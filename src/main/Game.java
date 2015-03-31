package main;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluOrtho2D;

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
	public float x0, y0, x1, y1;
	
	public Text test = new Text(Fonts.arial12, "test");

	/**
	 * OpenGL Einstellungen vornehmen und Level laden
	 * 
	 * @author Colin Dömer
	 */
	public Game() {

		resize();

		level = new Level1();
		fox = new Fox(level);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
	}

	/**
	 * Updatet das Level.
	 */
	public void update() {

		fox.update();
		x = fox.x;
		y = fox.y - 2.5f;
		y += 5f * ((float) (Main.height - Mouse.getY()) / Main.height - 0.5f);
		x += 5f * resolution * ((float) Mouse.getX() / Main.width - 0.5f);
		// if (x - width / 2 < 0) {
		// x = width / 2;
		// }
		if (y - height / 2 < 0) {
			y = height / 2;
		}
		// if (x + width / 2 > level.width) {
		// x = level.width - width / 2;
		// }
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

		x0 = x - width / 2;
		x1 = x + width / 2;
		y0 = y - height / 2;
		y1 = y + height / 2;
		setOrtho2D(x0, y0, x1, y1);
		
		test.render(0, 0);

		level.render(x0, y0, x1, y1);
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
	
	/**
	 * Setzt die Kamera.
	 * 
	 * @param x0 links
	 * @param y0 oben
	 * @param x1 rechts
	 * @param y1 unten
	 */
	public void setOrtho2D(float x0, float y0, float x1, float y1) {
	
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(x0, x1, y1, y0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
}
