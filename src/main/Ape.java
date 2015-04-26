package main;

import static main.Main.*;

public class Ape extends Entity {

	public Ape(float x, float y, Level level) {
		
		super(Textures.apeSitting, 7, x, y, 4, 4, level);
	}
	
	public void click() {
	
		if (random() < 0.5f) {
			Game.ths.think("Das ist ein dreckiger stinkender Affe.");
		} else {
			Game.ths.think("Wer weiß, wann er sich das letzte Mal gewaschen hat.");
		}
	}
}
