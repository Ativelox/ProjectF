package de.cormag.projectf.states.hud;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.State;
import de.cormag.projectf.worlds.LoadingScreen;

public class HUDState extends State {

	private static final long serialVersionUID = 1L;

	private Set<HUDElement> hudElements;
	private Set<HUDElement> hudElementsToAdd;
	private Handler handler;
	
	private boolean activeBossHealthBar;

	public HUDState(Handler handler) {
		super(handler);
		
		this.handler = handler;
		
		activeBossHealthBar = false;

		hudElements = new HashSet<>();
		hudElementsToAdd = new HashSet<>();

		addHUDElement(new PlayerHealthBar(handler));
	}

	@Override
	public void tick() {

		for (HUDElement e : hudElements) {

			e.tick();

		}
		
		for(HUDElement e : hudElementsToAdd){
			
			addHUDElement(e);
			
		}
	}

	@Override
	public void render(Graphics g) {
		
		if(!(handler.getWorld() instanceof LoadingScreen)){
			
			for (HUDElement e : hudElements) {
				e.render(g);
	
			}
		}
	}

	@Override
	public boolean tickLower() {
		return true;
	}

	@Override
	public boolean renderLower() {
		return true;
	}

	public void addHUDElement(HUDElement hudElementToAdd) {
		
		if(hudElementToAdd instanceof BossHealthBar && activeBossHealthBar){
			if(!hudElementsToAdd.contains(hudElementToAdd)){
				hudElementsToAdd.add(hudElementToAdd);
				return;
				
			}
			return;
			
		}
		
		if(hudElementToAdd instanceof BossHealthBar){
			
			activeBossHealthBar = true;
			
		}

		hudElements.add(hudElementToAdd);		

	}

	public void removeHUDElement(HUDElement hudElementToRemove) {
		
		if(hudElementsToAdd.contains(hudElementToRemove)){
			
			hudElementsToAdd.remove(hudElementToRemove);
			
		}

		if(hudElementToRemove instanceof BossHealthBar){
			
			activeBossHealthBar = false;
			
		}

		hudElements.remove(hudElementToRemove);

	}
	
	public boolean containsHUDElement(HUDElement hudElementToCheck){
		
		return hudElements.contains(hudElementToCheck);
		
	}

}
