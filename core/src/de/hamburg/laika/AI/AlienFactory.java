package de.hamburg.laika.AI;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import de.hamburg.laika.EnemyType.HealthComponent;
import de.hamburg.laika.Laika;
import de.hamburg.laika.EnemyType.IEnemyType;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

public class AlienFactory {

	private final GameState gs;
	
	ArrayList<AmountEnemyTypePair> enemyTypeAmountPairs = new ArrayList<AmountEnemyTypePair>();
		
	public AlienFactory(GameState gs) {
		this.gs = gs;
	}

	@Update
	public void update(Input i, Vector2 pos) {
		
		Array<AmountEnemyTypePair> trash = new Array<AmountEnemyTypePair>(enemyTypeAmountPairs.size());
		
		for (int j = 0; j < enemyTypeAmountPairs.size(); ++j) {
			if ( MathUtils.randomBoolean()) {
				GameObject enemy = new GameObject(new Vector2(Laika.WIDTH, MathUtils.random() * Laika.HEIGHT ), gs);
				AmountEnemyTypePair amountEnemyTypePair = enemyTypeAmountPairs.get(j);
				enemy.addComponent(amountEnemyTypePair.enemyType);
				enemy.addComponent(new HealthComponent(MathUtils.random(90, 110)));
				amountEnemyTypePair.amount--;
				if ( amountEnemyTypePair.amount == 0 ) {
					trash.add(amountEnemyTypePair);
				}	
			}
		}

		for (AmountEnemyTypePair amountEnemyTypePair : trash) {
			enemyTypeAmountPairs.remove(amountEnemyTypePair);
		}
		
	}
	
	public void registerEnemyType(Integer amount, IEnemyType enemyType) {
		enemyTypeAmountPairs.add(new AmountEnemyTypePair(amount, enemyType));
	}
	
	static class AmountEnemyTypePair {
		public int amount;
		public IEnemyType enemyType;
		
		public AmountEnemyTypePair( int amount, IEnemyType enemyType ) {
			this.amount = amount;
			this.enemyType = enemyType;
		}
	}
	
}
