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

public class ShieldControl {
	

	public Vector2 shieldRenderPos = new Vector2();
	public static final Vector2 SHIELD_OFFSET = new Vector2(100,0);
	public float shieldangle;
	
	@Update
	public void doUpdateThings(RelativityComponent rc, Input i, InputMap map, SpriteWrapper sw) {
		Vector2 tempOffset = SHIELD_OFFSET.cpy();
		tempOffset.rotate(shieldangle);
		shieldRenderPos = rc.offset.set(tempOffset);
		
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
		c.otherGo.selfDestruct(gs);
	}

}
