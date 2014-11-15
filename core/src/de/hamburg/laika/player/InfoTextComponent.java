package de.hamburg.laika.player;

import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.simpleservice.FontManager;

public class InfoTextComponent {
	
	public int offsetX = 850;
	public static final int TICKS_TO_CHANGE = 60 * 5 * 10;
	public int controlChangeTicks = TICKS_TO_CHANGE;
	
	@Render
	public void render(FontManager fm, CoinsComponent coins, RocketControl rocket) {
		
		fm.drawTextAbsolut(offsetX, 60, "Rockets " + (rocket.isCharged() ? "ready" : "charging"));
		fm.drawTextAbsolut(offsetX, 40, "" + coins.coins + " coins");
		controlChangeTicks--;
		fm.drawTextAbsolut(offsetX, 20, "Change ~" + controlChangeTicks * 60 / 1000 + "s" );
		if(controlChangeTicks <= 0 ) {
			controlChangeTicks = TICKS_TO_CHANGE;
		}
	}
}
