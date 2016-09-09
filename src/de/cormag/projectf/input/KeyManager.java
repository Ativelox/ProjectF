package de.cormag.projectf.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener, FocusListener {

	private boolean[] keys;
	private boolean[] notAffectedKeys;
	
	public boolean up, down, left, right, space, y, enter, e, shift, c, l, o, p, escape, g, arrowDown, arrowLeft,
			arrowRight, arrowUp, r, s, ctrl, one, two, three, four;
	

	public KeyManager() {
		keys = new boolean[700];
		notAffectedKeys = new boolean[700];
		
	}

	public void tick() {
		
		
		up = notAffectedKeys[KeyEvent.VK_W];
		down = notAffectedKeys[KeyEvent.VK_S];
		left = notAffectedKeys[KeyEvent.VK_A];
		right = notAffectedKeys[KeyEvent.VK_D];
		shift = notAffectedKeys[KeyEvent.VK_SHIFT];
		ctrl = notAffectedKeys[KeyEvent.VK_CONTROL];
		arrowDown = notAffectedKeys[KeyEvent.VK_DOWN];
		arrowLeft = notAffectedKeys[KeyEvent.VK_LEFT];
		arrowRight = notAffectedKeys[KeyEvent.VK_RIGHT];
		arrowUp = notAffectedKeys[KeyEvent.VK_UP];
		escape = notAffectedKeys[KeyEvent.VK_ESCAPE];
		enter = notAffectedKeys[KeyEvent.VK_ENTER];
		shift = notAffectedKeys[KeyEvent.VK_SHIFT];
		
		space = keys[KeyEvent.VK_SPACE];
		y = keys[KeyEvent.VK_Y];
		e = keys[KeyEvent.VK_E];
		c = keys[KeyEvent.VK_C];
		l = keys[KeyEvent.VK_L];
		o = keys[KeyEvent.VK_O];
		p = keys[KeyEvent.VK_P];
		g = keys[KeyEvent.VK_G];
		r = keys[KeyEvent.VK_R];
		s = keys[KeyEvent.VK_S];
		one = keys[KeyEvent.VK_1];
		two = keys[KeyEvent.VK_2];
		three = keys[KeyEvent.VK_3];
		four = keys[KeyEvent.VK_4];
		
	}

	public void resetKeys() {

		for (int i = 0; i < keys.length; i++) {

			keys[i] = false;
			notAffectedKeys[i] = false;

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		notAffectedKeys[e.getKeyCode()] = true;
		
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		notAffectedKeys[e.getKeyCode()] = false;
		
		keys[e.getKeyCode()] = false;
		
	
	}

	

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void focusGained(FocusEvent arg0) {

	}
	
	
	public boolean unaffectedKeys(KeyEvent e){
		
		return e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_A ||
				e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_D ||
				e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyCode() == KeyEvent.VK_UP ||
				e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_DOWN ||
				e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_CONTROL ||
				e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_ENTER;
		
	}

	@Override
	public void focusLost(FocusEvent e1) {

		resetKeys();

	}

}
