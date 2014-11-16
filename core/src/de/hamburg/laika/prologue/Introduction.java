package de.hamburg.laika.prologue;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

public class Introduction {
	
	public final int ticksToMiddle = 80;
	public final int ticksToEndStart	= 150;
	public final int ticksToSelfDestruct = 220;
	public int ticks = 0;
	public Object nextComponentToAdd;
	private static BitmapFont fonts;
	
	public enum Stage {
		TO_MIDDLE,HOVERING,TOEND, DEATH
	}

	public Stage getStage() {
		ticks++;
		if(ticks <= ticksToMiddle) {
			return Stage.TO_MIDDLE;
		} else if(ticks <= ticksToEndStart) {
			return Stage.HOVERING;
		} else if(ticks <= ticksToSelfDestruct) {
			return Stage.TOEND;
		} else {
			return Stage.DEATH;
		}
	}
	
	public void scrollAround(Vector2 pos, GameObject go, GameState gs) {
		Stage s = getStage();
		switch(s) {
		case TO_MIDDLE : 
			pos.x += 5;
			break;
		case HOVERING:
			break;
		case TOEND:
			pos.x += 10;
			break;
		case DEATH:
			go.selfDestruct(gs);
			GameObject newGo = new GameObject(new Vector2(10f, 330f),gs);
			newGo.addComponent(nextComponentToAdd);
			break;
		}		
	}
	
	public static void draw(Vector2 pos, String text, SpriteBatch sb) {
		if(fonts == null) {
			initFonts();
		}
		fonts.draw(sb, text, pos.x, pos.y);
	}

	private static void initFonts() {
		fonts = new BitmapFont();
		fonts.scale(1.5f);
		
	}
	
}
