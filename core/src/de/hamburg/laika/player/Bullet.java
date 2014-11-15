package de.hamburg.laika.player;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.ExtraSimpleCollisionComponent;
import de.kuro.lazyjam.simpleservice.FontManager;

public class Bullet extends ExtraSimpleCollisionComponent{

	public int damage;
	
	@Update
	public void foo(Vector2 pos, GameState gs, GameObject go, OrthographicCamera camera) {
		super.collideWith(pos, gs, go);
	}
	
	@Override
	public void collide(GameObject thisGo, GameObject otherGo, GameState gs) {
		thisGo.selfDestruct(gs);
		//TODO: do stuff
	}
	
	@Render
	public void pew(Vector2 pos, FontManager fm) {
		fm.drawTextAbsolut(pos.x, pos.y, "PEW");
	}

}
