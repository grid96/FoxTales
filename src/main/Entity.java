package main;

import org.newdawn.slick.opengl.Texture;

public class Entity implements Interactive {

	// public static Entity fruit = new Entity(Textures.fruit, 6, 35, 15, 1, 1);
	// public static Entity stone = new Entity(Textures.stone, 7, 35, 15, 1, 1);
	// public static Entity herbage = new Entity(Textures.herbage, 8, 35, 15, 1, 1);
	
	public Level level;
	public Texture texture;
	public AlphaMap alphaMap;
	public float x, y;
	public float w, h;
	public int id;
	
	public Entity(Texture texture, int id, float x, float y, float w, float h, Level level) {
		
		this.texture = texture;
		this.alphaMap = AlphaMap.getAlphaMap(texture);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.level = level;
	}
	
	public void update() {
		
	}
	
	public void render() {
	
		if (level.isVisible(this)) {
			Textures.renderQuad(texture, level.getNextX(x) - w / 2, y - h, w, h);
		}
	}
	
	public void click() {
		
	}
	
	public void interact(Item item) {
		
	}
	
	public int getID() {
		
		return id;
	}
	
	public boolean isMouseOver() {
		
		if (level.getNextX(Game.ths.getMouseX()) > level.getNextX(x) - w / 2 && level.getNextX(Game.ths.getMouseX()) < level.getNextX(x) + w / 2 && Game.ths.getMouseY() > y - h && Game.ths.getMouseY() < y) {
			if (alphaMap.hit((level.getNextX(Game.ths.getMouseX()) - (level.getNextX(x) - w / 2)) / w, (Game.ths.getMouseY() - (y - h)) / h)) {
				Game.ths.mouseOver = this;
				mouseOver();
				return true;
			}
		}
		return false;
	}
	
	public void mouseOver() {

	}
}
