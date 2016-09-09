package de.cormag.projectf.states.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.cormag.projectf.main.Game;

public class WorldName extends HUDElement {

	private String name;
	private int rectXFadeIn, rectXFadeOut;
	private int decayPrevention;

	public WorldName(String name) {
		this.name = name;

		rectXFadeIn = 0;
		rectXFadeOut = 300;
		decayPrevention = 0;

	}

	public void resetFadeVariables() {

		rectXFadeIn = 0;
		rectXFadeOut = 300;
		decayPrevention = 0;

	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 200));

		if (rectXFadeOut <= 0) {

			return;

		}

		if (decayPrevention >= 180) {
			g.fillRect(Game.WIDTH - rectXFadeOut, 0, 300, 50);
			return;

		}

		if (rectXFadeIn < 300) {
			g.fillRect(Game.WIDTH - rectXFadeIn, 0, 300, 50);
		} else {
			g.fillRect(Game.WIDTH - rectXFadeIn, 0, 300, 50);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", 1, 22));
			g.drawString(name, Game.WIDTH - 280, 32);

		}

	}

	@Override
	public void tick() {

		if (rectXFadeOut <= 0) {
			return;

		}

		if (decayPrevention >= 180) {
			rectXFadeOut -= 5;
			return;
		}

		if (rectXFadeIn < 300) {
			rectXFadeIn += 5;

		} else {
			decayPrevention++;

		}

	}
}
