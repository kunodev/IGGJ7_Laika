package de.hamburg.laika.EnemyType;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

import de.hamburg.laika.player.Bullet;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.cdiutils.annotations.Collide;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.tools.Collision;

public class Comet implements IEnemyType {

	@Collide
	public void kill(Collision co, GameState gs, HealthComponent hc, AssetManager assetMan) {
		Sound s = assetMan.get("Hit_Hurt11.wav");
		s.play();
		co.otherGo.selfDestruct(gs);
		if(hc.damage(co.otherGo.getComponent(Bullet.class).damage)) {
			co.thisGo.selfDestruct(gs);
		}
	}
}
