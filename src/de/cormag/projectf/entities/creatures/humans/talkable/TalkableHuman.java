package de.cormag.projectf.entities.creatures.humans.talkable;

import java.awt.Graphics;

import de.cormag.projectf.entities.creatures.humans.Human;
import de.cormag.projectf.main.Handler;

public abstract class TalkableHuman extends Human implements ICanTalk{

	private static final long serialVersionUID = 1L;
	
	private StandardTalkBehavior standardTalkBehavior;

	public TalkableHuman(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		standardTalkBehavior = new StandardTalkBehavior(handler);
		
	}
	
	@Override
	public void talk(boolean finishedTalking, TalkableHuman talkableHuman){
		
		talkableHuman.updateSpeech();
		
		standardTalkBehavior.talk(finishedTalking, talkableHuman);
		
	}
	
	@Override
	public void removeSpeechBubbleIfOOR(boolean remove){
		
		standardTalkBehavior.removeSpeechBubbleIfOOR(remove);
		
	}
	
	public void setSpeech(String content){
		
		standardTalkBehavior.getSpeechBubble().setContent(content);
		
	}
	
	public void renderSpeechbubble(Graphics g){
		
		standardTalkBehavior.getSpeechBubble().render(g);
		
	}
	
	@Override
	public void renderTalkNotification(Graphics g) {
		
		standardTalkBehavior.renderTalkNotification(g);
		
	}
	
	public abstract void updateSpeech();
	
	

}
