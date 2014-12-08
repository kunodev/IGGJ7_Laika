package de.hamburg.laika.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.inputmap.InputMap;
import de.hamburg.laika.inputmap.InputMap.Action;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.PNGSpriteRenderComponent;
import de.kuro.lazyjam.ecmodel.concrete.components.RelativityComponent;
import de.kuro.lazyjam.ecmodel.concrete.components.TickCooldownComponent;

public class SmallCannonControl extends TickCooldownComponent {
	
	public static final int SMALL_CANNON_TICKS = 10;
	
	public int bonusDamage;

	public Vector2 towerPos = new Vector2();
	public static final Vector2 TOWER_OFFSET = new Vector2(20,60);
	public static final Vector2 TOWER_BULLET = new Vector2(0,25);
	
	public float towerangle;

	public SmallCannonControl() {
		super(SMALL_CANNON_TICKS);
	}

	@Update
	public void update(
			RelativityComponent rc, Vector2 pos,
			Input i, InputMap map, GameState gs, PNGSpriteRenderComponent sw) {
		Vector2 tempBulletOffset = TOWER_BULLET.cpy();
		rc.offset.set(TOWER_OFFSET);

		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_AIM_LEFT))) {
			towerangle += 10;
			sw.sprite.s.setRotation(sw.sprite.s.getRotation() + 10);
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_AIM_RIGHT))) {
			towerangle -= 10;
			sw.sprite.s.setRotation(sw.sprite.s.getRotation() - 10);
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_SHOOT))) {
			if(super.canFire()) {
				sw.play = true;
				tempBulletOffset.rotate(towerangle);
				Vector2 rotation = new Vector2(0,1);
				rotation.rotate(towerangle);
				BulletFactory.createBulletHammer(pos.cpy().sub(0, 10).add(tempBulletOffset), gs, rotation, 10, true);
			}
		} else {
			sw.reset();
		}
	}
}
