package de.hamburg.laika.EnemyType;

import de.hamburg.laika.player.Bullet;
import de.kuro.lazyjam.cdiutils.annotations.Collide;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.tools.Collision;

public class Projectile implements IEnemyType {
	public final int damage;

	public Projectile(int damage) {
		this.damage = damage;
	}

	public Projectile() {
		this.damage = 25;
	}

	@Collide
	public void hit(Collision co, GameState gs) {
		HealthComponent health = co.otherGo.getComponent(HealthComponent.class);
		if (health != null && co.otherGo.getComponent(HealthComponent.class).damage(damage)) {
			co.otherGo.selfDestruct(gs);
		}

		if (co.thisGo.getComponent(HealthComponent.class).damage(co.otherGo.getComponent(Bullet.class).damage)) {
			co.thisGo.selfDestruct(gs);
		}
	}
}
