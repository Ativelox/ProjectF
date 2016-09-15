package de.cormag.projectf.states;

import java.awt.Color;
import java.awt.Graphics;

import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.sound.BGMPlayer;
import de.cormag.projectf.utils.time.GameTime;

public class GameOverState extends State {

	private static final long serialVersionUID = 1L;

	private int x, tx, txx;
	private BGMPlayer soundPlayer;
	private boolean isRunning;
	private Handler handler;

	public GameOverState(Handler handler) {
		super(handler);

		this.handler = handler;

		x = 0;
		tx = 0;
		txx = 0;

		soundPlayer = handler.getBGMPlayer();
		isRunning = false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IUpdateable#update()
	 */
	@Override
	public void update(final GameTime gameTime) {

		if (!isRunning) {
			soundPlayer.setSound("GameOver.pfsf");
			soundPlayer.playSound();
			isRunning = true;
		}

		soundPlayer.update(gameTime);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IRenderable#render(java.awt.
	 * Graphics)
	 */
	@Override
	public void render(Graphics g, final GameTime gameTime) {

		if (x < 255) {

			g.setColor(new Color(0, 0, 0, x));
			g.fillRect(0, 0, 1000, 700);
			x += 2;

		} else {

			g.setColor(new Color(0, 0, 0, 255));
			g.fillRect(0, 0, 1000, 700);

			if (tx < 255) {

				g.setColor(new Color(200, 0, 0, tx));
				g.setFont(Assets.OPTIMUS_PRINCEPS.deriveFont(80f));
				g.drawString("YOU DIED", 300, 300);

				tx += 3;

			} else {

				g.setColor(new Color(200, 0, 0, 255));
				g.setFont(Assets.OPTIMUS_PRINCEPS.deriveFont(80f));
				g.drawString("YOU DIED", 300, 300);

				if (txx < 255) {

					g.setColor(new Color(100, 0, 0, txx));
					g.setFont(Assets.OPTIMUS_PRINCEPS.deriveFont(40f));
					g.drawString("Press Space to go to the menus", 230, 400);

					txx += 2;

				} else {

					g.setColor(new Color(100, 0, 0, 255));
					g.setFont(Assets.OPTIMUS_PRINCEPS.deriveFont(40f));
					g.drawString("Press Space to go to the menus", 230, 400);

					if (handler.getKeyManager().space) {

						Handler newHandler = new Handler(handler.getGame());

						soundPlayer.stopCurrentSound();

						handler.getGame().getStateManager().clear();

						handler.getGame().getStateManager().push(new MenuState(newHandler));

					}

				}

			}

		}

	}

	@Override
	public boolean tickLower() {
		return false;
	}

	@Override
	public boolean renderLower() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IRenderable#getLayer()
	 */
	@Override
	public int getLayer() {
		return IRenderable.DEFAULT_LAYER;
	}

}
