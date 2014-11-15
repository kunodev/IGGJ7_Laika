package de.hamburg.laika.button;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import de.kuro.lazyjam.cdiutils.annotations.Render;
import de.kuro.lazyjam.cdiutils.annotations.Update;
import de.kuro.lazyjam.simpleservice.FontManager;

public class ButtonComponent {

	private Vector2 size;
	private Texture background;
	private ButtonListener listener;
	private String text;
	
	public ButtonComponent(Texture background, ButtonListener listener, String text, Vector2 size) {
		this.size = size;
		this.listener = listener;
		this.text = text;
		this.background = background;
	}
	public ButtonComponent(Texture background, ButtonListener listener, String text, float width, float height) {
		this.size = new Vector2(width, height);
		this.listener = listener;
		this.text = text;
		this.background = background;
	}
	
	@Update
	public void update(Input i, OrthographicCamera cam, Vector2 pos) {
		if (listener == null) {
			return;
		}
		
		Rectangle r = new Rectangle(pos.x - size.x / 2, pos.y - size.y / 2, size.x, size.y);
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
	public void render(SpriteBatch sb, FontManager fm, Vector2 pos) {
		TextBounds tb = fm.getBounds(text);
		sb.draw(background, pos.x - size.x/2, pos.y - size.y/2, size.x, size.y);
		fm.drawTextAbsolut(pos.x - tb.width/2, pos.y + tb.height/2, text, Color.GREEN);
	}
}
