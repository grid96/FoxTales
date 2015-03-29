package main;

import static main.Main.*;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;

/**
 * Texturen Verwaltung
 * 
 * @author Colin Dömer
 */
public class Textures {

	public static Texture testlevel, testfox;

	/**
	 * Lädt levelunabhängige Texturen.
	 */
	public static void load() {

		testlevel = loadTexture("testlevel", false);
		testfox = loadTexture("testfox", false);
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
			// long l = System.currentTimeMillis();
			texture = TextureLoader.getTexture(((compressed && compressedTextures) ? "JPG" : "PNG"), ResourceLoader.getResourceAsStream(name + ((compressed && compressedTextures) ? ".jpg" : ".png")));
			// System.out.println(System.currentTimeMillis() - l + " ms");
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

	/**
	 * Zeichnet ein texturiertes Rechteck
	 * 
	 * @param texture
	 *            Textur
	 * @param x
	 *            Start X
	 * @param y
	 *            Start Y
	 * @param w
	 *            Breite
	 * @param h
	 *            Höhe
	 */
	public static void renderQuad(Texture texture, float x, float y, float w, float h) {

		texture.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(x, y);
		glTexCoord2f(0, 1);
		glVertex2f(x, y + h);
		glTexCoord2f(1, 1);
		glVertex2f(x + w, y + h);
		glTexCoord2f(1, 0);
		glVertex2f(x + w, y);
		glEnd();
	}

	/**
	 * Zeichnet ein gespiegelt texturiertes Rechteck
	 * 
	 * @param texture
	 *            Textur
	 * @param x
	 *            Start X
	 * @param y
	 *            Start Y
	 * @param w
	 *            Breite
	 * @param h
	 *            Höhe
	 */
	public static void renderQuadMirrored(Texture texture, float x, float y, float w, float h) {

		texture.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(1, 0);
		glVertex2f(x, y);
		glTexCoord2f(1, 1);
		glVertex2f(x, y + h);
		glTexCoord2f(0, 1);
		glVertex2f(x + w, y + h);
		glTexCoord2f(0, 0);
		glVertex2f(x + w, y);
		glEnd();
	}

	/**
	 * Zeichnet einen Ausschnitt einer sich wiederholenden Textur
	 * 
	 * @param texture
	 *            Textur
	 * @param w
	 *            Breite
	 * @param h
	 *            Höhe
	 * @param x0
	 *            links
	 * @param y0
	 *            oben
	 * @param x1
	 *            rechts
	 * @param y1
	 *            unten
	 */
	public static void renderMapped(Texture texture, float w, float h, float x0, float y0, float x1, float y1) {

		texture.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(x0 % w / w, y0 % h / h);
		glVertex2f(x0, y0);
		glTexCoord2f(x0 % w / w, (y0 % h + y1 - y0) / h);
		glVertex2f(x0, y1);
		glTexCoord2f((x0 % w + x1 - x0) / w, (y0 % h + y1 - y0) / h);
		glVertex2f(x1, y1);
		glTexCoord2f((x0 % w + x1 - x0) / w, y0 % h / h);
		glVertex2f(x1, y0);
		glEnd();
	}
}
