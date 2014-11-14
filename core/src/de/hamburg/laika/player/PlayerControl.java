package de.hamburg.laika.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.inputmap.InputMap;
import de.hamburg.laika.inputmap.InputMap.Action;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.simpleservice.FontManager;

public class PlayerControl {
	
	public static final int SPEED = 3;
	
	@Update
	public void update(Input i, Vector2 pos, InputMap map) {
		if(i.isKeyPressed(map.actionToHWKey.get(Action.DOWN))) {
			pos.y -= SPEED;
		}
		if(i.isKeyPressed(Keys.UP)) {
			pos.y += SPEED;
		}
		if(i.isKeyPressed(Keys.LEFT)) {
			pos.x -= SPEED;
		}
		if(i.isKeyPressed(Keys.RIGHT)) {
			pos.x += SPEED;
		}
	}
	
	@Render
	public void render(FontManager fm, Vector2 pos) {
		fm.drawTextAbsolut(pos.x, pos.y, "PLAYER");
	}
}
