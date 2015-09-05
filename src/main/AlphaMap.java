package main;

import org.newdawn.slick.opengl.Texture;

/**
 * AlphaMap für die Kollisionsabfrage
 * 
 * @author Colin Dömer
 */
public class AlphaMap {

	public static AlphaMap stone, fruit, brokenFruit, herbage, herbageFruit, apeSitting, herbagePlant;

	public boolean[][] alpha;

	/**
	 * erstellt eine AlphaMap aus einer Textur
	 * 
	 * @param texture
	 *            Textur
	 */
	public AlphaMap(Texture texture) {

		byte[] data = texture.getTextureData();
		alpha = new boolean[texture.getImageWidth()][texture.getImageHeight()];
		for (int i = 0; i < data.length / 4; i++) {
			alpha[i % texture.getImageWidth()][i / texture.getImageWidth()] = (data[4 * i + 3] != 0);
		}
	}

	/**
	 * Kollisionsabfrage
	 * 
	 * @param x
	 * @param y
	 * @return Kollision
	 */
	public boolean hit(float x, float y) {

		return alpha[(int) (x * alpha.length)][(int) (y * alpha[0].length)];
	}

	/**
	 * gibt die zuvor generierte AlphaMap der entsprechenden Textur zurück
	 * 
	 * @param texture
	 * @return AlphaMap
	 */
	public static AlphaMap getAlphaMap(Texture texture) {

		if (texture == Textures.stone) {
			return stone;
		}
		if (texture == Textures.fruit) {
			return fruit;
		}
		if (texture == Textures.brokenFruit) {
			return brokenFruit;
		}
		if (texture == Textures.herbage) {
			return herbage;
		}
		if (texture == Textures.herbageFruit) {
			return herbageFruit;
		}
		if (texture == Textures.apeSitting) {
			return apeSitting;
		}
		if (texture == Textures.herbagePlant) {
			return herbagePlant;
		}
		return null;
	}

	/**
	 * lädt alle AlphaMaps
	 */
	public static void load() {

		stone = new AlphaMap(Textures.stone);
		fruit = new AlphaMap(Textures.fruit);
		brokenFruit = new AlphaMap(Textures.brokenFruit);
		herbage = new AlphaMap(Textures.herbage);
		herbagePlant = new AlphaMap(Textures.herbagePlant);
		herbageFruit = new AlphaMap(Textures.herbageFruit);
		apeSitting = new AlphaMap(Textures.apeSitting);
	}
}
