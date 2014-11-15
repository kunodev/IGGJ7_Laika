package de.hamburg.laika;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.AI.AlienFactory;
import de.hamburg.laika.EnemyMovement.PursueTarget;
import de.hamburg.laika.EnemyType.Comet;
import de.hamburg.laika.EnemyType.Jaeger;
import de.hamburg.laika.button.ButtonComponent;
import de.hamburg.laika.player.CoinsComponent;
import de.hamburg.laika.player.PlayerControl;
import de.hamburg.laika.player.ShieldControl;
import de.hamburg.laika.player.SpeedUpgradeComponent;
import de.hamburg.laika.player.UpgradeComponent;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.main.LazyJamApplicationAdapter;

public class Laika extends LazyJamApplicationAdapter {
	public static float WIDTH = 1024;
	public static float HEIGHT = 768;

	public Laika(){
		super(WIDTH, HEIGHT);
	}

	@Override
	public void loadAssets() {
		assetManager.load("badlogic.jpg", Texture.class);
		assetManager.load("cat.png", Texture.class);
		assetManager.load("poop.png", Texture.class);
	}
	
	@Override
	public void create () {
		super.create();
		GameState gs = new GameState();
		this.gscm.initMainGameState(gs);
		
		GameObject laika = new GameObject(new Vector2(50.f, 50.f), gs);
		laika.addComponent(new PlayerControl());

		AlienFactory alienFac = new AlienFactory();
		laika.addComponent(alienFac);
		laika.addComponent(new CoinsComponent());
		

		UpgradeComponent upgradeComponent = new SpeedUpgradeComponent();
		laika.addComponent(upgradeComponent);
		laika.addComponent(new ShieldControl());
		GameObject upgradeButton = new GameObject(new Vector2(50, 50),gs);
		upgradeButton.addComponent(new ButtonComponent(upgradeComponent, "Moar Speed", 100, 100));

		GameObject jaeger = new GameObject(new Vector2(WIDTH, HEIGHT), gs);
		jaeger.addComponent(new Jaeger(assetManager.get("cat.png", Texture.class), 96.0f, laika));

		GameObject comet = new GameObject(new Vector2(WIDTH * 0.33f, HEIGHT * 0.67f), gs);
		comet.addComponent(new Comet(assetManager.get("poop.png", Texture.class), 30.0f));
	}
}