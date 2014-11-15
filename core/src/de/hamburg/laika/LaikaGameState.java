package de.hamburg.laika;

import java.util.Iterator;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.AI.AlienFactory;
import de.hamburg.laika.EnemyMovement.LinearMovement;
import de.hamburg.laika.EnemyType.Comet;
import de.hamburg.laika.EnemyType.HealthComponent;
import de.hamburg.laika.EnemyType.Laser;
import de.hamburg.laika.EnemyType.factory.JaegerFactory;
import de.hamburg.laika.background.BackGroundGen;
import de.hamburg.laika.button.ButtonBuilder;
import de.hamburg.laika.inputmap.InputMap;
import de.hamburg.laika.player.ChangeControlsTask;
import de.hamburg.laika.player.InfoTextComponent;
import de.hamburg.laika.player.PlayerControl;
import de.hamburg.laika.player.RocketControl;
import de.hamburg.laika.player.ShieldControl;
import de.hamburg.laika.player.SmallCannonControl;
import de.hamburg.laika.player.buffcomponents.MainCannonModificationComponent;
import de.hamburg.laika.player.buffcomponents.ShieldModificationComponent;
import de.hamburg.laika.player.buffcomponents.SideCannonModificationComponent;
import de.hamburg.laika.player.buffcomponents.SpeedModificationComponent;
import de.hamburg.laika.player.buffcomponents.UpgradeComponent;
import de.kuro.lazyjam.asciiassetextension.AnimationWrapper;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.cdiutils.cdihelper.ServiceManager;
import de.kuro.lazyjam.cdiutils.context.GlobalContext;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.PNGSpriteRenderComponent;
import de.kuro.lazyjam.ecmodel.concrete.components.RelativityComponent;
import de.kuro.lazyjam.tools.LimitedTimeWorkerThread;
import de.kuro.lazyjam.tools.WorkerThread;

public class LaikaGameState extends GameState{

	WorkerThread controllerFuckUpThread;
	
	@Override
	protected void update(GlobalContext globalContext) {
		super.update(globalContext);
		final OrthographicCamera cam = globalContext.serviceMan.getService(OrthographicCamera.class);
		for(Iterator<GameObject> it = gameObjects.iterator(); it.hasNext();){
			GameObject go = it.next();
			final Vector2 pos = go.getPos();
			//if(!this.taggedGameObjects.get(Laika.TAG_DECO).contains(go)) {
				if(pos.x < 0 || pos.y < 0 || pos.x > cam.viewportWidth || pos.y > cam.viewportHeight){
					it.remove();
				}
			//}
		}
	}
	
	public void init(ServiceManager serviceman) {
		AssetManager assetManager = serviceman.getService(AssetManager.class);
		GameObject laika = new GameObject(new Vector2(50.f, 50.f), Laika.TAG_PLAYER, this);
		laika.addComponent(new PlayerControl());
		laika.addComponent(new HealthComponent(500));
		//laika.addComponent(new ASCIIPicture("PLAYER"));
		PNGSpriteRenderComponent shipAnimation = new PNGSpriteRenderComponent();
		shipAnimation.init("raumschiff_map+2+1", assetManager);
		laika.addComponent(shipAnimation);
		
		GameObject canon = new GameObject(new Vector2(50.f, 50.f), Laika.TAG_DECO, this);
		PNGSpriteRenderComponent cannonAnimation = new PNGSpriteRenderComponent();
		cannonAnimation.init("grosse_kanone_map+3+1", assetManager);
		cannonAnimation.sprite.s.setOriginCenter();
		cannonAnimation.sprite.s.setOrigin(cannonAnimation.sprite.s.getOriginX(), cannonAnimation.sprite.s.getOriginY() - 10);
		canon.addComponent(cannonAnimation);
		RelativityComponent relCanonComp = new RelativityComponent();
		relCanonComp.parent = laika;
		canon.addComponent(relCanonComp);
		canon.addComponent(new SmallCannonControl());
		
		BackGroundGen backGroundGen = new BackGroundGen(this);
		laika.addComponent(backGroundGen);
		//ADD STARSTUFF backGroundGen.registerEnemyType(enemyType);
		backGroundGen.registerStardust(assetManager.get("shootingstar1.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("shootingstar2.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("shootingstar3.png", Texture.class));
		
		backGroundGen.registerStardust(assetManager.get("purplestar.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("greenstar.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("pinkstar.png", Texture.class));
		
		backGroundGen.registerStardust(assetManager.get("sparkle1.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("sparkle2.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("sparkle3.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("sparkle4.png", Texture.class));
				
		AlienFactory alienFac = new AlienFactory(this);
		laika.addComponent(alienFac);
		laika.addComponent(new InfoTextComponent());


		UpgradeComponent upgradeComponent = new SpeedModificationComponent();
		UpgradeComponent upgradeComponent2 = new MainCannonModificationComponent();
		UpgradeComponent upgradeComponent3 = new SideCannonModificationComponent();
		
		laika.addComponent(upgradeComponent);
		laika.addComponent(upgradeComponent2);
		canon.addComponent(upgradeComponent3);
		laika.addComponent(new RocketControl());
		
		
		GameObject shield = new GameObject(new Vector2(), Laika.TAG_PLAYER, this);
		RelativityComponent relComp = new RelativityComponent();
		UpgradeComponent upgradeComponent4 = new ShieldModificationComponent();
		
		relComp.parent = laika;
		shield.addComponent(new ShieldControl());
		shield.addComponent(relComp);
		shield.addComponent(new SpriteWrapper(assetManager.get("schutzschild.png", Texture.class)));
		shield.addComponent(upgradeComponent4);
		
		ButtonBuilder bb = new ButtonBuilder(this);
		Texture buttonBG = assetManager.get("button.png", Texture.class);
		bb.createButton(upgradeComponent, "Moar Speed", buttonBG);
		bb.createButton(upgradeComponent2, "Moar big pew", buttonBG);
		bb.createButton(upgradeComponent3, "Moar Small pew", buttonBG);
		bb.createButton(upgradeComponent4, "Moar Shields", buttonBG);

		GameObject jaeger = new GameObject(new Vector2(Laika.WIDTH, Laika.HEIGHT), this);
		jaeger.addComponent(new Laser(assetManager.get("rain.png", Texture.class), 4.0f, 2.0f, 1.5f, 1.0f, 50));
		Texture catTexture = assetManager.get("cat.png", Texture.class);
		jaeger.addComponent(new SpriteWrapper(catTexture));
		
		GameObject comet = new GameObject(new Vector2(Laika.WIDTH * 0.33f, Laika.HEIGHT * 0.67f), this);
		Texture cometTex = assetManager.get("poop.png", Texture.class);
		SpriteWrapper sw = new SpriteWrapper(cometTex);
		comet.addComponent(new Comet(7.0f));
		comet.addComponent(sw);

		
		alienFac.registerEnemyType(50, new JaegerFactory(laika.getPos(), catTexture) );

		GameObject lazorKitten = new GameObject(new Vector2(Laika.WIDTH, Laika.HEIGHT * 0.8f), Laika.TAG_ENEMY, this);
		lazorKitten.addComponent(new LinearMovement(new Vector2(-2.0f, 0.0f)));
		lazorKitten.addComponent(new AnimationWrapper(assetManager.get("lazorkitten_map.png", Texture.class),3, 1, 1.f/6.f, Animation.PlayMode.LOOP_PINGPONG));

		ChangeControlsTask cct = new ChangeControlsTask(serviceman.getService(InputMap.class));
		controllerFuckUpThread = new LimitedTimeWorkerThread(5000, cct, Integer.MAX_VALUE);
		controllerFuckUpThread = new LimitedTimeWorkerThread(50000, cct, Integer.MAX_VALUE);
		controllerFuckUpThread.start();
		
	}
}
