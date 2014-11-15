package de.hamburg.laika.player;

import de.kuro.lazyjam.cdiutils.annotations.Service;

@Service
public class CoinsComponent {
	public int coins = 1000;
	
	public void removeCoins(int amount) {
		coins = Math.max(0, coins - amount);
	}

	public void addCoins(int delta) {
		this.coins += delta;
	}
}
