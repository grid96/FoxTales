package main;

import static main.Main.*;

public class PickUp extends Script {

	public DroppedItem item;
	
	public PickUp(DroppedItem item) {
		
		this.item = item;
		foxX = item.x;
	}
	
	public void update() {
		
		if (abs(Game.ths.fox.x - foxX) < 0.001f) {
			Game.ths.inventory.add(item.item);
			Game.ths.script = null;
		}
	}
}
