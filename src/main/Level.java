package main;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

/**
 * Basic Level
 * 
 * @author Colin Dömer
 */
public abstract class Level {

	public float width, height;
	public Texture texture;
	
	public ArrayList<Entity> entities = new ArrayList<>();
	public ArrayList<Particle> particles = new ArrayList<>();

	public void update() {

		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
	}

	public void render(float x0, float y0, float x1, float y1) {

		Textures.renderMapped(texture, width, height, x0, y0, x1, y1);
	}
	
	public void renderOverlay(float x0, float y0, float x1, float y1) {
		
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render();
		}
	}

	/**
	 * Überprüft ob es eine Kollision zwischen dem Level und dem übergebenen Rechteck gibt.
	 * 
	 * @param x
	 *            links
	 * @param y
	 *            oben
	 * @param w
	 *            rechts
	 * @param h
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
