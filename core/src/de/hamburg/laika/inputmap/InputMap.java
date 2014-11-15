package de.hamburg.laika.inputmap;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;

import de.kuro.lazyjam.cdiutils.annotations.Service;

@Service
public class InputMap {

	public enum Action {
		UP, DOWN, LEFT, RIGHT, SHOOT, 
		SHIELD_LEFT, SHIELD_RIGHT, SHIELD_SHOOT, SHIELD_AIM_LEFT, SHIELD_AIM_RIGHT
	}
	
	public enum InputState {
		NORMAL, INVERTED
	}
	
	public Map<Action, Integer> actionToHWKey;	
	private InputState state;
	
	public InputMap() {
		actionToHWKey = new HashMap<Action,Integer>();
		p1Controls();
		p2Shields();
		this.state = InputState.NORMAL;
	}
	
	public void p1Controls() {
		actionToHWKey.put(Action.UP, Keys.UP);
		actionToHWKey.put(Action.DOWN, Keys.DOWN);
		actionToHWKey.put(Action.LEFT, Keys.LEFT);
		actionToHWKey.put(Action.RIGHT, Keys.RIGHT);
		actionToHWKey.put(Action.SHOOT, Keys.ENTER);
	}
	
	public void p2Shields() {
		actionToHWKey.put(Action.SHIELD_LEFT, Keys.W);
		actionToHWKey.put(Action.SHIELD_RIGHT, Keys.D);
		actionToHWKey.put(Action.SHIELD_AIM_LEFT, Keys.A);
		actionToHWKey.put(Action.SHIELD_AIM_RIGHT, Keys.S);
		actionToHWKey.put(Action.SHIELD_SHOOT, Keys.G);
	}
	
	public void p2Controls() {
		actionToHWKey.put(Action.UP, Keys.W);
		actionToHWKey.put(Action.DOWN, Keys.S);
		actionToHWKey.put(Action.LEFT, Keys.A);
		actionToHWKey.put(Action.RIGHT, Keys.D);
		actionToHWKey.put(Action.SHOOT, Keys.G);		
	}
	
	public void p1Shields() {
		actionToHWKey.put(Action.SHIELD_LEFT, Keys.UP);
		actionToHWKey.put(Action.SHIELD_RIGHT, Keys.LEFT);
		actionToHWKey.put(Action.SHIELD_AIM_LEFT, Keys.UP);
		actionToHWKey.put(Action.SHIELD_AIM_RIGHT, Keys.RIGHT);
		actionToHWKey.put(Action.SHIELD_SHOOT, Keys.ENTER);
	}

	public void switcharoo() {
		if(state == InputState.INVERTED) {
			p1Controls();
			p2Shields();
			this.state = InputState.NORMAL;
		} else {
			p1Shields();
			p2Controls();
			this.state = InputState.INVERTED;
		}
	}
	
}
