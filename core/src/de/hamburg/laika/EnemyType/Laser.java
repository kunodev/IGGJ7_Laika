package de.hamburg.laika.EnemyType;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

public class Laser {
	public final Texture tex;
	public final float pause;
	public final float onTime;
	public final int damage;

	public boolean isLaserOn;
	public float stateTime;

	public Laser(Texture tex, float pauseSeconds, float pauseVariance, float onTimeSeconds, float onTimeVariance, int damage) {
		this.tex = tex;
		this.pause = MathUtils.random(pauseSeconds - pauseVariance, pauseSeconds + pauseVariance);
		this.onTime = MathUtils.random(onTimeSeconds - onTimeVariance, onTimeSeconds + onTimeVariance);
		this.isLaserOn = false;
		this.damage = damage;
		tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
	}

	@Update
	public void update(GameState gs, GameObject go) {
		stateTime += gs.getTickTimer() / 1000.0f;
		if ((!isLaserOn && stateTime > pause) || (isLaserOn && stateTime > onTime)) {
			isLaserOn = !isLaserOn;
			stateTime = 0.f;
		}

		if (isLaserOn && !gs.getTaggedGameObjects("ship").isEmpty()) {
			final GameObject ship = gs.getFirstTaggedGameObject("ship");
			if (ship != null) {
				collide(go, ship, gs);
			}
		}
	}

	@Render
	public void render(SpriteBatch batch, Vector2 pos) {
		if (isLaserOn) {
			batch.draw(tex, 0, pos.y, 0.0f, tex.getHeight() * 0.5f, pos.x, tex.getHeight() * 0.25f, 1.0f, 1.0f, 0.0f, 0, 0, (int) pos.x, tex.getHeight(), false, false);
		}
	}

	public void collide(GameObject thisGo, GameObject otherGo, GameState gs) {
		if (isLaserOn) {
			final float dist = Intersector.distanceLinePoint(thisGo.getPos().x, thisGo.getPos().y, 0.0f, thisGo.getPos().y, otherGo.getPos().x, otherGo.getPos().y);
			if (dist < 8 && otherGo.getComponent(HealthComponent.class) != null && otherGo.getComponent(HealthComponent.class).damage(damage)) {
				otherGo.selfDestruct(gs);
				System.out.println("FREAKIN' LAIKA KIlLED YOU!");
			}
		}
	}
}
