package main;

public class FlyingStone extends DroppedItem {

	public FlyingStone(Stone stone, float x, float y, float w, float h, Level level) {
		
		super(stone, x, y, w, h, level);
	}

	public void render() {
		
		super.render();
		if (level.isVisible(this)) {
			Textures.renderQuad(texture, level.getNextX(x), y, w, h);
		}
	}
}
