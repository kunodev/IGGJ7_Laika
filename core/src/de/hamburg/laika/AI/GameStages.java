package de.hamburg.laika.AI;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import de.hamburg.laika.EnemyMovement.AntiZMovement;
import de.hamburg.laika.EnemyMovement.CurveMovement;
import de.hamburg.laika.EnemyType.factory.BagCatFactory;
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

		if (alienFac.stageComplete() && stageCounter == 1) {
			final Texture cometTexture = assetManager.get("komet.png", Texture.class);
			CometFactory cometFac = new CometFactory(gs, 2.0f, false, cometTexture);
			alienFac.registerEnemyType(5, cometFac);

			final Texture bagCatTexture = assetManager.get("lazorkitten_map.png", Texture.class);

			System.out.println("Stage 1");
		}

		if (alienFac.stageComplete() && stageCounter == 2) {
			final Texture cometTexture = assetManager.get("komet.png", Texture.class);
			CometFactory cometFac = new CometFactory(gs, 2.0f, false, cometTexture);
			alienFac.registerEnemyType(5, cometFac);

			final Texture bagCatTexture = assetManager.get("lazorkitten_map.png", Texture.class);
			alienFac.registerEnemyType(4, new BagCatFactory(bagCatTexture, 100, new AntiZMovement(5, 100, 150)));

			System.out.println("Stage 2");
		}
		if (alienFac.stageComplete() && stageCounter == 3) {

			final Texture catTexture = assetManager.get("cat.png", Texture.class);
			alienFac.registerEnemyType(25, new JaegerFactory(ship.getPos(), catTexture));

			final Texture bagCatTexture = assetManager.get("lazorkitten_map.png", Texture.class);
			alienFac.registerEnemyType(12, new BagCatFactory(bagCatTexture, 100, new CurveMovement(4)));

			System.out.println("Stage 3");
		}

	}
}
