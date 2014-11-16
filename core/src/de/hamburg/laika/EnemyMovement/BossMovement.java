package de.hamburg.laika.EnemyMovement;

import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.Laika;
import de.kuro.lazyjam.cdiutils.annotations.Update;

public class BossMovement {
	private final int velocity; // per second
	private int forward;
	private boolean state;

	public BossMovement(int velocity, int forward) {
		this.velocity = velocity;
		this.forward = forward;
		state = false;
	}

	@Update
	public void update(Vector2 pos){
		if( forward > 0 ) {
			pos.x -= velocity;
			forward--;
		} else {
			if (pos.y > Laika.HEIGHT - 150) {
				state = false;
			} else if ( pos.y < 150 ) {
				state = true;
			}
			
			if (state == true) {
				pos.y += velocity;
			} else {
				pos.y -= velocity;
			}
		}
	}
}
