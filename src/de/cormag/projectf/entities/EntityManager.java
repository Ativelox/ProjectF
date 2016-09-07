package de.cormag.projectf.entities;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.cormag.projectf.entities.creatures.humans.Player;
import de.cormag.projectf.main.Handler;

public class EntityManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Entity> entities;
	private LinkedList<Entity> entitiesToAdd;
	private LinkedList<Entity> entitiesToRemove;
	private transient Handler handler;
	private Player player;
	private EntityComparator comparator;
	private boolean isCurrentlyTicking;

	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;

		isCurrentlyTicking = false;

		entities = new ArrayList<>();
		entitiesToAdd = new LinkedList<>();
		entitiesToRemove = new LinkedList<>();

		addEntity(player);

	}

	public void addEntity(Entity e) {
		if (e == null) {
			return;

		}
		if (isCurrentlyTicking) {
			entitiesToAdd.add(e);
		} else {
			entities.add(e);
		}
	}

	public void removeEntity(Entity e) {

		if (e == null) {
			return;

		}
		if (isCurrentlyTicking) {
			entitiesToRemove.add(e);
		} else {
			entities.remove(e);
		}
	}

	public boolean contains(Entity e) {

		if (e == null) {
			return false;

		}
		return entities.contains(e);

	}

	public Handler getHandler() {
		return handler;
	}

	public Player getPlayer() {
		return player;
	}

	public Iterator<Entity> getEntities() {

		return entities.iterator();

	}

	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);

		}
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void tick() {

		isCurrentlyTicking = true;

		for (Entity e : entities) {
			e.tick();
		}


		for (Entity e : entitiesToRemove) {
			entities.remove(e);
		}

		for (Entity e : entitiesToAdd) {
			entities.add(e);
		}

		entitiesToRemove.clear();
		entitiesToAdd.clear();

		entities.sort(comparator);

		isCurrentlyTicking = false;
	}

}
