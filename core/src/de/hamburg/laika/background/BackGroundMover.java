package de.hamburg.laika.background;

import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.Laika;
import de.kuro.lazyjam.cdiutils.annotations.Update;

public class BackGroundMover {
	
	public Vector2 speed = new Vector2(-40f,0f);
	
	@Update
	public void update(Vector2 pos) {
		pos.add(speed);
		if(pos.x < -Laika.WIDTH) {
			pos.x = Laika.WIDTH;
		}
	}

}
