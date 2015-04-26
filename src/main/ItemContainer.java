package main;

import java.util.Vector;

public class ItemContainer {

	public Vector<Item> items;

	public ItemContainer() {
		
		items = new Vector<>();
	}
	
	public void add(Item item) {
		
		items.add(item);
		item.container = this;
	}
	
	public void remove(Item item) {
		
		items.remove(item);
		item.container = null;
	}
	
	public int indexOf(Item item) {
		
		return items.indexOf(item);
	}
	
	public Item get(int i) {
		
		return items.get(i);
	}
	
	public int size() {
		
		return items.size();
	}
}
