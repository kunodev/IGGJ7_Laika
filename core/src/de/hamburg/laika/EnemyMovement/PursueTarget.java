package de.hamburg.laika.EnemyMovement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;

public class PursueTarget {
	private Vector2 toShip = new Vector2();

	private final GameObject target;
	private final float speed;

	public PursueTarget(GameObject target, float speed) {
		this.target = target;
		this.speed = speed;
	}

	@Update
	public void update(Vector2 pos) {
		toShip.set(target.getPos().x - pos.x, target.getPos().y - pos.y);
		toShip.nor();
		toShip.scl(speed);

		pos.mulAdd(toShip, Gdx.graphics.getDeltaTime());
	}
}
