package de.hamburg.laika.player.coins;

import de.kuro.lazyjam.cdiutils.annotations.Service;

@Service
public class CoinsService {
	public final int startCoins = 10;
	public int coins = startCoins;
	
	public void removeCoins(int amount) {
		coins = Math.max(0, coins - amount);
	}

	public void addCoins(int delta) {
		this.coins += delta;
	}
}
