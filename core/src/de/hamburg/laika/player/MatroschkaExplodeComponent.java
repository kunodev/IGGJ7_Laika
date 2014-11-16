package de.hamburg.laika.player;

import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Destroy;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

public class MatroschkaExplodeComponent {

	@Destroy
	public void spwanChildren(Vector2 pos, GameState gs) {
		int numBullets = 10;
		for (int i = 0; i < numBullets; i++) {
			float angle = (360f/numBullets) * i;
			Vector2 direction = new Vector2(0, 1);
			direction.rotate(angle);
			BulletFactory.createSmallMatroschka(pos, gs, direction, 12);
		}
	}
}
