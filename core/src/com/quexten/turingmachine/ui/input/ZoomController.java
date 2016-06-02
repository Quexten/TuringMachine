
package com.quexten.turingmachine.ui.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ZoomController implements InputProcessor {

	private OrthographicCamera camera;
	private boolean hasToLerpZoom;
	private boolean hasToLerpPosition;
	private float targetZoom;
	private Vector2 targetPosition = new Vector2();
	private Vector2 dragAnchorPosition = new Vector2();

	public ZoomController (OrthographicCamera camera) {
		this.camera = camera;
	}

	public void update () {
		if (hasToLerpZoom)
			camera.zoom += 5 * Gdx.graphics.getDeltaTime() * (targetZoom - camera.zoom);
		if (hasToLerpPosition)
			camera.position.lerp(new Vector3(targetPosition.x, targetPosition.y, 0), 5 * Gdx.graphics.getDeltaTime());
		if (Math.abs(targetZoom - camera.zoom) < 0.00001f)
			hasToLerpZoom = hasToLerpPosition = false;
		camera.update();
	}

	@Override
	public boolean keyDown (int keycode) {
		return false;
	}

	@Override
	public boolean keyUp (int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped (char character) {
		return false;
	}

	@Override
	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		Vector3 unprojectedPosition = camera.unproject(new Vector3(screenX, screenY, 0));
		dragAnchorPosition = new Vector2(unprojectedPosition.x, unprojectedPosition.y);
		return false;
	}

	@Override
	public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged (int screenX, int screenY, int pointer) {
		int screenCenterWidth = Gdx.graphics.getWidth();
		int screenCenterHeight = Gdx.graphics.getHeight();
		float screenDiffX = screenCenterWidth - Gdx.input.getX() - screenCenterWidth / 2f;
		float screenDiffY = (screenCenterHeight - (Gdx.graphics.getHeight() - Gdx.input.getY()) - screenCenterHeight / 2f);
		camera.position.set(dragAnchorPosition.x + screenDiffX * camera.zoom, dragAnchorPosition.y + screenDiffY * camera.zoom, 0);
		camera.update();
		hasToLerpPosition = false;

		return false;
	}

	@Override
	public boolean mouseMoved (int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled (int amount) {
		hasToLerpZoom = hasToLerpPosition = true;
		float lastzoom = camera.zoom;
		Vector2 lastposition = new Vector2(camera.position.x, camera.position.y);
		targetZoom += amount * camera.zoom * 0.5;
		targetZoom = Math.min(Math.max(1f, targetZoom), 5);
		Vector3 worldPos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0));
		targetPosition = new Vector2(worldPos.x, worldPos.y);
		targetPosition = targetPosition.add(lastposition.sub(targetPosition).scl(targetZoom / lastzoom));
		return false;
	}

}
