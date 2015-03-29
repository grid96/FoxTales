package main;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

/**
 * Basic Level
 * 
 * @author Colin Dömer
 */
public abstract class Level {

	public float width, height;
	public Texture texture;

	public void update() {

	}

	public void render(float x0, float y0, float x1, float y1) {

		glEnable(GL_TEXTURE_2D);
		Textures.renderMapped(texture, width, height, x0, y0, x1, y1);
	}

	/**
	 * Überprüft ob es eine Kollision zwischen dem Level und dem übergebenen Rechteck gibt.
	 * 
	 * @param x0
	 *            links
	 * @param y0
	 *            oben
	 * @param x1
	 *            rechts
	 * @param y1
	 *            unten
	 * @return Kollision
	 */
	public boolean collision(float x, float y, float w, float h) {

		if (y + h >= 0.9f * height) {
			return true;
		}
		return false;
	}
}
