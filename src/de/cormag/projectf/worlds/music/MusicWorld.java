package de.cormag.projectf.worlds.music;

import de.cormag.projectf.worlds.World;

public abstract class MusicWorld extends World{
	
	private static final long serialVersionUID = 1L;
	
	protected String defaultSoundtrack;
	protected boolean isRunning;

	public MusicWorld() {
		
		defaultSoundtrack = null;
		isRunning = false;
	}
	
	public String getDefaultSoundtrack(){
		
		return defaultSoundtrack;
		
	}

}
	