package de.cormag.projectf.logic.modes.players;

import de.cormag.projectf.entities.properties.ICanMove;
import de.cormag.projectf.input.KeyManager;
import de.cormag.projectf.logic.modes.IModeControl;
import de.cormag.projectf.logic.movement.IPlayerMovementBehavior;
import de.cormag.projectf.utils.time.GameTime;

/**
 * Controls the behaviors to fit those of a local player
 * 
 * @author Ativelox
 */

public class LocalPlayerControl implements IModeControl{
	
	/**
	 * the entity which is controlled by this object
	 */
	private final ICanMove mParent;
	
	/**
	 * move order receiving behavior object
	 */
	private final IPlayerMovementBehavior mMoveReceiver;
	
	/**
	 * the key manager that is used to set the controls for this object
	 */
	private final KeyManager mKeyManager;
	
	/**
	 * Whether the mode currently is active or not. Controlled by
	 * {@link #activate()} and {@link #terminate()}
	 */
	private boolean mIsActive;

	/**
	 * Creates a new local player control object, which lets the object move by using w, a, s, d.
	 * 
	 * @param parent the entity which is controlled by this object
	 * @param moveReceiver Move order receiving behavior object
	 * @param keyManager the key manager that is used to set the controls for this object
	 */
	public LocalPlayerControl(final ICanMove parent, final IPlayerMovementBehavior moveReceiver, final KeyManager keyManager) {
		
		mParent = parent;
		mMoveReceiver = moveReceiver;
		mKeyManager = keyManager;
		
		mIsActive = false;
	}

	@Override
	public void update(GameTime gameTime) {
		
		if(!(mKeyManager.up || mKeyManager.down || mKeyManager.left || mKeyManager.right)){
			mMoveReceiver.stopMovement();
			
		}
		
		mMoveReceiver.update(gameTime);
		
		if(!mIsActive){
			return;
		}
	
		
		if(mParent.isSprinting()){
			mMoveReceiver.runDown(mKeyManager.down);
			mMoveReceiver.runUp(mKeyManager.up);
			mMoveReceiver.runLeft(mKeyManager.left);
			mMoveReceiver.runRight(mKeyManager.right);
			
		}else{
			mMoveReceiver.moveDown(mKeyManager.down);
			mMoveReceiver.moveUp(mKeyManager.up);
			mMoveReceiver.moveLeft(mKeyManager.left);
			mMoveReceiver.moveRight(mKeyManager.right);
		
		}
		
	}

	@Override
	public void activate() {
		mIsActive = true;
		
	}

	@Override
	public void terminate() {
		mIsActive = false;
		
	}

}
