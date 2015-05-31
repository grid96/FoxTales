package main;

import static main.Main.*;

import org.lwjgl.input.Mouse;

public class Inventory {

	public ItemContainer container;
	public int selected = -1;
	public int mouseover = -1;
	public boolean dragged;
	public int startX, startY;

	public Inventory() {

		container = new ItemContainer();
	}

	public void update() {

		mouseover = -1;
		for (int i = 0; i < container.size(); i++) {
			if (Mouse.getX() > width / 2 - 50 * container.size() + 100 * i + 10 && height - Mouse.getY() > height - 120 && Mouse.getX() < width / 2 - 50 * container.size() + 100 * i + 90 && height - Mouse.getY() < height - 40) {
				mouseover = i;
			}
		}
		// while (Mouse.next()) {
		// if (Mouse.getEventButtonState()) {
		// if (Mouse.getEventButton() == 0) {
		// selected = mouseover;
		// }
		// if (Mouse.getEventButton() == 1) {
		// selected = -1;
		// }
		// }
		// }
	}

	public void render() {

		for (int i = 0; i < container.size(); i++) {
			if (i == selected) {
				if (dragged) {
					Textures.setColor(1, 1, 1, 0.8f);
					Textures.renderQuad(Textures.itemSlot, width / 2 - 50 * container.size() + 100 * i + 10, height - 120, 80, 80);
				} else {
					Textures.setColor(0.8f, 0.8f, 0.8f, 0.8f);
					Textures.renderQuad(Textures.itemSlot, width / 2 - 50 * container.size() + 100 * i + 10, height - 120, 80, 80);
				}
			} else {
				Textures.setColor(1, 1, 1, 0.8f);
				Textures.renderQuad(Textures.itemSlot, width / 2 - 50 * container.size() + 100 * i + 10, height - 120, 80, 80);
			}
			if (container.get(i) != null) {
				if (i != selected || !dragged) {
					Textures.setColor(1, 1, 1, 1);
					Textures.renderQuad(container.get(i).texture, width / 2 - 50 * container.size() + 100 * i + 18, height - 112, 64, 64);
				}
			}
		}
		if (dragged) {
			Textures.setColor(1, 1, 1, 1);
			Textures.renderQuad(container.get(selected).texture, width / 2 - 50 * container.size() + 100 * selected + 18 + Mouse.getX() - startX, height - 112 - Mouse.getY() + startY, 64, 64);
		}
	}

	public void add(Item item) {

		container.add(item);
	}

	public boolean click() {

		if (Mouse.getEventButtonState()) {
			if (Mouse.getEventButton() == 0) {
				if (mouseover != -1) {
					selected = mouseover;
					startX = Mouse.getX();
					startY = Mouse.getY();
					dragged = true;
				}
			}
			if (Mouse.getEventButton() == 1) {
				if (mouseover != -1) {
					if (selected != -1 && selected != mouseover) {
						int size = container.size();
						container.get(mouseover).craft(container.get(selected));
						if (container.size() == size) {
							container.get(selected).craft(container.get(mouseover));
						}
						Tutorial.craft();
						selected = -1;
					} else {
						selected = mouseover;
					}
				}
			}
			if (mouseover == -1) {
				return false;
			} else {
				return true;
			}
		} else {
			if (Mouse.getEventButton() == 0) {
				if (dragged && !Mouse.isButtonDown(0)) {
					int size = container.size();
					if (mouseover != -1 && mouseover != selected) {
						container.get(mouseover).craft(container.get(selected));
						if (container.size() == size) {
							container.get(selected).craft(container.get(mouseover));
						}
						Tutorial.craft();
					} else {
						if (Game.ths.mouseOver != null) {
							if (selected != -1) {
								Game.ths.mouseOver.give(container.get(selected));
								Tutorial.give();
							}
						}
					}
					dragged = false;
					selected = -1;
				}
			}
			return true;
		}
	}
}
