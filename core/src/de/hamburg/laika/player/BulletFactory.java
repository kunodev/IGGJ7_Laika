package de.hamburg.laika.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.Laika;
import de.hamburg.laika.EnemyType.HealthComponent;
import de.kuro.lazyjam.asciiassetextension.ASCIIPicture;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.ExtraSimpleCollisionComponent;
import de.kuro.lazyjam.ecmodel.concrete.components.VelocityComponent;

public class BulletFactory {
	
	public static Texture HAMMER;
	
	public static void createBullet(Vector2 pos, GameState gs) {
		createBullet(pos, gs, new Vector2(1,0), 10, true);
	}
	
	public static void createBullet(Vector2 pos, GameState gs, Vector2 direction, float speed, boolean playerBullet) {
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
		simpleCollision.tagToSearch = Laika.TAG_ENEMY;
		bullet.addComponent(simpleCollision);
	}
	
	public static void createBulletHammer(Vector2 pos, GameState gs, Vector2 direction, float speed, boolean playerBullet) {
		GameObject bullet = new GameObject(pos.cpy(), gs);
		Bullet comp = new Bullet();
		bullet.addComponent(comp);
		VelocityComponent vc = new VelocityComponent();
		vc.v.set(direction.nor().scl(speed));
		bullet.addComponent(vc);
		SpriteWrapper sw = new SpriteWrapper(HAMMER);
		sw.s.setOriginCenter();
		bullet.addComponent(sw);
		bullet.addComponent(new RotateSpriteComponent(sw.s));
		ExtraSimpleCollisionComponent simpleCollision = new ExtraSimpleCollisionComponent();
		simpleCollision.filters.add( e-> e.getComponent(HealthComponent.class) != null);
		simpleCollision.tagToSearch = Laika.TAG_ENEMY;
		bullet.addComponent(simpleCollision);
	}
	

	public static void createRocket(Vector2 pos, GameState gs, Vector2 target, Vector2 direction, int delay, float speed1, float speed2) {
		GameObject bullet = new GameObject(pos.cpy(), gs);
		Bullet comp = new Bullet();
		// double damage ftw!
		comp.damage *= 2;
		bullet.addComponent(comp);
		VelocityComponent vc = new VelocityComponent();
		bullet.addComponent(vc);
		
		VelocityChangerComponent vcc = new VelocityChangerComponent();

		vcc.add(0, pos.cpy().add(direction.cpy().nor().scl(speed1 * delay)), speed1);
		vcc.add(delay, target.cpy(), speed2);
		bullet.addComponent(vcc);
		
		ASCIIPicture asciiPic = new ASCIIPicture("SWOSH");
		bullet.addComponent(asciiPic);
		ExtraSimpleCollisionComponent simpleCollision = new ExtraSimpleCollisionComponent();
		simpleCollision.filters.add( e-> e.getComponent(HealthComponent.class) != null);
		simpleCollision.tagToSearch = Laika.TAG_ENEMY;
		bullet.addComponent(simpleCollision);
	}	
	
}
