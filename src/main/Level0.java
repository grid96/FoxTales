package main;

/**
 * First Level
 * 
 * @author Colin Dömer
 */
public class Level0 extends Level {

	public FlyingStone stone;
	
	public Level0() {
		
		background = Textures.background0;
		foreground = Textures.foreground0;
		width = 40;
		height = 20;
		
		HerbagePlant plant = new HerbagePlant(29.5f, 13, 4, 2, this);
		// plant.w = 1;
		// plant.h = 1;
		entities.add(plant);
		entities.add(new Ape(35, 2.5f, this));
		dropFruit();
	}
	
	public void dropFruit() {
		
		entities.add(new DroppedItem(new Fruit(), 17.5f, 12, 4f, 1.5f, this));
	}
	
	public void setFlyingStone(FlyingStone stone) {
		
		entities.add(stone);
		this.stone = stone;
	}
	
	public FlyingStone getFlyingStone() {
		
		return stone;
	}
}
