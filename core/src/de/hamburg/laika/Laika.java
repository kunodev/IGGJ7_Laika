package de.hamburg.laika;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.AI.AlienFactory;
import de.hamburg.laika.EnemyMovement.LinearMovement;
import de.hamburg.laika.EnemyType.Comet;
import de.hamburg.laika.EnemyType.HealthComponent;
import de.hamburg.laika.EnemyType.Laser;
import de.hamburg.laika.EnemyType.factory.JaegerFactory;
import de.hamburg.laika.background.BackGroundGen;
import de.hamburg.laika.background.BackGroundMover;
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
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.PNGSpriteRenderComponent;
import de.kuro.lazyjam.ecmodel.concrete.components.RelativityComponent;
import de.kuro.lazyjam.main.LazyJamApplicationAdapter;
import de.kuro.lazyjam.tools.LimitedTimeWorkerThread;
import de.kuro.lazyjam.tools.WorkerThread;

public class Laika extends LazyJamApplicationAdapter {
	public static final String TAG_PLAYER = "ship";
	public static final String TAG_ENEMY = "enemy";
	public static final String TAG_DECO = "deco";
	
	public static float WIDTH = 1280;
	public static float HEIGHT = 720;

	WorkerThread controllerFuckUpThread;
	
	public Laika() {
		super(WIDTH, HEIGHT);
	}

	@Override
	public void loadAssets() {
		assetManager.load("cat.png", Texture.class);
		assetManager.load("poop.png", Texture.class);
		assetManager.load("rain.png", Texture.class);
		assetManager.load("button.png", Texture.class);
		assetManager.load("background1280720.png", Texture.class);
		assetManager.load("raumschiff_map.png", Texture.class);
		assetManager.load("Go Cart - Loop Mix.mp3", Music.class);
		assetManager.load("schutzschild.png", Texture.class);
		assetManager.load("shootingstar1.png", Texture.class);
		assetManager.load("shootingstar1.png", Texture.class);
		assetManager.load("shootingstar2.png", Texture.class);
		assetManager.load("shootingstar3.png", Texture.class);
		assetManager.load("purplestar.png", Texture.class);
		assetManager.load("greenstar.png", Texture.class);
		assetManager.load("pinkstar.png", Texture.class);
		assetManager.load("lazorkitten_map.png", Texture.class);
	}

	@Override
	public void create() {
		super.create();
		GameState gs = new LaikaGameState();
		this.gscm.initMainGameState(gs);
		gs.bgm = assetManager.get("Go Cart - Loop Mix.mp3");
		gs.bgm.setLooping(true);
		gs.bgm.play();
		gs.bgm.setVolume(0.1f);
		
		//initBackGround(gs);

		GameObject laika = new GameObject(new Vector2(50.f, 50.f), TAG_PLAYER, gs);
		laika.addComponent(new PlayerControl());
		laika.addComponent(new HealthComponent(500));
		//laika.addComponent(new ASCIIPicture("PLAYER"));
		PNGSpriteRenderComponent shipAnimation = new PNGSpriteRenderComponent();
		shipAnimation.init("raumschiff_map+2+1", assetManager);
		laika.addComponent(shipAnimation);
		
		BackGroundGen backGroundGen = new BackGroundGen(gs);
		laika.addComponent(backGroundGen);
		//ADD STARSTUFF backGroundGen.registerEnemyType(enemyType);
		backGroundGen.registerStardust(assetManager.get("shootingstar1.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("shootingstar2.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("shootingstar3.png", Texture.class));
		
		backGroundGen.registerStardust(assetManager.get("purplestar.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("greenstar.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("pinkstar.png", Texture.class));
				
		AlienFactory alienFac = new AlienFactory(gs);
		laika.addComponent(alienFac);
		laika.addComponent(new InfoTextComponent());


		UpgradeComponent upgradeComponent = new SpeedModificationComponent();
		UpgradeComponent upgradeComponent2 = new MainCannonModificationComponent();
		UpgradeComponent upgradeComponent3 = new SideCannonModificationComponent();
		
		laika.addComponent(upgradeComponent);
		laika.addComponent(upgradeComponent2);
		laika.addComponent(upgradeComponent3);
		laika.addComponent(new RocketControl());
		laika.addComponent(new SmallCannonControl());
		
		GameObject shield = new GameObject(new Vector2(), TAG_PLAYER, gs);
		RelativityComponent relComp = new RelativityComponent();
		UpgradeComponent upgradeComponent4 = new ShieldModificationComponent();
		
		relComp.parent = laika;
		shield.addComponent(new ShieldControl());
		shield.addComponent(relComp);
		shield.addComponent(new SpriteWrapper(assetManager.get("schutzschild.png", Texture.class)));
		shield.addComponent(upgradeComponent4);
		
		ButtonBuilder bb = new ButtonBuilder(gs);
		Texture buttonBG = assetManager.get("button.png", Texture.class);
		bb.createButton(upgradeComponent, "Moar Speed", buttonBG);
		bb.createButton(upgradeComponent2, "Moar big pew", buttonBG);
		bb.createButton(upgradeComponent3, "Moar Small pew", buttonBG);
		bb.createButton(upgradeComponent4, "Moar Shields", buttonBG);

		GameObject jaeger = new GameObject(new Vector2(WIDTH, HEIGHT), gs);
		jaeger.addComponent(new Laser(assetManager.get("rain.png", Texture.class), 4.0f, 2.0f, 1.5f, 1.0f, 50));
		Texture catTexture = assetManager.get("cat.png", Texture.class);
		//jaeger.addComponent(new Jaeger(3.1f, laika.getPos()));
		//jaeger.addComponent(new SpriteWrapper(catTexture));
		
		GameObject comet = new GameObject(new Vector2(WIDTH * 0.33f, HEIGHT * 0.67f), gs);
		Texture cometTex = assetManager.get("poop.png", Texture.class);
		SpriteWrapper sw = new SpriteWrapper(cometTex);
		comet.addComponent(new Comet(7.0f));
		comet.addComponent(sw);

		
		alienFac.registerEnemyType(50, new JaegerFactory(laika.getPos(), catTexture) );

		GameObject lazorKitten = new GameObject(new Vector2(WIDTH, HEIGHT * 0.8f), TAG_ENEMY, gs);
		lazorKitten.addComponent(new LinearMovement(new Vector2(-2.0f, 0.0f)));
		lazorKitten.addComponent(new AnimationWrapper(assetManager.get("lazorkitten_map.png", Texture.class),3, 1, 1.f/6.f, Animation.PlayMode.LOOP_PINGPONG));

		ChangeControlsTask cct = new ChangeControlsTask(serviceMan.getService(InputMap.class));
		controllerFuckUpThread = new LimitedTimeWorkerThread(50000, cct, Integer.MAX_VALUE);
		controllerFuckUpThread.start();
		
	}

	private void initBackGround(GameState gs) {		
		GameObject backGround = new GameObject(new Vector2(WIDTH/2,HEIGHT/2), TAG_DECO, gs);
		Texture backgroundTex = assetManager.get("background1280720.png");
		backgroundTex.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		SpriteWrapper backgroundSprite = new SpriteWrapper(backgroundTex);
		System.out.println(backgroundTex.getWidth());
		BackGroundMover movement = new BackGroundMover();
		backGround.addComponent(backgroundSprite);
		backGround.addComponent(movement);
	}
}