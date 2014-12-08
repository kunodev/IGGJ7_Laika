package de.hamburg.laika.EnemyType;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import de.hamburg.laika.player.Bullet;
import de.kuro.lazyjam.cdiutils.annotations.Collide;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.tools.Collision;

public class BagCat implements IEnemyType {
	@Collide
	public void kill(Collision co, GameState gs, HealthComponent hc, AssetManager assetMan) {
		Sound s = assetMan.get("Hit_Hurt11.wav");
		s.play();
		co.otherGo.selfDestruct(gs);
		Bullet bullet = co.otherGo.getComponent(Bullet.class);
		if(bullet != null && hc != null && hc.damage(bullet.damage)) {
			co.thisGo.selfDestruct(gs);
		}
	}
}
