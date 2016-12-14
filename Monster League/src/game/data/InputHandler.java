package game.data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class InputHandler {

	public Controller gameControls;
	public DataSlave slave;
	public boolean click = false;
	
	
	public InputHandler() {
		slave = new DataSlave();
		gameControls = new Controller(slave);
	}
	
	
	public void Update() {
		slave.Reset();
		
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_SPACE && Keyboard.getEventKeyState()) {
				slave.space = true;
			}
		}
		
		while (Mouse.next()) {
			if (Mouse.isButtonDown(0)) {
				slave.lClick = true;
			}
		}
		
		if (!Mouse.isButtonDown(0) && click) {
			slave.lSelect = true;
			click = false;
			System.out.println("released");
			slave.mouseX = Mouse.getX();
			slave.mouseY = 880 - Mouse.getY();
		}
		
		gameControls.Update();
	}
	
	
}
