package projectf.cormag.tiles;

import projectf.cormag.gfx.Assets;

public class IceFrozenStone extends Tile{

	public IceFrozenStone(int id) {
		super(Assets.iceFrozenStone, id);
	}
	
	public boolean isSolid() {

		return true;
	}

}
