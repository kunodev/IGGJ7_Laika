package de.hamburg.laika.player;

import com.badlogic.gdx.graphics.Color;

import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.simpleservice.FontManager;

public class InfoTextComponent {
	
	public int offsetX = 850;
	public static final int TICKS_TO_CHANGE = 60 * 5 * 10;
	public int controlChangeTicks = TICKS_TO_CHANGE;
	
	public int flickerTimeSwitchOn = 30;
	public int flickerTimeSwitchOff = 10;
	public long ticks;
	public boolean flickerOn = true;
	
	
	@Render
	public void render(FontManager fm, CoinsComponent coins, RocketControl rocket) {
		ticks++;
		if ((flickerOn && ticks > flickerTimeSwitchOn) || (!flickerOn && ticks > flickerTimeSwitchOff)) {
			flickerOn = !flickerOn;
			ticks = 0;
		}
		
		
		fm.drawTextAbsolut(offsetX, 65, "Rockets ");
		if(flickerOn || rocket.isCharged()) fm.drawTextAbsolut(offsetX + 70, 65, rocket.isCharged() ? "ready" : "charging", rocket.isCharged() ? Color.GREEN : Color.RED);
		fm.drawTextAbsolut(offsetX, 45, "" + coins.coins + " coins");
		controlChangeTicks--;
		int controlChangeSeconds = controlChangeTicks * 60 / 1000;
		boolean controlChangeSoon = controlChangeSeconds <= 10;
		if(flickerOn || !controlChangeSoon) fm.drawTextAbsolut(offsetX, 25, "Change ~" + controlChangeTicks * 60 / 1000 + "s", controlChangeSoon ? Color.RED : Color.WHITE);
		if(controlChangeTicks <= 0 ) {
			controlChangeTicks = TICKS_TO_CHANGE;
		}
	}
}
