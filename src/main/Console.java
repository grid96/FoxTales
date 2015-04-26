package main;

import java.util.Vector;

@SuppressWarnings("unused")
public class Console {

	private Text text = new Text(Fonts.arial12, "test");
	private Vector<Text> texts = new Vector<>();
	private boolean focus;
	private boolean visible;
	private int x, y;
	private int width, height;
	private float scrollPos;
	private int scrollMax;
	private int inputHeight;

	public Console(int x, int y, int width, int height) {

		setBounds(x, y, width, height);
	}

	public void update() {

	}

	public void render() {

		Textures.renderColoredQuad(x, y, width, height - 30, 0, 0, 0, 0.25f);
		Textures.renderColoredQuad(x, y + height - 20, width, 20, 0, 0, 0, 0.25f);
		text.render(x + 5, y + height - 20);
	}

	public void addMessage(String text, int color) {

		Text t = new Text(Fonts.arial12, text, width - 20);
		t.setColor(color);
		texts.add(t);
	}

	public void isFocus(boolean focus) {

		this.focus = focus;
	}

	public void isVisible(boolean visible) {

		this.visible = visible;
	}

	public void setBounds(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}
