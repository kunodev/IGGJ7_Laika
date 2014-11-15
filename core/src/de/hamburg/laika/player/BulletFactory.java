package de.hamburg.laika.player;

import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.EnemyType.HealthComponent;
import de.kuro.lazyjam.asciiassetextension.ASCIIPicture;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.ExtraSimpleCollisionComponent;
import de.kuro.lazyjam.ecmodel.concrete.components.VelocityComponent;

public class BulletFactory {
	
	public static void createBullet(Vector2 pos, GameState gs) {
		createBullet(pos, gs, new Vector2(1,0), 10);
	}
	
	public static void createBullet(Vector2 pos, GameState gs, Vector2 direction, float speed) {
		GameObject bullet = new GameObject(pos.cpy(), gs);
		Bullet comp = new Bullet();
		bullet.addComponent(comp);
		VelocityComponent vc = new VelocityComponent();
		vc.v.set(direction.nor().scl(speed));
		bullet.addComponent(vc);
		ASCIIPicture asciiPic = new ASCIIPicture("PEW");
		bullet.addComponent(asciiPic);
		ExtraSimpleCollisionComponent simpleCollision = new ExtraSimpleCollisionComponent();
		simpleCollision.filters.add( e-> e.getComponent(HealthComponent.class) != null);
		bullet.addComponent(simpleCollision);
	}
	

	public static void createRocket(Vector2 pos, GameState gs, Vector2 direction, float speed) {
		GameObject bullet = new GameObject(pos.cpy(), gs);
		Bullet comp = new Bullet();
		// double damage ftw!
		comp.damage *= 2;
		bullet.addComponent(comp);
		VelocityComponent vc = new VelocityComponent();
		vc.v.set(direction.nor().scl(speed));
		bullet.addComponent(vc);
		ASCIIPicture asciiPic = new ASCIIPicture("SWOSH");
		bullet.addComponent(asciiPic);
		ExtraSimpleCollisionComponent simpleCollision = new ExtraSimpleCollisionComponent();
		simpleCollision.filters.add( e-> e.getComponent(HealthComponent.class) != null);
		bullet.addComponent(simpleCollision);
	}	
	
}
