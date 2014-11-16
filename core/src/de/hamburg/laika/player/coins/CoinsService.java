package de.hamburg.laika.player.coins;

import de.kuro.lazyjam.cdiutils.annotations.Service;

@Service
public class CoinsService {
	public int coins = 10;
	
	public void removeCoins(int amount) {
		coins = Math.max(0, coins - amount);
	}

	public void addCoins(int delta) {
		this.coins += delta;
	}
}
