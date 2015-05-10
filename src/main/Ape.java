package main;

import static main.Main.*;

public class Ape extends Entity {

	public String look2 = "Wer weiß, wann er sich das letzte Mal gewaschen hat.";
	public String talk2 = "Affe: Das ist mein Tor, suche dir ein eigenes.";
	public String talk3 = "Fuchs: Beiseite, du Drecksschleuder!";
	public int dialog = 0;
	public boolean hasStone = true;

	public Ape(float x, float y, Level level) {

		super(Textures.apeSitting, 7, x, y, 3, 3, level);
		take = "Ew...!";
		look = "Das ist ein dreckiger stinkender Affe.";
		talk = "Fuchs: Du versperrst mir den Weg!";
	}

	public void update() {

		if (dialog > 0) {
			dialog--;
			if (dialog == 360) {
				Game.ths.setText(talk);
			}
			if (dialog == 180) {
				Game.ths.setText(talk2);
			}
			if (dialog == 0) {
				Game.ths.setText(talk3);
				throwStone();
			}
		}
	}

	public void throwStone() {

		if (hasStone) {
			level.entities.add(new FlyingStone(new Stone(), 25.5f, 12.5f, 0.5f, 0.5f, level));
			hasStone = false;
		}
	}

	public void click() {

		if (random() < 0.5f) {
			Game.ths.setText("Das ist ein dreckiger stinkender Affe.");
			// Game.ths.think("Das ist ein dreckiger stinkender Affe.");
		} else {
			Game.ths.setText("Wer weiß, wann er sich das letzte Mal gewaschen hat.");
			// Game.ths.think("Wer weiß, wann er sich das letzte Mal gewaschen hat.");
		}
	}

	public void look() {

		if (random() < 0.5f) {
			Game.ths.setText(look);
		} else {
			Game.ths.setText(look2);
		}
	}

	public void talk() {

		dialog = 361;
	}

	public void give(Item item) {

		if (item instanceof Stone) {
			// TODO throwing stones
			item.container.remove(item);
			hasStone = true;
			throwStone();
		}
		if (item instanceof Fruit) {
			Game.ths.setText("Affe: Ich kann das so nicht essen.");
		}
		if (item instanceof Herbage) {
			Game.ths.setText("Affe: Ich kann das so nicht essen.");
		}
		if (item instanceof HerbageFruit) {
			// TODO ape movement
			item.container.remove(item);
			Game.ths.setText("GAME OVER");
			Sounds.play(Sounds.breaking);
		}
		if (item instanceof BrokenFruit) {
			item.container.remove(item);
			((Level0) level).dropFruit();
			Game.ths.setText("Affe: Lecker! Könnte ein bisschen mehr gewürzt sein.");
		}
	}
}
