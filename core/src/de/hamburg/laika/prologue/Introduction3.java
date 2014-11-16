package de.hamburg.laika.prologue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.simpleservice.FontManager;

public class Introduction3 extends Introduction {

	public Introduction3() {
		this.nextComponentToAdd = new Introduction4();
	}

	@Update
	public void doShizzle(Vector2 pos, GameObject go, GameState gs) {
		super.scrollAround(pos, go, gs);
	}

	@Render
	public void rendering(Vector2 pos, SpriteBatch sb) {
		draw(pos , "Her name was Laika...", sb);
	}
}
