package de.hamburg.laika.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.hamburg.laika.Laika;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) Laika.WIDTH;
		config.height = (int) Laika.HEIGHT;
		config.title = "Laika Space Adventure";
		new LwjglApplication(new Laika(), config);
	}
}