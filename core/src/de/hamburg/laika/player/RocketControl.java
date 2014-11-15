package de.hamburg.laika.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import de.hamburg.laika.inputmap.InputMap;
import de.hamburg.laika.inputmap.InputMap.Action;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

public class RocketControl {

	public final int buttonOffset = 200;
	public final int chargeTime = 200;
	
	public long timeCounter = 0;
	
	public long lastShot = 0;
	
	@Update
	public void update(Input i, OrthographicCamera cam, Vector2 pos, GameState gs) {
		timeCounter++;
		Vector3 tmp = cam.unproject(new Vector3(i.getX(), i.getY(), 0));
		Vector2 target = new Vector2(tmp.x, tmp.y);
		
		if (isCharged()) {
			if (target.y >= buttonOffset && i.isButtonPressed(Buttons.LEFT)) {
				lastShot = timeCounter;
				BulletFactory.createRocket(pos, gs, target, new Vector2(1,0), 50, 1, 20);
			}
		}	
	}
	
	public boolean isCharged() {
		return timeCounter - lastShot >= chargeTime;
	}
}
