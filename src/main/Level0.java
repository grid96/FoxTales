package main;

/**
 * First Level
 * 
 * @author Colin Dömer
 */
public class Level0 extends Level {

	public Level0() {
		
		background = Textures.background0;
		foreground = Textures.foreground0;
		width = 40;
		height = 20;
		
		HerbagePlant plant = new HerbagePlant(10, 13, this);
		plant.w = 1;
		plant.h = 1;
		entities.add(plant);
		entities.add(new Ape(35, 8.5f, this));
		dropFruit();
	}
	
	public void dropFruit() {
		
		entities.add(new DroppedItem(new Fruit(), 20, 13, this));
	}
}
