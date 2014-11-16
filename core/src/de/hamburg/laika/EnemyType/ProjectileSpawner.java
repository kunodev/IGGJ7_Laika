package de.hamburg.laika.EnemyType;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import de.hamburg.laika.EnemyMovement.LinearMovement;
import de.hamburg.laika.Laika;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

public class ProjectileSpawner {

	final float spawnChancePerTick;
	final float speed;
	final Texture tex;
	final int damage;

	public ProjectileSpawner(float spawnChancePerTick, float speed, Texture tex, int damage) {
		this.spawnChancePerTick = spawnChancePerTick;
		this.speed = speed;
		this.tex = tex;
		this.damage = damage;
	}

	@Update
	public void spawnProjectile(Vector2 pos, GameState gs, GameObject go){
		if(MathUtils.random() < spawnChancePerTick){
			GameObject projectile = new GameObject(pos.cpy(), Laika.TAG_ENEMY, gs);
			projectile.addComponent(new SpriteWrapper(tex));
			projectile.addComponent(new LinearMovement(new Vector2(-speed, 0.0f)));
			projectile.addComponent(new Projectile(damage));
		}
	}
}
