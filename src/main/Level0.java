package main;

/**
 * First Level
 * 
 * @author Colin Dömer
 */
public class Level0 extends Level {

	public Level0() {
		
		texture = Textures.testlevel;
		width = 80;
		height = 20;
		
		entities.add(new HerbagePlant(50, 17, this));
		entities.add(new DroppedItem(new Fruit(), 65, 16, this));
		entities.add(new Ape(75, 17.5f, this));
	}
}
