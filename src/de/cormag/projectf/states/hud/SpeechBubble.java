package de.cormag.projectf.states.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class SpeechBubble extends HUDElement {

	private String content;

	private int left = 200;
	private int top = 500;
	private int width = 750;
	private int height = 150;

	public SpeechBubble() {

		content = "";

	}

	@Override
	public void tick() {

	}

	public void render(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(left, top, width, height);
		g.setColor(Color.BLACK);

		for (int i = 0; i < 5; i++) {

			g.drawLine(left, top + i, left + width, top + i);
			g.drawLine(left, (top + height) - i, left + width, (top + height) - i);
			g.drawLine(left + i, top, left + i, top + height);
			g.drawLine((left + width) - i, top, (left + width) - i, top + height);

		}

		printText(g);

	}

	private void printText(Graphics g) {

		g.setFont(new Font(Font.DIALOG, 1, 17));

		g.drawString(content, left + 20, top + 30);

	}

	public void setContent(String content) {

		this.content = content;

	}

}
