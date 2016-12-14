package game.render.menus;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class EndScreen {

	private Texture VICTORY;
	private Texture DEFEAT;
	
	
	public EndScreen() {
		LoadTextures();
	}
	
	private void LoadTextures() {
		try {
			VICTORY = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Menu/Victory.png"));
			DEFEAT = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Menu/Defeat.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Update(boolean victory) {
		if (victory) {
			VICTORY.bind();
		} else {
			DEFEAT.bind();
		}
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
		
	}
}
