package de.hamburg.laika.EnemyMovement;

import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Update;

public class CurveMovement implements IEnemyMovement{
	private final int velocity; // per second
	private double state;
	
	public CurveMovement(int velocity) {
		this.velocity = velocity;
		state = 1;
	}

	@Update
	public void update(Vector2 pos){
		
			if ( state > 0 ) {
				state -= 0.0025;
			}
			pos.x -= Math.cos(state) * velocity/2.0f;
			pos.y += Math.sin(state) * velocity/2.0f; 
	}
}
