package de.cormag.projectf.sound;

import java.awt.Graphics;

import de.cormag.projectf.utils.time.GameTime;
import de.cormag.projectf.worlds.music.MusicWorld;

public class StandardMusicBehavior extends MusicBehavior {

	private BGMPlayer soundPlayer;
	private boolean isRunning;

	public StandardMusicBehavior(BGMPlayer soundPlayer) {

		this.soundPlayer = soundPlayer;

		isRunning = false;

	}

	@Override
	public void playBGM(String path) {

		if (!isRunning) {
			soundPlayer.setSound(path);
			soundPlayer.playSound();
			isRunning = true;
		}
	}

	@Override
	public void stopBGM() {

		if (isRunning) {
			soundPlayer.stopCurrentSound();
			isRunning = false;
		}

	}

	@Override
	public void updateBGM(final GameTime gameTime) {

		soundPlayer.update(gameTime);

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

	@Override
	public void recoverLastMusic(MusicWorld world) {

		soundPlayer.recoverLastSound(world);

	}

}
