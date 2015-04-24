package main;

import org.newdawn.slick.opengl.Texture;

public class Entity implements Interactive {

	public Texture texture;
	public float x, y;
	public float w, h;
	
	public Entity(Texture texture, float x, float y, float w, float h) {
		
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
}
