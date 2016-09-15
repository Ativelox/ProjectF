package de.cormag.projectf.worlds.music;

import de.cormag.projectf.sound.BGMPlayer;
import de.cormag.projectf.utils.time.GameTime;
import de.cormag.projectf.worlds.World;

public abstract class MusicWorld extends World {

	private static final long serialVersionUID = 1L;

	protected String defaultSoundtrack;
	protected boolean isRunning;
	protected BGMPlayer soundPlayer;

	public MusicWorld(BGMPlayer soundPlayer, String name) {
		super(name);

		this.soundPlayer = soundPlayer;

		defaultSoundtrack = null;
		isRunning = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.cormag.projectf.worlds.World#update(de.cormag.projectf.utils.time.
	 * GameTime)
	 */
	@Override
	public void update(final GameTime gameTime) {

		if (!isRunning) {

			soundPlayer.setSound(getDefaultSoundtrack());
			soundPlayer.playSound();
			isRunning = true;

		}

	}

	public String getDefaultSoundtrack() {

		return defaultSoundtrack;

	}

}
