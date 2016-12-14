package game.render.menus;

import game.data.DataSlave;
import game.entities.Button;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TitleScreen {
	
	private Texture screen;
	private Button continueButton;
	
	
	public TitleScreen() {
		
		LoadTextures();
		//create a button with a width of 1/4 the screen and 1/3 of the way up in the center and 40 thick
		continueButton = new Button(300, 100, 200, 100, "Continue");
	
	}
	
	public boolean Update(DataSlave slave) {
		screen.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2d(0, 1);
		GL11.glVertex2d(0,0);
		
		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(0,800);
		
		GL11.glTexCoord2d(1, 0);
		GL11.glVertex2d(800,800);
		
		GL11.glTexCoord2d(1, 1);
		GL11.glVertex2d(800,0);
		GL11.glEnd();
		
		return continueButton.Update(slave);
	}

	private void LoadTextures() {
		
		try {
			screen = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Menu/title.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
