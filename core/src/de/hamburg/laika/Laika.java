package de.hamburg.laika;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import de.hamburg.laika.player.BulletFactory;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.main.LazyJamApplicationAdapter;

public class Laika extends LazyJamApplicationAdapter {
	public static final String TAG_PLAYER = "ship";
	public static final String TAG_ENEMY = "enemy";
	public static final String TAG_DECO = "deco";
	
	public static float WIDTH = 1280;
	public static float HEIGHT = 720;
	
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
		assetManager.load("grosse_kanone_map.png", Texture.class);
		assetManager.load("Go Cart - Loop Mix.mp3", Music.class);
		assetManager.load("schutzschild.png", Texture.class);
		assetManager.load("hammer_pew.png", Texture.class);
		assetManager.load("shootingstar1.png", Texture.class);
		assetManager.load("shootingstar1.png", Texture.class);
		assetManager.load("shootingstar2.png", Texture.class);
		assetManager.load("shootingstar3.png", Texture.class);
		assetManager.load("purplestar.png", Texture.class);
		assetManager.load("greenstar.png", Texture.class);
		assetManager.load("pinkstar.png", Texture.class);
		assetManager.load("sichel_pew.png", Texture.class);
		assetManager.load("sparkle1.png", Texture.class);
		assetManager.load("sparkle2.png", Texture.class);
		assetManager.load("sparkle3.png", Texture.class);
		assetManager.load("sparkle4.png", Texture.class);
		assetManager.load("lazorkitten_map.png", Texture.class);
		assetManager.load("matroschka.png", Texture.class);
		assetManager.load("Laser_Shoot11.wav", Sound.class);

		assetManager.load("komet.png", Texture.class);
		assetManager.load("Hit_Hurt11.wav", Sound.class);
		assetManager.load("schriftzug.png", Texture.class);
		assetManager.load("shield.wav", Sound.class);
		assetManager.load("lazorkitten_pew.png", Texture.class);
	}

	@Override
	public void create() {
		super.create();
		
		LaikaPreGameState prelogueGameState = new LaikaPreGameState();
		this.gscm.initMainGameState(prelogueGameState);
		prelogueGameState.init(serviceMan);
		
//		LaikaGameState gs = new LaikaGameState();
//		this.gscm.initMainGameState(gs);
		
		
		Music bgm = assetManager.get("Go Cart - Loop Mix.mp3");
		bgm.setLooping(true);
		bgm.play();
		bgm.setVolume(0.1f);
		
		//initBackGround(gs);
		
		BulletFactory.HAMMER = assetManager.get("hammer_pew.png");
//		gs.init(serviceMan);
		BulletFactory.MATROSCHKA = assetManager.get("matroschka.png");
		BulletFactory.SICKLE = assetManager.get("sichel_pew.png");
		BulletFactory.assetMan = assetManager;
	}

//	private void initBackGround(GameState gs) {		
//		GameObject backGround = new GameObject(new Vector2(WIDTH/2,HEIGHT/2), TAG_DECO, gs);
//		Texture backgroundTex = assetManager.get("background1280720.png");
//		backgroundTex.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
//		SpriteWrapper backgroundSprite = new SpriteWrapper(backgroundTex);
//		System.out.println(backgroundTex.getWidth());
//		BackGroundMover movement = new BackGroundMover();
//		backGround.addComponent(backgroundSprite);
//		backGround.addComponent(movement);
//	}
}