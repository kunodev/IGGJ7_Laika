package de.kuro.lazyjam.ecmodel.concrete.components;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.kuro.lazyjam.asciiassetextension.IRectangleProvider;
import de.kuro.lazyjam.asciiassetextension.SpriteWrapper;
import de.kuro.lazyjam.cdiutils.annotations.Component;
import de.kuro.lazyjam.cdiutils.annotations.ComponentInit;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.cdiutils.cdihelper.CDICallHelper;
import de.kuro.lazyjam.cdiutils.context.GameObjectContext;

@Component(name="PNGSprite")
public class PNGSpriteRenderComponent extends SimpleAbstractAnimationComponent {	
	
	public int loopTick = 30;
	private int currentTick;
	
	@Update
	public void update() {
		currentTick++;
		if(currentTick >= loopTick) {
			this.incrementXOffset();
			currentTick = 0;
		}
	}
	
	@Render
	public void render(GameObjectContext goc) {
		CDICallHelper.callOnObject(goc, Render.class, super.getCurrent());
	}
	
	@ComponentInit
	public void init(String initString, AssetManager assetMan) {
		// Check params
		String[] initVal = initString.split("\\+");
		Texture preFab = assetMan.get(initVal[0] + ".png");
		int maxX = Integer.parseInt(initVal[1]);
		int maxY = Integer.parseInt(initVal[2]);
		// precalc
		int widthOfSprite = preFab.getWidth() / maxX;
		int heightOfSprite = preFab.getHeight() / maxY;
		int currX = 0;
		int currY = 0;
		// init fields
		if(this.renderableObjects == null) {
			this.renderableObjects = new ArrayList<ArrayList<IRectangleProvider>>();
		}
		for(int i=0; i<maxY; i++) {
			this.renderableObjects.add(new ArrayList<IRectangleProvider>());
			for(int j=0; j<maxX; j++) { 
				IRectangleProvider newDrawable = new SpriteWrapper(new Sprite(preFab, 
						currX * widthOfSprite, currY * heightOfSprite,
						widthOfSprite, heightOfSprite));
				this.renderableObjects.get(i).add(newDrawable);
				
				currX++;
			}
			currY++;
		}
	}
	
	public void applyToAllSprites(Consumer<SpriteWrapper> func) {
		for(ArrayList<IRectangleProvider> list : this.renderableObjects) {
			for(IRectangleProvider ele : list) {
				SpriteWrapper sw = (SpriteWrapper) ele;
				func.accept(sw);
			}
		}
	}
		
}
