package main;

import static main.Main.*;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Texturen Verwaltung
 * 
 * @author Colin Dömer
 */
public class Textures {

	public static Texture test;

	/**
	 * Lädt levelunabhängige Texturen.
	 */
	public static void load() {

		test = loadTexture("icon16", false);
	}

	/**
	 * Lädt Texturen für das erste Level.
	 */
	public static void loadLevel1() {

	}

	/**
	 * Lädt eine Textur.
	 * 
	 * @param name
	 *            Pfadname zur Bilddatei
	 * @param compressed
	 *            JPG oder PNG
	 * @return geladene Textur
	 */
	public static Texture loadTexture(String name, boolean compressed) {

		Texture texture = null;
		try {
			TextureLoader.getTexture(((compressed && compressedSounds) ? "JPG" : "PNG"), ResourceLoader.getResourceAsStream(name + ((compressed && compressedTextures) ? ".jpg" : ".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return texture;
	}

	/**
	 * Lädt mehrere Textur.
	 * 
	 * @param name
	 *            Pfadname zu den Bilddatein
	 * @param n
	 *            Anzahl an Bildern
	 * @param compressed
	 *            JPGs oder PNGs
	 * @return geladene Texturen
	 */
	public static Texture[] loadTextures(String name, int n, boolean compressed) {

		Texture[] textures = new Texture[n];
		for (int i = 0; i < n; i++) {
			textures[i] = loadTexture(name + i, compressed);
		}
		return textures;
	}
}
