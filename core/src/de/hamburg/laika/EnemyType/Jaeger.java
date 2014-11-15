package de.hamburg.laika.EnemyType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.player.Bullet;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.cdiutils.annotations.Collide;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.tools.Collision;

public class Jaeger implements IEnemyType {
	private Vector2 toTarget = new Vector2();

	private final Vector2 target;
	private final float speed;
//	private final Texture tex;

//	public Jaeger(Texture tex, float speed, GameObject target) {
//		this.tex = tex;
	public Jaeger(float speed, Vector2 target) {
		this.speed = speed;
		this.target = target;
	}

	@Update
	public void update(Vector2 pos, SpriteWrapper sw) {
		toTarget.set(target.x - pos.x, target.y - pos.y);
		toTarget.nor().scl(speed);
		sw.s.setPosition(pos.x, pos.y);
		sw.s.setRotation(toTarget.angle());
		pos.add(toTarget);
	}
	

	@Collide
	public void kill(Collision co, GameState gs, HealthComponent hc) {
		co.otherGo.selfDestruct(gs);
		if(hc.damage(co.otherGo.getComponent(Bullet.class).damage)) {
			co.thisGo.selfDestruct(gs);
		}
	}
	
//	@Render
//	public void render(SpriteBatch batch, Vector2 pos) {
////		batch.draw(tex, pos.x - tex.getWidth() * 0.5f, pos.y - tex.getHeight() * 0.5f, tex.getWidth() * 0.5f, tex.getHeight() * 0.5f, tex.getWidth(), tex.getHeight(), 1, 1, toTarget.angle(), 0, 0, tex.getWidth(), tex.getHeight(), false, false);
//	}
}
