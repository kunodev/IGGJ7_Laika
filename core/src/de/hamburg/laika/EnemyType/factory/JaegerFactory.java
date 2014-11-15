package de.hamburg.laika.EnemyType.factory;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.Laika;
import de.hamburg.laika.EnemyType.HealthComponent;
import de.hamburg.laika.EnemyType.Jaeger;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.ecmodel.concrete.components.ExtraSimpleCollisionComponent;

public class JaegerFactory implements IComponentCollectionFactory{

	public Vector2 targetVector;
	public Texture tex;
	
	public JaegerFactory(Vector2 target, Texture tex) {
		this.targetVector = target;
		this.tex = tex;
	}
	
	@Override
	public List<Object> createComponents() {
		List<Object> result = new ArrayList<Object>();
		//new Jaeger(3.1f, laika), new SpriteWrapper(catTexture), new ExtraSimpleCollisionComponent()
		result.add(new Jaeger(3.1f, targetVector));
		result.add(new SpriteWrapper(tex));

		result.add(new HealthComponent(MathUtils.random(90, 110)));
		ExtraSimpleCollisionComponent collComp = new ExtraSimpleCollisionComponent();
		collComp.tagToSearch = Laika.TAG_PLAYER;
		result.add(collComp);
//		result.add(e)
		return result;
	}

	
}
