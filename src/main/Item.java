package main;

import org.newdawn.slick.opengl.Texture;

public class Item implements Interactive {
	
	public Texture texture;
	public int id;
	
	public Item(Texture texture, int id) {
		
		this.texture = texture;
		this.id = id;
	}

	public int getID() {
		
		return id;
	}
}
