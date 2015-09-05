package main;

import org.newdawn.slick.opengl.Texture;

import static main.Main.*;

/**
 * Tutorial
 * 
 * @author Colin Dömer
 *
 */
public class Tutorial {

	public static Tutorial ths;
	public Texture[][] pages = new Texture[8][];
	public int page = 0;
	public int updates0, updates1;
	
	/**
	 * lädt die verschiedenen Tutorial Seiten
	 */
	public Tutorial() {
		
		ths = this;
		pages[0] = new Texture[]{Textures.tutorial[0]};
		pages[1] = new Texture[]{Textures.tutorial[1], Textures.tutorial[2], Textures.tutorial[3]};
		pages[2] = new Texture[]{Textures.tutorial[4], Textures.tutorial[5]};
		pages[3] = new Texture[]{Textures.tutorial[6], Textures.tutorial[7]};
		pages[4] = new Texture[]{Textures.tutorial[8], Textures.tutorial[9]};
		pages[5] = new Texture[]{Textures.tutorial[10], Textures.tutorial[11], Textures.tutorial[12]};
		pages[6] = new Texture[]{Textures.tutorial[13], Textures.tutorial[14], Textures.tutorial[15]};
		pages[7] = new Texture[]{Textures.tutorial[16]};
		updates0 = updates;
		if (!tutorial) {
			page = 8;
		}
	}
	
	/**
	 * schaltet die erste und letzte, zeitlich limitierte Seite um
	 */
	public void update() {
		
		if (page == 0 && updates - updates0 > 480) {
			page++;
		}
		if (page == 7 && updates - updates1 > 300) {
			page++;
		}
	}
	
	/**
	 * rendert die Tutorial Seite
	 */
	public void render() {
		
		if (page >= 0 && page < pages.length) {
			Textures.renderQuad(pages[page][(updates / 90) % pages[page].length], 32, 32, 1024, 512);
		}
	}
	
	/**
	 * schaltet von der 2. auf die 3. Seite um
	 */
	public static void move() {
		
		if (ths.page == 1) {
			ths.page++;
		}
	}
	
	/**
	 * schaltet von der 3. auf die 4. Seite um
	 */
	public static void look() {
		
		if (ths.page == 2) {
			ths.page++;
		}
	}
	
	/**
	 * schaltet von der 4. auf die 5. Seite um
	 */
	public static void talk() {
		
		if (ths.page == 3) {
			ths.page++;
		}
	}
	
	/**
	 * schaltet von der 5. auf die 6. Seite um
	 */
	public static void take() {
		
		if (ths.page == 4) {
			ths.page++;
		}
	}
	
	/**
	 * schaltet von der 6. auf die 7. Seite um
	 */
	public static void craft() {
		
		if (ths.page == 5) {
			ths.page++;
		}
	}
	
	/**
	 * schaltet von der 7. auf die 8. Seite um
	 */
	public static void give() {
		
		if (ths.page == 6) {
			ths.page++;
			ths.updates1 = updates;
		}
	}
}
