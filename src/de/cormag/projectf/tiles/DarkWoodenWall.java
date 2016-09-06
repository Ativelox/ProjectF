package de.cormag.projectf.tiles;

import de.cormag.projectf.gfx.Assets;

public class DarkWoodenWall extends Tile {

	public DarkWoodenWall(int id) {
		super(Assets.darkWoodenWall, id);

	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
