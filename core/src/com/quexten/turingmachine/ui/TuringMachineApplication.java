
package com.quexten.turingmachine.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.VisUI;
import com.quexten.turingmachine.TuringMachine;
import com.quexten.turingmachine.TuringMachineTests;
import com.quexten.turingmachine.ui.input.ZoomController;

public class TuringMachineApplication extends ApplicationAdapter {

	final int spacing = 40;
	final Color uiColor = new Color(0.4f, 0.4f, 0.4f, 1.0f);
	boolean paused = true;

	TuringMachine machine;

	Stage stage;
	OrthographicCamera camera;
	ZoomController controller;
	SpriteBatch batch;
	BitmapFont font;
	Texture pointerTexture;

	@Override
	public void create () {
		VisUI.load();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		controller = new ZoomController(camera);
		
		stage = new Stage();
		stage.addActor(new ControlWindow("Controls"));	
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(controller);
		Gdx.input.setInputProcessor(multiplexer);

		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("font.fnt"));
		font.setColor(uiColor);
		pointerTexture = new Texture(Gdx.files.internal("pointer.png"));

		machine = TuringMachineTests.getUnaryToBinaryMachine();
	}

	@Override
	public void render () {
		controller.update();
		stage.act();

		if (!this.paused) {
			machine.act();
		}

		if (!machine.finished)
			Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		else
			Gdx.gl.glClearColor(139 / 255f, 195 / 255f, 74 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (int i = 0; i < machine.tape.length; i++) {
			font.draw(batch, String.valueOf(machine.tape[i]), -machine.tape.length * spacing / 2 + i * spacing, 0);
		}
		font.draw(batch, machine.currentState.id, -Gdx.graphics.getWidth() / 2, 30 - Gdx.graphics.getHeight() / 2);
		batch.setColor(this.uiColor);
		batch.draw(this.pointerTexture, -machine.tape.length * spacing / 2 + machine.currentPosition * spacing - 15,10);
		batch.setColor(Color.WHITE);
		batch.end();
		stage.draw();
	}
}
