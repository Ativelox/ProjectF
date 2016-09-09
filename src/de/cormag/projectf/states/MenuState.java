package de.cormag.projectf.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.gfx.ImageLoader;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.sound.BGMPlayer;

public class MenuState extends State {

	private static final long serialVersionUID = 1L;

	private int x;

	private boolean mainMenu, options, controls;

	private BGMPlayer soundPlayer;
	private boolean isRunning;

	public MenuState(Handler handler) {
		super(handler);
		x = 0;
		mainMenu = true;
		controls = false;
		options = false;

		soundPlayer = handler.getBGMPlayer();

		isRunning = false;
	}

	@Override
	public void tick() {

		if (!isRunning) {

			soundPlayer.setSound("Intro.pfsf");
			soundPlayer.playSound();
			isRunning = true;

		}

		soundPlayer.tick();

		if (handler.getKeyManager().o) {

			mainMenu = false;
			options = true;

		} else if (handler.getKeyManager().c) {

			mainMenu = false;
			controls = true;

		} else if (options && handler.getKeyManager().escape) {

			mainMenu = true;
			options = false;

		} else if (controls && handler.getKeyManager().escape) {

			mainMenu = true;
			controls = false;

		} else {

			mainMenu = true;
			options = false;
			controls = false;

		}

	}

	@Override
	public void render(Graphics g) {

		if (mainMenu) {

			g.setColor(Color.WHITE);
			g.drawImage(Assets.background, 0, 0, 1000, 700, null);

			if (x < 200) {
				g.setFont(new Font(Font.DIALOG_INPUT, 1, 80));
				g.drawString("Ativelox's Legacy", 100, x);
				x += 5;

			} else {
				g.setFont(new Font(Font.DIALOG_INPUT, 1, 80));
				g.drawString("Ativelox's Legacy", 100, x);
			}

			if (x >= 200) {

				g.setColor(new Color(0, 0, 0, 180));
				g.fillRoundRect(200, 415, 530, 50, 40, 40);
				g.fillRoundRect(300, 515, 320, 50, 50, 50);
				g.fillRoundRect(300, 565, 320, 50, 50, 50);

				g.setFont(new Font(Font.DIALOG, 1, 30));
				g.setColor(Color.WHITE);
				g.drawString("Press Space to start a new game", 230, 450);

				if (handler.getKeyManager().space) {

					soundPlayer.stopCurrentSound();

					GameState gameState = new GameState(handler);
					handler.getGame().getStateManager().push(gameState);
					gameState.createHUD();
				}

				g.setFont(new Font(Font.DIALOG, 2, 30));
				g.setColor(Color.LIGHT_GRAY);
				g.drawString("Press O for Options", 330, 550);
				g.drawString("Press C for Controls", 330, 600);

				g.setColor(Color.white);
			}

		} else if (controls) {

			g.setFont(new Font(Font.DIALOG_INPUT, 1, 20));
			g.drawString("W/A/S/D: ", 100, 100);
			g.drawString("Shift: ", 100, 200);
			g.drawString("E: ", 100, 300);
			g.drawString("L: ", 100, 400);
			g.drawString("Space: ", 100, 500);
			g.drawString("G: ", 100, 600);

			g.setFont(new Font(Font.DIALOG, 2, 20));
			g.drawString("Move Player", 200, 100);
			g.drawString("Sprint", 200, 200);
			g.drawString("Basic interaction with objects", 200, 300);
			g.drawString("Leaving buildings", 200, 400);
			g.drawString("Attacking", 200, 500);
			g.drawString("Opening / closing the debug window", 200, 600);

		} else if (options) {

			g.setFont(new Font(Font.SANS_SERIF, 1, 80));
			g.drawString("None available yet!", 120, 350);

			for (int i = 0; i < 1000; i = i + 250) {
				g.drawImage(ImageLoader.loadImage("Kappa.pfpf"), i, 0, 200, 200, null);
			}

			for (int i = 0; i < 1000; i = i + 250) {
				g.drawImage(ImageLoader.loadImage("Kappa.pfpf"), i, 500, 200, 200, null);
			}
		}
	}

	@Override
	public boolean tickLower() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean renderLower() {
		// TODO Auto-generated method stub
		return false;
	}

}
