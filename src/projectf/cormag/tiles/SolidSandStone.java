package projectf.cormag.tiles;

import projectf.cormag.gfx.Assets;

public class SolidSandStone extends Tile {

	public SolidSandStone(int id) {
		super(Assets.solidSandStone, id);

	}

	public boolean isSolid() {

		return true;
	}

}
