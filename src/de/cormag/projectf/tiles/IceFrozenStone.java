package de.cormag.projectf.tiles;

import de.cormag.projectf.gfx.Assets;

public class IceFrozenStone extends Tile{

	public IceFrozenStone(int id) {
		super(Assets.iceFrozenStone, id);
	}
	
	public boolean isSolid() {

		return true;
	}

}
