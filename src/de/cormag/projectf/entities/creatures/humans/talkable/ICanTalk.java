package de.cormag.projectf.entities.creatures.humans.talkable;

import java.awt.Graphics;

public interface ICanTalk {
	
	public void talk(boolean finishedTalking, TalkableHuman talkableHuman);
	
	public void removeSpeechBubbleIfOOR(boolean remove);
	
	public void renderTalkNotification(Graphics g);

}
