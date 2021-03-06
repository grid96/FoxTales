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
 * @author Colin D�mer
 */
public class Textures {

	public static Texture logo, menuBackground, foreground0, stone, fruit, brokenFruit, herbage, herbageFruit, foxSitting, apeSitting, apeSittingWithout, herbagePlant, itemSlot, button, textbar, foxStanding;
	public static Texture[] background0;
	public static Texture[] foxWalking;
	public static Texture[] apeThrowing;
	public static Texture[] tutorial;
	public static Texture cutscene0, cutscene1, cutscene2;
	public static boolean loaded, loadedLevel0;

	/**
	 * L�dt levelunabh�ngige Texturen.
	 */
	public static void load() {

		if (loaded) {
			return;
		}
		logo = loadTexture("logo", false);
		menuBackground = loadTexture("menuBackground", false);
		stone = loadTexture("stone", false);
		fruit = loadTexture("fruit", false);
		brokenFruit = loadTexture("brokenFruit", false);
		herbage = loadTexture("herbage", false);
		herbageFruit = loadTexture("herbageFruit", false);
		itemSlot = loadTexture("itemSlot", false);
		button = loadTexture("button", false);
		textbar = loadTexture("textbar", false);
		loaded = true;
	}

	/**
	 * L�dt Texturen f�r das erste Level.
	 */
	public static void loadLevel0() {

		if (loadedLevel0) {
			return;
		}
		background0 = loadTextures("background0", 3, false);
		foreground0 = loadTexture("foreground0", false);
		foxSitting = loadTexture("foxSitting", false);
		foxStanding = loadTexture("foxStanding", false);
		foxWalking = loadTextures("foxWalking", 6, false);
		apeSitting = loadTexture("apeSitting", false);
		apeSittingWithout = loadTexture("apeSittingWithout", false);
		apeThrowing = loadTextures("apeThrowing", 6, false);
		herbagePlant = loadTexture("herbage", false);
		cutscene0 = loadTexture("cutscene0", false);
		cutscene1 = loadTexture("cutscene1", false);
		cutscene2 = loadTexture("cutscene2", false);
		tutorial = loadTextures("tutorial", 17, false);
		loadedLevel0 = true;
	}

	/**
	 * L�dt eine Textur.
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
			texture = TextureLoader.getTexture(((compressed && compressedTextures) ? "JPG" : "PNG"), ResourceLoader.getResourceAsStream(name + ((compressed && compressedTextures) ? ".jpg" : ".png")));
		} catch (Exception e) {
			try {
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("missingTexture.png"));
			} catch (IOException e1) {
			}
			System.err.println("Image not found: " + name + ((compressed && compressedTextures) ? ".jpg" : ".png"));
		}
		return texture;
	}

	/**
	 * L�dt mehrere Textur.
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
	 *            H�he
	 */
	public static void renderQuad(Texture texture, float x, float y, float w, float h) {

		glEnable(GL_TEXTURE_2D);
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
	 * Zeichnet ein gef�rbtes Rechteck
	 * 
	 * @param x
	 *            Start X
	 * @param y
	 *            Start Y
	 * @param w
	 *            Breite
	 * @param h
	 *            H�he
	 * @param r
	 *            Rot
	 * @param g
	 *            Gr�n
	 * @param b
	 *            Blau
	 * @param a
	 *            Alpha
	 */
	public static void renderColoredQuad(float x, float y, float w, float h, float r, float g, float b, float a) {

		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		glColor4f(r, g, b, a);
		glVertex2f(x, y);
		glVertex2f(x, y + h);
		glVertex2f(x + w, y + h);
		glVertex2f(x + w, y);
		glColor4f(1, 1, 1, 1);
		glEnd();
		glEnable(GL_TEXTURE_2D);
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
	 *            H�he
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
	 *            H�he
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

	/**
	 * Setzt die Zeichenfarbe.
	 * 
	 * @param r
	 *            Rot
	 * @param g
	 *            Gr�n
	 * @param b
	 *            Blau
	 * @param a
	 *            Alpha
	 */
	public static void setColor(float r, float g, float b, float a) {

		glColor4f(r, g, b, a);
	}
}
