package game.logic;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Text2Word {
	
	protected Texture symbols;
	
	public Text2Word() {
		LoadTexture();
	}
	
	private void LoadTexture() {
		try {
			symbols = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Accessories/Alphabet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Render(int xPos, int yPos, int size, String words) {
		words = words.toLowerCase();
		char[] letters = words.toCharArray();
		int[] values = new int[letters.length];
				
		symbols.bind();
		
		GL11.glBegin(GL11.GL_QUADS);		
		
		for (int j = 0; j < letters.length; j++) {
			values[j] = letters[j] - 96;
			int i = values[j];
			double constant = 0.02304;
					
			if (values[j] > 0) {	
				if (i > 9 && i < 15) {
					constant = 0.0197;
				} else if (i < 23) {
					constant = 0.02;
				} else if (i < 27) {
					constant = 0.02017;
				}
				
				GL11.glTexCoord2d(0 + (constant * (i - 1)), 0.5);
				GL11.glVertex2d(xPos + (size * j), yPos);

				GL11.glTexCoord2d(0 + (constant * (i - 1)), 0);
				GL11.glVertex2d(xPos + (size * j), yPos + size);

				GL11.glTexCoord2d(constant * i, 0);
				GL11.glVertex2d(xPos + size + (size * j), yPos + size);

				GL11.glTexCoord2d(constant * i, 0.5);
				GL11.glVertex2d(xPos + size + (size * j), yPos);
			}else {
				i += 49;
				constant = 0.019;
				if (i > 7 ) {
					constant = 0.0197;
				}
				
				GL11.glTexCoord2d(0 + (constant * (i - 1)), 1.0);
				GL11.glVertex2d(xPos + (size * j), yPos);

				GL11.glTexCoord2d(0 + (constant * (i - 1)), 0.4);
				GL11.glVertex2d(xPos + (size * j), yPos + size);

				GL11.glTexCoord2d(constant * i, 0.4);
				GL11.glVertex2d(xPos + size + (size * j), yPos + size);

				GL11.glTexCoord2d(constant * i, 1.0);
				GL11.glVertex2d(xPos + size + (size * j), yPos);
			}
			
		}
				
		GL11.glEnd();
	}
}
