package de.hamburg.laika.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.inputmap.InputMap;
import de.hamburg.laika.inputmap.InputMap.Action;
import de.kuro.lazyjam.asciiassetextension.ASCIIPicture;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.ecmodel.concrete.components.PNGSpriteRenderComponent;
import de.kuro.lazyjam.simpleservice.FontManager;

public class SmallCannonControl {
	
	public static final int SMALL_CANNON_TICKS = 10;
	public int currentSmallCannonTicks;
	public int cannonTicksReduction;
	
	public int bonusDamage;

	public Vector2 towerPos = new Vector2();
	public static final Vector2 TOWER_OFFSET = new Vector2(25,0);
	
	public float towerangle;
	
	@Update
	public void update(Input i, Vector2 pos, InputMap map, GameState gs, PNGSpriteRenderComponent ship) {
		Rectangle shipRect = ship.sprite.getRectangle();
		Vector2 shipCenter = pos.cpy().add(shipRect.getWidth()/2, shipRect.getHeight()/2);

		Vector2 tempTowerOffset = TOWER_OFFSET.cpy();
		tempTowerOffset.rotate(towerangle);
		towerPos = tempTowerOffset.cpy().add(shipCenter);		

		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_AIM_LEFT))) {
			towerangle -= 10;
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_AIM_RIGHT))) {
			towerangle += 10;
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_SHOOT))) {
			if(currentSmallCannonTicks >= SMALL_CANNON_TICKS - cannonTicksReduction) {
				BulletFactory.createBullet(pos.cpy().add(tempTowerOffset.nor().scl(5.5f)), gs, tempTowerOffset, 10, true);
				currentSmallCannonTicks = 0;
			}
		}
		currentSmallCannonTicks++;
	}
	
	@Render
	public void render(FontManager fm) {
		fm.drawTextAbsolutCentered((int)towerPos.x, (int)towerPos.y, "TOWER", Color.WHITE);
	}
	
}
