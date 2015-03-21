package main;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;

import static main.Main.*;
import static org.lwjgl.opengl.GL11.*;

public class Game extends Thread {

	public Screen screen;

	public Game() throws LWJGLException {

		new Thread(this).start();
	}

	public void init() throws LWJGLException {

		Display.setTitle("Fox Tales");

		Display.setFullscreen(fullscreen);
		if (!fullscreen) {
			Display.setDisplayMode(new DisplayMode(width, height));
		}
		width = Display.getDisplayMode().getWidth();
		height = Display.getDisplayMode().getHeight();

		Display.setVSyncEnabled(vsync);

		// try {
		// Display.setIcon(new ByteBuffer[] { new ImageIOImageData().imageToByteBuffer(ImageIO.read(Textures.class.getResourceAsStream("/icon16.png")), false, false, null), new ImageIOImageData().imageToByteBuffer(ImageIO.read(Textures.class.getResourceAsStream("/icon32.png")), false, false, null) });
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		Display.create();
		Keyboard.create();
		Mouse.create();

		// if (mute) {
		// Sounds.volumeSound = 0;
		// Sounds.volumeMusic = 0;
		// }

		// Textures.loadTextures();
		// Cursors.loadCursors();
		// Sounds.loadSounds();

		// Mouse.setNativeCursor(Cursors.def);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		// if (shortcut) {
		// screen = new Singleplayer();
		// } else {
		// screen = new Mainmenu();
		// }

		// Sounds.play(Sounds.music);
	}

	public void update() {

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			destroy();
		}
		// screen.update();
	}

	public void render() {

		// screen.render();
	}

	public void run() {

		try {
			init();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		long t0 = System.nanoTime();
		long t = System.nanoTime();
		long ms = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double d = 0;
		int f = 0;
		int u = 0;
		while (!Display.isCloseRequested()) {
			t = System.nanoTime();
			d += (t - t0) / ns;
			t0 = t;
			while (d >= 1) {
				update();
				u++;
				d--;
			}
			render();
			f++;
			frames++;
			if (System.currentTimeMillis() - ms > 1000) {
				ms += 1000;
				System.out.println(fps + " fps, " + ups + " ups");
				fps = f;
				ups = u;
				u = 0;
				f = 0;
			}
		}
		destroy();
	}

	public void destroy() {

		Mouse.destroy();
		Keyboard.destroy();
		Display.destroy();
		AL.destroy();
		System.exit(0);
	}
}
