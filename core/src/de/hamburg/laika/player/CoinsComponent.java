package de.hamburg.laika.player;

import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.simpleservice.FontManager;

public class CoinsComponent {
	public int coins = 1000;
	
	public void removeCoins(int amount) {
		coins = Math.max(0, coins - amount);
	}
}
