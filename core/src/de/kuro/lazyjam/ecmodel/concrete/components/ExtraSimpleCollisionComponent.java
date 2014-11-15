package de.kuro.lazyjam.ecmodel.concrete.components;

import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

public abstract class ExtraSimpleCollisionComponent {
	
	public int range = 5;
	
	@Update
	public void collideWith(Vector2 pos, GameState gs, GameObject go) {
		gs.gameObjects.stream()
			.filter(e -> e.getComponent(ExtraSimpleCollisionComponent.class) != null)
			.filter(e -> e.getPos().dst(pos) <= range)
			.filter(e -> e != go)
			.forEach(e -> e.getComponent(ExtraSimpleCollisionComponent.class).collide(e, go, gs));
	}
	
	public abstract void collide(GameObject thisGo, GameObject otherGo, GameState gs);
	
	
}
