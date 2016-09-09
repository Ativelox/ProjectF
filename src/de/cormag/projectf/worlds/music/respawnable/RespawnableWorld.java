package de.cormag.projectf.worlds.music.respawnable;

import de.cormag.projectf.entities.EntityManager;
import de.cormag.projectf.sound.BGMPlayer;
import de.cormag.projectf.worlds.music.MusicWorld;

public abstract class RespawnableWorld extends MusicWorld {

	private static final long serialVersionUID = 1L;

	public RespawnableWorld(BGMPlayer soundPlayer, String name) {
		super(soundPlayer, name);

	}

	public void respawnEntities() {

		setEntityManager(getInitializedEntityManager());

	}

	@Override
	public EntityManager getEntityManager() {

		return getInitializedEntityManager();

	}

	public abstract EntityManager getInitializedEntityManager();

	public abstract void setEntityManager(EntityManager entityManager);

}
