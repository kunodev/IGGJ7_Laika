package de.hamburg.laika.player.buffcomponents;

import de.hamburg.laika.LaikaGameState;
import de.hamburg.laika.player.SmallCannonControl;
import de.hamburg.laika.player.coins.CoinsService;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.settings.*;

public class SpaceTimeContinuumButton extends UpgradeComponent {
	
	public static final int COOLDOWN = 60 * 6;
	public static final int DURATION = 60 * 5;
	public int loadingTicks = 0;
	
	boolean timeTravelAction;
	boolean timeTravelActive;
	
	@Update
	public void update(LaikaGameState gs) {
		loadingTicks++;
		if(timeTravelAction) {
			gs.TICK_TIME = Constants.DEFAULT_TICK_TIME*2;
			timeTravelActive = true;
			timeTravelAction = false;
		}
		if(timeTravelActive && loadingTicks >= DURATION/2) {
			gs.TICK_TIME = Constants.DEFAULT_TICK_TIME;
			loadingTicks = 0;			
		}
	}
	
	@Override
	public void onClick() {
		if(loadingTicks >= COOLDOWN) {
			loadingTicks = 0;
			timeTravelAction = true;
		}
	}
	
	@Override
	public void onRightMouseClick() {
		
	}
}
