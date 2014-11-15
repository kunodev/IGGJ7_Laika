package de.hamburg.laika.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.kuro.lazyjam.ecmodel.concrete.GameObject;
import de.kuro.lazyjam.ecmodel.concrete.GameState;

public class ButtonBuilder {
	int numButtons = 0;
	GameState gs;
	
	public ButtonBuilder(GameState gs) {
		this.gs = gs;
	}
	
	public GameObject createButton(ButtonListener bl, String text, Texture background) {
		GameObject ub = new GameObject(new Vector2(100+numButtons++*200, 50), gs);
		ub.addComponent(new ButtonComponent(background, bl, text, new Vector2(200, 50)));
		return ub;
	}
}
