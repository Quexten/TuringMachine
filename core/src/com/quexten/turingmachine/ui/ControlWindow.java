
package com.quexten.turingmachine.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.quexten.turingmachine.TuringMachineTests;

public class ControlWindow extends VisWindow {

	public ControlWindow (String title) {
		super(title);
		this.setResizable(true);
		this.setPosition(99999, 99999);
		
		final VisTextField inputField = new VisTextField();
		this.add(inputField);
		VisTextButton updateTapeButton = new VisTextButton("Update Tape");
		updateTapeButton.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				((TuringMachineApplication)Gdx.app.getApplicationListener()).machine.setTape(inputField.getText());
			}			
		});
		this.add(updateTapeButton).fill();
		this.row();
		
		VisTextButton resetButton = new VisTextButton("Reset");
		resetButton.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				((TuringMachineApplication)Gdx.app.getApplicationListener()).paused = true;
				((TuringMachineApplication)Gdx.app.getApplicationListener()).machine = TuringMachineTests.getTestMachine();
				((TuringMachineApplication)Gdx.app.getApplicationListener()).machine.setTape(inputField.getText());
			}			
		});
		this.add(resetButton).fill();	
		VisTextButton stepButton = new VisTextButton("Step");
		stepButton.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				((TuringMachineApplication)Gdx.app.getApplicationListener()).machine.act();
			}			
		});
		this.add(stepButton).fill();
		this.row();
		
		VisTextButton runButton = new VisTextButton("Run");
		runButton.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				((TuringMachineApplication)Gdx.app.getApplicationListener()).paused = false;
			}			
		});
		this.add(runButton).fill();
		VisTextButton pauseButton = new VisTextButton("Pause");
		pauseButton.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				((TuringMachineApplication)Gdx.app.getApplicationListener()).paused = true;
			}			
		});
		this.add(pauseButton).fill();
		
		
		this.pack();
	}

}
