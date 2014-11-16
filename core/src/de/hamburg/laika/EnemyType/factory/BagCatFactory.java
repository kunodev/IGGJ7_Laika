package de.hamburg.laika.EnemyType.factory;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import de.hamburg.laika.EnemyType.BagCat;
import de.hamburg.laika.EnemyType.HealthComponent;
import de.kuro.lazyjam.asciiassetextension.AnimationWrapper;

public class BagCatFactory implements IComponentCollectionFactory {
	final Texture catTex;
	final int health;
	final Object movement;

	public BagCatFactory(Texture catTex, int health, Object movement) {
		this.catTex = catTex;
		this.health = health;
		this.movement = movement;
	}

	@Override
	public List<Object> createComponents() {
		ArrayList<Object> components = new ArrayList<>();
		components.add(movement);
		components.add(new HealthComponent(100));
		components.add(new BagCat());
		components.add(new AnimationWrapper(catTex, 3, 1, 1.f / 6.f, Animation.PlayMode.LOOP_PINGPONG));
		return components;
	}
}
