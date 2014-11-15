package de.hamburg.laika.player.buffcomponents;

import de.hamburg.laika.player.CoinsComponent;
import de.hamburg.laika.player.PlayerControl;
import de.kuro.lazyjam.cdiutils.annotations.Update;

public class SpeedModificationComponent extends UpgradeComponent {	
	boolean buy = false;
	boolean sell = false;
	
	@Update
	public void update(PlayerControl pc, CoinsComponent cc) {
		if (buy && cc.coins >= 10) {
			cc.removeCoins(10);
			numUpdates += 10;
			buy = false;
		}
		if(sell && numUpdates >= 10) {
			cc.addCoins(10);
			numUpdates -= 10;
			sell = false;
		}
		
		pc.speedModifier = 1f +numUpdates/100.0f;
	}
	
	@Override
	public void onClick() {
		buy = true;
	}
	
	@Override
	public void onRightMouseClick() {
		sell = true;
	}
}
