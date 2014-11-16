package de.hamburg.laika.EnemyType;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
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
	public void hit(Collision co, GameState gs, AssetManager assetMan) {
		Sound s = assetMan.get("Hit_Hurt11.wav");
		s.play();
		co.thisGo.selfDestruct(gs);
		final HealthComponent health = co.otherGo.getComponent(HealthComponent.class);
		if (health != null && health.damage(damage)) {
			co.otherGo.selfDestruct(gs);
		}
	}
}
