package game.entities;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class StatBar {
	
	private int minX, maxX, minY, maxY;
	private Texture border, bar, bonus, background;
	
	private int maxValue; //a value from the data slave
	
	private void LoadTexture() {
		try {
			border = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Accessories/StatBorder.png"));
			bar = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Accessories/StatBar.png"));
			bonus = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Accessories/StatBonus.png"));
			background = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Accessories/StatBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public StatBar(int xPos, int yPos, int width, int height, int maxStat) {
		
		minX = xPos;
		maxX = xPos + width;
		
		minY = yPos;
		maxY = yPos + height;
		
		maxValue = maxStat;
		
		LoadTexture();
	}
	
	public void Update(int currentValue, int bonusValue) {
		
		background.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2d(0, 1);
		GL11.glVertex2d(minX, minY);
		
		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(minX, maxY);
		
		GL11.glTexCoord2d(1, 0);
		GL11.glVertex2d(maxX, maxY);
		
		GL11.glTexCoord2d(1, 1);
		GL11.glVertex2d(maxX, minY);
		GL11.glEnd();
		
		
		if (bonusValue > 0) {
			bonus.bind();
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2d(0, 1);
			GL11.glVertex2d(minX, minY);
			
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex2d(minX, maxY);
			
			GL11.glTexCoord2d(1, 0);
			GL11.glVertex2d((minX + (int) (( maxX - minX) * ((double)bonusValue / (double) maxValue))) + ((int) (( maxX - minX) * ((double)currentValue / (double) maxValue))), maxY);
			
			GL11.glTexCoord2d(1, 1);
			GL11.glVertex2d((minX + (int) ((maxX - minX) * ((double)bonusValue / (double) maxValue))) + ((int) (( maxX - minX) * ((double)currentValue / (double) maxValue))), minY);
			GL11.glEnd();
		}
		if (currentValue > 0) {
			bar.bind();

			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2d(0, 1);
			GL11.glVertex2d(minX, minY);
			
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex2d(minX, maxY);
			
			GL11.glTexCoord2d(1, 0);
			GL11.glVertex2d(minX + (int) (( maxX - minX) * ((double)currentValue / (double) maxValue)), maxY);
			
			GL11.glTexCoord2d(1, 1);
			GL11.glVertex2d(minX + (int) ((maxX - minX) * ((double)currentValue / (double) maxValue)), minY);
			GL11.glEnd();
		}
		
		border.bind();
	 	GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2d(0, 1);
		GL11.glVertex2d(minX, minY);
		
		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(minX, maxY);
		
		GL11.glTexCoord2d(1, 0);
		GL11.glVertex2d(maxX, maxY);
		
		GL11.glTexCoord2d(1, 1);
		GL11.glVertex2d(maxX, minY);
		GL11.glEnd();
	}
}
