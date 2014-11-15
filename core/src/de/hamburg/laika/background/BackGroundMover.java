package de.hamburg.laika.background;

import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.Laika;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.cdiutils.annotations.Update;

public class BackGroundMover {
	
	public Vector2 speed = new Vector2(0.01f,0f);
	public float x = 0f;
	public float y = 0f;
	
	@Update
	public void update(SpriteWrapper sw) {
		x += speed.x;
		y += speed.y;
		sw.s.setRegion(x, y, x+1, y+1);
	}

}
