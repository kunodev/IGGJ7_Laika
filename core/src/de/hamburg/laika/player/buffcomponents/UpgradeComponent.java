package de.hamburg.laika.player.buffcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.button.ButtonListener;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.simpleservice.FontManager;

public class UpgradeComponent implements ButtonListener {
	
	protected int numUpdates = 0;
	public Vector2 offset = new Vector2(100, 0);
	
	@Render
	public void render(FontManager fm, Vector2 pos) {
		fm.drawTextAbsolutCentered((int)pos.x + (int)offset.x, (int)pos.y + (int)offset.y, "" + numUpdates, Color.WHITE);
	}

	@Override
	public void onHover() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void onClick() {
		numUpdates++;
	}

	@Override
	public void onRightMouseClick() {
		numUpdates++;
	}
}
