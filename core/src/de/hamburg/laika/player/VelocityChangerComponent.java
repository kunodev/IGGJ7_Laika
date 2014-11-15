package de.hamburg.laika.player;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.components.VelocityComponent;

public class VelocityChangerComponent {
	
	public long timeCounter = 0;
	
	private class Change {
		public int time;
		public Vector2 target;
		public float speed;
		public Change(int time, Vector2 target, float speed) {
			super();
			this.time = time;
			this.target = target;
			this.speed = speed;
		}
		
	}
	// time,target, speed
	public ArrayList<Change> targets = new ArrayList<Change>();
	
	public void add(int time, Vector2 target, float speed) {
		targets.add(new Change(time, target, speed));
	}
	
	@Update
	public void update(VelocityComponent vc, Vector2 pos, SpriteWrapper sw) {
		timeCounter++;
		
		Iterator<Change> it = targets.iterator();
		while(it.hasNext()) {
			Change e = (Change)it.next();
			if (e.time <= timeCounter) {
				it.remove();
				vc.v = e.target.cpy().sub(pos).nor().scl(e.speed);
				sw.s.setRotation((float)(Math.atan2(vc.v.x, vc.v.y) * 180 / -Math.PI) + 90);
			}
			
		}
	}
}
