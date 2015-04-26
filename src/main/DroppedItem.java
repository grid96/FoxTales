package main;

public class DroppedItem extends Entity {

	public Item item;
	
	public DroppedItem(Item item, float x, float y, Level level) {
		
		super(item.texture, 6, x, y, 1, 1, level);
		this.item = item;
	}
	
	public void click() {
		
		Game.ths.inventory.add(((DroppedItem) Game.ths.mouseOver).item);
		level.entities.remove(Game.ths.mouseOver);
	}
}
