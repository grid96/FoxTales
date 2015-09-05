package main;

import static main.Main.*;

/**
 * fliegender Stein (kann theoretisch in der Luft gefangen werden)
 * 
 * @author Colin Dömer
 *
 */
public class FlyingStone extends DroppedItem {

	public int updates0;
	
	public FlyingStone(Stone stone, float x, float y, float w, float h, Level level) {
		
		super(stone, x, y, w, h, level);
		updates0 = updates;
	}
	
	/**
	 * bewegt den Stein anhand seiner Flugkurve
	 */
	public void update() {
	
		if(updates - updates0 < 60) {
			x = 36 - 11 * (updates - updates0) / 60f;
			y = 0.161168f * x * x - 11.0235f * x + 188.358f;
			w = 0.25f + 0.25f * (updates - updates0) / 60f;
			h = w;
		} else {
			x = 25;
			y = 13.5f;
			w = 0.5f;
			h = w;
		}
		if (updates - updates0 == 35) {
			Sounds.play(Sounds.stone);
		}
	}

	public void render() {
		
		super.render();
		if (level.isVisible(this)) {
			Textures.renderQuad(texture, level.getNextX(x), y, w, h);
		}
	}
}
