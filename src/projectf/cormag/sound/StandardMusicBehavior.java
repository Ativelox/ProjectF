package projectf.cormag.sound;

import java.awt.Graphics;

public class StandardMusicBehavior extends MusicBehavior{
	
	private BGMPlayer soundPlayer;
	private boolean isRunning;

	public StandardMusicBehavior(BGMPlayer soundPlayer) {
		
		this.soundPlayer = soundPlayer;
		
		isRunning = false;
		
	}

	@Override
	public void playBGM(String path) {
		
		if(!isRunning){
			soundPlayer.setSound(path);
			soundPlayer.playSound();
			isRunning = true;
		}
	}

	@Override
	public void stopBGM() {
		
		if(isRunning){
			soundPlayer.stopCurrentSound();
			isRunning = false;
		}
		
	}

	@Override
	public void tickBGM() {
		
		soundPlayer.tick();
		
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
