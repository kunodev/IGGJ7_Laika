package de.hamburg.laika.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.inputmap.InputMap;
import de.hamburg.laika.inputmap.InputMap.Action;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.ecmodel.concrete.GameState;
import de.kuro.lazyjam.simpleservice.FontManager;

public class ShieldControl {
	
	public Vector2 shieldRenderPos = new Vector2();
	public static final Vector2 SHIELD_OFFSET = new Vector2(50,0);
	

	public Vector2 towerPos = new Vector2();
	public static final Vector2 TOWER_OFFSET = new Vector2(25,0);
	
	public float shieldangle;
	public float towerangle;
	
	@Update
	public void update(Input i, Vector2 pos, InputMap map, GameState gs) {
		Vector2 tempOffset = SHIELD_OFFSET.cpy();
		tempOffset.rotate(shieldangle);
		shieldRenderPos = tempOffset.cpy().add(pos);

		Vector2 tempTowerOffset = SHIELD_OFFSET.cpy();
		tempTowerOffset.rotate(towerangle);
		towerPos = tempTowerOffset.cpy().add(pos);
		
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_LEFT))) {
			shieldangle -= 10;
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_RIGHT))) {
			shieldangle += 10;
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_AIM_LEFT))) {
			towerangle -= 10;
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_AIM_RIGHT))) {
			towerangle += 10;
		}
		if(i.isKeyPressed(map.actionToHWKey.get(Action.SHIELD_SHOOT))) {
			BulletFactory.createBullet(pos.cpy().add(tempTowerOffset.nor().scl(5.5f)), gs, tempTowerOffset, 10);
		}
	}
	
	@Render
	public void render(FontManager fm) {
		fm.drawTextAbsolut(shieldRenderPos.x, shieldRenderPos.y, "SHIELD");
		fm.drawTextAbsolut(towerPos.x, towerPos.y, "TOWER");
	}
	
}