package de.hamburg.laika.player;

import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.simpleservice.FontManager;

public class InfoTextComponent {
	
	public int offsetX = 850;
	
	@Render
	public void render(FontManager fm, CoinsComponent coins, RocketControl rocket) {
		fm.drawTextAbsolut(offsetX, 60, "Rockets " + (rocket.isCharged() ? "ready" : "charging"));
		fm.drawTextAbsolut(offsetX, 40, "" + coins.coins + " coins");
	}
}
