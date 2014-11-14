package de.hamburg.laika.EnemyType;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import de.kuro.lazyjam.cdiutils.annotations.Render;

public class Comet {
	final Texture tex;

	public Comet(Texture tex) {
		this.tex = tex;
	}

	@Render
	public void render(SpriteBatch batch, Vector2 pos){
		batch.draw(tex, pos.x, pos.y);
	}
}
