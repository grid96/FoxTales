package main;

import org.newdawn.slick.opengl.Texture;

public class AlphaMap {

	public static AlphaMap stone, fruit, brokenFruit, herbage, herbageFruit, apeSitting;
	
	public boolean[][] alpha;
	
	public AlphaMap(Texture texture) {
		
		byte[] data = texture.getTextureData();
		alpha = new boolean[texture.getImageWidth()][texture.getImageHeight()];
		for (int i = 0; i < data.length / 4; i++) {
			alpha[i % texture.getImageWidth()][i / texture.getImageWidth()] = (data[4 * i + 3] != 0);
		}
	}
	
	public boolean hit(float x, float y) {
		
		return alpha[(int) (x * alpha.length)][(int) (y * alpha[0].length)];
	}
	
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
		return null;
	}
	
	public static void load() {
		
		stone = new AlphaMap(Textures.stone);
		fruit = new AlphaMap(Textures.fruit);
		brokenFruit = new AlphaMap(Textures.brokenFruit);
		herbage = new AlphaMap(Textures.herbage);
		herbageFruit = new AlphaMap(Textures.herbageFruit);
		apeSitting = new AlphaMap(Textures.apeSitting);
	}
}
