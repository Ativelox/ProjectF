package projectf.cormag.tiles;

import projectf.cormag.gfx.Assets;

public class WaterTile extends Tile {

	public WaterTile(int id) {
		super(Assets.water, id);

	}

	public boolean isSolid() {

		return true;
	}

}
