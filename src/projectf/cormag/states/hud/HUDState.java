package projectf.cormag.states.hud;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import projectf.cormag.main.Handler;
import projectf.cormag.states.State;
import projectf.cormag.worlds.LoadingScreen;

public class HUDState extends State {

	private static final long serialVersionUID = 1L;

	private Set<HUDElement> hudElements;
	private Handler handler;

	public HUDState(Handler handler) {
		super(handler);
		
		this.handler = handler;

		hudElements = new HashSet<>();

		addHUDElement(new PlayerHealthBar(handler));
	}

	@Override
	public void tick() {

		for (HUDElement e : hudElements) {

			e.tick();

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

	public boolean addHUDElement(HUDElement hudElementToAdd) {

		return hudElements.add(hudElementToAdd);

	}

	public boolean removeHUDElement(HUDElement hudElementToRemove) {

		return hudElements.remove(hudElementToRemove);

	}
	
	public boolean containsHUDElement(HUDElement hudElementToCheck){
		
		return hudElements.contains(hudElementToCheck);
		
	}

}
