package de.hamburg.laika.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.inputmap.InputMap;
import de.hamburg.laika.inputmap.InputMap.Action;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.VelocityComponent;
import de.kuro.lazyjam.simpleservice.FontManager;

public class PlayerControl {
	
	public static final int SPEED = 3;
	
	public float speedModifier = 1.0f;
	
	@Update
	public void update(Input i, Vector2 pos, InputMap map, GameState gs) {
		if(i.isKeyPressed(map.actionToHWKey.get(Action.DOWN))) {
			pos.y -= SPEED * speedModifier;
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.UP))) {
			pos.y += SPEED * speedModifier;;
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.LEFT))) {
			pos.x -= SPEED * speedModifier;;
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.RIGHT))) {
			pos.x += SPEED * speedModifier;;
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHOOT))) {
			BulletFactory.createBullet(pos, gs);
		}
	}
	
	@Render
	public void render(FontManager fm, Vector2 pos) {
		fm.drawTextAbsolut(pos.x, pos.y, "PLAYER");
	}
}
