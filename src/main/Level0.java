package main;

import static main.Main.*;

/**
 * Tutorial Level
 * 
 * @author Colin Dömer
 */
public class Level0 extends Level {

	public FlyingStone stone;
	public int updates0;

	/**
	 * erzeugt das Level mit Entities und startet die Musik
	 */
	public Level0() {

		background = Textures.background0;
		foreground = Textures.foreground0;
		width = 40;
		height = 20;

		HerbagePlant plant = new HerbagePlant(29.5f, 13, 4, 2, this);
		entities.add(plant);
		entities.add(new Ape(35, 2.5f, this));
		entities.add(new Torii(32.8f, 2.3f, 6, 5, this));
		dropFruit();

		Sounds.play(Sounds.music0);

		updates0 = updates;
	}

	public void update() {

		super.update();
	}

	public boolean collision(float x, float y, float w, float h) {

		if (y > 12.0f) {
			return true;
		}
		if (x < -1.5f) {
			return true;
		}
		if (x > 37.5f) {
			return true;
		}
		return false;
	}

	/**
	 * erzeugt einen neue aufnehmbare Frucht
	 */
	public void dropFruit() {

		entities.add(new DroppedItem(new Fruit(), 17.5f, 12, 4f, 1.5f, this));
	}

	/**
	 * erzeugt einen neuen fliegenden Stein
	 * 
	 * @param stone
	 *            Stein
	 */
	public void setFlyingStone(FlyingStone stone) {

		entities.add(stone);
		this.stone = stone;
	}

	/**
	 * gibt den vorgandenen fliegenden Stein zurück
	 * 
	 * @return fliegender Stein
	 */
	public FlyingStone getFlyingStone() {

		return stone;
	}
}
