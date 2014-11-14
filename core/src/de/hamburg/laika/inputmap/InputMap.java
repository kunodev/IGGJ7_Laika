package de.hamburg.laika.inputmap;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;

import de.kuro.lazyjam.cdiutils.annotations.Service;

@Service
public class InputMap {

	public enum Action {
		UP, DOWN
	}
	
	public Map<Action, Integer> actionToHWKey;
	
	
	public InputMap() {
		actionToHWKey = new HashMap<Action,Integer>();
		actionToHWKey.put(Action.UP, Keys.UP);
		actionToHWKey.put(Action.DOWN, Keys.DOWN);
	}
	
}
