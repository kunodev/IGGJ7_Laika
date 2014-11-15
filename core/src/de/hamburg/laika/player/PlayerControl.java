package de.hamburg.laika.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.Laika;
import de.hamburg.laika.inputmap.InputMap;
import de.hamburg.laika.inputmap.InputMap.Action;
import de.kuro.lazyjam.cdiutils.annotations.Collide;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.VelocityComponent;
import de.kuro.lazyjam.ecmodel.concrete.tools.Collision;
import de.kuro.lazyjam.simpleservice.FontManager;

public class PlayerControl {
	
	public static final int SPEED = 3;
	public static final int BIG_CANNON_TICKS = 20;
	
	public float speedModifier = 1.0f;
	public int currentCannonTicks;
	
	@Update
	public void update(Input i, Vector2 pos, InputMap map, GameState gs) {
		if(i.isKeyPressed(map.actionToHWKey.get(Action.DOWN))) {
			pos.y = Math.max(0.0f, Math.min(Laika.HEIGHT, pos.y - SPEED * speedModifier));
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.UP))) {
			pos.y = Math.max(0.0f, Math.min(Laika.HEIGHT, pos.y + SPEED * speedModifier));
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.LEFT))) {
			pos.x = Math.max(0.0f, Math.min(Laika.WIDTH, pos.x - SPEED * speedModifier));
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.RIGHT))) {
			pos.x = Math.max(0.0f, Math.min(Laika.WIDTH, pos.x + SPEED * speedModifier));
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHOOT))) {
			if(currentCannonTicks >= BIG_CANNON_TICKS) {
				BulletFactory.createBullet(pos, gs);
				currentCannonTicks = 0;
			}
		}
		currentCannonTicks++;
	}
	

	@Collide
	public void kill(Collision co, GameState gs) {
		co.otherGo.selfDestruct(gs);
		co.thisGo.selfDestruct(gs);
	}

}
