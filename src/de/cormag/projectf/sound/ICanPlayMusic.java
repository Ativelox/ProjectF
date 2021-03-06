package de.cormag.projectf.sound;

import java.awt.Graphics;

import de.cormag.projectf.utils.time.GameTime;
import de.cormag.projectf.worlds.music.MusicWorld;

public interface ICanPlayMusic {

	public void playBGM(String path);

	public void playBGM();

	public void stopBGM();

	public void updateBGM(final GameTime gameTime);

	public void renderMusicChooseArea(String[] paths);

	public void removeMusicChooseAreaIfOOR();

	public void renderPlayNotification(Graphics g);

	public void recoverLastMusic(MusicWorld world);

}
