package de.hamburg.laika.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.inputmap.InputMap;
import de.hamburg.laika.inputmap.InputMap.Action;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.cdiutils.annotations.Collide;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.RelativityComponent;
import de.kuro.lazyjam.ecmodel.concrete.tools.Collision;
import de.kuro.lazyjam.simpleservice.FontManager;
import de.hamburg.laika.EnemyType.HealthComponent;

public class ShieldControl {
	

	public Vector2 shieldRenderPos = new Vector2();
	public static final Vector2 SHIELD_OFFSET = new Vector2(100,0);
	public float shieldangle;
	public boolean shieldActive = false;
	
	public static final int SHIELD_HP_START = 100;
	public int shieldHP = SHIELD_HP_START;
	
	public static final int SHIELD_RELOAD_TICKS = 60*5;
	public int shieldTicks = 0;
	
	@Update
	public void doUpdateThings(RelativityComponent rc, Input i, InputMap map, SpriteWrapper sw) {
		Vector2 tempOffset = SHIELD_OFFSET.cpy();
		tempOffset.rotate(shieldangle);
		shieldRenderPos = rc.offset.set(tempOffset);
		
		if(!shieldActive) {
			sw.s.setAlpha((float)shieldTicks/(float)SHIELD_RELOAD_TICKS);
			shieldTicks++;
			if(shieldTicks >= SHIELD_RELOAD_TICKS) {
				this.shieldActive = true;
				this.shieldHP = SHIELD_HP_START;
				this.shieldTicks = 0;
			}
		} else {
			sw.s.setAlpha(1f);			
		}
		
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_LEFT))) {
			sw.s.setRotation(sw.s.getRotation() - 10);
			shieldangle -= 10;
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_RIGHT))) {
			sw.s.setRotation(sw.s.getRotation() + 10);
			shieldangle += 10;
		}
		
	}
	
	@Collide
	public void repel(Collision c, GameState gs) {
		if(shieldActive) {
			HealthComponent otherComp = c.otherGo.getComponent(HealthComponent.class);
			this.shieldHP -= otherComp.getHealth();
			if(shieldHP <= 0) {
				this.shieldActive = false;
			}
			c.otherGo.selfDestruct(gs);
		}
	}

}
