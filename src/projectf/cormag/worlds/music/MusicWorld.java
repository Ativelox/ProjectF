package projectf.cormag.worlds.music;

import projectf.cormag.worlds.World;

public abstract class MusicWorld extends World{
	
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
