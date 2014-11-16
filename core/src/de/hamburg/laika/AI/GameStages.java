package de.hamburg.laika.AI;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import de.hamburg.laika.EnemyMovement.AntiZMovement;
import de.hamburg.laika.EnemyMovement.CurveMovement;
import de.hamburg.laika.EnemyType.factory.BagCatFactory;
import de.hamburg.laika.EnemyType.factory.CometFactory;
import de.hamburg.laika.EnemyType.factory.JaegerFactory;
import de.hamburg.laika.EnemyType.factory.PoopFactory;
import de.kuro.lazyjam.asciiassetextension.AnimationWrapper;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

public class GameStages {

	private AlienFactory alienFac;
	private AssetManager assetManager;
	private final GameObject ship;

	int stageCounter = 0;

	public GameStages(AlienFactory alienFac, AssetManager assetManager, GameObject ship) {
		this.alienFac = alienFac;
		this.assetManager = assetManager;
		this.ship = ship;
	}

	@Update
	public void update(GameState gs) {

		if (alienFac.stageComplete() && stageCounter == 0) {
			
			alienFac.setChance(0.5f);
			final Texture cometTexture = assetManager.get("komet.png", Texture.class);
			CometFactory cometFac = new CometFactory(gs, 2.0f, false, cometTexture);
			alienFac.registerEnemyType(5, cometFac);

			stageCounter++;
			System.out.println("Stage 1");
		}

		if (alienFac.stageComplete() && stageCounter == 1) {
			final Texture cometTexture = assetManager.get("komet.png", Texture.class);
			CometFactory cometFac = new CometFactory(gs, 2.0f, false, cometTexture);
			alienFac.registerEnemyType(5, cometFac);

			final Texture bagCatTexture = assetManager.get("lazorkitten_map.png", Texture.class);
			final Texture bagCatLazerTexture = assetManager.get("lazorkitten_pew.png", Texture.class);
			final Texture bagCatLazerAniTex = assetManager.get("lazorkitten_spawnlazor.png", Texture.class);
			final Animation bagCatLazerAni = AnimationWrapper.loadAnimation(bagCatLazerAniTex, 3, 1, 1.0f/9.0f, Animation.PlayMode.NORMAL);

			alienFac.registerEnemyType(4, new BagCatFactory(bagCatTexture, bagCatLazerTexture, bagCatLazerAni, 100, new AntiZMovement(5, 100, 150)));

			stageCounter++;
			System.out.println("Stage 2");
		}
		if (alienFac.stageComplete() && stageCounter == 2) {

			final Texture catTexture = assetManager.get("katzenblobb.png", Texture.class);
			alienFac.registerEnemyType(25, new JaegerFactory(ship.getPos(), catTexture));

			stageCounter++;
			System.out.println("Stage 3");
		}
		
		if (alienFac.stageComplete() && stageCounter == 3 ) {
			final Texture bagCatTexture = assetManager.get("lazorkitten_map.png", Texture.class);
			final Texture bagCatLazerTexture = assetManager.get("lazorkitten_pew.png", Texture.class);
			final Texture bagCatLazerAniTex = assetManager.get("lazorkitten_spawnlazor.png", Texture.class);
			final Animation bagCatLazerAni = AnimationWrapper.loadAnimation(bagCatLazerAniTex, 3, 1, 1.0f/9.0f, Animation.PlayMode.NORMAL);

			alienFac.registerEnemyType(12, new BagCatFactory(bagCatTexture, bagCatLazerTexture,  bagCatLazerAni, 100, new CurveMovement(4)));

			stageCounter++;
			System.out.println("Stage 4");
		}
		if (alienFac.stageComplete() && stageCounter == 4) {
			
			alienFac.setChance(3.0f);
			final Texture cometTexture = assetManager.get("komet.png", Texture.class);
			CometFactory cometFac = new CometFactory(gs, 3.0f, false, cometTexture);
			alienFac.registerEnemyType(5, cometFac);
			CometFactory cometFac2 = new CometFactory(gs, 3.0f, true, cometTexture);
			alienFac.registerEnemyType(5, cometFac2);
			stageCounter++;
			System.out.println("Stage 4");
		}
		if (alienFac.stageComplete() && stageCounter == 5) {
			
			alienFac.setChance(0.5f);

			final Texture bagCatTexture = assetManager.get("lazorkitten_map.png", Texture.class);
			final Texture bagCatLazerTexture = assetManager.get("lazorkitten_pew.png", Texture.class);
			final Texture bagCatLazerAniTex = assetManager.get("lazorkitten_spawnlazor.png", Texture.class);
			final Animation bagCatLazerAni = AnimationWrapper.loadAnimation(bagCatLazerAniTex, 3, 1, 1.0f/9.0f, Animation.PlayMode.NORMAL);
			alienFac.registerEnemyType(12, new BagCatFactory(bagCatTexture, bagCatLazerTexture, bagCatLazerAni, 100, new CurveMovement(4)));
			
			stageCounter++;
		}
		if (alienFac.stageComplete() && stageCounter == 6) {
			
			alienFac.setChance(0.5f);

			final Texture bagCatTexture = assetManager.get("lazorkitten_map.png", Texture.class);
			final Texture bagCatLazerTexture = assetManager.get("lazorkitten_pew.png", Texture.class);
			
			final Texture bagCatLazerAniTex = assetManager.get("lazorkitten_spawnlazor.png", Texture.class);
			final Animation bagCatLazerAni = AnimationWrapper.loadAnimation(bagCatLazerAniTex, 3, 1, 1.0f/9.0f, Animation.PlayMode.NORMAL);
			alienFac.registerEnemyType(12, new BagCatFactory(bagCatTexture, bagCatLazerTexture,bagCatLazerAni, 100, new CurveMovement(4)));
			stageCounter++;
		}
		
		if (alienFac.stageComplete() && stageCounter == 7) {
			
			alienFac.setChance(1.0f);

			final Texture cometTexture = assetManager.get("komet.png", Texture.class);
			CometFactory cometFac = new CometFactory(gs, 3.0f, false, cometTexture);
			alienFac.registerEnemyType(15, cometFac);
			
			stageCounter++;
		}
		
		if (alienFac.stageComplete() && stageCounter == 8) {
			alienFac.setChance(90); 
			
			final Texture poopTex = assetManager.get("poop.png", Texture.class);
			
			PoopFactory poopFac = new PoopFactory(poopTex, 100, gs);
			alienFac.registerEnemyType(12, poopFac);
			
			stageCounter++;
		}
		
		if (alienFac.stageComplete() && stageCounter == 9) {
			alienFac.setChance(0.5f); 
			
			final Texture bagCatTexture = assetManager.get("lazorkitten_map.png", Texture.class);
			final Texture bagCatLazerTexture = assetManager.get("lazorkitten_pew.png", Texture.class);
			

			final Texture bagCatLazerAniTex = assetManager.get("lazorkitten_spawnlazor.png", Texture.class);
			final Animation bagCatLazerAni = AnimationWrapper.loadAnimation(bagCatLazerAniTex, 3, 1, 1.0f/9.0f, Animation.PlayMode.NORMAL);
			alienFac.registerEnemyType(12, new BagCatFactory(bagCatTexture, bagCatLazerTexture, bagCatLazerAni, 100, new CurveMovement(4)));
			
			stageCounter++;
		}
		
		if (alienFac.stageComplete() && stageCounter == 10) {
			alienFac.setChance(90); 
			
			final Texture poopTex = assetManager.get("poop.png", Texture.class);
			
			PoopFactory poopFac = new PoopFactory(poopTex, 100, gs);
			alienFac.registerEnemyType(12, poopFac);
			
			stageCounter++;
		}
 

	}
}
