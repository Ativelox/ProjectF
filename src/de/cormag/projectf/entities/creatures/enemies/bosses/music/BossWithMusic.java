package de.cormag.projectf.entities.creatures.enemies.bosses.music;

import java.awt.Graphics;

import de.cormag.projectf.entities.creatures.enemies.bosses.Boss;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.sound.ICanPlayMusic;
import de.cormag.projectf.sound.StandardMusicBehavior;
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

	public void update() {
		super.update();

		if ((damagedOnce || seenPlayer)) {

			playBGM(path);

		}

		if (this.health <= 0) {

			recoverLastMusic((MusicWorld) handler.getWorld());

		}

		tickBGM();
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
	public void tickBGM() {

		standardMusicBehavior.tickBGM();

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
		// TODO Auto-generated method stub

	}

	@Override
	public void renderMusicChooseArea(String[] paths) {
		// TODO Auto-generated method stub

	}

}
