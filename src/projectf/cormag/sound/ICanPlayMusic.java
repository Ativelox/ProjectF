package projectf.cormag.sound;

import java.awt.Graphics;

public interface ICanPlayMusic {
	
	public void playBGM(String path);
	
	public void playBGM();
	
	public void stopBGM();
	
	public void tickBGM();
	
	public void renderMusicChooseArea(String[] paths);
	
	public void removeMusicChooseAreaIfOOR();
	
	public void renderPlayNotification(Graphics g);

	
	
}
