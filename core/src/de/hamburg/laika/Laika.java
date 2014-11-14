package de.hamburg.laika;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.button.ButtonComponent;
import de.hamburg.laika.player.PlayerControl;
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
		
		
		UpgradeComponent upgradeComponent = new UpgradeComponent();
		laika.addComponent(upgradeComponent);
		GameObject upgradeButton = new GameObject(new Vector2(50, 50),gs);
		upgradeButton.addComponent(new ButtonComponent(upgradeComponent, "Click me", 100, 100));
	}
}

