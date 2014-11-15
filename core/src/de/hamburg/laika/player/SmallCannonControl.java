package de.hamburg.laika.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.inputmap.InputMap;
import de.hamburg.laika.inputmap.InputMap.Action;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.PNGSpriteRenderComponent;
import de.kuro.lazyjam.ecmodel.concrete.components.RelativityComponent;

public class SmallCannonControl {
	
	public static final int SMALL_CANNON_TICKS = 1;
	public int currentSmallCannonTicks;

	public Vector2 towerPos = new Vector2();
	public static final Vector2 TOWER_OFFSET = new Vector2(20,60);
	public static final Vector2 TOWER_BULLET = new Vector2(0,20);
	
	public float towerangle;
	
	@Update
	public void update(RelativityComponent rc, Input i, Vector2 pos, InputMap map, GameState gs, PNGSpriteRenderComponent sw) {
		Vector2 tempBulletOffset = TOWER_BULLET.cpy();
		rc.offset = TOWER_OFFSET.cpy();

		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_AIM_LEFT))) {
			towerangle += 10;
			sw.sprite.s.setRotation(sw.sprite.s.getRotation() + 10);
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_AIM_RIGHT))) {
			towerangle -= 10;
			sw.sprite.s.setRotation(sw.sprite.s.getRotation() - 10);
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_SHOOT))) {
			System.out.println("shoot");
			if(currentSmallCannonTicks >= SMALL_CANNON_TICKS) {
				tempBulletOffset.rotate(towerangle);
				tempBulletOffset.sub(0, -5);

				Vector2 rotation = new Vector2(0,1);
				rotation.rotate(towerangle);
				BulletFactory.createBulletHammer(pos.cpy(), gs, rotation, 10, true);
				currentSmallCannonTicks = 0;
			}
		}
		currentSmallCannonTicks++;
	}
}