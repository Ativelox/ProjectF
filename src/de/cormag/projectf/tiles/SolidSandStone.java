package de.cormag.projectf.tiles;

import de.cormag.projectf.gfx.Assets;

public class SolidSandStone extends Tile {

	public SolidSandStone(int id) {
		super(Assets.solidSandStone, id);

	}

	public boolean isSolid() {

		return true;
	}

}
