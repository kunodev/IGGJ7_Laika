package de.hamburg.laika;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import de.hamburg.laika.player.BulletFactory;
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
		assetManager.load("lazorkitten_map.png", Texture.class);
		assetManager.load("matroschka.png", Texture.class);
	}

	@Override
	public void create() {
		super.create();
		LaikaGameState gs = new LaikaGameState();
		this.gscm.initMainGameState(gs);
		
		
		gs.bgm = assetManager.get("Go Cart - Loop Mix.mp3");
		gs.bgm.setLooping(true);
		gs.bgm.play();
		gs.bgm.setVolume(0.1f);
		
		//initBackGround(gs);
		
		BulletFactory.HAMMER = assetManager.get("hammer_pew.png");
		BulletFactory.MATROSCHKA = assetManager.get("matroschka.png");

		gs.init(serviceMan);
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