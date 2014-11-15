package de.hamburg.laika.prologue;

import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Update;

public class GuideLaikaComponent {
	
	public static final int MAX_TICKS = 500;
	
	public int ticks;
	
	@Update
	public void guide(Vector2 pos) {
		ticks++;
		if(ticks <= MAX_TICKS) {
			pos.x += 1f;
		}
	}
	
	

}
