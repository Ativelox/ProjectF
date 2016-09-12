package de.cormag.projectf.states.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

import de.cormag.projectf.entities.creatures.humans.controlable.Player;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.utils.time.GameTime;

public class PlayerHealthBar extends HUDElement implements Serializable {

	private static final long serialVersionUID = 1L;

	private Player player;
	private float playerHealth;
	private float playerMaxHealth;
	private float playerStamina;
	private float playerMaxStamina;
	private int playerMagic;
	private int playerMaxMagic;

	PlayerHealthBar(Handler handler) {

		this.player = handler.getPlayer();

		playerHealth = player.getLifepoints();
		playerMaxHealth = player.getMaxLifepoints();
		playerStamina = player.getStamina();
		playerMaxStamina = player.getMaxStamina();
		playerMagic = player.getMagic();
		playerMaxMagic = player.getMaxMagic();

	}

	@Override
	public void update(final GameTime gameTime) {

		playerHealth = player.getLifepoints();
		playerMaxHealth = player.getMaxLifepoints();
		playerStamina = player.getStamina();
		playerMaxStamina = player.getMaxStamina();
		playerMagic = player.getMagic();
		playerMaxMagic = player.getMaxMagic();

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {

		g.setFont(new Font(Font.MONOSPACED, 1, 20));
		g.setColor(Color.WHITE);
		g.drawString("Cormag", 100, 25);

		g.setColor(new Color(153, 0, 0, 180));
		g.setFont(new Font(Font.DIALOG_INPUT, 1, 15));
		g.fillRect(100, 35, (int) (9999 / playerMaxHealth * (playerHealth / 30)), 15);
		g.setColor(Color.red);
		g.drawString(((int)playerHealth) + "/" + ((int)playerMaxHealth), 105, 46);
		g.drawRect(99, 34, (int) ((9999 / playerMaxHealth * (playerMaxHealth) / 30)) + 1, 16);

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


		g.drawImage(Assets.avatar_standard, 10, 10, 80, 80, null);

		

	}

}
