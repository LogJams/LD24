package game.data;

import game.entities.Monster;
import game.logic.EnemyHandler;
import game.render.FightScene;
import game.render.menus.BattleResult;
import game.render.menus.EndScreen;
import game.render.menus.IntroScreen;
import game.render.menus.Menu;
import game.render.menus.TitleScreen;

public class Controller {

	private TitleScreen title;
	private IntroScreen intro;
	private EndScreen end;
	private Menu menu;
	private BattleResult results;
	private FightScene combat;
	
	private EnemyHandler eHandle;

	public DataSlave slave;
	public Monster player;
	public Monster currentEnemy;
	public int numEnemies = 63; //total contestants - 1
	public int numRounds;

	public int round = 0;

	protected boolean victory = true;

	private String STATE = "TITLE"; //can be TITLE, STARTUP, MENU, BATLLE, RESULT, or END

	public Controller(DataSlave slave) {

		this.slave = slave;

		numRounds = FindNumRounds(); slave.numRounds = numRounds;
		player = new Monster();
		currentEnemy = new Monster();
		eHandle = new EnemyHandler(100);
		slave.setCharacters(player, eHandle.getEnemies());

		title = new TitleScreen();
		intro = new IntroScreen();
		end = new EndScreen();
		menu = new Menu(player.getMaxValue());
		results = new BattleResult();
		combat = new FightScene();
		
		numRounds = 5;
	}


	private int FindNumRounds() {
		int total = numEnemies + 1;
		int count = 0;
		while (total > 1) {
			count ++;
			total /= 2;
		}
		return count;
	}


	public void Update() {
		if (STATE.equals("MENU")) {
			if (menu.Update(slave)) {
				STATE = "BATTLE";
				currentEnemy = eHandle.RandomEnemy(round);
				combat.Initialize(currentEnemy, slave.user);
			}
		}else if (STATE.equals("BATTLE")) {
			combat.Update(slave);
			if (slave.space && combat.isFinished()) {
				round ++;
				slave.currentRound ++;
				if ((slave.defeat || round >= numRounds)) {
					STATE = "END";
				} else {
					STATE = "RESULT";
					combat.Cleanup();
				}
			}
		}else if (STATE.equals("STARTUP")) {
			intro.Update();
			if (slave.space) {
				STATE = "MENU";
			}
		}else if (STATE.equals("TITLE")) {
			if (title.Update(slave)) {
				STATE = "STARTUP";
			}
		}else if (STATE.equals("RESULT")) {
			if (results.Update(slave)) {
				STATE = "MENU";
				slave.user.UpdatePoints(5);
			}
		}else if (STATE.equals("END")) {
			end.Update(!slave.defeat);

		}
		
	//	System.out.println("ROUND:" + round);
	//	System.out.println("Points" + slave.user.getUnspentPoints());

	}


	public String getSTATE() {
		return STATE;
	}


	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
}
