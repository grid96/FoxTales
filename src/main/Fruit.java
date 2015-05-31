package main;

public class Fruit extends Item {
	
	public Fruit() {
		
		super(Textures.fruit, 1);
		take = "Sie hat eine harte Schale.";
		look = "Die Litschi fällt nicht weit vom Stamm.";
		talk = "Ist das dein Ernst? Erst mit dem Torii, dann mit dem Stein.. Jetzt soll ich auch noch mit der Frucht reden.";
	}
	
	public void craft(Item item) {

		if (item instanceof Stone) {
			container.replace(this, new BrokenFruit());
			Sounds.play(Sounds.fruit);
		}
		if (item instanceof Herbage) {
			Game.ths.setText("So kann ich sie nicht zusammenfügen.");
		}
	}
}
