package de.hamburg.laika.background;

	

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.Laika;
import de.hamburg.laika.EnemyMovement.LinearMovement;
import de.hamburg.laika.player.coins.CoinFactory;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;


	public class BackGroundGen {
		
		Random rand = new Random(1);
		
		public boolean withCoins = true;
		
		private final GameState gs;
		ArrayList<SpriteWrapper> stardust = new ArrayList<SpriteWrapper>();
			
		public BackGroundGen(GameState gs) {
			this.gs = gs;
		}

		@Update
		public void update() {
			
			for (SpriteWrapper dust : stardust) {
				if ( rand.nextFloat() * 100f < 2 ) {
					GameObject star = new GameObject(new Vector2(Laika.WIDTH, rand.nextFloat() * Laika.HEIGHT ), gs);
					star.addComponent(new LinearMovement(new Vector2(-rand.nextFloat() * (rand.nextInt(15) + 10.f), 0.f)));
					star.addComponent(dust);
				}
			}
			if(withCoins && rand.nextFloat() * 100f < 1f) {
				GameObject coin = CoinFactory.createCoinAt(new Vector2(Laika.WIDTH, rand.nextFloat() * Laika.HEIGHT ), gs, (int) (rand.nextFloat() * 20));
				coin.addComponent(new LinearMovement(new Vector2(-rand.nextFloat() * (rand.nextInt(15) + 10.f), 0.f)));
				coin.addComponent(coin);
			}
		}
		
		public void registerStardust(Texture starTexture) {
			stardust.add(new SpriteWrapper(starTexture));
		}
	}
