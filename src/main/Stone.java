package main;

/**
 * Stein
 * 
 * @author Colin D�mer
 *
 */
public class Stone extends Item {

	public Stone() {
		
		super(Textures.stone, 2);
		take = "";
		look = "Ein Stein...";
		talk = "Guten Tag Stein. Wie war der Flug?";
	}
}
