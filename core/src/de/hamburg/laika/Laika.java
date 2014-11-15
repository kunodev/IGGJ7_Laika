package de.hamburg.laika;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.AI.AlienFactory;
import de.hamburg.laika.EnemyType.Comet;
import de.hamburg.laika.EnemyType.HealthComponent;
import de.hamburg.laika.EnemyType.Jaeger;
import de.hamburg.laika.EnemyType.Laser;
import de.hamburg.laika.EnemyType.factory.JaegerFactory;
import de.hamburg.laika.background.BackGroundGen;
import de.hamburg.laika.button.ButtonBuilder;
import de.hamburg.laika.button.ButtonComponent;
import de.hamburg.laika.inputmap.InputMap;
import de.hamburg.laika.player.*;
import de.kuro.lazyjam.asciiassetextension.ASCIIPicture;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.ecmodel.IGameState;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.ExtraSimpleCollisionComponent;
import de.kuro.lazyjam.main.LazyJamApplicationAdapter;
import de.kuro.lazyjam.tools.LimitedTimeWorkerThread;
import de.kuro.lazyjam.tools.WorkerThread;

public class Laika extends LazyJamApplicationAdapter {
	public static final String TAG_PLAYER = "ship";
	public static final String TAG_ENEMY = "enemy";
	
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
	}

	@Override
	public void create() {
		super.create();
		GameState gs = new GameState();
		this.gscm.initMainGameState(gs);
		
		GameObject backGround = new GameObject(new Vector2(0f,10f), gs);
		SpriteWrapper backgroundSprite = new SpriteWrapper(assetManager.get("background1280720.png"));
		backgroundSprite.s.setSize(Laika.WIDTH, Laika.HEIGHT);
		backGround.addComponent(backgroundSprite);

		GameObject laika = new GameObject(new Vector2(50.f, 50.f), TAG_PLAYER, gs);
		laika.addComponent(new PlayerControl());
		laika.addComponent(new HealthComponent(500));
		laika.addComponent(new ASCIIPicture("PLAYER"));
		
		BackGroundGen backGroundGen = new BackGroundGen(gs);
		laika.addComponent(backGroundGen);
		//ADD STARSTUFF backGroundGen.registerEnemyType(enemyType);
		
		AlienFactory alienFac = new AlienFactory(gs);
		laika.addComponent(alienFac);
		laika.addComponent(new CoinsComponent());
		laika.addComponent(new InfoTextComponent());


		UpgradeComponent upgradeComponent = new SpeedUpgradeComponent();
		laika.addComponent(upgradeComponent);
		laika.addComponent(new ShieldControl());
		laika.addComponent(new RocketControl());
		
		ButtonBuilder bb = new ButtonBuilder(gs);
		Texture buttonBG = assetManager.get("button.png", Texture.class);
		bb.createButton(upgradeComponent, "Moar Speed", buttonBG);
		bb.createButton(null, "nothing", buttonBG);
		bb.createButton(null, "nothing", buttonBG);
		bb.createButton(null, "nothing", buttonBG);

		GameObject jaeger = new GameObject(new Vector2(WIDTH, HEIGHT), gs);
		jaeger.addComponent(new Laser(assetManager.get("rain.png", Texture.class), 8.0f, 2.0f, 1.5f, 1.0f, 50));
		Texture catTexture = assetManager.get("cat.png", Texture.class);
		jaeger.addComponent(new Jaeger(3.1f, laika.getPos()));
		jaeger.addComponent(new SpriteWrapper(catTexture));
		
		GameObject comet = new GameObject(new Vector2(WIDTH * 0.33f, HEIGHT * 0.67f), gs);
		Texture cometTex = assetManager.get("poop.png", Texture.class);
		SpriteWrapper sw = new SpriteWrapper(cometTex);
		comet.addComponent(new Comet(7.0f));
		comet.addComponent(sw);
		
		alienFac.registerEnemyType(50, new JaegerFactory(laika.getPos(), catTexture) );
		
		ChangeControlsTask cct = new ChangeControlsTask(serviceMan.getService(InputMap.class));
		controllerFuckUpThread = new LimitedTimeWorkerThread(5000, cct, Integer.MAX_VALUE);
		controllerFuckUpThread.start();
		
	}
}