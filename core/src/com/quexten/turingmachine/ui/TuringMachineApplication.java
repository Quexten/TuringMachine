
package com.quexten.turingmachine.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.quexten.turingmachine.TuringMachine;
import com.quexten.turingmachine.TuringMachineTests;

public class TuringMachineApplication extends ApplicationAdapter {

	final int spacing = 40;
	final Color uiColor = new Color(0.4f, 0.4f, 0.4f, 1.0f);
	boolean paused = true;

	TuringMachine machine;
	SpriteBatch batch;
	BitmapFont font;
	Texture pointerTexture;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("font.fnt"));
		font.setColor(uiColor);
		pointerTexture = new Texture(Gdx.files.internal("pointer.png"));

		machine = TuringMachineTests.getUnaryToBinaryMachine();
	}

	@Override
	public void render () {
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT))
			machine.act();
		if (Gdx.input.isKeyJustPressed(Keys.ENTER))
			this.paused = false;

		if (!this.paused) {
			machine.act();
		}

		if (!machine.finished)
			Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		else
			Gdx.gl.glClearColor(139 / 255f, 195 / 255f, 74 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		batch.begin();
		for (int i = 0; i < machine.tape.length; i++) {
			font.draw(batch, String.valueOf(machine.tape[i]),
				Gdx.graphics.getWidth() / 2 - machine.tape.length * spacing / 2 + i * spacing, Gdx.graphics.getHeight() / 2);
		}
		font.draw(batch, machine.currentState.id, 0, 30);
		batch.setColor(this.uiColor);
		batch.draw(this.pointerTexture,
			Gdx.graphics.getWidth() / 2 - machine.tape.length * spacing / 2 + machine.currentPosition * spacing - 15,
			Gdx.graphics.getHeight() / 2 + 10);
		batch.setColor(Color.WHITE);
		batch.end();
	}
}
