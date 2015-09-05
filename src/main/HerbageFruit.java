package main;

/**
 * Aufgebrochene Frucht mit Kräutern
 * 
 * @author Colin Dömer
 *
 */
public class HerbageFruit extends Item {

	public HerbageFruit() {
		
		super(Textures.herbageFruit, 5);
		take = "Ich habe sie schon genommen.";
		look = "Eine ermüdende Geschmacksexplosion.";
		talk = "Der Affe wird danach nichts mehr sagen.";
	}
	
	public void craft(Item item) {

		if (item instanceof Stone) {
			Game.ths.setText("Die Frucht ist schon klein genug.");
		}
		if (item instanceof Herbage) {
			Game.ths.setText("Noch mehr und der Affe wacht gar nicht mehr auf.");
		}
	}
}
