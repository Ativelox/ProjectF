package projectf.cormag.states.hud;

import java.awt.Graphics;

public abstract class HUDElement {

	public abstract void render(Graphics g);

	public abstract void tick();

}
