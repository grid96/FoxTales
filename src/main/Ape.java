package main;

import static main.Main.*;

/**
 * Affe
 * 
 * @author Colin D�mer
 *
 */
public class Ape extends Entity {

	public String look2 = "Wer wei�, wann er sich das letzte Mal gewaschen hat.";
	public String talk2 = "Affe: Das ist mein Tor, suche dir ein eigenes.";
	public String talk3 = "Fuchs: Beiseite!";
	public int dialog = 0;
	public boolean hasStone = true;
	public int colorApe = 0x4444CC;
	public int colorFox = 0xAA2200;
	public int lastThrow = -90;

	public Ape(float x, float y, Level level) {

		super(Textures.apeSitting, 7, x, y, 3, 3, level);
		take = "Ew...!";
		look = "Das ist ein dreckiger stinkender Affe.";
		talk = "Fuchs: Du versperrst mir den Weg!";
	}

	/**
	 * bringt den laufenden Dialog vorran und generiert einen fliegenden Stein, falls dieser zuvor geworfen wurde
	 */
	public void update() {

		if (dialog > 0) {
			dialog--;
			if (dialog == 360) {
				Game.ths.setText(talk, colorFox);
			}
			if (dialog == 180) {
				Game.ths.setText(talk2, colorApe);
			}
			if (dialog == 0) {
				Game.ths.setText(talk3, colorFox);
				throwStone();
			}
		}
		if (updates - lastThrow == 50) {
			level.entities.add(new FlyingStone(new Stone(), 35.75f, 0.25f, 0.25f, 0.25f, level));
		}
	}

	/**
	 * wirft den Stein
	 */
	public void throwStone() {

		if (hasStone) {
			lastThrow = updates;
			Sounds.play(Sounds.ape);
			hasStone = false;
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
			item.container.remove(item);
			hasStone = true;
			throwStone();
		}
		if (item instanceof Fruit) {
			Game.ths.setText("Affe: Ich kann das so nicht essen.", colorApe);
		}
		if (item instanceof Herbage) {
			Game.ths.setText("Affe: Ich kann das so nicht essen.", colorApe);
		}
		if (item instanceof HerbageFruit) {
			item.container.remove(item);
			Game.ths.setText("GAME OVER");
			Sounds.play(Sounds.fruit);
		}
		if (item instanceof BrokenFruit) {
			item.container.remove(item);
			((Level0) level).dropFruit();
			Game.ths.setText("Affe: Lecker! K�nnte ein bisschen mehr gew�rzt sein.", colorApe);
		}
	}
	
	public void render() {

		getTexture();
		Textures.renderQuad(texture, x - w / 2, y - h, w, h);
	}

	/**
	 * gibt die Textur des Affens abh�ngig vom letzten Steinwurf zur�ck
	 */
	public void getTexture() {

		if (updates - lastThrow < 15) {
			texture = Textures.apeThrowing[0];
		} else if (updates - lastThrow < 35) {
			texture = Textures.apeThrowing[1];
		} else if (updates - lastThrow < 50) {
			texture = Textures.apeThrowing[2];
		} else if (updates - lastThrow < 55) {
			texture = Textures.apeThrowing[3];
		} else if (updates - lastThrow < 65) {
			texture = Textures.apeThrowing[4];
		} else if (updates - lastThrow < 85) {
			texture = Textures.apeThrowing[5];
		} else {
			texture = Textures.apeSitting;
		}
	}
}
