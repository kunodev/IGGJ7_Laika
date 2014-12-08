package de.hamburg.laika.player;

import de.hamburg.laika.LaikaGameOverState;
import de.hamburg.laika.LaikaGameState;
import de.kuro.lazyjam.cdiutils.annotations.Destroy;
import de.kuro.lazyjam.cdiutils.cdihelper.ServiceManager;
import de.kuro.lazyjam.ecmodel.GameStateContextManager;

public class GameOverComponent {
	
	@Destroy
	public void loadGameOver(GameStateContextManager gscm, ServiceManager serviceMan) {
		gscm.removeGameState(LaikaGameOverState.class);
		LaikaGameOverState gameGameState = new LaikaGameOverState(serviceMan);
		gscm.addGameState(LaikaGameOverState.class, gameGameState);
		gscm.setGameState(LaikaGameOverState.class);
		gameGameState.init(serviceMan);
	}
}
