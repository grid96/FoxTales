package main;

public class DroppedItem extends Entity {

	public Item item;
	
	public DroppedItem(Item item, float x, float y, Level level) {
		
		super(item.texture, 6, x, y, 0.5f, 0.5f, level);
		this.item = item;
		look = item.look;
		take = item.take;
		talk = item.talk;
	}
	
	public void click() {
		
		Game.ths.inventory.add(((DroppedItem) Game.ths.mouseOver).item);
		level.entities.remove(Game.ths.mouseOver);
	}
	
	public void take() {
		
		super.take();
		Game.ths.inventory.add(item);
		level.entities.remove(Game.ths.mouseOver);
	}
	
	public void give(Item item) {
		
		take();
	}
}
