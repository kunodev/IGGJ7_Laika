package de.hamburg.laika.AI;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import de.hamburg.laika.EnemyType.factory.CometFactory;
import de.hamburg.laika.EnemyType.factory.JaegerFactory;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

public class GameStages {

	private AlienFactory alienFac;
	private AssetManager assetManager;
	private final GameObject ship;
	
	int stageCounter = 1;
	
	public GameStages(AlienFactory alienFac, AssetManager assetManager, GameObject ship) {
		this.alienFac = alienFac;
		this.assetManager = assetManager;
		this.ship = ship;
	}
	
	@Update
	public void update(GameState gs) {

		if(alienFac.stageComplete() && stageCounter == 1) {
			final Texture cometTexture = assetManager.get("komet.png", Texture.class);			
			CometFactory cometFac = new CometFactory(gs, 2.0f, false, cometTexture);
			alienFac.registerEnemyType(5, cometFac);

			System.out.println("Stage 1");
		}
		
		if(alienFac.stageComplete() && stageCounter == 2) {
			final Texture cometTexture = assetManager.get("komet.png", Texture.class);			
			CometFactory cometFac = new CometFactory(gs, 2.0f, false, cometTexture);
			alienFac.registerEnemyType(5, cometFac);

			System.out.println("Stage 2");
		}
		if(alienFac.stageComplete() && stageCounter == 3) {
			
			final Texture catTexture = assetManager.get("cat.png", Texture.class);
			alienFac.registerEnemyType(25, new JaegerFactory(ship.getPos(), catTexture) );
			
			System.out.println("Stage 3");
		}
			
	}
}
