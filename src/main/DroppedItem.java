package main;

/**
 * auf dem Boden liegendes Item
 * 
 * @author Colin Dömer
 */
public class DroppedItem extends Entity {

	public Item item;

	/**
	 * erzeugt ein Entity mit übergebener Kollisionsbox für ein Item im Level
	 * 
	 * @param item
	 *            aufnehmbares Item
	 * @param x
	 *            x Koordinate
	 * @param y
	 *            y Koordinate
	 * @param w
	 *            Breite des Items
	 * @param h
	 *            Höhe des Items
	 * @param level
	 *            Level
	 */
	public DroppedItem(Item item, float x, float y, float w, float h, Level level) {

		super(item.texture, 6, x, y, w, h, level);
		this.item = item;
		alphaMap = null;
		take = item.take;
		look = item.look;
		talk = item.talk;
	}

	public void take() {

		super.take();
		Game.ths.inventory.add(item);
		level.entities.remove(Game.ths.mouseOver);
	}

	public void give(Item item) {

		take();
	}

	public boolean isMouseOver() {

		if (level.getNextX(Game.ths.getMouseX()) > level.getNextX(x) && level.getNextX(Game.ths.getMouseX()) < level.getNextX(x) + w && Game.ths.getMouseY() > y && Game.ths.getMouseY() < y + h) {
			if (alphaMap == null || alphaMap.hit((level.getNextX(Game.ths.getMouseX()) - (level.getNextX(x) - w / 2)) / w, (Game.ths.getMouseY() - (y - h)) / h)) {
				Game.ths.mouseOver = this;
				return true;
			}
		}
		return false;
	}

	public void render() {

		if (Main.debug) {
			Textures.renderColoredQuad(x, y, w, h, 1, 0, 0, 0.25f);
		}
	}
}
