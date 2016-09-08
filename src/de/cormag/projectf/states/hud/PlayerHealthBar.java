package de.cormag.projectf.states.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

import de.cormag.projectf.entities.creatures.humans.Player;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;

public class PlayerHealthBar extends HUDElement implements Serializable {

	private static final long serialVersionUID = 1L;

	private Player player;
	private int playerHealth;
	private int playerMaxHealth;
	private float playerStamina;
	private float playerMaxStamina;
	private int playerMagic;
	private int playerMaxMagic;

	public PlayerHealthBar(Handler handler) {

		this.player = handler.getPlayer();
		

		playerHealth = player.getHealth();
		playerMaxHealth = player.getMaxHealth();
		playerStamina = player.getStamina();
		playerMaxStamina = player.getMaxStamina();
		playerMagic = player.getMagic();
		playerMaxMagic = player.getMaxMagic();

	}

	public void tick() {

		playerHealth = player.getHealth();
		playerMaxHealth = player.getMaxHealth();
		playerStamina = player.getStamina();
		playerMaxStamina = player.getMaxStamina();
		playerMagic = player.getMagic();
		playerMaxMagic = player.getMaxMagic();

	}

	public void render(Graphics g) {


		g.setFont(new Font(Font.MONOSPACED, 1, 20));
		g.setColor(Color.WHITE);
		g.drawString("Cormag", 100, 25);

		g.setColor(new Color(153, 0, 0, 180));
		g.setFont(new Font(Font.DIALOG_INPUT, 1, 15));
		g.fillRect(100, 35, 9999 / playerMaxHealth * (playerHealth / 30), 15);
		g.setColor(Color.red);
		g.drawString(playerHealth + "/" + playerMaxHealth, 105, 46);
		g.drawRect(99, 34, (9999 / playerMaxHealth * (playerMaxHealth / 30)) + 1, 16);

		g.setColor(new Color(0, 127, 0, 180));
		g.fillRect(100, 55, (int) (999 / playerMaxStamina * (playerStamina / 5)), 15);
		g.setColor(Color.green);
		g.drawString((int) playerStamina + "/" + (int) playerMaxStamina, 105, 66);
		g.drawRect(99, 54, (int) ((999 / playerMaxStamina * (playerStamina / 5)) + 1), 16);

		g.setColor(new Color(0, 0, 153, 180));
		g.fillRect(100, 75, (999 / playerMaxMagic * (playerMagic / 5)), 15);
		g.setColor(new Color(76, 76, 255));
		g.drawString(playerMagic + "/" + playerMaxMagic, 105, 86);
		g.drawRect(99, 74, (999 / playerMaxMagic * (playerMagic / 5) + 1), 16);

		g.setColor(Color.black);
		g.drawRoundRect(10, 10, 80, 80, 10, 10);

		if (this.playerHealth == playerMaxHealth) {

			g.drawImage(Assets.avatar_standard, 10, 10, 80, 80, null);

		} else if (this.playerHealth >= playerMaxHealth * 0.625 && this.playerHealth <= playerMaxHealth * 0.875) {

			g.drawImage(Assets.avatar_sad, 10, 10, 80, 80, null);

		} else if (this.playerHealth >= playerMaxHealth * 0.25 && this.playerHealth <= playerMaxHealth * 0.5) {

			g.drawImage(Assets.avatar_depressed, 10, 10, 80, 80, null);

		} else if (this.playerHealth == 1) {

			g.drawImage(Assets.avatar_sad_cry, 10, 10, 80, 80, null);

		} else {

			g.drawImage(Assets.avatar_very_sad, 10, 10, 80, 80, null);

		}
		
	}

}
