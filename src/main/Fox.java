package main;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import static main.Main.*;

/**
 * Fuchs
 * 
 * @author Colin D�mer
 */
public class Fox {

	public Level level;
	public float x = 40, y = 15f;
	public float vx, vy, ax, ay;
	public boolean onGround;
	public boolean mirrored;
	public Texture texture;

	public Fox(Level level) {

		this.level = level;
		texture = Textures.testfox;
	}

	public void update() {

		ax = 0;
		ay = 0.01f;
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if (onGround) {
				vy = -0.2f;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			if (vx > -0.1f) {
				ax -= 0.02f;
				if (vx < -0.1f) {
					vx = -0.1f;
				}
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			if (vx < 0.1f) {
				ax += 0.02f;
				if (vx > 0.1f) {
					vx = 0.1f;
				}
			}
		}
		if (!Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_D)) {
			if (vx > 0) {
				ax -= 0.01f;
				if (vx < 0.01f) {
					ax = -vx;
				}
			}
			if (vx < 0) {
				ax += 0.01f; 
				if (vx > -0.01f) {
					ax = -vx;
				}
			}
		}

		vx += ax;
		vy += ay;

		if (vx > 0) {
			mirrored = false;
		}
		if (vx < 0) {
			mirrored = true;
		}

		float x = this.x - 2.5f;
		float y = this.y - 2.5f;
		float w = 5;
		float h = 2.5f;
		if (!level.collision(x + vx, y, w, h)) {
			this.x += vx;
		} else {
			for (float dx = vx / 2f; abs(dx) > 0.01f; dx /= 2f) {
				if (!level.collision(x + dx, y, w, h)) {
					this.x += dx;
					x = this.x - 2.5f;
				}
			}
			vx = 0;
		}
		if (!level.collision(x, y + vy, w, h)) {
			this.y += vy;
			onGround = false;
		} else {
			for (float dy = vy / 2f; abs(dy) > 0.01f; dy /= 2f) {
				if (!level.collision(x, y + dy, w, h)) {
					this.y += dy;
					y = this.y - 2.5f;
				}
			}
			vy = 0;
			onGround = true;
		}
	}

	public void render() {

		if (mirrored) {
			Textures.renderQuadMirrored(texture, x - 2.5f, y - 2.5f, 5, 2.5f);
		} else {
			Textures.renderQuad(texture, x - 2.5f, y - 2.5f, 5, 2.5f);
		}
	}
}