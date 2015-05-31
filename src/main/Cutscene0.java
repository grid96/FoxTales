package main;

import static main.Main.*;

import org.lwjgl.input.Keyboard;
public class Cutscene0 extends Cutscene{

	public int updates0;
	public Text text;
	public Text text1, text2;
	
	public Cutscene0() {
	
		updates0 = updates;
		text1 = new Text(Fonts.sfr36, Fonts.wrap(Fonts.sfr36, "Wach auf, Fuchs! Es ist Zeit zu gehen. Vollbringe acht gute Taten und ich gew‰hre dir einen Wunsch, der dir viel Macht verleiht. Und ich weiﬂ, dass du bei dieser Aussicht nicht widerstehen kannst...", Main.width * 0.8f));
		text2 = new Text(Fonts.sfr36, Fonts.wrap(Fonts.sfr36, "Ich kann dich nicht weiter begleiten. Meine Kraft schwindet, denn die Natur ist aus dem Gleichgewicht geraten und ich bin an diesen Wald gebunden.", Main.width * 0.8f));
	}
	
	public void update() {
		
		int time = updates - updates0;
		if (time == 90) {
			Sounds.play(Sounds.intro);
		}
		if (time == 120) {
			text = text1;
		}
		if (time == 750) {
			text = text2;
		}
		if (time == 1200) {
			text = null;
		}
		if (time == 1640) {
			setScreen(new Game());
			Sounds.stop(Sounds.intro);
		}
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					setScreen(new Game());
					Sounds.stop(Sounds.intro);
				}
			}
		}
	}
	
	public void render() {
		
		super.prerender();
		
		setOrtho2D(0, 0, Main.width, Main.height);
		
		Textures.renderColoredQuad(0, 0, Main.width, Main.height, 0, 0, 0, 1);
		
		Textures.logo.bind();
		int time = updates - updates0;
		if (time < 120) {
			Textures.setColor(1, 1, 1, time / 120f);
			Textures.renderQuad(Textures.cutscene0, 0, Main.height / 2 - Main.width / 4, Main.width, Main.width / 2);
		} else if (time < 300) {
			Textures.renderQuad(Textures.cutscene0, 0, Main.height / 2 - Main.width / 4, Main.width, Main.width / 2);
		} else if (time < 480) {
			Textures.renderQuad(Textures.cutscene0, 0, Main.height / 2 - Main.width / 4, Main.width, Main.width / 2);
			Textures.setColor(1, 1, 1, (time - 300) / 180f);
			Textures.renderQuad(Textures.cutscene1, 0, Main.height / 2 - Main.width / 4, Main.width, Main.width / 2);
		} else if (time < 780) {
			Textures.renderQuad(Textures.cutscene1, 0, Main.height / 2 - Main.width / 4, Main.width, Main.width / 2);
		} else if (time < 900) {
			Textures.renderQuad(Textures.cutscene1, 0, Main.height / 2 - Main.width / 4, Main.width, Main.width / 2);
			Textures.setColor(1, 1, 1, (time - 780) / 120f);
			Textures.renderQuad(Textures.cutscene2, 0, Main.height / 2 - Main.width / 4, Main.width, Main.width / 2);
		} else if (time < 1200) {
			Textures.renderQuad(Textures.cutscene2, 0, Main.height / 2 - Main.width / 4, Main.width, Main.width / 2);
		} else if (time < 1320) {
			Textures.renderQuad(Textures.cutscene2, 0, Main.height / 2 - Main.width / 4, Main.width, Main.width / 2);
			Textures.renderColoredQuad(0, 0, Main.width, Main.height, 1, 1, 1, (time - 1200) / 480f);
			Textures.setColor(1, 1, 1, (time - 1200) / 120f);
			Textures.renderQuad(Textures.logo, Main.width / 2 - 256 * (1 + (time - 1200) / 900f), Main.height / 2 - 256 * (1 + (time - 1200) / 900f), 512 * (1 + (time - 1200) / 900f), 512 * (1 + (time - 1200) / 900f));
		} else if (time < 1520) {
			Textures.renderQuad(Textures.cutscene2, 0, Main.height / 2 - Main.width / 4, Main.width, Main.width / 2);
			Textures.renderColoredQuad(0, 0, Main.width, Main.height, 1, 1, 1, 0.25f);
			Textures.renderQuad(Textures.logo, Main.width / 2 - 256 * (1 + (time - 1200) / 900f), Main.height / 2 - 256 * (1 + (time - 1200) / 900f), 512 * (1 + (time - 1200) / 900f), 512 * (1 + (time - 1200) / 900f));
		} else {
			Textures.renderQuad(Textures.cutscene2, 0, Main.height / 2 - Main.width / 4, Main.width, Main.width / 2);
			Textures.renderColoredQuad(0, 0, Main.width, Main.height, 1, 1, 1, 0.25f + (time - 1520) / 160f);
			Textures.setColor(1, 1, 1, 1 - (time - 1520) / 120f);
			Textures.renderQuad(Textures.logo, Main.width / 2 - 256 * (1 + (time - 1200) / 900f), Main.height / 2 - 256 * (1 + (time - 1200) / 900f), 512 * (1 + (time - 1200) / 900f), 512 * (1 + (time - 1200) / 900f));
		}
		
		renderText();
		
		super.postrender();
	}
	
	public void renderText() {

		Textures.setColor(1, 1, 1, 1);
		if (text != null) {
			text.renderCenter(Main.width / 2, Main.height / 2 + Main.width / 4 - text.height, 0, (int) text.width, (int) text.height, 0, 0, 0);
		}
	}
}
