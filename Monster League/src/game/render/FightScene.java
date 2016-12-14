package game.render;

import game.data.DataSlave;
import game.entities.Monster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class FightScene {

	private Texture background;
	private Texture pressSpace;
	private int frame = 0;
	private double seconds = 0;
	private Monster enemy;
	private Monster player;

	int count;

	int numPosts = 1;
	int raised = 22;

	private int pAttackPower;
	private int eAttackPower;
	private double pDefense;
	private double eDefense;

	private int lastTime;
	private int lastTime2;
	private ArrayList<String> oldStrings = new ArrayList<String>();

	private String attack;


	private Random random = new Random();

	private double playerCooldown;
	private double enemyCooldown;

	private boolean finished = true;

	public FightScene() {
		LoadTextures();
	}

	private void LoadTextures() {
		try {
			background = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Menu/Battle.png"));
			pressSpace = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Accessories/PressSpace.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Initialize(Monster enemy, Monster player) {
		frame = 0;
		seconds = 0;
		this.enemy = enemy;
		this.player = player;
		
		oldStrings = new ArrayList<String>();
		

		finished = false;

		playerCooldown = 13 - (player.getAgility() / 2);
		enemyCooldown = 13 - (enemy.getAgility() / 2);

		pDefense = (player.getTotalStrength() + 1) / 2;
		eDefense = (enemy.getTotalStrength() + 1) / 2;

	}

	public void Update(DataSlave slave) {
		frame ++;
		if (frame == 60) {
			seconds ++;
			frame = 0;

		}
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

		GL11.glTranslated(500, 60, 0);
		enemy.Draw();
		GL11.glLoadIdentity();
		GL11.glTranslated(100, 60, 0);
		player.Draw();
		GL11.glLoadIdentity();

		slave.toText.Render(450, 480, 22, "Agility " + (enemy.getAgility() + enemy.getAgilityBonus()));
		slave.toText.Render(450, 450, 22, "Dexterity " + (enemy.getDexterity() + enemy.getDexterityBonus()));
		slave.toText.Render(450, 420, 22, "Strength " + (enemy.getStrength() + enemy.getStrengthBonus()));
		slave.toText.Render(450, 390, 22, "Intelligence " + enemy.getIntelligence());

		slave.toText.Render(10, 480, 22, "Agility " + (player.getAgility() + player.getAgilityBonus()));
		slave.toText.Render(10, 450, 22, "Dexterity " + (player.getDexterity() + player.getDexterityBonus()));
		slave.toText.Render(10, 420, 22, "Strength " + (player.getStrength() + player.getStrengthBonus()));
		slave.toText.Render(10, 390, 22, "Intelligence " + player.getIntelligence());


		CalculateCombat(slave);
	}

	private void CalculateCombat(DataSlave slave) {
		count = 0;
		if (finished) {
			count = 1;
		}
		pAttackPower = 0;
		eAttackPower = 0;
		attack = null;
		//	System.out.println(seconds);
		//	System.out.println((seconds % playerCooldown));

		if (!finished) {
			if (seconds % playerCooldown == 2.0 && seconds != lastTime) {//player's turn
				lastTime = (int) seconds;

				if (player.getNumSpikes() > 0 && player.getIntelligence() > 4) {
					attack = "Spike Attack";
					pAttackPower = 1 + player.getNumSpikes();
				} else if (random.nextBoolean() && player.getIntelligence() > 2) {
					attack = "Throw Debris";
					pAttackPower = 2;
				} else {
					attack = "Basic Attack";
					pAttackPower = 1;
				}



				oldStrings.add("Your monster used " + attack);	
				//checks for dodge
				if (random.nextInt(player.getDexterity() + 3) < random.nextInt(enemy.getAgility() + 1)) {
					oldStrings.add("Enemy Dodges");
				}
				else {//dealing damage
					double crit = 1;
					if (random.nextFloat() < player.getTotalDexterity() / 10) {
						crit = 2.5;
					}
					int damage = pAttackPower *(int) crit * ((player.getTotalStrength() + 2) / enemy.getTotalStrength());

					eDefense -= damage;

					if (damage > 0) {
						oldStrings.add("Enemy Takes " + damage + " Damage");
					}else {
						oldStrings.add("Enemy Takes almost no Damage");
					}

					if (eDefense < 1) {
						finished = true;
					}
				}
			}

			if ((seconds % enemyCooldown) == 0.0 && seconds != lastTime2) {//enemy's turn
				lastTime2 = (int) seconds;

				if (enemy.getNumSpikes() > 0 && enemy.getIntelligence() > 4) {
					attack = "Spike Attack";
					eAttackPower = 1 + player.getNumSpikes();
				} else if (random.nextBoolean() && enemy.getIntelligence() > 2) {
					attack = "Throw Debris";
					eAttackPower = 2;
				} else {
					attack = "Basic Attack";
					eAttackPower = 1;
				}



				oldStrings.add("Enemy used " + attack);	
				//checks for dodge
				if (random.nextInt(player.getDexterity() + 2) < random.nextInt(enemy.getAgility())) {
					oldStrings.add("Your Monster Dodges");
				}
				else {//dealing damage
					double crit = 1;
					if (random.nextFloat() < enemy.getTotalDexterity() / 10) {
						crit = 2.5;
					}
					int damage = eAttackPower * (int) crit * ((enemy.getTotalStrength() + 1) / player.getTotalStrength());

					pDefense -= damage;

					if (damage > 0) {
						oldStrings.add("Your Monster Takes " + damage + " Damage");
					}else {
						oldStrings.add("Your Monster Takes almost no Damage");
					}
					if (pDefense < 1) {
						finished = true;
						slave.defeat = true;
					}
				}

			}
		}

		if (seconds == 90) {
			finished = true;
			if (eDefense < pDefense) {
				slave.defeat = true;
			}
		}

		UpdateBattleFeed(slave);
		if (finished && count == 1) {
			PressSpaceQuad();
		}
	}

	private void UpdateBattleFeed(DataSlave slave) {
		/*	boolean animate = false;

		if (raised < numPosts * 22) {
			animate = true;
		} else {
			animate = false;
		}
		if (animate) {
			GL11.glRotated(Math.sin(Math.toRadians(System.nanoTime() / 2000000)), 0, 0, 1);
		}*/

		if (seconds < 81) {
			GL11.glTranslated(-18, 0, 0);
		}
		slave.toText.Render(380, 346, 40,"" + (90 - (int) seconds));

		GL11.glLoadIdentity();

		slave.toText.Render(10, 350, 25,"Health " + (int) pDefense);
		slave.toText.Render(450, 350, 25,"Health " + (int) eDefense);

		for (int i = oldStrings.size() - 1; i > -1 ; i--) {
			GL11.glTranslated(0, 15, 0);
			if (oldStrings.get(i) != null) {
				slave.toText.Render(130, 620, 15, oldStrings.get(i));
			}
		}
		GL11.glLoadIdentity();

		if (oldStrings.size() > 12) {
			oldStrings.remove(0);
		}
	}


	public void PressSpaceQuad() {

		pressSpace.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2d(0, 1);
		GL11.glVertex2d(200,200);

		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(200,400);

		GL11.glTexCoord2d(1, 0);
		GL11.glVertex2d(600,400);

		GL11.glTexCoord2d(1, 1);
		GL11.glVertex2d(600,200);
		GL11.glEnd();
	}

	public void Cleanup() {
		for (int i = 0; i < oldStrings.size(); i++) {
			oldStrings.remove(i);
		}
		attack = null;
		lastTime = 0;
		lastTime2 = 0;
	}


	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

}
