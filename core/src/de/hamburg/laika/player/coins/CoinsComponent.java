package de.hamburg.laika.player.coins;

import de.kuro.lazyjam.cdiutils.annotations.Collide;

public class CoinsComponent {

	public int amount = 10;
	
	@Collide
	public void collision(CoinsService cs) {
		cs.addCoins(amount);
	}

}
