package de.hamburg.laika.EnemyMovement;

import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Update;

public class LinearMovement implements IEnemyMovement {
	final Vector2 velocity; // per second

	public LinearMovement(Vector2 velocity) {
		this.velocity = velocity;
	}

	@Update
	public void update(Vector2 pos){
		pos.add(velocity);
	}
}
