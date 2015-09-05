package main;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import static main.Main.*;

/**
 * Level
 * 
 * @author Colin Dömer
 */
public abstract class Level {

	public float width, height;
	public Texture foreground;
	public Texture[] background;

	public ArrayList<Entity> entities = new ArrayList<>();

	/**
	 * Updatet das Level
	 */
	public void update() {

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		Game.ths.mouseOver = null;
		for (int i = entities.size() - 1; i >= 0 && !entities.get(i).isMouseOver(); i--) {

		}
	}

	/**
	 * Rendert den Hintergrund des Level
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
	public void render(float x0, float y0, float x1, float y1) {

		Textures.setColor(1, 1, 1, 1);
		Textures.renderMapped(background[(updates / 20) % background.length], width, height, x0, y0, x1, y1);
		Textures.setColor(1, 1, 1, updates % 20 / 20f);
		Textures.renderMapped(background[(updates / 20 + 1) % background.length], width, height, x0, y0, x1, y1);
		Textures.setColor(1, 1, 1, 1);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render();
		}
	}

	/**
	 * Rendert den Vordergrund des Level
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
	public void renderOverlay(float x0, float y0, float x1, float y1) {

		Textures.renderMapped(foreground, width, height, x0, y0, x1, y1);
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

		return false;
	}

	/**
	 * gibt den gekürzten X Wert im Level zurück
	 * 
	 * @param x
	 * @return gekürzter X Wert
	 */
	public float getLevelX(float x) {

		if (x < 0) {
			return x + width * (int) (-x / width + 1);
		} else {
			return x % width;
		}
	}

	/**
	 * gibt den nächsten existen X Wert zum übergebenen X Wert im Level zurück
	 * 
	 * @param x
	 * @return nachster X Wert
	 */
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

	/**
	 * gibt die Anzahl an gelaufenen Iterationen anhand des übergebenen x zurück
	 * 
	 * @param x
	 * @return Iterationen
	 */
	public int getIteration(float x) {

		if (x < 0) {
			return (int) (x / width - 1);
		} else {
			return (int) (x / width);
		}
	}

	/**
	 * prüft ob sich ein Entity im gerenderten Bereich befindet
	 * 
	 * @param entity
	 * @return sichtbar
	 */
	public boolean isVisible(Entity entity) {

		return (getNextX(entity.x) + entity.w / 2 > Game.ths.x0 && getNextX(entity.x) - entity.w / 2 < Game.ths.x1 && entity.y + entity.h / 2 > Game.ths.y0 && entity.y - entity.h / 2 < Game.ths.y1);
	}
}
