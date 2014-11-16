package de.hamburg.laika.EnemyType.factory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import de.hamburg.laika.EnemyType.HealthComponent;
import de.hamburg.laika.Laika;
import de.hamburg.laika.LaikaGameState;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.ExtraSimpleCollisionComponent;
import de.kuro.lazyjam.ecmodel.concrete.components.VelocityComponent;

public class CometFactory {
	public static int MAX_HEALTH = 200;

	public static void createComet(GameState gs, float speed, boolean aimAtPlayer, Texture texture) {
		final Vector2 pos = new Vector2();
		final DIRECTION dir = createPosInSafeZone(pos);
		final GameObject comet = new GameObject(pos, Laika.TAG_ENEMY, gs);

		Vector2 velocity;
		if (aimAtPlayer && !gs.getTaggedGameObjects(Laika.TAG_PLAYER).isEmpty()) {
			velocity = gs.getFirstTaggedGameObject(Laika.TAG_PLAYER).getPos().sub(pos);
		} else {
			switch (dir) {
				case TOP:
					velocity = createPosInSafeZone(DIRECTION.BOTTOM);
					break;
				case RIGHT:
					velocity = createPosInSafeZone(DIRECTION.LEFT);
					break;
				case BOTTOM:
					velocity = createPosInSafeZone(DIRECTION.TOP);
					break;
				case LEFT:
					velocity = createPosInSafeZone(DIRECTION.RIGHT);
					break;
				default:
					velocity = null;
			}
		}

		final VelocityComponent vc = new VelocityComponent();
		vc.v.set(velocity.nor().scl(speed));
		comet.addComponent(vc);

		final HealthComponent healthComponent = new HealthComponent(MAX_HEALTH);
		comet.addComponent(healthComponent);

		final SpriteWrapper sw = new SpriteWrapper(texture);
		sw.s.setRotation(velocity.angle());
		sw.s.setFlip(true, false);
		comet.addComponent(sw);

		/*ExtraSimpleCollisionComponent simpleCollision = new ExtraSimpleCollisionComponent();
		simpleCollision.tagToSearch = Laika.TAG_PLAYER;
		comet.addComponent(simpleCollision);*/
	}

	static enum DIRECTION {
		TOP, RIGHT, BOTTOM, LEFT
	}

	private static DIRECTION createPosInSafeZone(Vector2 pos) {
		final float offset = LaikaGameState.SAFE_ZONE_SIZE * 0.5f;
		final DIRECTION dir = DIRECTION.values()[MathUtils.random(DIRECTION.values().length - 1)];
		switch (dir) {
			case TOP:
				pos.set(MathUtils.random(-offset, Laika.WIDTH + offset), Laika.HEIGHT + offset);
				return dir;

			case BOTTOM:
				pos.set(MathUtils.random(-offset, Laika.WIDTH + offset), -offset);
				return dir;
			case LEFT:
				pos.set(-offset, MathUtils.random(-offset, Laika.HEIGHT + offset));
				return dir;
			case RIGHT:
				pos.set(Laika.WIDTH + offset, MathUtils.random(-offset, Laika.HEIGHT + offset));
				return dir;
		}
		return null;
	}

	private static Vector2 createPosInSafeZone(DIRECTION dir) {
		final float offset = LaikaGameState.SAFE_ZONE_SIZE * 0.5f;
		switch (dir) {
			case TOP:
				return new Vector2(MathUtils.random(-offset, Laika.WIDTH + offset), Laika.HEIGHT + offset);
			case BOTTOM:
				return new Vector2(MathUtils.random(-offset, Laika.WIDTH + offset), -offset);
			case LEFT:
				return new Vector2(-offset, MathUtils.random(-offset, Laika.HEIGHT + offset));
			case RIGHT:
				return new Vector2(Laika.WIDTH + offset, MathUtils.random(-offset, Laika.HEIGHT + offset));
		}

		return null;
	}
}
