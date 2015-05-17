package main;

import org.newdawn.slick.opengl.Texture;

import static main.Main.*;

public class Tutorial {

	public static Tutorial ths;
	public Texture[][] pages = new Texture[8][];
	public int page = 0;
	public int updates0, updates1;
	
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
	
	public void update() {
		
		if (page == 0 && updates - updates0 > 480) {
			page++;
		}
		if (page == 7 && updates - updates1 > 300) {
			page++;
		}
	}
	
	public void render() {
		
		if (page >= 0 && page < pages.length) {
			Textures.renderQuad(pages[page][(updates / 90) % pages[page].length], 32, 32, 1024, 512);
		}
	}
	
	public static void move() {
		
		if (ths.page == 1) {
			ths.page++;
		}
	}
	
	public static void look() {
		
		if (ths.page == 2) {
			ths.page++;
		}
	}
	
	public static void talk() {
		
		if (ths.page == 3) {
			ths.page++;
		}
	}
	
	public static void take() {
		
		if (ths.page == 4) {
			ths.page++;
		}
	}
	
	public static void craft() {
		
		if (ths.page == 5) {
			ths.page++;
		}
	}
	
	public static void give() {
		
		if (ths.page == 6) {
			ths.page++;
			ths.updates1 = updates;
		}
	}
}
