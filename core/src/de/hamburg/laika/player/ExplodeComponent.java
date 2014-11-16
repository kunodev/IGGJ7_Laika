package de.hamburg.laika.player;

import de.kuro.lazyjam.cdiutils.annotations.Update;

public class ExplodeComponent {
	public boolean start = false;
	
	@Update
	public void update() {
		if (start) System.out.println("start");
	}
}
