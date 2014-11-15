package de.hamburg.laika.background;

	

	import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.Laika;
import de.hamburg.laika.EnemyType.IEnemyType;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;


	public class BackGroundGen {
		
		Random rand = new Random(1);
		
		private final GameState gs;
		
		ArrayList<IEnemyType> stardust = new ArrayList<IEnemyType >();
			
		public BackGroundGen(GameState gs) {
			this.gs = gs;
		}

		@Update
		public void update(Input i, Vector2 pos) {
			
			for (IEnemyType dust : stardust) {
				if ( rand.nextFloat() * 100f > 0.5 ) {
					GameObject enemy = new GameObject(new Vector2(Laika.WIDTH, rand.nextFloat() * Laika.HEIGHT ), gs);
					enemy.addComponent(dust);
				}
			}			
		}
		
		public void registerEnemyType(IEnemyType enemyType) {
			stardust.add(enemyType);
		}
	}
