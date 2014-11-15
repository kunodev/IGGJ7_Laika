package de.hamburg.laika.EnemyType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Render;

public class Comet implements IEnemyType {
	final Texture tex;
	final float rotationSpeed;
	float rotation;

	public Comet(Texture tex, float rotationSpeed) {
		this.tex = tex;
		this.rotationSpeed = rotationSpeed;
		this.rotation = MathUtils.random(360.0f);
	}

	@Render
	public void render(SpriteBatch batch, Vector2 pos) {
		rotation = (rotation + Gdx.graphics.getDeltaTime() * rotationSpeed) % 360.0f;
		batch.draw(tex, pos.x - tex.getWidth() * 0.5f, pos.y - tex.getHeight() * 0.5f, tex.getWidth() * 0.5f, tex.getHeight() * 0.5f, tex.getWidth(), tex.getHeight(), 1, 1, rotation, 0, 0, tex.getWidth(), tex.getHeight(), false, false);
	}
}
