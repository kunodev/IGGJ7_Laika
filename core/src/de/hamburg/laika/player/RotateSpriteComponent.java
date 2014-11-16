package de.hamburg.laika.player;

import com.badlogic.gdx.graphics.g2d.Sprite;

import de.kuro.lazyjam.cdiutils.annotations.Update;

public class RotateSpriteComponent {
	Sprite s;	
	float degreesPerTick = 10f;
	
	public RotateSpriteComponent(Sprite s, float degreesPerTick) {
		this.s = s;
		this.degreesPerTick = degreesPerTick;
	}

	public RotateSpriteComponent(Sprite s) {
		this.s = s;
	}

	@Update
	public void update() {
	s.setRotation((s.getRotation() + degreesPerTick) % 360.0f);
	}
}
