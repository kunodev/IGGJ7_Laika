package de.hamburg.laika.EnemyType.factory;

import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.Laika;
import de.hamburg.laika.EnemyMovement.LinearMovement;
import de.hamburg.laika.EnemyType.BagCat;
import de.hamburg.laika.EnemyType.HealthComponent;
import de.hamburg.laika.player.RotateSpriteComponent;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.ExtraSimpleCollisionComponent;

public class PoopFactory implements IComponentCollectionFactory { 
	final Texture poopTex;
	final int health;
	float lastY = Laika.HEIGHT;
	GameState gs;

	public PoopFactory(Texture poopTex, int health, GameState gs) {
		this.health = health;
		this.poopTex = poopTex;
		this.gs = gs;
	}

	@Override
	public List<Object> createComponents() {
		
		GameObject poop = new GameObject(new Vector2(Laika.WIDTH, lastY), Laika.TAG_ENEMY, gs);
		lastY -= poopTex.getHeight();
		
		poop.addComponent(new HealthComponent(health));
		SpriteWrapper sw = new SpriteWrapper(poopTex);
		poop.addComponent(sw);
		poop.addComponent(new LinearMovement(new Vector2(-3.0f, 0)));
		poop.addComponent(new BagCat());
		poop.addComponent(new RotateSpriteComponent(sw.s, 3.0f));
		ExtraSimpleCollisionComponent collComp = new ExtraSimpleCollisionComponent();
		collComp.tagToSearch = Laika.TAG_PLAYER;
		poop.addComponent(collComp);

		return Collections.emptyList();
	}
}
