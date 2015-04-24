package main;

import static main.Main.*;

import org.lwjgl.input.Mouse;

/**
 * Hauptmenü
 * 
 * @author Colin Dömer
 */
public class MainMenu extends Screen {

	public Text play;
	public int focus = -1;

	public MainMenu() {

		super();
		play = new Text(Fonts.sfr48, "Tutorial spielen");
		play.setColor(0x000000);
	}

	public void update() {

		focus = -1;
		if (Mouse.getX() > (width - play.width) / 2 && Mouse.getX() < (width + play.width) / 2 && height - Mouse.getY() > (height - play.height) / 2 && height - Mouse.getY() < (height + play.height) / 2) {
			focus = 0;
		}
		while (Mouse.next()) {
			if (Mouse.getEventButtonState()) {
				if (Mouse.getEventButton() == 0) {
					if (focus == 0) {
						setScreen(new Game());
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
		if (focus == 0) {
			play.renderCenter(width / 2, height / 2 - play.height / 2, 0, play.width * (1 + 0.05f * sin(2 * updates)), play.height * (1 + 0.05f * sin(2 * updates)), 0, 0, 0);
		} else {
			play.renderCenter(width / 2, height / 2 - play.height / 2);
		}

		postrender();
	}
}
