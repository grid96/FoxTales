package main;

import static main.Main.*;

import org.lwjgl.input.Mouse;

public class Inventory {

	public int size = 6;
	public ItemContainer container;
	public int selected = -1;
	public int mouseover = -1;

	public Inventory() {

		container = new ItemContainer(size);
	}
	
	public void update() {
		
		mouseover = -1;
		for (int i = 0; i < size; i++) {
			if (Mouse.getX() > width / 2 - 50 * size + 100 * i + 10 && height - Mouse.getY() > height - 100 && Mouse.getX() < width / 2 - 50 * size + 100 * i + 90 && height - Mouse.getY() < height - 20) {
				mouseover = i;
			}
		}
		while (Mouse.next()) {
			if (Mouse.getEventButtonState()) {
				if (Mouse.getEventButton() == 0) {
					selected = mouseover;
				}
				if (Mouse.getEventButton() == 1) {
					selected = -1;
				}
			}
		}
	}

	public void render() {
		
		for (int i = 0; i < size; i++) {
			if (i == selected) {
				Textures.renderColoredQuad(width / 2 - 50 * size + 100 * i + 10, height - 100, 80, 80, 0, 0, 0, 0.4f);
			} else {
				Textures.renderColoredQuad(width / 2 - 50 * size + 100 * i + 10, height - 100, 80, 80, 0, 0, 0, 0.2f);
			}
			if (container.get(i) != null) {
				Textures.renderQuad(container.get(i).item.texture, width / 2 - 50 * size + 100 * i + 10, height - 100, 80, 80);
			}
		}
	}
	
	public void add(ItemSlot slot) {
		
		container.add(slot);
	}
	
	public void add(Item item) {
		
		container.add(item);
	}
}
