package de.cormag.projectf.entities.creatures.humans.controlable;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import de.cormag.projectf.entities.creatures.humans.talkable.TalkableHuman;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.InventoryState;

public class Player extends ControlableHuman implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient boolean inventory;
	private float lastX, lastY;
	private TalkableHuman lastEncountered;
	private Handler handler;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y);

		this.handler = handler;

		inventory = false;

		applyResources();

	}

	@Override
	public void update() {
		super.update();

		handler.getGameCamera().centerOnEntity(this);

		talk(false);

		checkInventoryState();

	}

	@Override
	public void render(Graphics g) {

		renderTalkNotification(g);

		super.render(g, getCurrentAnimationFrame(Assets.player_left[1], Assets.player_right[1], Assets.player_up[1],
				Assets.player_down[1]));

	}

	private void checkInventoryState() {

		if (handler.getKeyManager().escape && !inventory) {

			handler.getGame().getStateManager().push(new InventoryState(handler, this));

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {

					inventory = true;
				}

			}, 333);
		}

	}

	public void talk(boolean finishedTalking) {

		if ((returnNearStandingTalkableHuman() != null) && (returnNearStandingTalkableHuman()
				.getProperCollisionRectangle().intersects(this.getNearbyRectangle()))) {

			lastEncountered = returnNearStandingTalkableHuman();

			returnNearStandingTalkableHuman().talk(finishedTalking, returnNearStandingTalkableHuman());

		} else if (lastEncountered != null) {

			lastEncountered.removeSpeechBubbleIfOOR(true);

		}
	}

	public void renderTalkNotification(Graphics g) {

		if (returnNearStandingTalkableHuman() != null && returnNearStandingTalkableHuman().getProperCollisionRectangle()
				.intersects(this.getNearbyRectangle())) {

			returnNearStandingTalkableHuman().renderTalkNotification(g);

		}

	}

	public boolean getInventoryStatus() {

		return inventory;

	}

	public void setInventoryStatus(boolean inventory) {

		this.inventory = inventory;

	}

	@Override
	public void setX(float x) {

		lastX = this.x;

		this.x = x;

	}

	@Override
	public void setY(float y) {

		lastY = this.y;

		this.y = y;

	}

	public float getLastX() {

		return lastX;

	}

	public float getLastY() {

		return lastY;

	}

	@Override
	public void applyResources() {
		super.applyResources();

	}

}