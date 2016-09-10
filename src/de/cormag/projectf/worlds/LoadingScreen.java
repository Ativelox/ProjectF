package de.cormag.projectf.worlds;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.Clip;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.sound.BGMPlayer;
import de.cormag.projectf.worlds.music.MusicWorld;

public class LoadingScreen extends World {

	private static final long serialVersionUID = 1L;

	private int countRenderTicks;

	/**
	 * @return used if change worlds
	 */
	public LoadingScreen(World queuedWorld, Handler handler, float spawnX, float spawnY) {
		super(null);

		loadWorld("LoadingScreen.pflf");
		countRenderTicks = 0;

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				handler.getPlayer().setRelativeX(spawnX);
				handler.getPlayer().setRelativeY(spawnY);

			}

		}, 500);

		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				handler.setWorld(queuedWorld);

			}

		}, 1000);

	}

	public LoadingScreen(World queuedWorld, Handler handler, float spawnX, float spawnY, BGMPlayer bgmPlayer) {
		super(null);

		loadWorld("LoadingScreen.pflf");
		countRenderTicks = 0;

		bgmPlayer.stopCurrentSound();

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
					
				handler.getPlayer().setRelativeX(spawnX);
				handler.getPlayer().setRelativeY(spawnY);

			}

		}, 500);

		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				if (queuedWorld instanceof MusicWorld) {

					handler.setWorld(queuedWorld);

					World[] worlds = bgmPlayer.getVisitedMusicWorlds();
					Clip[] clips = bgmPlayer.getVisitedMusicWorldsClip();

					if (worlds != null && clips != null) {

						for (int j = 0; j < worlds.length; j++) {

							if (worlds[j] != null && worlds[j].equals(queuedWorld)) {

								bgmPlayer.setClip(clips[j]);
							}

						}

					}
				} else {
					handler.setWorld(queuedWorld);

				}
			}

		}, 1000);

	}

	public void render(Graphics g) {

		g.setFont(new Font(Font.DIALOG_INPUT, 1, 50));
		g.drawString("Loading", 50, 600);

		if (countRenderTicks >= 15) {

			g.fillRoundRect(290, 575, 25, 25, 90, 90);

			if (countRenderTicks >= 30) {

				g.fillRoundRect(325, 575, 25, 25, 90, 90);

				if (countRenderTicks >= 45) {

					g.fillRoundRect(360, 575, 25, 25, 90, 90);

				}
			}
		}

		countRenderTicks++;
	}

	@Override
	public EntityManager getEntityManager() {
		return null;
	}

}
