package main;

import org.lwjgl.input.Keyboard;
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
	public Entity mouseOverLast;
	public Entity mouseOver;
	public int mouseOverCounter;
	public Text text;
	public int textVanish;
	public int updates0;
	public Tutorial tutorial;
	public int nextWaterSound = 300;

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
		tutorial = new Tutorial();
		// console = new Console(20, Main.height - 220, 300, 200);

		// script = new PickUp((DroppedItem) level.entities.get(0));

		updates0 = updates;
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

		fox.y = 14.5f;
		if (fox.x < -1.5f) {
			fox.x = -1.5f;
		}
		if (fox.x > 37.5f) {
			fox.x = 37.5f;
		}

		x = fox.x;
		y = fox.y - 5.5f;
		// y += 8f * ((float) (Main.height - Mouse.getY()) / Main.height - 0.5f);
		// x += 4f * resolution * ((float) Mouse.getX() / Main.width - 0.5f);
		if (x - width / 2 < 0) {
			x = width / 2;
		}
		if (y - height / 2 < 0) {
			y = height / 2;
		}
		if (x + width / 2 > level.width) {
			x = level.width - width / 2;
		}
		if (y + height / 2 > level.height * 0.775f) {
			y = level.height * 0.775f - height / 2;
		}

		// if (updates % 60 == 0) {
		// console.addMessage("test", 0xFFFFFFFF);
		// }

		// x = fox.x;
		y = level.height * 0.393f;

		inventory.update();

		if (mouseOver != null && mouseOver == mouseOverLast) {
			mouseOverCounter++;
			if (mouseOverCounter == 60) {
				if (text == null || textVanish < 120) {
					mouseOver.look();
					Tutorial.look();
					try {
						Mouse.setNativeCursor(Cursors.look);
					} catch (Exception e) {
					}
				} else {
					mouseOverCounter--;
				}
			}
		} else {
			mouseOverCounter = 0;
		}

		try {
			if (mouseOver != mouseOverLast) {
				if (mouseOver != null) {
					Mouse.setNativeCursor(Cursors.take);
				} else {
					Mouse.setNativeCursor(Cursors.standard);
				}
			}
		} catch (Exception e) {
		}

		mouseOverLast = mouseOver;

		// if (updates % 150 == 0) {
		// think("Ich denke also bin ich.");
		// }

		if (textVanish > 0) {
			if (textVanish < 120) {
				// text.setColor(0x010000 * (int) (0xFF * (textVanish / 120f)) + 0x000100 * (int) (0xFF * (textVanish / 120f)) + 0x000001 * (int) (0xFF * (textVanish / 120f)));
				text.setAlpha(textVanish / 120f);
			}
			textVanish--;
			if (textVanish <= 0) {
				text.destroy();
				text = null;
			}
		}

		nextWaterSound--;
		if (nextWaterSound <= 0) {
			Sounds.play(Sounds.water);
			nextWaterSound = 600 + randomInt(300);
		}

		while (Mouse.next()) {
			if (!inventory.click()) {
				// level.click();
				if (Mouse.getEventButtonState()) {
					if (mouseOver != null) {
						if (Mouse.getEventButton() == 0) {
							if (inventory.selected != -1) {
								mouseOver.give(inventory.container.get(inventory.selected));
								Tutorial.give();
							} else {
								mouseOver.take();
								Tutorial.take();
							}
						}
						if (Mouse.getEventButton() == 1) {
							mouseOver.talk();
							Tutorial.talk();
							if (mouseOver.talk != null || !mouseOver.talk.equals("")) {
								try {
									Mouse.setNativeCursor(Cursors.talk);
								} catch (Exception e) {
								}
							}
						}
					}
					inventory.selected = -1;
				}
			}
		}

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					setScreen(new MainMenu(this));
				}
			}
		}

		tutorial.update();
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
		renderText();
		Textures.setColor(1, 1, 1, 1);
		tutorial.render();
		// console.render();

		if (updates - updates0 < 90) {
			Textures.renderColoredQuad(0, 0, Main.width, Main.height, 1, 1, 1, 1 - (updates - updates0) / 90f);
		}

		postrender();
	}

	public void renderText() {

		Textures.setColor(1, 1, 1, 1);
		Textures.renderQuad(Textures.textbar, 0, Main.height - 30, Main.width, 30);
		if (text != null) {
			text.renderCenter(Main.width / 2, Main.height - 32, 0, (int) text.width, (int) text.height, 0, 0, 0);
		}
	}

	/**
	 * Wird aufgerufen, wenn sich die Auflösung ändert.
	 */
	public void resize() {

		height = 15f * Main.height / (Main.height - 30f);
		width = height * resolution;
	}

	public float getMouseX() {

		return level.getLevelX(x0 + (x1 - x0) * ((float) Mouse.getX() / Main.width));
	}

	public float getMouseY() {

		return y0 + (y1 - y0) * ((float) (Main.height - Mouse.getY()) / Main.height);
	}

	public void think(String text) {

		level.particles.add(new Thought(text, fox.x, fox.y - 4f, level));
	}

	public void setText(String text, int color) {

		if (text == null) {
			return;
		}
		if (this.text != null) {
			this.text.destroy();
		}
		this.text = new Text(Fonts.sfr36, text);
		this.text.setColor(color);
		textVanish = 300;
	}

	public void setText(String text) {

		setText(text, 0x000000);
	}
}
