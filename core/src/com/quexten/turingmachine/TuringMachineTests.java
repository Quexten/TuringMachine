package com.quexten.turingmachine;

import com.quexten.turingmachine.TuringTransition.Direction;

public class TuringMachineTests {
	
	public static TuringMachine getTestMachine() {		
		return getUnaryToBinaryMachine();
	}
	
	public static TuringMachine getUnaryToBinaryMachine() {
		TuringMachine machine = new TuringMachine("AAAAAAAA");
		
		TuringState turingState = new TuringState("q0", false);
		turingState.addTransition(new TuringTransition('A', 'A', "q0", Direction.Right));
		turingState.addTransition(new TuringTransition('$', '$', "q1", Direction.Left));
		turingState.addTransition(new TuringTransition('B', 'B', "q1", Direction.Left));
		machine.addState(turingState);

		turingState = new TuringState("q1", false);
		turingState.addTransition(new TuringTransition('A', 'B', "q2", Direction.Left));
		turingState.addTransition(new TuringTransition('0', '0', "q5", Direction.Right));
		turingState.addTransition(new TuringTransition('1', '1', "q5", Direction.Right));
		machine.addState(turingState);
		
		turingState = new TuringState("q2", false);
		turingState.addTransition(new TuringTransition('A', 'A', "q2", Direction.Left));
		turingState.addTransition(new TuringTransition('$', '1', "q0", Direction.Right));
		turingState.addTransition(new TuringTransition('0', '1', "q0", Direction.Right));
		turingState.addTransition(new TuringTransition('1', '0', "q3", Direction.Left));
		machine.addState(turingState);
		
		turingState = new TuringState("q3", false);
		turingState.addTransition(new TuringTransition('1', '0', "q3", Direction.Left));
		turingState.addTransition(new TuringTransition('0', '1', "q4", Direction.Right));
		turingState.addTransition(new TuringTransition('$', '1', "q4", Direction.Right));
		machine.addState(turingState);
		
		turingState = new TuringState("q4", false);
		turingState.addTransition(new TuringTransition('1', '1', "q4", Direction.Right));
		turingState.addTransition(new TuringTransition('0', '0', "q4", Direction.Right));
		turingState.addTransition(new TuringTransition('A', 'A', "q0", Direction.Right));
		turingState.addTransition(new TuringTransition('B', 'B', "q0", Direction.None));
		machine.addState(turingState);
		
		turingState = new TuringState("q5", false);
		turingState.addTransition(new TuringTransition('B', '$', "q5", Direction.Right));
		turingState.addTransition(new TuringTransition('$', '$', "q6", Direction.Left));
		machine.addState(turingState);
		
		turingState = new TuringState("q6", false);
		turingState.addTransition(new TuringTransition('$', '$', "q6", Direction.Left));
		turingState.addTransition(new TuringTransition('1', '1', "q7", Direction.Left));
		turingState.addTransition(new TuringTransition('0', '0', "q7", Direction.Left));
		machine.addState(turingState);
		
		turingState = new TuringState("q7", false);
		turingState.addTransition(new TuringTransition('$', '$', "q8", Direction.Right));
		turingState.addTransition(new TuringTransition('1', '1', "q7", Direction.Left));
		turingState.addTransition(new TuringTransition('0', '0', "q7", Direction.Left));
		machine.addState(turingState);
		
		turingState = new TuringState("q8", true);
		machine.addState(turingState);
		
		machine.setCurrentState("q0");
		return machine;
	}
	
	/**
	 * This machine is just to validate the turing machine implementation working
	 */
	public void getWorkingExample() {
		TuringMachine machine = new TuringMachine("AAA");
		TuringState q1 = new TuringState("q1", false);
		q1.addTransition(new TuringTransition('A', 'B', "q2", Direction.Right));
		machine.addState(q1);

		TuringState q2 = new TuringState("q2", false);
		q2.addTransition(new TuringTransition('A', 'C', "q1", Direction.Right));
		q2.addTransition(new TuringTransition('$', '$', "q3", Direction.None));
		machine.addState(q2);
		
		TuringState q3 = new TuringState("q3", true);
		machine.addState(q3);
		
		machine.setCurrentState(q1);
	}
	
}
