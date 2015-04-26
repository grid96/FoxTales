package main;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import static main.Main.*;

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

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		Game.ths.mouseOver = null;
		for (int i = entities.size() - 1; i >= 0 && !entities.get(i).isMouseOver(); i--) {
			
		}
	}
	
	public boolean click() {
		
		if (Game.ths.mouseOver != null) {
			if (Game.ths.mouseOver instanceof DroppedItem) {
				Game.ths.inventory.add(((DroppedItem) Game.ths.mouseOver).item);
				entities.remove(Game.ths.mouseOver);
			}
			return true;
		}
		return false;
	}

	public void render(float x0, float y0, float x1, float y1) {

		Textures.renderMapped(texture, width, height, x0, y0, x1, y1);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render();
		}
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

		if (y + h >= 0.925f * height) {
			return true;
		}
		return false;
	}
	
	public float getLevelX(float x) {
		
		if (x < 0) {
			return x + width * (int) (-x / width + 1);
		} else {
			return x % width;
		}
	}
	
	public float getNextX(float x) {
		
		float x1 = width * getIteration(Game.ths.x) + x;
		float x2;
		if (x1 < Game.ths.x) {
			x2 = x1 + width;
		} else {
			x2 = x1 - width;
		}
		if (abs(x1 - Game.ths.x) < abs(x2 - Game.ths.x)) {
			return x1;
		} else {
			return x2;
		}
	}
	
	public int getIteration(float x) {
		
		if (x < 0) {
			return (int) (x / width - 1);
		} else {
			return (int) (x / width);
		}
	}
	
	public boolean isVisible(Entity entity) {
		
		return (getNextX(entity.x) + entity.w / 2 > Game.ths.x0 && getNextX(entity.x) - entity.w / 2 < Game.ths.x1 && entity.y + entity.h / 2 > Game.ths.y0 && entity.y - entity.h / 2 < Game.ths.y1);
	}
}
