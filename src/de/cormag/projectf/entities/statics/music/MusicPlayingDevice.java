package de.cormag.projectf.entities.statics.music;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.cormag.projectf.entities.statics.StaticEntity;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.sound.BGMPlayer;
import de.cormag.projectf.sound.ICanPlayMusic;
import de.cormag.projectf.sound.SelectionMusicBehavior;
import de.cormag.projectf.utils.time.GameTime;
import de.cormag.projectf.worlds.music.MusicWorld;

public abstract class MusicPlayingDevice extends StaticEntity implements ICanPlayMusic {

	private static final long serialVersionUID = 1L;

	private SelectionMusicBehavior selectionMusicBehavior;
	protected Rectangle musicTriggerRect;
	private Handler handler;

	private String[] allBGMS;

	public MusicPlayingDevice(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

		this.handler = handler;

		selectionMusicBehavior = new SelectionMusicBehavior(handler);

		musicTriggerRect = new Rectangle(0, 0, 0, 0);

		allBGMS = BGMPlayer.getAllBGMs();

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);

		if (handler.getPlayer().getProperCollisionRectangle().intersects(musicTriggerRect)) {

			renderMusicChooseArea(allBGMS);
			updateBGM(gameTime);
			playBGM();

		}

	}

	@Override
	public void render(Graphics g, final GameTime gameTime, BufferedImage imageToDrawTo) {
		super.render(g, gameTime, imageToDrawTo);

		if (handler.getPlayer().getProperCollisionRectangle().intersects(musicTriggerRect)) {

			renderPlayNotification(g);

		}

		g.setColor(Color.GREEN);
		g.drawRect(musicTriggerRect.x, musicTriggerRect.y, musicTriggerRect.width, musicTriggerRect.height);

	}

	public Rectangle getMusicTriggerRectangle() {

		return musicTriggerRect;

	}

	@Override
	public void playBGM() {

		selectionMusicBehavior.playBGM();

	}

	@Override
	public void stopBGM() {

		selectionMusicBehavior.stopBGM();

	}

	@Override
	public void updateBGM(final GameTime gameTime) {

		selectionMusicBehavior.updateBGM(gameTime);

	}

	public void renderMusicChooseArea(String[] paths) {

		selectionMusicBehavior.renderMusicChooseArea(paths);

	}

	@Override
	public void removeMusicChooseAreaIfOOR() {

		selectionMusicBehavior.removeMusicChooseAreaIfOOR();

	}

	@Override
	public void renderPlayNotification(Graphics g) {

		selectionMusicBehavior.renderPlayNotification(g);

	}

	@Override
	public void recoverLastMusic(MusicWorld world) {

	}

	@Override
	public void playBGM(String path) {

	}

}
