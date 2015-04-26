package main;

import org.lwjgl.input.Mouse;

import static main.Main.*;

/**
 * Level Darstellung
 * 
 * @author Colin Dömer
 */
public class Game extends Screen {

	public static Game ths;

	public Level level;
	public Fox fox;
	public float x, y;
	public float width, height;
	public float x0, y0, x1, y1;
	public Console console;
	public Inventory inventory;
	public boolean inInventory;
	public Script script;
	public Interactive mouseOver;

	// public Text test = new Text(Fonts.arial12, "test");

	/**
	 * OpenGL Einstellungen vornehmen und Level laden
	 * 
	 * @author Colin Dömer
	 */
	public Game() {

		super();

		ths = this;

		resize();

		level = new Level0();
		fox = new Fox(level);
		inventory = new Inventory();
		// console = new Console(20, Main.height - 220, 300, 200);

		inventory.add(new Fruit());

		level.entities.add(new DroppedItem(new Stone(), 20, 15, 1, 1, level));
		// script = new PickUp((DroppedItem) level.entities.get(0));
	}

	/**
	 * Updatet das Level.
	 */
	public void update() {

		if (script != null) {
			script.update();
		}

		level.update();

		// console.update();
		fox.update();

		x = fox.x;
		y = fox.y - 2.5f;
		y += 5f * ((float) (Main.height - Mouse.getY()) / Main.height - 0.5f);
		x += 5f * resolution * ((float) Mouse.getX() / Main.width - 0.5f);
		// if (x - width / 2 < 0) {
		// x = width / 2;
		// }
		if (y - height / 2 < 0) {
			y = height / 2;
		}
		// if (x + width / 2 > level.width) {
		// x = level.width - width / 2;
		// }
		if (y + height / 2 > level.height) {
			y = level.height - height / 2;
		}

		// if (updates % 60 == 0) {
		// console.addMessage("test", 0xFFFFFFFF);
		// }

		inventory.update();

		if (updates % 150 == 0) {
			level.particles.add(new Thought("Ich denke also bin ich.", fox.x, fox.y - 2, level));
		}
		
		while (Mouse.next()) {
			if (Mouse.getEventButtonState()) {
				if (!inventory.click()) {
					level.click();
				}
			}
		}
	}

	/**
	 * Rendert das Level.
	 */
	public void render() {

		prerender();

		x0 = x - width / 2;
		x1 = x + width / 2;
		y0 = y - height / 2;
		y1 = y + height / 2;
		setOrtho2D(x0, y0, x1, y1);

		level.render(x0, y0, x1, y1);
		fox.render();
		level.renderOverlay(x0, y0, x1, y1);

		setOrtho2D(0, 0, Main.width, Main.height);
		inventory.render();
		// console.render();

		postrender();
	}

	/**
	 * Wird aufgerufen, wenn sich die Auflösung ändert.
	 */
	public void resize() {

		height = 10f;
		width = height * resolution;
	}

	public float getMouseX() {

		return level.getLevelX(x0 + (x1 - x0) * ((float) Mouse.getX() / Main.width));
	}

	public float getMouseY() {

		return y0 + (y1 - y0) * ((float) (Main.height - Mouse.getY()) / Main.height);
	}
}
