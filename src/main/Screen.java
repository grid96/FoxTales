package main;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluOrtho2D;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL12;

/**
 * Anzeige
 * 
 * @author Colin Dömer
 */
public abstract class Screen {

	public Screen() {
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
	}
	
	public void update() {
		
	}

	public void render() {
		
	}
	
	public void prerender() {
		
		glClearColor(0.8f, 0.9f, 1.0f, 1f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glColor4f(1, 1, 1, 1);
	}
	
	public void postrender() {
		
		Display.update();
	}
	
	/**
	 * Setzt die Kamera.
	 * 
	 * @param x0
	 *            links
	 * @param y0
	 *            oben
	 * @param x1
	 *            rechts
	 * @param y1
	 *            unten
	 */
	public void setOrtho2D(float x0, float y0, float x1, float y1) {

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(x0, x1, y1, y0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
}
