package de.hamburg.laika.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.Laika;
import de.hamburg.laika.EnemyType.HealthComponent;
import de.kuro.lazyjam.asciiassetextension.ASCIIPicture;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.ExtraSimpleCollisionComponent;
import de.kuro.lazyjam.ecmodel.concrete.components.PNGSpriteRenderComponent;
import de.kuro.lazyjam.ecmodel.concrete.components.VelocityComponent;

public class BulletFactory {
	
	public static Texture HAMMER;
	public static Texture MATROSCHKA;
	public static Texture SICKLE;
	public static AssetManager assetMan;
	
	public static void createBullet(Vector2 pos, GameState gs) {
		createBullet(pos, gs, new Vector2(1,0), 10, true);
	}
	
	public static void createBulletSickle(Vector2 pos, GameState gs) {
		createBulletSickle(pos, gs, new Vector2(1,0), 10, true);
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
	
	public static void createBulletSickle(Vector2 pos, GameState gs, Vector2 direction, float speed, boolean playerBullet) {
		GameObject bullet = new GameObject(pos.cpy(), gs);
		Bullet comp = new Bullet();
		bullet.addComponent(comp);
		VelocityComponent vc = new VelocityComponent();
		vc.v.set(direction.nor().scl(speed));
		bullet.addComponent(vc);
		PNGSpriteRenderComponent sp = new PNGSpriteRenderComponent();
		sp.init("sichel_pew+3+1", assetMan);
		sp.playOnce = true;
		sp.loopTick = 10;
		sp.sprite.s.setOriginCenter();
		sp.sprite.s.setOrigin(sp.sprite.s.getOriginX(), sp.sprite.s.getOriginY()  -5);
		bullet.addComponent(sp);
		bullet.addComponent(new RotateSpriteComponent(sp.sprite.s));
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
	

	public static void createMatroschka(Vector2 pos, GameState gs, Vector2 target, Vector2 direction, int delay, float speed1, float speed2) {
		GameObject bullet = new GameObject(pos.cpy(), gs);
		Bullet comp = new Bullet();
		// double damage ftw!
		comp.damage *= 1.1;
		bullet.addComponent(comp);
		VelocityComponent vc = new VelocityComponent();
		bullet.addComponent(vc);
		
		VelocityChangerComponent vcc = new VelocityChangerComponent();

		vcc.add(0, pos.cpy().add(direction.cpy().nor().scl(speed1 * delay)), speed1);
		vcc.add(delay, target.cpy(), speed2);
		bullet.addComponent(vcc);
		
		bullet.addComponent(new MatroschkaExplodeComponent());
		
		SpriteWrapper sw = new SpriteWrapper(MATROSCHKA);
		sw.s.setOriginCenter();
		bullet.addComponent(sw);
		ExtraSimpleCollisionComponent simpleCollision = new ExtraSimpleCollisionComponent();
		simpleCollision.filters.add( e-> e.getComponent(HealthComponent.class) != null);
		simpleCollision.tagToSearch = Laika.TAG_ENEMY;
		bullet.addComponent(simpleCollision);
	}	
	
	public static void createSmallMatroschka(Vector2 pos, GameState gs, Vector2 direction, float speed) {
		GameObject bullet = new GameObject(pos.cpy(), gs);
		Bullet comp = new Bullet();
		// double damage ftw!
		comp.damage *= 2;
		bullet.addComponent(comp);
		VelocityComponent vc = new VelocityComponent();
		bullet.addComponent(vc);
		vc.v.set(direction.nor().scl(speed));	
		SpriteWrapper sw = new SpriteWrapper(MATROSCHKA);
		sw.s.setSize(sw.s.getWidth()/3, sw.s.getHeight()/3);
		sw.s.setOriginCenter();
		sw.s.setRotation(direction.angle());
		bullet.addComponent(sw);
		ExtraSimpleCollisionComponent simpleCollision = new ExtraSimpleCollisionComponent();
		simpleCollision.activeAfter = 10;
		simpleCollision.filters.add( e-> e.getComponent(HealthComponent.class) != null);
		simpleCollision.tagToSearch = Laika.TAG_ENEMY;
		bullet.addComponent(simpleCollision);
	}
}
