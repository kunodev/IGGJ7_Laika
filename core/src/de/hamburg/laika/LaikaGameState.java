package de.hamburg.laika;

import java.util.Iterator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.context.GlobalContext;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

public class LaikaGameState extends GameState{

	@Override
	protected void update(GlobalContext globalContext) {
		super.update(globalContext);
		final OrthographicCamera cam = globalContext.serviceMan.getService(OrthographicCamera.class);
		for(Iterator<GameObject> it = gameObjects.iterator(); it.hasNext();){
			GameObject go = it.next();
			final Vector2 pos = go.getPos();
			if(!this.taggedGameObjects.get(Laika.TAG_DECO).contains(go)) {
				if(pos.x < 0 || pos.y < 0 || pos.x > cam.viewportWidth || pos.y > cam.viewportHeight){
					it.remove();
				}
			}
		}
	}
}
