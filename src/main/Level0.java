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
		
		entities.add(new DroppedItem(new Stone(), 20, 17, this));
		entities.add(new DroppedItem(new Herbage(), 50, 17, this));
		entities.add(new DroppedItem(new Fruit(), 65, 16, this));
		entities.add(new Ape(75, 18, this));
	}
}
