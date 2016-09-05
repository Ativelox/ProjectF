package projectf.cormag.entities.statics.buildings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import projectf.cormag.entities.statics.StaticEntity;
import projectf.cormag.main.Handler;
import projectf.cormag.utils.Utils;
import projectf.cormag.worlds.LoadingScreen;

public abstract class EnterableBuilding extends StaticEntity{

	private static final long serialVersionUID = 1L;
	
	protected float doorX, doorY;
	protected int doorWidth, doorHeight;
	
	protected Rectangle doorHitbox;
	
	private int id;

	public EnterableBuilding(Handler handler, int x, int y, int width, int height, int id) {
		super(handler, x, y, width, height);
		
		doorX = 0;
		doorY = 0;
		doorWidth = 0;
		doorHeight = 0;
		
		this.id = id;
		
		doorHitbox = new Rectangle((int) (x - xOffset + doorX), (int) (y - yOffset + doorY), doorWidth, doorHeight);
		
	}
	
	public void tick(){
		super.tick();
		
		doorHitbox = new Rectangle((int) (x - xOffset + doorX), (int) (y - yOffset + doorY), doorWidth, doorHeight);
		enterBuildingIfEntered();
		
		
	}
	
	public void render(Graphics g){
		super.render(g);		
		renderDoorHitbox(g);
		
		if(checkPlayerDoorCollision()){
			
			Utils.openNotificationWindow(g, "Enter (E)");
			
		}

	}

	protected void renderDoorHitbox(Graphics g){
		
		g.setColor(Color.RED);
		g.drawRect(doorHitbox.x, doorHitbox.y, doorHitbox.width, doorHitbox.height);
		
	}
	
	protected boolean checkPlayerDoorCollision(){
		
		if(doorHitbox.intersects(handler.getPlayer().getProperCollisionRectangle())){
			return true;
			
		}else{
			return false;
			
		}
	}
	
	protected void enterBuildingIfEntered(){
		
		//TODO: add ID in constructor, switch through ID's to get the corresponding world for the ID of the house
		
		switch(id){
			
		//Default House
			case 1:		if(handler.getKeyManager().e && checkPlayerDoorCollision()){
						handler.setWorld(new LoadingScreen(handler.getDefaultHouse(), handler, handler.getDefaultHouse().getLeaveableTileX() , 
						handler.getDefaultHouse().getLeaveableTileY() - handler.getPlayer().getHeight()));
						}
						break;
		//Weapon/Armory Shop
			case 2:		if(handler.getKeyManager().e && checkPlayerDoorCollision()){
						handler.setWorld(new LoadingScreen(handler.getDefaultInn(), handler, handler.getDefaultInn().getLeaveableTileX() , 
						handler.getDefaultInn().getLeaveableTileY() - handler.getPlayer().getHeight()));
						}
						break;
		
		//Default House with decoration i guess
			case 3:
			
		//tbd
			case 4: 	
		
		}
			
	}

}


