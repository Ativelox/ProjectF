package de.cormag.projectf.worlds.music;

import de.cormag.projectf.sound.BGMPlayer;
import de.cormag.projectf.worlds.World;

public abstract class MusicWorld extends World{
	
	private static final long serialVersionUID = 1L;
	
	protected String defaultSoundtrack;
	protected boolean isRunning;
	protected BGMPlayer soundPlayer;

	public MusicWorld(BGMPlayer soundPlayer) {
		
		this.soundPlayer = soundPlayer;
		
		defaultSoundtrack = null;
		isRunning = false;
	}
	
	public void tick(){
		
		if(!isRunning){
			
			soundPlayer.setSound(getDefaultSoundtrack());
			soundPlayer.playSound();
			isRunning = true;
			
		}
		
	}
	
	public String getDefaultSoundtrack(){
		
		return defaultSoundtrack;
		
	}

}
	