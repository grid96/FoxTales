package main;

import static main.Main.*;

public class Thought extends Particle {

	public Text text;
	public float a = 1;
	
	public Thought(String text, float x, float y, Level level) {
		
		super(x, y, level);
		this.text = new Text(Fonts.sfr24, text);
		this.text.setColor(0x000000);
		time = 300;
	}
	
	public void update() {
		
		super.update();
		y -= 0.0025f;
		if (time < 90) {
			a = time / 90f;
		}
		if (time <= 0) {
			text.destroy();
		}
	}
	
	public void render() {
		
		text.setAlpha(a);
		text.renderCenter(x, y, 0, 10f * text.width / width * resolution, 10f * text.height / height, 0, 0, 0);
		Textures.setColor(1, 1, 1, 1);
	}
}
