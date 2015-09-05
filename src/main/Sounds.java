package main;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.openal.SoundStore;

import static main.Main.*;

/**
 * Sounds und Musik Verwaltung
 * 
 * @author Colin Dömer
 */
public class Sounds {

	private static float soundVolume = 1.0f;
	private static float musicVolume = 0.2f;
	public static boolean loaded, loadedLevel0;

	public static Sound ape, fruit, stone, herbage, water, intro;
	public static Music titlemusic, music0;

	/**
	 * Lädt levelunabhängige Sounds.
	 */
	public static void load() {

		if (loaded) {
			return;
		}
		setMusicVolume(musicVolume);
		setSoundVolume(soundVolume);
		titlemusic = loadMusic("titlemusic", false);
		loaded = true;
	}

	/**
	 * Lädt Texturen für das erste Level.
	 */
	public static void loadLevel0() {

		if (loadedLevel0) {
			return;
		}
		music0 = loadMusic("music0", false);
		ape = loadSound("ape", false);
		fruit = loadSound("fruit", false);
		stone = loadSound("stone", false);
		herbage = loadSound("herbage", false);
		water = loadSound("water", false);
		intro = loadSound("intro", false);
		loadedLevel0 = true;
	}

	/**
	 * Lädt Musik.
	 * 
	 * @param name
	 *            Pfadname zur Sounddatei
	 * @param compressed
	 *            OGG oder WAV
	 * @return geladene Musik
	 */
	public static Music loadMusic(String name, boolean compressed) {

		Music music = null;
		try {
			music = new Music(name + ((compressed && compressedSounds) ? ".ogg" : ".wav"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return music;
	}

	/**
	 * Lädt einen Sound.
	 * 
	 * @param name
	 *            Pfadname zur Sounddatei
	 * @param compressed
	 *            OGG oder WAV
	 * @return geladener Sound
	 */
	public static Sound loadSound(String name, boolean compressed) {

		Sound sound = null;
		try {
			sound = new Sound(name + ((compressed && compressedSounds) ? ".ogg" : ".wav"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return sound;
	}

	/**
	 * Lädt mehrere Sounds.
	 * 
	 * @param name
	 *            Pfadname zur den Sounddateien
	 * @param n
	 *            Anzahl an Sounds
	 * @param compressed
	 *            OGG oder WAV
	 * @return geladene Sounds
	 */
	public static Sound[] loadSounds(String name, int n, boolean compressed) {

		Sound[] sounds = new Sound[n];
		for (int i = 0; i < n; i++) {
			sounds[i] = loadSound(name + i, compressed);
		}
		return sounds;
	}

	/**
	 * Spielt Musik in einem Loop ab.
	 * 
	 * @param music
	 *            abzuspielende Musik
	 */
	public static void play(Music music) {

		if (music != null) {
			music.loop();
			music.setVolume(musicVolume);
		}
	}

	/**
	 * Spielt einen zufälligen Sound ab.
	 * 
	 * @param sounds
	 *            abspielbare Sounds
	 */
	public static void play(Sound[] sounds) {

		if (sounds != null) {
			play(sounds[(int) (sounds.length * random())]);
		}
	}

	/**
	 * Spielt einen Sound ab.
	 * 
	 * @param sound
	 *            abzuspielender Sound
	 */
	public static void play(Sound sound) {

		if (sound != null) {
			sound.play();
		}
	}

	/**
	 * Stoppt einen Sound.
	 * 
	 * @param sound
	 *            zu stoppender Sound
	 */
	public static void stop(Sound sound) {

		if (sound != null) {
			sound.stop();
		}
	}

	/**
	 * setzt die Soundlautstärke
	 * 
	 * @param volume
	 *            Pegel
	 */
	public static void setSoundVolume(float volume) {

		soundVolume = volume;
		SoundStore.get().setSoundVolume(soundVolume);
	}

	/**
	 * setzt die Musiklautstärke
	 * 
	 * @param volume
	 *            Pegel
	 */
	public static void setMusicVolume(float volume) {

		musicVolume = volume;
	}
}
