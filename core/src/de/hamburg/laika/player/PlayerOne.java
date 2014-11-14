package de.hamburg.laika.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.simpleservice.FontManager;

public class PlayerOne {
	
	@Update
	public void update(Input i, Vector2 pos) {
		if(i.isKeyPressed(Keys.DOWN)) {
			pos.y -= 3;
		}
		if(i.isKeyPressed(Keys.UP)) {
			pos.y += 3;
		}
		if(i.isKeyJustPressed(Keys.LEFT)) {
			pos.x -= 3;
		}
		if(i.isKeyPressed(Keys.RIGHT)) {
			pos.x += 3;
		}
	}
	
	@Render
	public void render(FontManager fm, Vector2 pos) {
		fm.drawTextAbsolut(pos.x, pos.y, "PLAYER");
	}

}
