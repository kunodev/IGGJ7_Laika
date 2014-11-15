package de.kuro.lazyjam.ecmodel.concrete.components;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.EnemyType.HealthComponent;
import de.hamburg.laika.player.PlayerControl;
import de.kuro.lazyjam.asciiassetextension.IRectangleProvider;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.cdiutils.context.GameObjectContext;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

public class ExtraSimpleCollisionComponent {
	
	public List<Predicate<GameObject>> filters;
	public String tagToSearch;

	public ExtraSimpleCollisionComponent() {
		filters = new ArrayList<Predicate<GameObject>>();
	}
	
	@Update
	public void collideWith(Vector2 pos, GameState gs, GameObjectContext goc, IRectangleProvider rect) {
		Stream<GameObject> gameObjectsStream;
		if(tagToSearch == null) {
			gameObjectsStream = gs.gameObjects.stream();			
		} else {
			gameObjectsStream = gs.taggedGameObjects.get(tagToSearch).stream();
		}

		Predicate<GameObject> finalFilter = e -> e != goc.go;
		
		for(Predicate<GameObject> filter : this.filters) {
			finalFilter = finalFilter.and(filter);
		}
		finalFilter = finalFilter.and(e -> (e.getComponent(IRectangleProvider.class) != null) && e.getComponent(IRectangleProvider.class).getRectangle().overlaps(rect.getRectangle()));
		gameObjectsStream
			.filter(finalFilter)
			.forEach(e -> e.callCollide(goc));
	}

}
