package de.hamburg.laika.player.buffcomponents;

import de.hamburg.laika.player.CoinsComponent;
import de.hamburg.laika.player.SmallCannonControl;
import de.kuro.lazyjam.cdiutils.annotations.Update;

public class SideCannonModificationComponent extends UpgradeComponent{
	
	boolean buy = false;
	boolean sell = false;
	
	public static final int PRICE = 100;
	public static final int ATOMIC_BUFF = 1;
	
	@Update
	public void update(SmallCannonControl scc, CoinsComponent cc) {
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
		
		scc.cannonTicksReduction = numUpdates/5 * ATOMIC_BUFF;
		scc.bonusDamage = numUpdates * ATOMIC_BUFF * 5;
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
