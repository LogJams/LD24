package game.entities;

import java.io.IOException;

import game.data.DataSlave;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Button {
	
	private int minX, maxX, minY, maxY;
	private Texture texture;
	private boolean clicked = false;
	private String name;
	
	
	
	public Button (int minX, int minY, int width, int height, String name) {
		this.minX = minX;
		maxX = minX + width;
		
		this.minY = minY;
		maxY = minY + height;
		
		this.name = name;
		
		
		LoadTexture();
	}
	
	private void LoadTexture() {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Buttons/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean Update(DataSlave slave) { //returns if the button has been clicked
		clicked = false;
		if (Mouse.getX() > minX &&
				Mouse.getX() < maxX &&
				Mouse.getY() > minY &&
				Mouse.getY() < maxY) { //if the mouse is down over the button
			GL11.glColor3d(0.5, 1.0, 0.5);
			clicked = slave.lClick;
		}else {
			GL11.glColor3d(1.0, 1.0, 1.0);
		}
		texture.bind();
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
		
		GL11.glColor3d(1.0, 1.0, 1.0);
				
		return clicked;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
