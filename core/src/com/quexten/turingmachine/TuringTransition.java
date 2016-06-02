
package com.quexten.turingmachine;

public class TuringTransition {

	public enum Direction {
		Right, Left, None
	};
	
	//The Character that needs to be read in order to transition
	char readChar;
	//The Character that needs to be written when transitioning
	char writeChar;
	//The Id of the state that needs to be transitioned to
	String transitionId;
	//The direction the turing machine moves towards
	Direction dir;

	public TuringTransition (char readChar, char writeChar, String transitionId, Direction dir) {
		this.readChar = readChar;
		this.writeChar = writeChar;
		this.transitionId = transitionId;
		this.dir = dir;
	}

}
