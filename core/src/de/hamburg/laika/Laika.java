package de.hamburg.laika;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.EnemyMovement.PursueTarget;
import de.hamburg.laika.EnemyType.Comet;
import de.hamburg.laika.button.ButtonComponent;
import de.hamburg.laika.player.PlayerControl;
import de.hamburg.laika.player.SpeedUpgradeComponent;
import de.hamburg.laika.player.UpgradeComponent;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.main.LazyJamApplicationAdapter;

public class Laika extends LazyJamApplicationAdapter {


	@Override
	public void loadAssets() {
		assetManager.load("badlogic.jpg", Texture.class);
	}
	
	@Override
	public void create () {
		super.create();
		GameState gs = new GameState();
		this.gscm.initMainGameState(gs);
		
		GameObject laika = new GameObject(new Vector2(),gs);
		laika.addComponent(new PlayerControl());

		UpgradeComponent upgradeComponent = new SpeedUpgradeComponent();
		laika.addComponent(upgradeComponent);
		GameObject upgradeButton = new GameObject(new Vector2(50, 50),gs);
		upgradeButton.addComponent(new ButtonComponent(upgradeComponent, "Moar Speed", 100, 100));

		GameObject comet = new GameObject(new Vector2(1024, 768), gs);
		comet.addComponent(new PursueTarget(laika, 96.f));
		comet.addComponent(new Comet(assetManager.get("badlogic.jpg", Texture.class)));
	}
}

