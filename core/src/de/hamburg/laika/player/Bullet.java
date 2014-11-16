package de.hamburg.laika.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import de.hamburg.laika.EnemyType.HealthComponent;
import de.kuro.lazyjam.cdiutils.annotations.Collide;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.tools.Collision;

public class Bullet {

	public int damage = 34;
	boolean sounded = false; 

	@Update
	public void doShizzle(AssetManager assetMan) {
		if(!sounded) {
			sounded = true;
			Sound s = assetMan.get("Laser_Shoot11.wav");
			s.play();
		}
	}
	
	@Collide
	public void collide(Collision co, GameState gs) {
		co.thisGo.selfDestruct(gs);
		if (co.otherGo.getComponent(HealthComponent.class).damage(damage)) {
			co.otherGo.selfDestruct(gs);
		}
		//TODO: do stuff
	}

}
