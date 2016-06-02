package com.quexten.turingmachine;

import java.util.ArrayList;

public class TuringState {

	public String id;
	boolean isEnd;
	ArrayList<TuringTransition> transitions = new ArrayList<TuringTransition>();	
	
	public TuringState(String id, boolean isEnd) {
		this.id = id;
		this.isEnd = isEnd;
	}
	
	public void addTransition(TuringTransition transition) {
		this.transitions.add(transition);
	}
	
}