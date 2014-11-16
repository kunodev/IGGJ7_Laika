package de.hamburg.laika.EnemyMovement;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Update;

public class CurveMovement implements IEnemyMovement{
	private final int velocity; // per second
	private float state;
	
	public CurveMovement(int velocity) {
		this.velocity = velocity;
		state = 1;
	}

	@Update
	public void update(Vector2 pos){
			if ( state > -1 ) {
				state -= 0.0025;
			}else{
				state = 1;
			}
			pos.x -= MathUtils.cos(state) * velocity;
			pos.y += MathUtils.sin(state) * velocity;
	}
}
