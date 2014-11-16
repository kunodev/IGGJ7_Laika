package de.hamburg.laika.EnemyType.factory;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import de.hamburg.laika.EnemyType.BagCat;
import de.hamburg.laika.EnemyType.HealthComponent;
import de.hamburg.laika.EnemyType.Laser;
import de.kuro.lazyjam.asciiassetextension.AnimationWrapper;

public class BagCatFactory implements IComponentCollectionFactory {
	final Texture catTex;
	final Texture lazerTex;
	final int health;
	final Object movement;
	final Animation lazerAni;

	public BagCatFactory(Texture catTex, Texture lazerTex, Animation lazerAni, int health, Object movement) {
		this.catTex = catTex;
		this.lazerTex = lazerTex;
		this.health = health;
		this.movement = movement;
		this.lazerAni = lazerAni;
	}

	@Override
	public List<Object> createComponents() {
		ArrayList<Object> components = new ArrayList<>();
		components.add(movement);
		components.add(new HealthComponent(100));
		components.add(new BagCat());
		components.add(new AnimationWrapper(catTex, 3, 1, 1.f / 6.f, Animation.PlayMode.LOOP_PINGPONG));
		components.add(new Laser(lazerTex,lazerAni, 3.0f, 2.0f, 1.0f, 0.5f, 5, -16.0f, 33.0f));
		return components;
	}
}
