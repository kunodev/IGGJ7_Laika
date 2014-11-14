package de.hamburg.laika.player;

import com.badlogic.gdx.math.Vector2;

import de.hamburg.laika.button.ButtonListener;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.simpleservice.FontManager;

public class UpgradeComponent implements ButtonListener {
	private int numUpdates = 0;
	
	@Render
	public void render(FontManager fm, Vector2 pos) {
		fm.drawTextAbsolut(pos.x + 100, pos.y, "" + numUpdates);
	}

	@Override
	public void onHover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick() {
		numUpdates++;
	}
}
