package main;

public class DroppedItem extends Entity {

	public Item item;

	public DroppedItem(Item item, float x, float y, float w, float h, Level level) {

		super(item.texture, 6, x, y, w, h, level);
		this.item = item;
		alphaMap = null;
		look = item.look;
		take = item.take;
		talk = item.talk;
	}

	public void click() {

		Game.ths.inventory.add(((DroppedItem) Game.ths.mouseOver).item);
		level.entities.remove(Game.ths.mouseOver);
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
				mouseOver();
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
