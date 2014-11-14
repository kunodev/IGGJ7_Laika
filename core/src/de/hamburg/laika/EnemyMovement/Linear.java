package de.hamburg.laika.EnemyMovement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.kuro.lazyjam.cdiutils.annotations.Update;

public class Linear {
	final Vector2 velocity; // per second

	public Linear(Vector2 velocity) {
		this.velocity = velocity;
	}

	@Update
	public void update(Vector2 pos){
		pos.mulAdd(velocity, Gdx.graphics.getDeltaTime());
	}
}
