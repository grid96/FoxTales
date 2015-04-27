package main;

public class BrokenFruit extends Item {

	public BrokenFruit() {
		
		super(Textures.brokenFruit, 3);
		take = "Ich habe sie schon genommen.";
		look = "Das saftige Innere kommt zum Vorschein.";
		talk = "Sie antwortet nicht.";
	}

	public void craft(Item item) {

		if (item instanceof Stone) {
			Game.ths.setText("Die Frucht ist schon klein genug.");
		}
		if (item instanceof Herbage) {
			item.container.remove(item);
			container.replace(this, new HerbageFruit());
			Game.ths.setText("Eine ermüdende Geschmacksexplosion.");
		}
	}
}
