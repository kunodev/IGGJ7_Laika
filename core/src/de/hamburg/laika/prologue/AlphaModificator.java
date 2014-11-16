package de.hamburg.laika.prologue;

import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.cdiutils.annotations.Update;

public class AlphaModificator {

	public int ticks;
	
	@Update
	public void einblendung(SpriteWrapper sw) {
		ticks++;
		sw.s.setAlpha(Math.min(1f, ticks/(float)GuideLaikaComponent.MAX_TICKS));
	}
	
}
