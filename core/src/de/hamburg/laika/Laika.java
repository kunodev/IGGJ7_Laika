package de.hamburg.laika;

import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.AI.AlienFactory;
import de.hamburg.laika.button.ButtonComponent;
import de.hamburg.laika.player.CoinsComponent;
import de.hamburg.laika.player.PlayerControl;
import de.hamburg.laika.player.SpeedUpgradeComponent;
import de.hamburg.laika.player.UpgradeComponent;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.main.LazyJamApplicationAdapter;

public class Laika extends LazyJamApplicationAdapter {


	@Override
	public void loadAssets() {
		
	}
	
	@Override
	public void create () {
		super.create();
		GameState gs = new GameState();
		this.gscm.initMainGameState(gs);
		
		GameObject laika = new GameObject(new Vector2(),gs);
		laika.addComponent(new PlayerControl());
		
		AlienFactory alienFac = new AlienFactory();		
		laika.addComponent(alienFac);
		laika.addComponent(new CoinsComponent());
		
		UpgradeComponent upgradeComponent = new SpeedUpgradeComponent();
		laika.addComponent(upgradeComponent);
		GameObject upgradeButton = new GameObject(new Vector2(50, 50),gs);
		upgradeButton.addComponent(new ButtonComponent(upgradeComponent, "Moar Speed", 100, 100));
	}
}

