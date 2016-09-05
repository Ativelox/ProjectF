package projectf.cormag.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import projectf.cormag.main.Handler;

public abstract class LeaveableBuilding extends World{

	private static final long serialVersionUID = 1L;
	
	protected Rectangle leaveableTileHitbox;
	
	protected int leaveableTileX, leaveableTileY;
	protected static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;
	

	public LeaveableBuilding(Handler handler, String path) {
		
		this.handler = handler;
		
		loadWorld(path);
		

		leaveableTileX = 0;
		leaveableTileY = 0;
		
		leaveableTileHitbox = new Rectangle(0, 0, 0, 0);
		
	}
	
	public void tick(){
		
		leaveableTileHitbox = new Rectangle((int) (leaveableTileX - handler.getGameCamera().getxOffset()), 
				(int) (leaveableTileY - handler.getGameCamera().getyOffset()), TILE_WIDTH, TILE_HEIGHT);
			
		if(checkPlayerLeaveableTileCollision()){	

			handler.setWorld(new LoadingScreen(handler.getLastWorld(), handler, handler.getPlayer().getLastX(), handler.getPlayer().getLastY()));

		}
		
	}

	public void render(Graphics g){
		super.render(g);
		
		g.setColor(Color.RED);
		
		g.drawRect(leaveableTileHitbox.x, leaveableTileHitbox.y, leaveableTileHitbox.width, leaveableTileHitbox.height);
		
	}
	
	protected boolean checkPlayerLeaveableTileCollision(){
		
		if(leaveableTileHitbox.intersects(handler.getPlayer().getProperCollisionRectangle())){
			
			return true;
			
		}else{
			return false;
			
		}
	}
	
	public int getLeaveableTileX(){
		
		return leaveableTileX;
	}
	
	public int getLeaveableTileY(){
		
		return leaveableTileY;
	}

}
