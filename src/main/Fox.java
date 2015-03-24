package main;

import org.newdawn.slick.opengl.Texture;

/**
 * Fuchs
 * 
 * @author Colin Dömer
 */
public class Fox {
	
	public float x = 40, y = 17.5f;
	public Texture texture;
	
	public Fox() {
	
		texture = Textures.testfox;
	}
	
	public void update() {
		
	}
	
	public void render() {
		
		Textures.renderQuad(texture, x - 2.5f, y - 2.5f, 5, 2.5f);
	}
}
