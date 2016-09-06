package de.cormag.projectf.tiles.blank;

import java.awt.image.BufferedImage;

import de.cormag.projectf.tiles.Tile;

public abstract class BlankTile extends Tile{

	public BlankTile(BufferedImage texture, int id) {
		super(texture, id);
		
	}
	
	public boolean isSolid(){
		return true;
	}
	
	@Override
	public boolean isBlank(){
		return true;
		
	}

}
