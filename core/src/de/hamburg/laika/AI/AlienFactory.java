package de.hamburg.laika.AI;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Update;

public class AlienFactory {
	
	private int time = 0;
	
	private enum Phases {		
		
		P1(0, 50, 1),
		P2(50, 70, 2);
		
		private int startTime;
		private int endTime;
		private int enemyType;
		
		private Phases(int startTime, int endTime, int enemyType) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.enemyType = enemyType;
		}
		
		public int getStartTime() {
			return startTime;
		}
		
		public int getEndTime() {
			return endTime;
		}
		
		public int getEnemyType() {
			return enemyType;
		}
	}
	
	@Update
	public void update(Input i, Vector2 pos) {
		time++; // addTime
		
		for (Phases currenPhase : getPhases()) {
			currenPhase.getEnemyType();
			// CHECK FOR SPAWNING.
		}
		
	}

	private ArrayList<Phases> getPhases() {
		ArrayList<Phases> currentPhases = new ArrayList<Phases>();
		for (Phases phase : Phases.values()) {
			if ( time >= phase.getStartTime() && time <= phase.getEndTime() ) {
				currentPhases.add(phase);
			}
		}
		return currentPhases;
	}	
}
