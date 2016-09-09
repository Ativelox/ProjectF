package de.cormag.projectf.tiles.teleport;

import java.awt.image.BufferedImage;

import de.cormag.projectf.tiles.Tile;

public abstract class TeleportTile extends Tile {

	private String path;

	public TeleportTile(BufferedImage texture, int id, String path) {
		super(texture, id);

		this.path = path;

	}

	public String getWorldToTeleportTo() {

		return path;

	}
}
