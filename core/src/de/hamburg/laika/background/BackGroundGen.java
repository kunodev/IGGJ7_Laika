package de.hamburg.laika.background;

	

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.Laika;
import de.hamburg.laika.EnemyMovement.LinearMovement;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;


	public class BackGroundGen {
		
		Random rand = new Random(1);
		
		private final GameState gs;
		
		ArrayList<Texture> stardust = new ArrayList<Texture>();
			
		public BackGroundGen(GameState gs) {
			this.gs = gs;
		}

		@Update
		public void update() {
			
			for (Texture dust : stardust) {
				if ( rand.nextFloat() * 100f > 0.5 ) {
					GameObject star = new GameObject(new Vector2(Laika.WIDTH, rand.nextFloat() * Laika.HEIGHT ), gs);
					star.addComponent(new LinearMovement(new Vector2(-2f, 0.f)));
					star.addComponent(new SpriteWrapper(dust));
				}
			}			
		}
		
		public void registerStardust(Texture starTexture) {
			stardust.add(starTexture);
		}
	}
