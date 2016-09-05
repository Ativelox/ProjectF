package projectf.cormag.entities.creatures.humans.talkable;

import java.awt.Graphics;

import projectf.cormag.main.Handler;
import projectf.cormag.states.hud.SpeechBubble;
import projectf.cormag.utils.Utils;

public class StandardTalkBehavior extends TalkBehavior{

	private SpeechBubble speechBubble;
	private Handler handler;
	private boolean bubbleShown;
	
	public StandardTalkBehavior(Handler handler) {
		
		this.handler = handler;
	
		speechBubble = new SpeechBubble();
		
		bubbleShown = false;
		
	}

	@Override
	public void talk(boolean finishedTalking, TalkableHuman talkableHuman) {
		
		if(!finishedTalking && handler.getKeyManager().enter && !bubbleShown){
			
			handler.getGame().getStateManager().getGameState().getHUDState().addHUDElement(speechBubble);
			
			bubbleShown = true;
			
		}else if(bubbleShown && handler.getKeyManager().l){

			handler.getGame().getStateManager().getGameState().getHUDState().removeHUDElement(speechBubble);
			
			bubbleShown = false;
			
		}
		
	}
	
	public SpeechBubble getSpeechBubble(){
		
		return speechBubble;
		
	}

	@Override
	public void removeSpeechBubbleIfOOR(boolean remove) {
		
		if(remove && bubbleShown){
			
			handler.getGame().getStateManager().getGameState().getHUDState().removeHUDElement(speechBubble);
			
			bubbleShown = false;
			
		}
		
	}

	@Override
	public void renderTalkNotification(Graphics g) {
		
		Utils.openNotificationWindow(g, "Talk (Ent)");
		
	}

}
