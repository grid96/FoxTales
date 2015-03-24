package main;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

/**
 * Basic Level
 * 
 * @author Colin Dömer
 */
public abstract class Level {

	public float width, height;
	public Texture texture;
	
	public void update() {
		
	}
	
	public void render() {
		
		glEnable(GL_TEXTURE_2D);
		Textures.renderQuad(texture, 0, 0, width, height);
	}
}
