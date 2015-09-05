package main;

import org.newdawn.slick.opengl.Texture;

/**
 * Entity im Level
 * 
 * @author Colin Dömer
 */
public abstract class Entity {

	public Level level;
	public Texture texture;
	public AlphaMap alphaMap;
	public float x, y;
	public float w, h;
	public int id;
	public String take, look, talk;

	public Entity(Texture texture, int id, float x, float y, float w, float h, Level level) {

		this.texture = texture;
		this.alphaMap = AlphaMap.getAlphaMap(texture);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.level = level;
	}

	/**
	 * update für Animationen
	 */
	public void update() {

	}

	/**
	 * renders das Entity
	 */
	public void render() {

		if (level.isVisible(this)) {
			Textures.renderQuad(texture, level.getNextX(x) - w / 2, y - h, w, h);
		}
	}

	/**
	 * gibt dem Entity ein Item
	 * 
	 * @param item
	 *            übergebenes Item
	 */
	public void give(Item item) {

	}

	public int getID() {

		return id;
	}

	/**
	 * überprüft ob sich die Maus über dem Item befindet
	 * 
	 * @return Kollision
	 */
	public boolean isMouseOver() {

		if (level.getNextX(Game.ths.getMouseX()) > level.getNextX(x) - w / 2 && level.getNextX(Game.ths.getMouseX()) < level.getNextX(x) + w / 2 && Game.ths.getMouseY() > y - h && Game.ths.getMouseY() < y) {
			if (alphaMap.hit((level.getNextX(Game.ths.getMouseX()) - (level.getNextX(x) - w / 2)) / w, (Game.ths.getMouseY() - (y - h)) / h)) {
				Game.ths.mouseOver = this;
				return true;
			}
		}
		return false;
	}

	/**
	 * nimmt das Entity auf
	 */
	public void take() {

		Game.ths.setText(take);
	}

	/**
	 * sieht sich das Entity an
	 */
	public void look() {

		Game.ths.setText(look);
	}

	/**
	 * redet mit dem Entity
	 */
	public void talk() {

		Game.ths.setText(talk);
	}
}
