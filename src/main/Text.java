package main;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.util.glu.GLU.gluOrtho2D;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL12;
import org.newdawn.slick.Font;

/**
 * Visueller Textbuffer
 * 
 * @author Colin Dömer
 */
public class Text {

	public Font font;
	public String text;
	public int width, height;
	public int limit;

	public int frameBuffer = -1;
	public int texture = -1;
	public int renderBuffer = -1;

	public Text(Font font, String text) {

		this.font = font;
		this.text = text;
		width = font.getWidth(text);
		height = font.getLineHeight() * Fonts.lines(text);
		init();
	}

	public Text(Font font, String text, int limit) {

		this.font = font;
		this.text = text;
		this.limit = limit;
		width = limit;
		height = font.getLineHeight() * Fonts.lines(text);
		init();
	}

	/**
	 * Erstellt eine Textur mit dem Text mittels eines Framebuffers.
	 */
	public void init() {

		frameBuffer = glGenFramebuffers();
		texture = glGenTextures();
		renderBuffer = glGenRenderbuffers();

		glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer);

		glBindTexture(GL_TEXTURE_2D, texture);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

		glTexParameteri(GL_TEXTURE_2D, GL_GENERATE_MIPMAP, GL_TRUE);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, main.Main.width, main.Main.height, 0, GL_RGBA, GL_INT, (ByteBuffer) null);

		glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, texture, 0);

		glBindRenderbuffer(GL_RENDERBUFFER, renderBuffer);
		glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT24, main.Main.width, main.Main.height);
		glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, renderBuffer);

		glBindTexture(GL_TEXTURE_2D, 0);

		glBindFramebuffer(GL_FRAMEBUFFER, frameBuffer);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluOrtho2D(0, width, height, 0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
		font.drawString(0, 0, text);

		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}

	public void renderCenter(float x, float y) {

		renderCenter(x, y, 0, width, height, 0, 0, 0);
	}

	public void renderCenter(float x, float y, float z, float w, float h, float a, float xf, float yf) { // ONLY HORIZONTALLY CENTERED

		render(x - w / 2, y, z, w, h, a, xf + w / 2, yf);
	}

	public void render(float x, float y) {

		render(x, y, 0, width, height, 0, 0, 0);
	}

	public void render(float x, float y, float z, float w, float h, float a, float xf, float yf) {

		glTranslatef(x + xf, y + yf, 0);
		glRotatef(a, 0, 0, 1);
		glTranslatef(-xf, -yf, 0);
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, texture);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		glBegin(GL_QUADS);
		glColor3f(1, 1, 1);
		glTexCoord2f(0, 1);
		glVertex3f(0, 0, z);
		glTexCoord2f(0, 0);
		glVertex3f(0, h, z);
		glTexCoord2f(1, 0);
		glVertex3f(w, h, z);
		glTexCoord2f(1, 1);
		glVertex3f(w, 0, z);
		glEnd();
		glTranslatef(xf, yf, 0);
		glRotatef(-a, 0, 0, 1);
		glTranslatef(-(x + xf), -(y + yf), 0);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public void destroy() {

		glDeleteFramebuffers(frameBuffer);
		glDeleteRenderbuffers(renderBuffer);
		glDeleteTextures(texture);
	}
}
