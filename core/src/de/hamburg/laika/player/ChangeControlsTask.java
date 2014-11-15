package de.hamburg.laika.player;

import de.hamburg.laika.inputmap.InputMap;

public class ChangeControlsTask implements Runnable {
	
	public InputMap im;
	
	public ChangeControlsTask(InputMap im) {
		this.im = im;
	}

	@Override
	public void run() {
		im.switcharoo();
	}

}
