package game.render.menus;

import game.data.DataSlave;
import game.entities.Button;
import game.entities.StatBar;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Menu {

	private Texture background;
	private Button continueButton;
	private Button[] plus = new Button[5];
	private Button[] minus = new Button[5];//5 is the number of stats
	private Button[] skins = new Button[4];//4 is the number of skins
	private Button[] appendage = new Button[4];//2 arms and 2 legs
	private StatBar[] stats = new StatBar[5];
	
	private Button addSpikes = new Button(600, 255, 75, 75, "Spikes");
	private Button addArmor = new Button(600, 120, 75, 75, "Armor");

	public Menu(int maxStat) {
		LoadTextures();
		continueButton = new Button(410, 10, 215, 85, "Continue");
		for (int i = 0; i < 5; i++) {
			plus[i] = new Button(760, 602 - (33 * i), 15, 15, "Plus");
			minus[i] = new Button(740, 602 - (33 * i), 15, 15, "Minus");
			stats[i] = new StatBar(550, 600 - (33 * i), 175, 20, maxStat);
		}
		skins[0] = new Button(80, 280, 50, 50, "Slime");
		skins[1] = new Button(80, 220, 50, 50, "Skin");
		skins[2] = new Button(80, 160, 50, 50, "Fur");
		skins[3] = new Button(80, 100, 50, 50, "Scale");
		
		appendage[0] = new Button(270, 280, 50, 50, "Arm");
		appendage[1] = new Button(270, 220, 50, 50, "Arm");
		appendage[2] = new Button(270, 160, 50, 50, "Leg");
		appendage[3] = new Button(270, 100, 50, 50, "Leg");

	}

	private void LoadTextures() {
		try {
			background = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Menu/MainScreen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean Update(DataSlave slave) {
		
		background.bind();
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

		
		
		UpdateStats(slave);
		
		GL11.glTranslated(80, 450, 0);
		slave.user.Draw();
		GL11.glLoadIdentity();

		slave.toText.Render(690, 715, 45, "F" + slave.currentRound);
		slave.toText.Render(575, 650, 45, "" + slave.user.getUnspentPoints());
		
		return continueButton.Update(slave);
	}

	public void UpdateStats(DataSlave slave) {
		for (int i = 0; i < 5; i++) {
			if (plus[i].Update(slave)) {
				if (i == 0) {
					slave.user.addAgility();
				}else if (i == 1) {
					slave.user.addDexterity();
				}else if (i == 2) {
					slave.user.addStrength();
				}else if (i == 3) {
					slave.user.addSize();
				}else if (i == 4) {
					slave.user.addIntelligence();
				}



			}
			if (minus[i].Update(slave)) {
				if (i == 0) {
					slave.user.removeAgility();
				}else if (i == 1) {
					slave.user.removeDexterity();
				}else if (i == 2) {
					slave.user.removeStrength();
				}else if (i == 3) {
					slave.user.removeSize();
				}else if (i == 4) {
					slave.user.removeIntelligence();
				}
			}
			stats[i].Update(slave.user.getStat(i), slave.user.getBonus(i));
			
			
			if (i < 4) {
				if (skins[i].Update(slave)) {
					slave.user.setSkin(i);
				}
				if (appendage[i].Update(slave)) {
					slave.user.addAppendage(i);
				}
			}
			
			
		}
		if(addSpikes.Update(slave)) {
			slave.user.addSpike();
		}if (addArmor.Update(slave)) {
			slave.user.AddArmor();
		}
		
	}
	
	
	
	public void PrintStats(DataSlave slave) {
		System.out.println("Agility: " + slave.user.getAgility());
		System.out.println("Dexterity: " + slave.user.getDexterity());
		System.out.println("Strength: " + slave.user.getStrength());
		System.out.println("Size: " + slave.user.getSize());
		System.out.println("Intelligence: " + slave.user.getIntelligence());
		System.out.println();
	}

}
