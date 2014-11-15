package de.hamburg.laika.EnemyType;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.cdiutils.annotations.Update;

public class Comet implements IEnemyType {
	final float rotationSpeed;
	float rotation;
	
	public Comet(float rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
		this.rotation = MathUtils.random(360.0f);
	}
	
	@Update
	public void updateSprite(SpriteWrapper sw) {
		rotation = (rotation + rotationSpeed) % 360.0f;
		sw.s.setRotation(rotation);
	}

//	@Render
//	public void render(SpriteBatch batch, Vector2 pos) {
//		batch.draw(tex, pos.x - tex.getWidth() * 0.5f, pos.y - tex.getHeight() * 0.5f, tex.getWidth() * 0.5f, tex.getHeight() * 0.5f, tex.getWidth(), tex.getHeight(), 1, 1, rotation, 0, 0, tex.getWidth(), tex.getHeight(), false, false);
//	}
	
}
