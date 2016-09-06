package projectf.cormag.entities.statics.music;

import java.awt.Graphics;
import java.awt.Rectangle;

import projectf.cormag.gfx.Assets;
import projectf.cormag.main.Handler;

public class Piano extends MusicPlayingDevice{

	private static final long serialVersionUID = 1L;
	
	private Handler handler;
	
	public static final int WIDTH = 64 * 2, HEIGHT = 75 * 2;

	public Piano(Handler handler, float x, float y) {
		super(handler, x, y, WIDTH, HEIGHT);
		
		this.handler = handler;
		
		musicTriggerRect = new Rectangle((int) (x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset() + (HEIGHT - handler.getPlayer().getProperCollisionRectangle().height)), 
				WIDTH, handler.getPlayer().getProperCollisionRectangle().height); 
		
		getBounds().height = HEIGHT - handler.getPlayer().getProperCollisionRectangle().height;
	}
	
	public void tick(){
		super.tick();
		
		musicTriggerRect = new Rectangle((int) (x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset() + (HEIGHT - handler.getPlayer().getProperCollisionRectangle().height)), 
				WIDTH, handler.getPlayer().getProperCollisionRectangle().height);
		
		if(!handler.getPlayer().getProperCollisionRectangle().intersects(musicTriggerRect)){
			
			super.removeMusicChooseAreaIfOOR();
			
		}
		
		
	}
	
	public void render(Graphics g){
		g.drawImage(Assets.piano, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), WIDTH, HEIGHT, null);
		
		super.render(g);
		
	}



	



}
