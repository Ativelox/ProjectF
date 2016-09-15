package de.cormag.projectf.logic.movement;

import java.awt.geom.Point2D;
import java.util.Optional;

import de.cormag.projectf.entities.creatures.Creature;
import de.cormag.projectf.entities.properties.ICanMove;
import de.cormag.projectf.entities.properties.IHaveAnimations;
import de.cormag.projectf.utils.time.GameTime;

public class LocalPlayerMovementBehavior implements IPlayerMovementBehavior {

	private final ICanMove mParent;

	private boolean mHasAnimation;

	private Optional<ICanMove> mFollowTarget;

	private Optional<Point2D> mCurrentDestination;

	private boolean mIsMoving;

	public LocalPlayerMovementBehavior(ICanMove parent) {
		mParent = parent;
		mFollowTarget = Optional.empty();
		mCurrentDestination = Optional.empty();

		mIsMoving = false;

		if (mParent instanceof IHaveAnimations) {
			mHasAnimation = true;

		} else {
			mHasAnimation = false;
		}

	}

	@Override
	public boolean isMoving() {
		return mIsMoving;
	}

	@Override
	public void moveTo(float x, float y) {
		if (mFollowTarget.isPresent()) {
			mFollowTarget = Optional.empty();
		}

		mCurrentDestination = Optional.of(new Point2D.Float(x, y));

		mIsMoving = true;
	}

	@Override
	public void follow(ICanMove target) {
		if (mCurrentDestination.isPresent()) {
			mCurrentDestination = Optional.empty();
		}

		mFollowTarget = Optional.of(target);
		mIsMoving = true;
	}

	@Override
	public void update(GameTime gameTime) {

		if (!mIsMoving) {
			return;
		}

		if (mFollowTarget.isPresent()) {
			ICanMove target = mFollowTarget.get();
			mCurrentDestination = Optional.of(new Point2D.Float(target.getRelativeX(), target.getRelativeY()));

		}

		// TODO: in general remove those collision codes from creature and move
		// them to icanmove or a seperate collisionmanager,
		// so you are no longer bound to being a creature in order to check for
		// collisions

		// TODO: also check for tile collision

		if (mCurrentDestination.isPresent() && mParent instanceof Creature) {
			Creature mParentAsCreature = (Creature) mParent;
			Point2D destination = mCurrentDestination.get();

			if (!mParentAsCreature.checkEntityCollisions(destination.getX() - mParent.getRelativeX(), 0f)) {
				mParent.setRelativeX((float) destination.getX());

			}
			if (!mParentAsCreature.checkEntityCollisions(0f, destination.getY() - mParent.getRelativeY())) {
				mParent.setRelativeY((float) destination.getY());

			}

		}

	}

	@Override
	public void stopMovement() {

		mIsMoving = false;
	}

	@Override
	public void moveUp(boolean isMoving) {
		if (isMoving) {
			moveTo(mParent.getRelativeX(), mParent.getRelativeY() - mParent.getMovementSpeed());

			if (mHasAnimation) {
				((IHaveAnimations) mParent).setVerticalDirection(-1);

			}
		} else {
			if (mHasAnimation && ((IHaveAnimations) mParent).getVerticalDirection() == -1) {
				((IHaveAnimations) mParent).setVerticalDirection(0);

			}

		}

	}

	@Override
	public void moveDown(boolean isMoving) {
		if (isMoving) {
			moveTo(mParent.getRelativeX(), mParent.getRelativeY() + mParent.getMovementSpeed());

			if (mHasAnimation) {
				((IHaveAnimations) mParent).setVerticalDirection(1);

			}
		} else {
			if (mHasAnimation && ((IHaveAnimations) mParent).getVerticalDirection() == 1) {
				((IHaveAnimations) mParent).setVerticalDirection(0);

			}

		}

	}

	@Override
	public void moveRight(boolean isMoving) {
		if (isMoving) {
			moveTo(mParent.getRelativeX() + mParent.getMovementSpeed(), mParent.getRelativeY());

			if (mHasAnimation) {
				((IHaveAnimations) mParent).setHorizontalDirection(1);

			}
		} else {
			if (mHasAnimation && ((IHaveAnimations) mParent).getHorizontalDirection() == 1) {
				((IHaveAnimations) mParent).setHorizontalDirection(0);

			}

		}
	}

	@Override
	public void moveLeft(boolean isMoving) {
		if (isMoving) {
			moveTo(mParent.getRelativeX() - mParent.getMovementSpeed(), mParent.getRelativeY());

			if (mHasAnimation) {
				((IHaveAnimations) mParent).setHorizontalDirection(-1);

			}
		} else {
			if (mHasAnimation && ((IHaveAnimations) mParent).getHorizontalDirection() == -1) {
				((IHaveAnimations) mParent).setHorizontalDirection(0);

			}

		}

	}

	@Override
	public void runUp(boolean isMoving) {
		if (isMoving) {
			moveTo(mParent.getRelativeX(), mParent.getRelativeY() - mParent.getRunningSpeed());

			if (mHasAnimation) {
				((IHaveAnimations) mParent).setVerticalDirection(-1);

			}
		} else {
			if (mHasAnimation && ((IHaveAnimations) mParent).getVerticalDirection() == -1) {
				((IHaveAnimations) mParent).setVerticalDirection(0);

			}

		}

	}

	@Override
	public void runDown(boolean isMoving) {
		if (isMoving) {
			moveTo(mParent.getRelativeX(), mParent.getRelativeY() + mParent.getRunningSpeed());

			if (mHasAnimation) {
				((IHaveAnimations) mParent).setVerticalDirection(1);

			}
		} else {
			if (mHasAnimation && ((IHaveAnimations) mParent).getVerticalDirection() == 1) {
				((IHaveAnimations) mParent).setVerticalDirection(0);

			}

		}

	}

	@Override
	public void runRight(boolean isMoving) {
		if (isMoving) {
			moveTo(mParent.getRelativeX() + mParent.getRunningSpeed(), mParent.getRelativeY());

			if (mHasAnimation) {
				((IHaveAnimations) mParent).setHorizontalDirection(1);

			}
		} else {
			if (mHasAnimation && ((IHaveAnimations) mParent).getHorizontalDirection() == 1) {
				((IHaveAnimations) mParent).setHorizontalDirection(0);

			}

		}
	}

	@Override
	public void runLeft(boolean isMoving) {
		if (isMoving) {
			moveTo(mParent.getRelativeX() - mParent.getRunningSpeed(), mParent.getRelativeY());

			if (mHasAnimation) {
				((IHaveAnimations) mParent).setHorizontalDirection(-1);

			}
		} else {
			if (mHasAnimation && ((IHaveAnimations) mParent).getHorizontalDirection() == -1) {
				((IHaveAnimations) mParent).setHorizontalDirection(0);

			}

		}

	}

}
