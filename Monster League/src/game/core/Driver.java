package game.core;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import game.data.InputHandler;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Driver {
	
	public InputHandler inputs;
	public int screenWidth = 800;
	public int screenHeight = 800;
	

	Driver() {

		// Initialization
		try {
			Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
			Display.setTitle("Monster League");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		
		LogicInitialization();
		
		GraphicInitialization();
		
		while (!Display.isCloseRequested()) {
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
			UpdateLogic();
						
			Display.update();
			Display.sync(60);
			
		}//end update loop
		
		Display.destroy();
		
	}
	
	
	
	
	public void LogicInitialization() {
		inputs = new InputHandler();
	}
	
	
	
	
	
	public void GraphicInitialization() {
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
	}
	
	
	
	
	
	public void UpdateLogic() {
		inputs.Update();
	}
	
	
	
	
	

	

	public static void main(String[] args) {

		new Driver();
		
	}
}