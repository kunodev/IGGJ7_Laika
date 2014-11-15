package de.hamburg.laika.button;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import de.kuro.lazyjam.cdiutils.annotations.InjectedService;
import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.simpleservice.FontManager;

public class ButtonComponent {

	private Vector2 size;
	private ButtonListener listener;
	private String text;
	
	public ButtonComponent(ButtonListener listener, String text, Vector2 size) {
		this.size = size;
		this.listener = listener;
		this.text = text;
	}
	public ButtonComponent(ButtonListener listener, String text, float width, float height) {
		this.size = new Vector2(width, height);
		this.listener = listener;
		this.text = text;
	}
	
	@Update
	public void update(Input i, OrthographicCamera cam, Vector2 pos) {
		Rectangle r = new Rectangle(pos.x, pos.y, size.x, size.y);
		
		
		Vector3 tmp = cam.unproject(new Vector3(i.getX(), i.getY(), 0));
		float x = tmp.x;
		float y = tmp.y;
		
		
		
		if (r.contains(x, y)) {
			listener.onHover();
			if (i.isButtonPressed(Buttons.LEFT)) {
				listener.onClick();
			}
		}
	}
	
	@Render
	public void render(FontManager fm, Vector2 pos) {
		fm.drawTextAbsolut(pos.x, pos.y, text);
	}
}
