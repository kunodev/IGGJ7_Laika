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

	public final int buttonOffset = 100;
	@Update
	public void update(Input i, OrthographicCamera cam, Vector2 pos) {
		
		
		Vector3 tmp = cam.unproject(new Vector3(i.getX(), i.getY(), 0));
		float x = tmp.x;
		float y = tmp.y;
		
		
		if (y >= buttonOffset) {
			
			
		}
	}
}
