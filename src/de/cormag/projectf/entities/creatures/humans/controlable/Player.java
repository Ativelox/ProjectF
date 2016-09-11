package de.cormag.projectf.entities.creatures.humans.controlable;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import de.cormag.projectf.entities.creatures.humans.talkable.TalkableHuman;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.logic.modes.IModeControl;
import de.cormag.projectf.logic.modes.players.EPlayerMode;
import de.cormag.projectf.logic.modes.players.LocalPlayerControl;
import de.cormag.projectf.logic.modes.players.PlayerModeManager;
import de.cormag.projectf.logic.movement.IMoveBehavior;
import de.cormag.projectf.logic.movement.MoveBehavior;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.states.InventoryState;
import de.cormag.projectf.utils.time.GameTime;

public class Player extends ControlableHuman implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient boolean inventory;
	private TalkableHuman lastEncountered;
	private Handler handler;
	
	private float mOldRelativeX, mOldRelativeY;
	
	private PlayerModeManager mPlayerModeManager;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y);

		this.handler = handler;

		inventory = false;
		
		IMoveBehavior moveBehavior = new MoveBehavior(this);
		IModeControl localPlayerControl = new LocalPlayerControl(this, moveBehavior, handler.getKeyManager());
		mPlayerModeManager = new PlayerModeManager(this, localPlayerControl, EPlayerMode.LOCAL_PLAYER_MODE);

		applyResources();

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);
		mPlayerModeManager.update(gameTime);
		
		handler.getGameCamera().centerOnEntity(this);

		talk(false);

		checkInventoryState();

	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {
		super.render(g, gameTime, getCurrentAnimationFrame(Assets.player_left[1], Assets.player_right[1], Assets.player_up[1],
				Assets.player_down[1]));
		
		renderTalkNotification(g);

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
	
	public void setOldX(float newPosX){
		mOldRelativeX = newPosX;
	}
	
	public void setOldY(float newPosY){
		mOldRelativeY = newPosY;
	}
	
	public float getOldX(){
		return mOldRelativeX;
	}

	public float getOldY(){
		return mOldRelativeY;
	}

	@Override
	public void applyResources() {
		super.applyResources();

	}


}