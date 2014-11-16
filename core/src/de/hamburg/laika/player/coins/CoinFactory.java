package de.hamburg.laika.player.coins;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.Laika;
import de.kuro.lazyjam.ecmodel.concrete.*;
import de.kuro.lazyjam.ecmodel.concrete.components.*;
import de.kuro.lazyjam.asciiassetextension.*;

public class CoinFactory {
	
	public static final int DEFAULT_AMOUNT = 10;
	public static Texture COIN;
	
	public static GameObject createCoinAt(Vector2 pos, GameState gs, int amount) {
		amount = Math.max(1, amount);
		GameObject coin = new GameObject(pos, gs);
		CoinsComponent coinComp = new CoinsComponent();
		coinComp.amount = amount;
		coin.addComponent(coinComp);
		SpriteWrapper sw = new SpriteWrapper(COIN);
		sw.s.scale(amount/DEFAULT_AMOUNT);
		coin.addComponent(sw);
		ExtraSimpleCollisionComponent collision = new ExtraSimpleCollisionComponent();
		collision.tagToSearch = Laika.TAG_PLAYER;
		coin.addComponent(collision);
		return coin;
		
	}

}
