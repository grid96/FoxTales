package main;

public class Herbage extends Item {

	public Herbage() {
		
		super(Textures.herbage, 4);
		take = "Ich habe sie schon genommen.";
		look = "Verzehrfertig.";
		talk = "Ihr werdet nützlich sein.";
	}
	
	public void craft(Item item) {

		if (item instanceof Stone) {
			Game.ths.setText("Sie sind schon klein genug.");
		}
	}
}
