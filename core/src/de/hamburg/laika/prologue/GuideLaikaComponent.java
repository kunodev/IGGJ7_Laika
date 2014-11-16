package de.hamburg.laika.prologue;

import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.simpleservice.FontManager;

public class GuideLaikaComponent {
	
	public static final int MAX_TICKS = 640;
	
	public int ticks;
	
	@Update
	public void guide(Vector2 pos) {
		ticks++;
		if(!done()) {
			pos.x += 1f;
		}
	}
	
	public boolean done() {
		return ticks > MAX_TICKS;
	}
	
	

}
