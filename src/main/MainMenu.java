package main;

import static main.Main.*;

import org.lwjgl.input.Mouse;

/**
 * Hauptmenü
 * 
 * @author Colin Dömer
 */
public class MainMenu extends Screen {

	public Game game;
	public int focus = -1;
	public Text[] buttons = new Text[3];

	public MainMenu(Game game) {

		super();
		this.game = game;
		if (game == null) {
			buttons[0] = new Text(Fonts.sfr48, "Tutorial spielen");
			buttons[0].setColor(0x000000);
		} else {
			buttons[0] = new Text(Fonts.sfr48, "Spiel fortsetzen");
			buttons[0].setColor(0x000000);
		}
		buttons[1] = new Text(Fonts.sfr48, "Optionen");
		buttons[1].setColor(0x000000);
		buttons[2] = new Text(Fonts.sfr48, "Beenden");
		buttons[2].setColor(0x000000);
	}

	public void update() {

		focus = -1;
		for (int i = 0; i < buttons.length; i++) {
			if (Mouse.getX() > (width - buttons[i].width) / 2 && Mouse.getX() < (width + buttons[i].width) / 2 && height - Mouse.getY() > (height - buttons[i].height) / 2 + 50 * i && height - Mouse.getY() < (height + buttons[i].height) / 2 + 50 * i) {
				focus = i;
			}
		}
		while (Mouse.next()) {
			if (Mouse.getEventButtonState()) {
				if (Mouse.getEventButton() == 0) {
					if (focus == 0) {
						if (game == null) {
							setScreen(new Game());
						} else {
							setScreen(game);
						}
					}
					if (focus == 2) {
						Main.game.destroy();
					}
				}
			}
		}
	}

	public void render() {

		prerender();

		setOrtho2D(-0.5f, -0.5f / resolution, 0.5f, 0.5f / resolution);
		Textures.renderQuad(Textures.menuBackground, -0.5f, -0.375f, 1, 0.75f);
		Textures.renderQuad(Textures.logo, -0.125f, -0.3125f + 0.01f * sin(updates), 0.25f, 0.25f);

		setOrtho2D(0, 0, width, height);
		for (int i = 0; i < buttons.length; i++) {
			if (focus == i) {
				buttons[i].renderCenter(width / 2, height / 2 - buttons[i].height / 2 + 50 * i, 0, buttons[i].width * (1 + 0.05f * sin(2 * updates)), buttons[i].height * (1 + 0.05f * sin(2 * updates)), 0, 0, 0);
			} else {
				buttons[i].renderCenter(width / 2, height / 2 - buttons[i].height / 2 + 50 * i);
			}
		}

		postrender();
	}
}
