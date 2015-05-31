package main;

public class Torii extends Entity {

	public Torii(float x, float y, float w, float h, Level level) {
		
		super(null, 8, x, y, w, h, level);
		alphaMap = null;
		take = "Es steht seit 1000 Jahren hier, ich will es nicht aus seinem Platz reißen.";
		look = "Das ist ein Tor. Normalerweise kann man hindurch gehen, nur hängt dieser stinkige Affe da rum und versperrt mir den Weg.";
		talk = "Einige Leute reden mit sich selbst, andere mit totem Stein.";
	}
	
	public void give(Item item) {

		if (item instanceof Stone) {
			Game.ths.setText("Es besteht schon aus Stein.");
		}
		if (item instanceof Fruit) {
			Game.ths.setText("Das wird nichts.");
		}
		if (item instanceof Herbage) {
			Game.ths.setText("Sinnlos.");
		}
		if (item instanceof HerbageFruit) {
			Game.ths.setText("Du kommst auf die dümmsten Ideen.");
		}
		if (item instanceof BrokenFruit) {
			Game.ths.setText("Die Frucht ist schon klein genug.");
		}
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
