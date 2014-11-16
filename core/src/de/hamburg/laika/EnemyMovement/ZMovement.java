package de.hamburg.laika.EnemyMovement;

import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Update;

public class ZMovement implements IEnemyMovement{
	private final int velocity; // per second
	private int forward;
	private int diagonal;

	public ZMovement(int velocity, int forward, int diagonal) {
		this.velocity = velocity;
		this.forward = forward;
		this.diagonal = diagonal;
	}

	@Update
	public void update(Vector2 pos){
		if( forward > 0 ) {
			pos.x -= velocity;
			forward--;
		} else if ( diagonal > 0 ) {
			pos.x += velocity/2.f;
			pos.y += velocity/2.f;
			diagonal--;
		} else {
			pos.x -= velocity;
		}
	}
}
