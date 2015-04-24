package main;

import org.newdawn.slick.opengl.Texture;

public class Item implements Interactive {

	public static Item stone = new Item(Textures.stone);
	public static Item fruit = new Item(Textures.fruit);
	public static Item brokenFruit = new Item(Textures.brokenFruit);
	public static Item herbage = new Item(Textures.herbage);
	public static Item herbageFruit = new Item(Textures.herbageFruit);
	
	public Texture texture;
	
	public Item(Texture texture) {
		
		this.texture = texture;
	}
}
