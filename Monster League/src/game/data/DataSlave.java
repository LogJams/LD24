package game.data;

import game.entities.Monster;
import game.logic.Text2Word;

public class DataSlave {

	public boolean space, lClick, rClick, lSelect, rSelect;
	public int mouseX, mouseY, numRounds;
	public Monster user;
	public Monster[] enemies;
	public Monster currentEnemy;
	
	public Text2Word toText;
	public boolean defeat = false;
	public int currentRound;
	
	public DataSlave() {
		toText = new Text2Word();
	}
	
	public void Reset() {
		space = false;
		lClick = false;
		rClick = false;
	}
	
	public void setCharacters(Monster user, Monster[] enemies) {
		this.user = user;
		this.enemies = enemies;
	}
}
