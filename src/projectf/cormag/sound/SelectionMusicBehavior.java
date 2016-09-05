package projectf.cormag.sound;

import java.awt.Graphics;

import projectf.cormag.main.Handler;
import projectf.cormag.utils.Utils;

public class SelectionMusicBehavior extends MusicBehavior{
	
	private BGMPlayer soundPlayer;
	private Handler handler;
	private boolean bubbleShown;


	public SelectionMusicBehavior(Handler handler) {
		
		this.handler = handler; 
		
		this.soundPlayer = handler.getBGMPlayer();
	
	}
	

	@Override
	public void playBGM() {
		
		if(handler.getGame().getDisplay().getClickedSoundtrack() != null){
			
			soundPlayer.setSound(handler.getGame().getDisplay().getClickedSoundtrack());
			soundPlayer.playSound();

			
		}
	}
	

	@Override
	public void stopBGM() {

			soundPlayer.stopCurrentSound();
		
	}

	@Override
	public void tickBGM() {
		
		soundPlayer.tick();
		
	}
	
	@Override
	public void renderMusicChooseArea(String paths[]) {
		
		
		if(!bubbleShown && handler.getKeyManager().enter){
			

			handler.getGame().getDisplay().getMusicList().setVisible(true);

			bubbleShown = true;
			
		}
		
	}

	@Override
	public void removeMusicChooseAreaIfOOR() {
		
		if(bubbleShown){
			
			handler.getGame().getDisplay().getMusicList().setVisible(false);
			
			bubbleShown = false;
			
		}
		
		
	}

	@Override
	public void renderPlayNotification(Graphics g) {
		
		Utils.openNotificationWindow(g, "Play (Ent)");
		
	}

	@Override
	public void playBGM(String path) {
		
		
	}



}