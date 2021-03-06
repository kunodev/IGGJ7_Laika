package de.hamburg.laika;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.background.BackGroundGen;
import de.hamburg.laika.prologue.Introduction1;
import de.hamburg.laika.prologue.StartGameComponent;
import de.kuro.lazyjam.cdiutils.cdihelper.ServiceManager;
import de.kuro.lazyjam.ecmodel.concrete.GameObject;

public class LaikaPreGameState extends LaikaGameState{
	
	
	public LaikaPreGameState(ServiceManager sm) {
		super(sm);
	}

	@Override
	public void init(ServiceManager serviceMan) {
		GameObject dummyMain = new GameObject(new Vector2(), this);
		AssetManager assetManager = serviceMan.getService(AssetManager.class);

		BackGroundGen backGroundGen = new BackGroundGen(this);
		backGroundGen.withCoins = false;
		backGroundGen.registerStardust(assetManager.get("shootingstar1.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("shootingstar2.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("shootingstar3.png", Texture.class));
		
		backGroundGen.registerStardust(assetManager.get("purplestar.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("greenstar.png", Texture.class));
		backGroundGen.registerStardust(assetManager.get("pinkstar.png", Texture.class));
		dummyMain.addComponent(backGroundGen);
		dummyMain.addComponent(new StartGameComponent());
		
		
		GameObject text1Obj = new GameObject(new Vector2(1f,330f), this);
		text1Obj.addComponent(new Introduction1());
		
		
		
	}
	

}
