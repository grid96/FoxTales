package main;

import org.newdawn.slick.opengl.Texture;

/**
 * Item
 * 
 * @author Colin Dömer
 */
public abstract class Item {

	public Texture texture;
	public int id;
	public ItemContainer container;
	public String take, look, talk;

	public Item(Texture texture, int id) {

		this.texture = texture;
		this.id = id;
	}

	/**
	 * Craftet 2 Items zusammen
	 * 
	 * @param item
	 *            Item
	 */
	public void craft(Item item) {

	}
}
