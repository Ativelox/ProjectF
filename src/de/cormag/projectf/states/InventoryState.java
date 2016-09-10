package de.cormag.projectf.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import de.cormag.projectf.entities.creatures.humans.controlable.Player;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;

public class InventoryState extends State {

	private static final long serialVersionUID = 1L;

	private static final int LEFT_PADDING = 100;
	private static final int TOP_PADDING = 50;
	private static final int BUTTON_PADDING = 10;
	private static final int BUTTON_WIDTH = 120;
	private static final int BUTTON_HEIGHT = 80;

	private Player player;

	public InventoryState(Handler handler, Player player) {
		super(handler);

		this.player = player;
	}

	@Override
	public void tick() {

		if (handler.getKeyManager().escape && player.getInventoryStatus()) {

			handler.getGame().getStateManager().pop();

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {

					player.setInventoryStatus(false);

				}

			}, 333);

		}

	}

	@Override
	public void render(Graphics g) {

		g.setColor(new Color(0, 0, 0, 180));
		g.fillRect(0, 0, 1000, 700);

		g.setColor(new Color(0, 0, 125, 180));
		g.fillRect(0, 620, 1000, 30);
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SANS_SERIF, 1, 20));
		g.drawString("Escape: Back", 200, 642);

		g.setColor(new Color(0, 0, 80, 200));
		g.fillRect(770, 55, 200, 500);

		g.setFont(new Font(Font.DIALOG_INPUT, 1, 25));
		g.setColor(new Color(0, 0, 0));

		g.drawImage(Assets.menuLayerBackground, LEFT_PADDING, TOP_PADDING, BUTTON_WIDTH, BUTTON_HEIGHT, null);
		g.drawString("Skills", LEFT_PADDING + 17, TOP_PADDING + BUTTON_HEIGHT / 2 + 10);

		g.drawImage(Assets.menuLayerBackground, LEFT_PADDING + BUTTON_WIDTH + BUTTON_PADDING, TOP_PADDING, BUTTON_WIDTH,
				BUTTON_HEIGHT, null);
		g.drawString("Items", LEFT_PADDING + BUTTON_WIDTH + BUTTON_PADDING + 25, TOP_PADDING + BUTTON_HEIGHT / 2 + 10);

		g.drawImage(Assets.menuLayerBackground, LEFT_PADDING + BUTTON_WIDTH * 2 + BUTTON_PADDING * 2, TOP_PADDING,
				BUTTON_WIDTH, BUTTON_HEIGHT, null);
		g.drawString("Equip", LEFT_PADDING + BUTTON_WIDTH * 2 + BUTTON_PADDING * 2 + 25,
				TOP_PADDING + BUTTON_HEIGHT / 2 + 10);

		g.drawImage(Assets.menuLayerBackground, LEFT_PADDING + BUTTON_WIDTH * 3 + BUTTON_PADDING * 3, TOP_PADDING,
				BUTTON_WIDTH, BUTTON_HEIGHT, null);
		g.drawString("Status", LEFT_PADDING + BUTTON_WIDTH * 3 + BUTTON_PADDING * 3 + 16,
				TOP_PADDING + BUTTON_HEIGHT / 2 + 10);

		g.drawImage(Assets.menuLayerBackground, LEFT_PADDING + BUTTON_WIDTH * 4 + BUTTON_PADDING * 4, TOP_PADDING,
				BUTTON_WIDTH, BUTTON_HEIGHT, null);
		g.drawString("System", LEFT_PADDING + BUTTON_WIDTH * 4 + BUTTON_PADDING * 4 + 16,
				TOP_PADDING + BUTTON_HEIGHT / 2 + 10);

		g.setColor(Color.GRAY);
		g.fillRoundRect(LEFT_PADDING, TOP_PADDING + BUTTON_HEIGHT + 20, 640, 150, 40, 40);

		g.setColor(new Color(200, 200, 200));
		g.fillRect(LEFT_PADDING + 160, TOP_PADDING + BUTTON_HEIGHT + 20, 100, 30);
		g.fillRoundRect(LEFT_PADDING, TOP_PADDING + BUTTON_HEIGHT + 20, 640, 30, 40, 40);
		g.fillRoundRect(LEFT_PADDING, TOP_PADDING + BUTTON_HEIGHT + 20, 185, 150, 40, 40);

		g.drawImage(Assets.avatarMenuFace, LEFT_PADDING + 20, TOP_PADDING + BUTTON_HEIGHT + 20, 150, 150, null);

		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.DIALOG_INPUT, 1, 30));
		g.drawString("Cormag", LEFT_PADDING + 190, 174);
		g.drawString("Lv. " + player.getLevel(), 450, 174);

		// EXP
		g.setColor(Color.BLACK);
		g.fillRoundRect(600, 158, 100, 15, 10, 10);
		g.setColor(new Color(0, 180, 0));
		g.fillRoundRect(600, 158, 100 / player.experienceNeeded() * player.getExperience(), 15, 10, 10);

		g.setColor(Color.black);

		g.fillRoundRect(350, 220, (200 / player.getMaxHealth()) * player.getMaxHealth(), 15, 10, 10);
		g.setColor(new Color(180, 50, 30));
		g.fillRoundRect(350, 220, (200 / player.getMaxHealth()) * player.getLifepoints(), 15, 10, 10);

		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.DIALOG_INPUT, 1, 23));
		g.drawString("HP", 330, 220);

		g.setColor(new Color(50, 50, 50));
		g.drawString(player.getLifepoints() + "/" + player.getMaxHealth(), 410, 225);

	}

	@Override
	public boolean tickLower() {

		return false;
	}

	@Override
	public boolean renderLower() {

		return true;
	}

}
