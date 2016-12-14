package game.entities;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Monster {
	
	private int agility, dexterity, intelligence, strength, size, agilityBonus, dexterityBonus, intelligenceBonus, sizeBonus;
	private double strengthBonus;
	private int oldAgility, oldDexterity, oldIntelligence, oldStrength, oldSize;
	
	private boolean legs, legs2, arms, arms2, armor1, armor2;
	private int numSpikes = 0;
	
	private int unspentPoints = 7;
	
	protected int maxValue = 9 ; //the max points a stat can have
	
	private String TYPE = "Slime"; //can be  Fur, Scale, Skin, Slime. Refers to folders in res/Monsters for texture path
	private Texture skin;
	
	public Monster() {
		agility = 1; dexterity = 1; intelligence = 1; strength = 1; size = 1;
		agilityBonus = 0; dexterityBonus = 0; intelligenceBonus = 0; strengthBonus = 0; sizeBonus = 0;
		oldAgility = 1; oldDexterity = 1; oldIntelligence = 1; oldStrength = 1; oldSize = 1;
		
		LoadTexture();

	}
	
	public void LoadTexture() {
		try {
			skin = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Monsters/" + TYPE + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void TypeChange(String TYPE) {
		this.TYPE = TYPE;
		LoadTexture();
	}
	
	public void Draw() {
								
		int i = 1;
		skin.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2d(0.125 * (i - 1), 0.5);
		GL11.glVertex2d(0,0);
		
		GL11.glTexCoord2d(0.125 * (i - 1), 0);
		GL11.glVertex2d(0,300 * (((float) size / (float) maxValue)));
		
		GL11.glTexCoord2d(0.125 * i, 0);
		GL11.glVertex2d(300 * (((float) size / (float) maxValue)),300 * (((float) size / (float) maxValue)));
		
		GL11.glTexCoord2d(0.125 * i, 0.5);
		GL11.glVertex2d(300 * (((float) size / (float) maxValue)),0);
		

		if (armor1) {
			GL11.glTexCoord2d(0.125 * (4 - 1), 1.0);
			GL11.glVertex2d(0,0);
			
			GL11.glTexCoord2d(0.125 * (4 - 1), 0.5);
			GL11.glVertex2d(0,300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * 4, 0.5);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * 4, 1.0);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),0);
		}
		
		if (armor2) {
			GL11.glTexCoord2d(0.125 * (5 - 1), 1.0);
			GL11.glVertex2d(0,0);
			
			GL11.glTexCoord2d(0.125 * (5 - 1), 0.5);
			GL11.glVertex2d(0,300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * 5, 0.5);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * 5, 1.0);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),0);
		}
		
		for (int q = 1; q <= numSpikes; q++) {
			GL11.glTexCoord2d(0.125 * (q - 1), 1.0);
			GL11.glVertex2d(0,0);
			
			GL11.glTexCoord2d(0.125 * (q - 1), 0.5);
			GL11.glVertex2d(0,300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * q, 0.5);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * q, 1.0);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),0);
		}
		
		
		
		
		
		i = 6;
		
		i --;
		if (legs2) {
			GL11.glTexCoord2d(0.125 * (i - 1), 0.5);
			GL11.glVertex2d(0,0);
			
			GL11.glTexCoord2d(0.125 * (i - 1), 0);
			GL11.glVertex2d(0,300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * i, 0);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * i, 0.5);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),0);
		}
		
		i --;
		if (legs) {
			GL11.glTexCoord2d(0.125 * (i - 1), 0.5);
			GL11.glVertex2d(0,0);
			
			GL11.glTexCoord2d(0.125 * (i - 1), 0);
			GL11.glVertex2d(0,300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * i, 0);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * i, 0.5);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),0);
		}
		
		i --;
		if (arms2) {
			GL11.glTexCoord2d(0.125 * (i - 1), 0.5);
			GL11.glVertex2d(0,0);
			
			GL11.glTexCoord2d(0.125 * (i - 1), 0);
			GL11.glVertex2d(0,300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * i, 0);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * i, 0.5);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),0);
		}
		
		i --;
		if (arms) {
			GL11.glTexCoord2d(0.125 * (i - 1), 0.5);
			GL11.glVertex2d(0,0);
			
			GL11.glTexCoord2d(0.125 * (i - 1), 0);
			GL11.glVertex2d(0,300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * i, 0);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),300 * (((float) size / (float) maxValue)));
			
			GL11.glTexCoord2d(0.125 * i, 0.5);
			GL11.glVertex2d(300 * (((float) size / (float) maxValue)),0);
		}
		
		
		GL11.glEnd();
	}
	
	public void UpdatePoints(int points) {
		oldAgility = agility;
		oldDexterity = dexterity;
		oldIntelligence = intelligence;
		oldStrength = strength;
		oldSize = size;
		
		unspentPoints += points;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getUnspentPoints() {
		return unspentPoints;
	}

	public void setUnspentPoints(int unspentPoints) {
		this.unspentPoints = unspentPoints;
	}

	public int getOldAgility() {
		return oldAgility;
	}

	public void setOldAgility(int oldAgility) {
		this.oldAgility = oldAgility;
	}

	public int getOldDexterity() {
		return oldDexterity;
	}

	public void setOldDexterity(int oldDexterity) {
		this.oldDexterity = oldDexterity;
	}

	public int getOldIntelligence() {
		return oldIntelligence;
	}

	public void setOldIntelligence(int oldIntelligence) {
		this.oldIntelligence = oldIntelligence;
	}

	public int getOldStrength() {
		return oldStrength;
	}

	public void setOldStrength(int oldStrength) {
		this.oldStrength = oldStrength;
	}

	public int getOldSize() {
		return oldSize;
	}

	public void setOldSize(int oldSize) {
		this.oldSize = oldSize;
	}
	//////////adders and removers for attributes\\\\\\\\\\\\
	public void addAgility() {
		if (agility < maxValue && unspentPoints > 0) {
			agility ++;
			unspentPoints --;
		}
	}
	public void removeAgility() {
		if (agility > oldAgility && agility > 0) {
			agility --;
			unspentPoints ++;
		}
	}
	
	public void addStrength() {
		if (strength < maxValue && unspentPoints > 0) {
			strength ++;
			unspentPoints --;
		}
	}
	public void removeStrength() {
		if (strength > oldStrength && strength > 0) {
			strength --;
			unspentPoints ++;
		}
	}
	
	public void addSize() {
		if (size < maxValue && unspentPoints > 0) {
			size ++;
			unspentPoints --;
			strengthBonus += 0.5;
		}
	}
	public void removeSize() {
		if (size > oldSize && size > 0) {
			size --;
			unspentPoints ++;
			strengthBonus -= 0.5;
		}
	}
	
	public void addDexterity() {
		if (dexterity < maxValue && unspentPoints > 0) {
			dexterity ++;
			unspentPoints --;
		}
	}
	public void removeDexterity() {
		if (dexterity > oldDexterity && dexterity > 0) {
			dexterity --;
			unspentPoints ++;
		}
	}
	
	public void addIntelligence() {
		if (intelligence < maxValue && unspentPoints > 0) {
			intelligence ++;
			unspentPoints --;
		}
	}
	public void removeIntelligence() {
		if (intelligence > oldIntelligence && intelligence > 0) {
			intelligence --;
			unspentPoints ++;
		}
	}

	public int getMaxValue() {
		return maxValue;
	}
	
	public int getStat(int i) {
		int k = 0;
		if (i == 0) {
			k = agility;
		} else if (i == 1) {
			k = dexterity;
		} else if (i == 2) {
			k = strength;
		} else if (i == 3) {
			k = size;
		} else if (i == 4) {
			k = intelligence;
		}
		return k;
		}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	
	public boolean setLegs() {
		return legs;
	}
	
	public boolean setLegs2() {
		return legs2;
	}
	
	public boolean setArms() {
		return arms;
	}
	
	public boolean setArms2() {
		return arms2;
	}
	
	public int getNumSpikes() {
		return numSpikes;
	}
	
	public void addAppendage(int i) {
		if (unspentPoints >= 2) {
			if (i == 0 && !arms2) {
				arms = !arms;
				unspentPoints -= 2;
				if (arms) {
					dexterityBonus += 1;
				} else {
					dexterityBonus -= 1;
				}
			}else if (i == 1 && arms) {
				arms2 = !arms2;
				unspentPoints -= 2;
				if (arms2) {
					dexterityBonus += 1;
				} else {
					dexterityBonus -= 1;
				}
			}else if (i == 2 && !legs2) {
				legs = !legs;
				unspentPoints -= 2;
				if (legs) {
					agilityBonus += 1;
				} else {
					agilityBonus -= 1;
				}
			}else if (i == 3 && legs) {
				legs2 = !legs2;
				unspentPoints -= 2;
				if (legs2) {
					agilityBonus += 1;
				} else {
					agilityBonus -= 1;
				}
			}
		}
	}
	
	public void addSpike() {
		if (unspentPoints >= 2 && numSpikes < 3) {
			unspentPoints -= 2;
			numSpikes ++;
			strengthBonus +=1;
		}
	}

	public void setSkin(int i) {
		if (unspentPoints >= 3 && TYPE.equals("Slime")) {
			if (i == 0 && TYPE != "Slime") {
				TYPE = "Slime";
				unspentPoints -= 3;
			} else if (i == 1 && TYPE != "Skin") {
				TYPE = "Skin";
				unspentPoints -= 3;
				agilityBonus += 1;
				dexterityBonus += 1;
			} else if (i == 2 && TYPE != "Fur") {
				TYPE = "Fur";
				unspentPoints -= 3;
				strengthBonus += 1;
				agilityBonus +=1;
			} else if (i == 3 && TYPE != "Scale") {
				TYPE = "Scale";
				unspentPoints -= 3;
				strengthBonus += 2;
			}
		}
		LoadTexture();
	}

	public void AddArmor() {
		if (unspentPoints >= 2 && (!armor1 || !armor2)) {
			unspentPoints -=2;
			if (armor1) {
				armor2 = true;
				strengthBonus += 1.5;
			} else {
				armor1 = true;
				strengthBonus += 1.5;
			}
		}
	}

	public int getBonus(int i) {
		int k = 0;
		if (i == 0) {
			k = agilityBonus;
		} else if (i == 1) {
			k = dexterityBonus;
		} else if (i == 2) {
			k = (int) strengthBonus;
		} else if (i == 3) {
			k = sizeBonus;
		} else if (i == 4) {
			k = intelligenceBonus;
		}
		return k;
		
	}

	public int getStrengthBonus() {
		return (int) strengthBonus;
	}

	public int getAgilityBonus() {
		return agilityBonus;
	}

	public int getDexterityBonus() {
		return dexterityBonus;
	}
	
	public int getTotalStrength() {
		return (int) (strength + strengthBonus);
	}
	
	public int getTotalAgility() {
		return agility + agilityBonus;
	}
	
	public int getTotalDexterity() {
		return dexterity + dexterityBonus;
	}
	
	
}
