package game.logic;

import java.util.ArrayList;
import java.util.Random;

import game.entities.Monster;

public class EnemyHandler {

	ArrayList<Monster> enemies;
	ArrayList<String> focus; //can be STRENGTH, AGILITY, INTELLIGENCE
	int numEnemies;

	Random random = new Random();

	public EnemyHandler(int numEnemies) {
		enemies = new ArrayList<Monster>();
		focus = new ArrayList<String>();
		this.numEnemies = numEnemies;

		for (int i = 0; i < numEnemies; i++) {
			enemies.add(new Monster());
			if (random.nextBoolean()) {
				focus.add("STRENGTH");
			}else if (random.nextFloat() > 0.25) {
				focus.add("AGILITY");
			} else {
				focus.add("INTELLIGENCE");
			}
		}		


	}


	public void UpgradeEnemy(Monster enemy, int round) {
		String focus = "INTELLIGENCE";
		if (random.nextBoolean()) {
			focus = "STRENGTH";
		} else if (random.nextBoolean()) {
			focus = "AGILITY";
		}

		enemy.UpdatePoints((round) * 5);

		if (random.nextFloat() < 0.3) {
			enemy.setSkin(1);
		}else if (random.nextFloat() < 0.3) {
			enemy.setSkin(2);
		}else if (random.nextFloat() < 0.4) {
			enemy.setSkin(3);
		}

		while (enemy.getUnspentPoints() > 0) {
			if (focus.equals("STRENGTH")) {
				enemy.addStrength();
			}else if (focus.equals("AGILITY")) {
				enemy.addAgility();
			}else if (focus.equals("Intelligence")) {
				enemy.addIntelligence();
			}
			if (random.nextFloat() > 0.5) {
				enemy.addAgility();
			}if (random.nextFloat() > 0.4) {
				enemy.addStrength();
			}if (random.nextFloat() > 0.5) {
				enemy.addSize();
			}if (random.nextFloat() > 0.9) {
				enemy.addIntelligence();
			}if (random.nextFloat() > 0.6) {
				enemy.addDexterity();
			}if (random.nextFloat() > 0.6) {
				enemy.addAppendage(0);
			}if (random.nextFloat() > 0.6) {
				enemy.addAppendage(1);
			}if (random.nextFloat() > 0.6) {
				enemy.addAppendage(2);
			}if (random.nextFloat() > 0.6) {
				enemy.addAppendage(3);
			}if (random.nextFloat() > 0.6) {
				enemy.addSpike();
			}if (random.nextFloat() > 0.6) {
				enemy.AddArmor();		
			}

		}
	}

	public Monster[] getEnemies() {
		Monster[] enemy = new Monster[numEnemies];
		for (int i = 0; i < numEnemies; i++) {
			enemy[i] = enemies.get(i);
		}
		return enemy;
	}

	public Monster RandomEnemy(int round) {
		Monster enemy;
		int i = random.nextInt(numEnemies);
		enemy = enemies.get(i);
		UpgradeEnemy(enemy, round);
		return enemy;
	}
}
