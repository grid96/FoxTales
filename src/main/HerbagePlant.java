package main;

public class HerbagePlant extends DroppedItem {

	public HerbagePlant(float x, float y, float w, float h, Level level) {
		
		super(new Herbage(), x, y, w, h, level);
		texture = Textures.herbagePlant;
		alphaMap = null;
		take = "";
		look = "Das ist Nachtschatten. Das macht müde.";
		talk = "Ihr habt ein schattiges Plätzchen.";
	}

	public void take() {
		
		if (!Game.ths.inventory.container.contains(item.id)) {
			Game.ths.inventory.add(new Herbage());
			Game.ths.setText(take);
			Sounds.play(Sounds.ripping);
		}
	}
	
	public void give(Item item) {

		if (item instanceof Stone) {
			take();
		}
		if (item instanceof Fruit) {
			Game.ths.setText("Das geht so nicht.");
		}
		if (item instanceof Herbage) {
			Game.ths.setText("Ich kann sie nicht wieder ankleben.");
		}
		if (item instanceof HerbageFruit) {
			Game.ths.setText("Die Frucht ist genug gewürzt.");
		}
	}
}
