package de.hamburg.laika.player;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.util.Pair;

import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.components.VelocityComponent;

public class VelocityChangerComponent {
	
	public long timeCounter = 0;
	// time,target, speed
	public ArrayList<Pair<Integer, Pair<Vector2,Float>>> targets = new ArrayList<Pair<Integer, Pair<Vector2,Float>>>();
	
	public void add(int time, Vector2 target, float speed) {
		targets.add(new Pair<Integer, Pair<Vector2,Float>>(time, new Pair<Vector2, Float>(target, speed)));
	}
	
	@Update
	public void update(VelocityComponent vc, Vector2 pos) {
		timeCounter++;
		
		Iterator<Pair<Integer, Pair<Vector2, Float>>> it = targets.iterator();
		while(it.hasNext()) {
			Pair<Integer, Pair<Vector2, Float>> e = (Pair<Integer, Pair<Vector2, Float>>)it.next();
			int time = (Integer) e.getKey();
			if (time <= timeCounter) {
				Pair<Vector2, Float> p = (Pair<Vector2, Float>) e.getValue();
				Vector2 target = (Vector2) p.getKey();
				float speed = (Float) p.getValue();
				
				it.remove();
				vc.v = target.cpy().sub(pos).nor().scl(speed);
			}
			
		}
	}
}
