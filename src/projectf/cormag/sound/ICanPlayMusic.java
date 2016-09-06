package projectf.cormag.sound;

import java.awt.Graphics;

import projectf.cormag.worlds.music.MusicWorld;

public interface ICanPlayMusic {
	
	public void playBGM(String path);
	
	public void playBGM();
	
	public void stopBGM();
	
	public void tickBGM();
	
	public void renderMusicChooseArea(String[] paths);
	
	public void removeMusicChooseAreaIfOOR();
	
	public void renderPlayNotification(Graphics g);
	
	public void recoverLastMusic(MusicWorld world);

	
	
}
