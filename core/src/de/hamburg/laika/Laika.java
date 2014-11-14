package de.hamburg.laika;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.EnemyMovement.Linear;
import de.hamburg.laika.EnemyType.Comet;
import de.hamburg.laika.player.PlayerOne;
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
		laika.addComponent(new PlayerOne());

		GameObject meteor = new GameObject(new Vector2(640.f, 768.f),gs);
		meteor.addComponent(new Linear(new Vector2(-32.f, -64.f)));
		meteor.addComponent(new Comet(assetManager.get("badlogic.jpg", Texture.class)));
	}
}

