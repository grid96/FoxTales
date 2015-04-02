package main;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;

import static main.Main.*;

/**
 * Hauptanwendung
 * 
 * @author Colin Dˆmer
 */
public class FoxTales extends Thread {

	public Screen screen;
	public long time;

	/**
	 * Startet einen neuen Thread.
	 */
	public FoxTales() {

		new Thread(this).start();
	}

	/**
	 * Erstellt das Display, l‰dt Texturen und Sounds.
	 * 
	 * @throws LWJGLException
	 *             OpenGL konnte nicht initialisiert werden.
	 */
	public void init() throws LWJGLException {

		Display.setTitle("Inari");

		Display.setFullscreen(fullscreen);
		if (!fullscreen) {
			Display.setDisplayMode(new DisplayMode(width, height));
		}
		width = Display.getDisplayMode().getWidth();
		height = Display.getDisplayMode().getHeight();

		Display.setVSyncEnabled(vsync);

		loadDisplayIcon();

		Display.create();
		Keyboard.create();
		Mouse.create();

		if (mute) {
			Sounds.setSoundVolume(0);
			Sounds.setMusicVolume(0);
		}

		time = System.currentTimeMillis();
		System.out.println("Loading Textures ... ");
		Textures.load();
		System.out.println(" completed in " + (System.currentTimeMillis() - time) + " ms");
		time = System.currentTimeMillis();
		System.out.println("Loading Cursors ... ");
		Cursors.load();
		System.out.println(" completed in " + (System.currentTimeMillis() - time) + " ms");
		time = System.currentTimeMillis();
		System.out.println("Loading Fonts ... ");
		Fonts.load();
		System.out.println(" completed in " + (System.currentTimeMillis() - time) + " ms");
		time = System.currentTimeMillis();
		System.out.println("Loading Sounds ... ");
		Sounds.load();
		System.out.println(" completed in " + (System.currentTimeMillis() - time) + " ms");

		Mouse.setNativeCursor(Cursors.standard);

		if (shortcut) {
			setScreen(new Game());
		} else {
			setScreen(new Game());
		}
	}

	/**
	 * L‰dt das Fensters Icon.
	 */
	public void loadDisplayIcon() {

		try {
			Display.setIcon(new ByteBuffer[] { new ImageIOImageData().imageToByteBuffer(ImageIO.read(Textures.class.getResourceAsStream("/icon16.png")), false, false, null), new ImageIOImageData().imageToByteBuffer(ImageIO.read(Textures.class.getResourceAsStream("/icon32.png")), false, false, null) });
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auswerten der Eingaben und updaten der Anzeige.
	 */
	public void update() {

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			destroy();
		}
		screen.update();
	}

	/**
	 * Rendert das Bild.
	 */
	public void render() {

		screen.render();
	}

	/**
	 * Gameloop
	 */
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
				updates++;
				d--;
			}
			render();
			f++;
			frames++;
			if (System.currentTimeMillis() - ms > 1000) {
				ms += 1000;
				fps = f;
				ups = u;
				u = 0;
				f = 0;
				System.out.println(fps + " fps, " + ups + " ups");
			}
		}
		destroy();
	}

	public void setScreen(Screen screen) {

		this.screen = screen;
	}

	/**
	 * Schlieﬂt das Fenster und beendet das Spiel.
	 */
	public void destroy() {

		Mouse.destroy();
		Keyboard.destroy();
		Display.destroy();
		AL.destroy();
		System.out.println("Inari stopped " + LocalDateTime.now());
		System.exit(0);
	}
}
