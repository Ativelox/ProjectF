package de.cormag.projectf.entities.creatures.enemies.bosses.music;

import java.awt.Graphics;

import de.cormag.projectf.entities.creatures.enemies.bosses.Boss;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.sound.ICanPlayMusic;
import de.cormag.projectf.sound.StandardMusicBehavior;
import de.cormag.projectf.utils.time.GameTime;
import de.cormag.projectf.worlds.music.MusicWorld;

public abstract class BossWithMusic extends Boss implements ICanPlayMusic {

	private static final long serialVersionUID = 1L;

	private StandardMusicBehavior standardMusicBehavior;

	private String path;

	public BossWithMusic(Handler handler, float x, float y, int width, int height, String path) {
		super(handler, x, y, width, height);

		this.path = path;

		standardMusicBehavior = new StandardMusicBehavior(handler.getBGMPlayer());

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

		if ((damagedOnce || seenPlayer)) {

			playBGM(path);

		}

		if (this.health <= 0) {

			recoverLastMusic((MusicWorld) handler.getWorld());

		}

		updateBGM(gameTime);
	}

	@Override
	public void playBGM(String path) {

		standardMusicBehavior.playBGM(path);

	}

	@Override
	public void stopBGM() {

		standardMusicBehavior.stopBGM();

	}

	@Override
	public void updateBGM(final GameTime gameTime) {

		standardMusicBehavior.updateBGM(gameTime);

	}

	@Override
	public void recoverLastMusic(MusicWorld world) {

		stopBGM();
		standardMusicBehavior.recoverLastMusic(world);

	}

	@Override
	public void removeMusicChooseAreaIfOOR() {

	}

	@Override
	public void renderPlayNotification(Graphics g) {

	}

	@Override
	public void playBGM() {

	}

	@Override
	public void renderMusicChooseArea(String[] paths) {

	}

}
