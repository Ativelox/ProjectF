package de.cormag.projectf.states;

import java.awt.Graphics;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class StateManager {

	private Stack<State> stateStack;

	private GameState gameState;

	public StateManager() {

		stateStack = new Stack<>();

	}

	public void render(Graphics g) {

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

			state.render(g);

		}
	}

	public void tick() {

		LinkedList<State> list = new LinkedList<>(stateStack);
		Collections.reverse(list);

		for (State state : list) {

			state.tick();

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

}
