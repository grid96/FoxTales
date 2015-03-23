package main;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

/**
 * Level Darstellung
 * 
 * @author Colin Dömer
 */
public class Game extends Screen {

	/**
	 * OpenGL Einstellungen vornehmen und Level laden
	 * 
	 * @author Colin Dömer
	 */
	public Game() {

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	/**
	 * Rendert das Level.
	 */
	public void render() {

		glClearColor(0.8f, 0.9f, 1.0f, 1f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		Display.update();
	}
}
