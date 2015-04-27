package main;

public class Torii extends Entity {

	public Torii(float x, float y, Level level) {
		
		super(Textures.torii, 8, x, y, 1, 1, level);
		take = "Es steht seit 1000 Jahren hier, ich will es nicht aus seinem Platz reißen.";
		look = "Das ist ein Tor. Normalerweise kann man hindurch gehen, nur hängt dieser stinkige Affe da rum und versperrt mir den Weg.";
		talk = "Einige Leute reden mit sich selbst, andere mit totem Stein.";
	}
	
	public void craft(Item item) {

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
}
