package de.hamburg.laika.player;

import de.hamburg.laika.EnemyType.HealthComponent;
import de.kuro.lazyjam.cdiutils.annotations.Collide;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.tools.Collision;

public class Bullet {

	public int damage = 34;

	@Collide
	public void collide(Collision co, GameState gs) {
		co.thisGo.selfDestruct(gs);
		if (co.otherGo.getComponent(HealthComponent.class).damage(damage)) {
			co.otherGo.selfDestruct(gs);
		}
		//TODO: do stuff
	}

}
