package main;

import static main.Main.*;

import org.lwjgl.input.Mouse;

/**
 * Hauptmen�
 * 
 * @author Colin D�mer
 */
public class MainMenu extends Screen {

	public Screen screen;
	public int focus = -1;
	public Text[] buttons = new Text[3];

	/**
	 * erzeugt das Hauptmen� mit Buttons
	 * 
	 * @param screen
	 */
	public MainMenu(Screen screen) {

		super();
		this.screen = screen;
		if (screen == null) {
			buttons[0] = new Text(Fonts.wr36, "Tutorial spielen");
			buttons[0].setColor(0x000000);
		} else {
			buttons[0] = new Text(Fonts.wr36, "Spiel fortsetzen");
			buttons[0].setColor(0x000000);
		}
		buttons[1] = new Text(Fonts.wr36, "Optionen");
		buttons[1].setColor(0x000000);
		buttons[2] = new Text(Fonts.wr36, "Beenden");
		buttons[2].setColor(0x000000);
	}

	/**
	 * updatet den Button Fokus
	 */
	public void update() {

		focus = -1;
		for (int i = 0; i < buttons.length; i++) {
			if (Mouse.getX() > width / 2 - 120 && Mouse.getX() < width / 2 + 120 && height - Mouse.getY() > height / 2 - buttons[i].height / 2 + 80 * i - 30 && height - Mouse.getY() < height / 2 - buttons[i].height / 2 + 80 * i + 30) {
				focus = i;
			}
		}
		while (Mouse.next()) {
			if (Mouse.getEventButtonState()) {
				if (Mouse.getEventButton() == 0) {
					if (focus == 0) {
						if (screen == null) {
							setScreen(new Cutscene0());
						} else {
							setScreen(screen);
						}
					}
					if (focus == 2) {
						Main.game.destroy();
					}
				}
			}
		}
	}

	/**
	 * rendert das Hauptmen�
	 */
	public void render() {

		prerender();

		setOrtho2D(-0.5f, -0.5f / resolution, 0.5f, 0.5f / resolution);
		Textures.renderQuad(Textures.menuBackground, -0.5f, -0.375f, 1, 0.75f);
		Textures.renderQuad(Textures.logo, -0.125f, -0.3125f + 0.01f * sin(updates), 0.25f, 0.25f);

		setOrtho2D(0, 0, width, height);
		for (int i = 0; i < buttons.length; i++) {
			if (focus == i) {
				Textures.renderQuad(Textures.button, width / 2 - 120 * (1 + 0.05f * sin(2 * updates)), height / 2 - buttons[i].height / 2 + 80 * i - 30 * (1 + 0.05f * sin(2 * updates)), 240 * (1 + 0.05f * sin(2 * updates)), 60 * (1 + 0.05f * sin(2 * updates)));
			} else {
				Textures.renderQuad(Textures.button, width / 2 - 120, height / 2 - buttons[i].height / 2 + 80 * i - 30, 240, 60);
			}
		}
		for (int i = 0; i < buttons.length; i++) {
			if (focus == i) {
				buttons[i].renderCenter(width / 2, height / 2 - buttons[i].height / 2 + 80 * i - 25, 0, buttons[i].width * (1 + 0.05f * sin(2 * updates)), buttons[i].height * (1 + 0.05f * sin(2 * updates)), 0, 0, 0);
			} else {
				buttons[i].renderCenter(width / 2, height / 2 - buttons[i].height / 2 + 80 * i - 25);
			}
		}

		postrender();
	}
}
