package main;

public class DroppedItem extends Entity {

	public Item item;
	
	public DroppedItem(Item item, float x, float y, float w, float h, Level level) {
		
		super(item.texture, 6, x, y, w, h, level);
		this.item = item;
	}
	
	public void clicked() {
		
		
	}
}
