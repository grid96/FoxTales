package main;

import static org.lwjgl.opengl.GL11.glColor3f;

import org.newdawn.slick.Font;

public class ColoredText extends Text {

	public float r, g, b;
	
	public ColoredText(Font font, String text, int limit, int color) {
		
		super(font, text, limit);
	}
	
	public void renderPart(float x, float y, float x0, float x1, float y0, float y1) {
		
		glColor3f(r, g, b);
		super.renderPart(x, y, x0, x1, y0, y1);
	}
}
