package de.cormag.projectf.states;

import java.awt.Graphics;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import de.cormag.projectf.entities.properties.IRenderable;
import de.cormag.projectf.entities.properties.IUpdateable;
import de.cormag.projectf.utils.time.GameTime;

public class StateManager implements IUpdateable, IRenderable {

	private Stack<State> stateStack;

	private GameState gameState;

	public StateManager() {

		stateStack = new Stack<>();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IRenderable#render(java.awt.
	 * Graphics)
	 */
	@Override
	public void render(Graphics g, final GameTime gameTime) {

		LinkedList<State> tempList = new LinkedList<>();
		LinkedList<State> list = new LinkedList<>(stateStack);
		Collections.reverse(list);

		for (State state : list) {

			tempList.add(state);

			if (!state.renderLower()) {

				break;

			}

		}

		Collections.reverse(tempList);

		for (State state : tempList) {

			state.render(g, gameTime);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IUpdateable#update()
	 */
	@Override
	public void update(final GameTime gameTime) {

		LinkedList<State> list = new LinkedList<>(stateStack);
		Collections.reverse(list);

		for (State state : list) {

			state.update(gameTime);

			if (!state.tickLower()) {

				break;

			}

		}

	}

	public State push(State state) {

		if (state instanceof GameState) {

			this.gameState = (GameState) state;

		}

		return stateStack.push(state);

	}

	public State pop() {

		return stateStack.pop();

	}

	public void clear() {

		this.gameState = null;

		stateStack.clear();

	}

	public State peek() {

		if (stateStack.isEmpty()) {

			return null;

		} else {

			return stateStack.peek();

		}
	}

	public Iterator<State> getStateIterator() {

		return stateStack.iterator();

	}

	public GameState getGameState() {

		return gameState;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.cormag.projectf.entities.properties.IRenderable#getLayer()
	 */
	@Override
	public int getLayer() {
		return IRenderable.DEFAULT_LAYER;
	}

}
