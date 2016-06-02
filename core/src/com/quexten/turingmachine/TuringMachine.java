
package com.quexten.turingmachine;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.quexten.turingmachine.TuringTransition.Direction;

public class TuringMachine {

	public char[] tape = new char[512];
	public ArrayList<TuringState> nodes = new ArrayList<TuringState>();

	public TuringState currentState;
	public int currentPosition = tape.length / 2;
	public boolean finished = false;

	public TuringMachine (String initialTape) {
		// Clear Tape
		for (int i = 0; i < tape.length; i++)
			tape[i] = '$';

		// Write Initial Tape
		for (int i = 0; i < initialTape.length(); i++)
			tape[currentPosition + i] = initialTape.charAt(i);
	}

	public void addState (TuringState state) {
		nodes.add(state);
	}

	public void act () {
		if (currentState != null) {
			if (currentState.isEnd) {
				this.finished = true;
				return;
			}
			for (int i = 0; i < currentState.transitions.size(); i++) {
				TuringTransition transition = currentState.transitions.get(i);
				if (transition.readChar == read()) {
					setCurrentState(transition.transitionId);
					write(transition.writeChar);
					move(transition.dir);
					break;
				}				
			}
		} else {
			Gdx.app.error("Turing", "Error - No Starting State");
		}
	}

	public void setCurrentState (String id) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).id.equals(id))
				setCurrentState(nodes.get(i));
		}
	}

	public void setCurrentState (TuringState state) {
		this.currentState = state;
	}

	public void write (char c) {
		tape[currentPosition] = c;
	}

	public char read () {
		return tape[currentPosition];
	}

	private void move (Direction dir) {
		if (dir == Direction.Left)
			currentPosition--;
		else if (dir == Direction.Right)
			currentPosition++;

	}

}
