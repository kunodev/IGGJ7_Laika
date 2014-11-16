package de.hamburg.laika.EnemyType;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import de.hamburg.laika.Laika;
import de.kuro.lazyjam.asciiassetextension.IRectangleProvider;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

import java.util.Iterator;

public class Laser {
	public final Texture tex;
	public final float pause;
	public final float onTime;
	public final int damage;
	public final float yRenderOffset;
	public final float xRenderOffset;

	public boolean isLaserOn;
	public float stateTime;
	private Animation animation;

	public Laser(Texture tex, Animation animation, float pauseSeconds, float pauseVariance, float onTimeSeconds, float onTimeVariance, int damage, float xRenderOffset, float yRenderOffset) {
		this.tex = tex;
		this.animation = animation;
		this.pause = MathUtils.random(pauseSeconds - pauseVariance, pauseSeconds + pauseVariance);
		this.onTime = MathUtils.random(onTimeSeconds - onTimeVariance, onTimeSeconds + onTimeVariance);
		this.isLaserOn = false;
		this.damage = damage;
		this.xRenderOffset = xRenderOffset;
		this.yRenderOffset = yRenderOffset;
		tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
	}

	@Update
	public void update(GameState gs, GameObject go, AssetManager assetMan) {
		stateTime += gs.getTickTimer() / 1000.0f;
		if ((!isLaserOn && stateTime > pause) || (isLaserOn && stateTime > onTime)) {
			isLaserOn = !isLaserOn;
			stateTime = 0.f;
		}

		if (isLaserOn && !gs.getTaggedGameObjects(Laika.TAG_PLAYER).isEmpty()) {
			for (Iterator<GameObject> it = gs.getTaggedGameObjects(Laika.TAG_PLAYER).iterator(); it.hasNext(); ) {
				final GameObject ship = it.next();
				collide(go, ship, gs, assetMan);
			}
		}
	}

	@Render
	public void render(SpriteBatch batch, Vector2 pos) {
		if (isLaserOn) {
			final TextureRegion texReg = animation.getKeyFrame(stateTime);
			batch.draw(tex, 0, pos.y + yRenderOffset, 0.0f, tex.getHeight(), pos.x + xRenderOffset, tex.getHeight(), 1.0f, 1.0f, 0.0f, 0, 0, (int) (pos.x + xRenderOffset), tex.getHeight(), false, false);
			batch.draw(texReg, pos.x - texReg.getRegionWidth() * 0.5f, pos.y - texReg.getRegionHeight() * 0.5f);
		}
	}

	public void collide(GameObject thisGo, GameObject otherGo, GameState gs, AssetManager assetMan) {
		if (isLaserOn) {
			float distanceThreshold = (otherGo.getComponent(IRectangleProvider.class) != null) ? otherGo.getComponent(IRectangleProvider.class).getRectangle().getHeight() * 0.5f : 8.0f;

			final float dist = Intersector.distanceLinePoint(thisGo.getPos().x, thisGo.getPos().y, 0.0f, thisGo.getPos().y, otherGo.getPos().x, otherGo.getPos().y);
			if (dist < distanceThreshold && otherGo.getComponent(HealthComponent.class) != null) {
				Sound s = assetMan.get("Hit_Hurt11.wav");
				s.play();
				if (otherGo.getComponent(HealthComponent.class).damage(damage)) {
					otherGo.selfDestruct(gs);
				}
			}
		}
	}
}
