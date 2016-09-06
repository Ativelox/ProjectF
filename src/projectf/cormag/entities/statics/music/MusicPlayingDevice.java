package projectf.cormag.entities.statics.music;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import projectf.cormag.entities.statics.StaticEntity;
import projectf.cormag.main.Handler;
import projectf.cormag.sound.BGMPlayer;
import projectf.cormag.sound.ICanPlayMusic;
import projectf.cormag.sound.SelectionMusicBehavior;
import projectf.cormag.worlds.music.MusicWorld;

public abstract class MusicPlayingDevice extends StaticEntity implements ICanPlayMusic{

	private static final long serialVersionUID = 1L;
	
	private SelectionMusicBehavior selectionMusicBehavior;
	protected Rectangle musicTriggerRect;
	private Handler handler;
	
	private String[] allBGMS;

	public MusicPlayingDevice(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		this.handler = handler;
		
		selectionMusicBehavior = new SelectionMusicBehavior(handler);
		
		musicTriggerRect = new Rectangle(0, 0, 0, 0);
		
		allBGMS = BGMPlayer.getAllBGMs();
		
	}
	
	public void tick(){
		super.tick();
		
		if(handler.getPlayer().getProperCollisionRectangle().intersects(musicTriggerRect)){
			
			renderMusicChooseArea(allBGMS);
			tickBGM();
			playBGM();
			
		}
		
	
		
	}
	
	public void render(Graphics g){
		super.render(g);
		
		if(handler.getPlayer().getProperCollisionRectangle().intersects(musicTriggerRect)){
			
			renderPlayNotification(g);
			
		}
		
		g.setColor(Color.GREEN);
		g.drawRect(musicTriggerRect.x, musicTriggerRect.y, musicTriggerRect.width, musicTriggerRect.height);
		
	}
	
	public Rectangle getMusicTriggerRectangle(){
		
		return musicTriggerRect;
		
	}
	
	@Override
	public void playBGM() {
		
		selectionMusicBehavior.playBGM();
		
	}
	
	@Override
	public void stopBGM(){
		
		selectionMusicBehavior.stopBGM();
		
	}
	
	@Override
	public void tickBGM(){
		
		selectionMusicBehavior.tickBGM();
		
	}
	
	public void renderMusicChooseArea(String[] paths){
		
		selectionMusicBehavior.renderMusicChooseArea(paths);
		
	}
	
	@Override
	public void removeMusicChooseAreaIfOOR() {
		
		selectionMusicBehavior.removeMusicChooseAreaIfOOR();
		
	}

	@Override
	public void renderPlayNotification(Graphics g) {
		
		selectionMusicBehavior.renderPlayNotification(g);
		
	}
	
	@Override
	public void recoverLastMusic(MusicWorld world) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void playBGM(String path){
		
		
	}

}
