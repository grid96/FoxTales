package main;

public class ItemContainer {

	public ItemSlot[] slots;
	
	public ItemContainer(int size) {
		
		slots = new ItemSlot[size];
	}
	
	public boolean add(Item item) {
		
		return add(new ItemSlot(item));
	}
	
	public boolean add(ItemSlot slot) {
		
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] == null) {
				slots[i] = slot;
				return true;
			}
		}
		return false;
	}
	
	public boolean remove(Item item) {
		
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] != null && slots[i].item == item) {
				slots[i] = null;
				return true;
			}
		}
		return false;
	}
	
	public boolean remove(ItemSlot slot) {
		
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] == slot) {
				slots[i] = null;
				return true;
			}
		}
		return false;
	}
	
	public int size() {
		
		return slots.length;
	}
	
	public ItemSlot get(int i) {
		
		if (i < size()) {
			return slots[i];
		}
		return null;
	}
}
