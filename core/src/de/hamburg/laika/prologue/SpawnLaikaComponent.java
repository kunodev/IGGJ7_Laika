package de.hamburg.laika.prologue;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.PNGSpriteRenderComponent;

public class SpawnLaikaComponent {

	@Update
	public void createProLogueLaika(GameState gs, AssetManager assetManager, GameObject go) {
		GameObject laika = new GameObject(new Vector2(10f,330f), gs);
		PNGSpriteRenderComponent shipAnimation = new PNGSpriteRenderComponent();
		shipAnimation.init("raumschiff_map+2+1", assetManager);
		laika.addComponent(shipAnimation);
		laika.addComponent(new GuideLaikaComponent());
		go.selfDestruct(gs);
	}
	
}
