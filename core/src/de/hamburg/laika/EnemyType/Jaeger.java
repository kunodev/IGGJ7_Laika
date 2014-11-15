package de.hamburg.laika.EnemyType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;

public class Jaeger implements IEnemyType {
	private Vector2 toTarget = new Vector2();

	private final GameObject target;
	private final float speed;
	private final Texture tex;

	public Jaeger(Texture tex, float speed, GameObject target) {
		this.tex = tex;
		this.speed = speed;
		this.target = target;
	}

	@Update
	public void update(Vector2 pos) {
		toTarget.set(target.getPos().x - pos.x, target.getPos().y - pos.y);
		toTarget.nor().scl(speed);

		pos.mulAdd(toTarget, Gdx.graphics.getDeltaTime());
	}

	@Render
	public void render(SpriteBatch batch, Vector2 pos) {
		batch.draw(tex, pos.x - tex.getWidth() * 0.5f, pos.y - tex.getHeight() * 0.5f, tex.getWidth() * 0.5f, tex.getHeight() * 0.5f, tex.getWidth(), tex.getHeight(), 1, 1, toTarget.angle(), 0, 0, tex.getWidth(), tex.getHeight(), false, false);
	}
}
