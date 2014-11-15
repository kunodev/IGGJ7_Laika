package de.hamburg.laika.player;

import de.kuro.lazyjam.cdiutils.annotations.Update;

public class SpeedUpgradeComponent extends UpgradeComponent {

	@Update
	public void update(PlayerControl pc) {
		pc.speedModifier = 1f +numUpdates/100.0f;
	}
}
