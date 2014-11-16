package de.hamburg.laika.player.buffcomponents;

import de.hamburg.laika.player.ShieldControl;
import de.hamburg.laika.player.coins.CoinsService;
import de.kuro.lazyjam.cdiutils.annotations.Update;

public class ShieldModificationComponent extends UpgradeComponent {
	
	boolean buy = false;
	boolean sell = false;
	
	public static final int PRICE = 10;
	public static final int ATOMIC_BUFF = 3;
	
	@Update
	public void update(ShieldControl sc, CoinsService cc) {
		if (buy && cc.coins >= PRICE) {
			cc.removeCoins(PRICE);
			numUpdates += ATOMIC_BUFF;
			buy = false;
		}
		if(sell && numUpdates >= ATOMIC_BUFF) {
			cc.addCoins(PRICE);
			numUpdates -= ATOMIC_BUFF;
			sell = false;
		}
		
		sc.bonusShieldHP = numUpdates * ATOMIC_BUFF;
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
