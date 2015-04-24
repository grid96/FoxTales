package main;

import java.awt.GraphicsEnvironment;

import org.newdawn.slick.Font;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 * Schrift Verwaltung
 * 
 * @author Colin Dömer
 */
public class Fonts {

	public static final int PLAIN = java.awt.Font.PLAIN;
	public static final int BOLD = java.awt.Font.BOLD;
	public static final int ITALIC = java.awt.Font.ITALIC;
	public static Font arial12;
	public static Font sfr24, sfr48;

	/**
	 * Lädt Schriftarten.
	 */
	public static void load() {

		createFont("ShrimpFriedRiceNo1");
		
		arial12 = loadFont("Arial", PLAIN, 12);
		sfr24 = loadFont("ShrimpFriedRiceNo1", PLAIN, 24);
		sfr48 = loadFont("ShrimpFriedRiceNo1", PLAIN, 48);
	}

	/**
	 * Fügt automatisch Zeilenumbrüche ein.
	 * 
	 * @param font
	 *            Schrift
	 * @param input
	 *            Ausgangstext
	 * @param width
	 *            maximale Breite
	 * @return formatierte Text
	 */
	public static String wrap(Font font, String input, float width) {

		String output = "";
		String word = "";
		float length = 0;
		for (int i = 0; i < input.length(); i++) {
			if (i < input.length() - 1 && input.substring(i, i + 1).equals("\n")) {
				output += word;
				output += "\n";
				word = "";
				length = 0;
			} else {
				length += font.getWidth(input.charAt(i) + "");
				if (input.charAt(i) == ' ') {
					output += word + " ";
					word = "";
				} else {
					word += input.charAt(i);
					if (length > width) {
						output += "\n";
						length = font.getWidth(word);
					}
				}
			}
		}
		output += word + " ";
		return output;
	}

	/**
	 * Zählt die zeilen eines Textes.
	 * 
	 * @param input
	 *            Ausgangstext
	 * @return Anzahl Zeilen
	 */
	public static int lines(String input) {

		return input.split("\n").length;
	}

	/**
	 * Installiert eine Schriftart. Benötigt Adminrechte!
	 * 
	 * @param name
	 *            Name der Schriftart
	 */
	public static void createFont(String name) {

		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			java.awt.Font font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream("/" + name + ".ttf"));
			ge.registerFont(font);
		} catch (Exception e) {
		}
	}

	/**
	 * Lädt eine Schirft mit fester Größe.
	 * 
	 * @param name
	 *            Name der Schriftart
	 * @param style
	 *            Kursiv oder Fett
	 * @param size
	 *            Schriftgröße
	 * @return Geladene Schriftart
	 */
	@SuppressWarnings("unchecked")
	public static Font loadFont(String name, int style, int size) {

		try {
			UnicodeFont font = new UnicodeFont(new java.awt.Font(name, style, size));
			font.getEffects().add(new ColorEffect(java.awt.Color.white));
			font.addGlyphs(32, 127);
			font.addGlyphs("üäÄöÖÜß");
			font.loadGlyphs();
			return font;
		} catch (Exception e) {
			return null;
		}
	}
}
