package de.hamburg.laika.AI;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import de.hamburg.laika.Laika;
import de.hamburg.laika.LaikaGameState;
import de.hamburg.laika.EnemyType.factory.IComponentCollectionFactory;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

public class AlienFactory {

	private final GameState gs;
	private final Random rand = new Random(1);
	float chance = 0;
	
	ArrayList<AmountEnemyTypePair> enemyTypeAmountPairs = new ArrayList<AmountEnemyTypePair>();
		
	public AlienFactory(GameState gs) {
		this.gs = gs;
	}

	@Update
	public void update() {
		
		Array<AmountEnemyTypePair> trash = new Array<AmountEnemyTypePair>(enemyTypeAmountPairs.size());
		
		for (int i = 0; i < enemyTypeAmountPairs.size(); ++i) {
			if ( rand.nextFloat() * 100.f <= chance ) {
				GameObject enemy = new GameObject(new Vector2(Laika.WIDTH + LaikaGameState.HALF_SAFE_ZONE_SIZE, rand.nextFloat() * Laika.HEIGHT ),Laika.TAG_ENEMY, gs);
				AmountEnemyTypePair amountEnemyTypePair = enemyTypeAmountPairs.get(i);
				for(Object obj : amountEnemyTypePair.factory.createComponents()) {
					enemy.addComponent(obj);
				}
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
	
	public void registerEnemyType(Integer amount, IComponentCollectionFactory componentFac) {
		enemyTypeAmountPairs.add(new AmountEnemyTypePair(amount, componentFac));
	}
	
	public boolean stageComplete() {
		return enemyTypeAmountPairs.isEmpty(); 
	}
	
	public void setChance(float chance) {
		this.chance = chance;
	}
	
	static class AmountEnemyTypePair {
		public int amount;
		public IComponentCollectionFactory factory;
		
		public AmountEnemyTypePair( int amount, IComponentCollectionFactory factory) {
			this.amount = amount;
			this.factory = factory;
		}
	}
}
