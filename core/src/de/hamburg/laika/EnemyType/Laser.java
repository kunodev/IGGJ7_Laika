package de.hamburg.laika.EnemyType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;

public class Laser {
	public final Texture tex;
	public final float pause;
	public final float onTime;

	public boolean isLaserOn;
	public float stateTime;

	public Laser(Texture tex, float pauseSeconds, float pauseVariance, float onTimeSeconds, float onTimeVariance) {
		this.tex = tex;
		this.pause = MathUtils.random(pauseSeconds - pauseVariance, pauseSeconds + pauseVariance);
		this.onTime = MathUtils.random(onTimeSeconds - onTimeVariance, onTimeSeconds + onTimeVariance);
		this.isLaserOn = false;
		tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
	}

	@Update
	public void update(){
		stateTime += Gdx.graphics.getDeltaTime();
		if((!isLaserOn && stateTime > pause) || (isLaserOn && stateTime > onTime)){
			isLaserOn = !isLaserOn;
			stateTime = 0.f;
		}
	}

	@Render
	public void render(SpriteBatch batch, Vector2 pos) {
		if(isLaserOn){
			batch.draw(tex, 0, pos.y, 0.0f, tex.getHeight() * 0.5f, pos.x, tex.getHeight() * 0.25f, 1.0f, 1.0f, 0.0f, 0, 0, (int)pos.x, tex.getHeight(), false, false);
		}
	}
}
