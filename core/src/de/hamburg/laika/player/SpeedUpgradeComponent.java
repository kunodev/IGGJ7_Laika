package de.hamburg.laika.player;

import de.kuro.lazyjam.cdiutils.annotations.Update;

public class SpeedUpgradeComponent extends UpgradeComponent {	
	boolean buy = false;
	
	@Update
	public void update(PlayerControl pc, CoinsComponent cc) {
		if (buy && cc.coins >= 10) {
			cc.removeCoins(10);
			numUpdates += 10;
			buy = false;
		}
		
		pc.speedModifier = 1f +numUpdates/100.0f;
	}
	
	@Override
	public void onClick() {
		buy = true;
	}
}
