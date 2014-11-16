package de.hamburg.laika.prologue;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.hamburg.laika.LaikaGameState;
import de.hamburg.laika.LaikaPreGameState;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.cdiutils.cdihelper.ServiceManager;
import de.kuro.lazyjam.ecmodel.GameStateContextManager;

public class StartGameComponent {

	@Update
	public void pollForMouseClick(GameStateContextManager gscm, Input i, ServiceManager serviceMan) {
		if(i.isButtonPressed(Buttons.LEFT)) {
			LaikaGameState gameGameState = new LaikaGameState();
			gscm.addGameState(LaikaGameState.class, gameGameState);
			gscm.setGameState(LaikaGameState.class);
			gameGameState.init(serviceMan);
		}
	}
	
	@Render
	public void howtoStart(SpriteBatch batch) {
		BitmapFont font = new BitmapFont();
//		font.scale(1.3f);
		font.draw(batch, "Click anywhere to Start", 0f, 20f);
	}
	
}
