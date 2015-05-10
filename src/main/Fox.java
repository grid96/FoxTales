package main;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import static main.Main.*;

/**
 * Fuchs
 * 
 * @author Colin Dömer
 */
public class Fox {

	public Level level;
	public float x = 1.25f, y = 14.5f;
	public float w = 5, h = 5;
	public float vx, vy, ax, ay;
	public boolean onGround;
	public boolean mirrored;
	public Texture texture;
	public int idle = 300;

	public Fox(Level level) {

		this.level = level;
		texture = Textures.foxSitting;
	}

	public void update() {

		// System.out.println((int) (100 * x) / 100f + " " + (int) (100 * level.getNextX(20)) / 100f);

		ax = 0;
		ay = 0.01f;
		boolean right = false;

		if (Game.ths.script == null) {
			// if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			// if (onGround) {
			// vy = -0.2f;
			// }
			// }
			if (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				if (vx > -0.1f) {
					ax -= 0.02f;
					if (vx < -0.1f) {
						vx = -0.1f;
					}
				}
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
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
		} else {
			if (Game.ths.script.foxX < x) {
				if (vx > -0.1f) {
					ax -= 0.02f;
					if (vx < -0.1f) {
						vx = -0.1f;
					}
				}
			}
			if (Game.ths.script.foxX > x) {
				if (vx < 0.1f) {
					ax += 0.02f;
					if (vx > 0.1f) {
						vx = 0.1f;
					}
				}
				right = true;
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

		if (vx == 0) {
			idle++;
		} else {
			idle = 0;
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

		if (Game.ths.script != null) {
			if ((Game.ths.script.foxX > x && !right) || (Game.ths.script.foxX < x && right)) {
				x = Game.ths.script.foxX;
				vx = 0;
			}
		}
	}

	public void render() {

		getTexture();
		if (mirrored) {
			Textures.renderQuadMirrored(texture, x - w / 2, y - h, w, h);
		} else {
			Textures.renderQuad(texture, x - w / 2, y - h, w, h);
		}
	}

	public void getTexture() {

		if (idle >= 240) {
			texture = Textures.foxSitting;
		} else {
			// if (idle > 0) {
			texture = Textures.foxStanding;
			// } else {
			// texture = Textures.foxWalking[(updates / 10) % Textures.foxWalking.length];
			// }
		}
	}
}
