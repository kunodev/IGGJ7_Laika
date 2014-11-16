package de.hamburg.laika.prologue;

import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.simpleservice.FontManager;

public class Introduction4 extends Introduction {

	public Introduction4() {
		this.nextComponentToAdd = new Introduction5();
	}

	@Update
	public void doShizzle(Vector2 pos, GameObject go, GameState gs) {
		super.scrollAround(pos, go, gs);
	}

	@Render
	public void rendering(Vector2 pos, FontManager fm) {
		fm.drawTextAbsolut(pos.x, pos.y, "She died a few hours after launch from heat...");
	}
}
