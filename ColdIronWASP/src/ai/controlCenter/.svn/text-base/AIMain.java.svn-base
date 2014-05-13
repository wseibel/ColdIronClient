package ai.controlCenter;

import java.util.LinkedList;

import ai.helper.AIHelper;
import ai.helper.AiModelAnalyzer;
import model.game.CIClient;
import model.game.Peon;
import model.game.Sector;
import model.game.User;

public class AIMain extends Thread {
	
	private CIClient ciClient = null;
	private AIHelper aiHelper = null;
	private AiModelAnalyzer aiModel = null;
	private RushAI rushAI = null;
	private User currentUser = null;

	// set priorities here (lowest priority = 0, top priority = 10)
	protected int buildPeonPriority = 0;
	protected int buildCatapultPriority = 0;
	protected int buildBarrackLvl2Priority = 0;
	
	// will be set from createUnits class
	protected Boolean combatUnitsPriority;
	protected Boolean catapultPriority;
	protected Boolean level2Priority;
	protected Boolean level3Priority;
	protected Boolean needFarm;
	
	public Peon lastMovedPeon;
	
	// related to moveCombatants...
	// sectors with at least one unit or one building of the enemy on them
	public LinkedList<Sector> enemySectors  = null; 
	// sectors with at least one unit or one building of the own ai on them
	public LinkedList<Sector> ownSectors = null; 
	// sectors from ownSectors with at least one unit of the enemy on them
	public LinkedList<Sector> ownSectorsUnderAttack = null; 
	
	// while false all combatants defend the best own sector of all being under
	// attack and in case of peace spread even over all sectors with own peons
	// and/or buildings on them (also to intercept scouts)
	// while true all combatants attack the best enemy sector and nothing else
	public Boolean goAttacking = false;
	
	// the ratio of ownCombatStrength/enemyCombatStrength - above this threshold
	// MoveCombatants will set goAttacking to true (also when UNITLIMIT got reached)
	public double attackRatio = 1.1;
	
	// set of priorities for selecting the best sector to defend or attack
	// ...there will be a priority-sum over all buildings, resource-types
	// and peons for each sector and the sector with maximum gets chosen... 
	// data to calculate the best sector to defend (the higher the better)
	public int defendPeon = 1; // each instance counts
	public int defendWood = 0; // only one count (amount does not matter) 
	public int defendStone = 0; // only one count (amount does not matter) 
	public int defendIron = 10; // only one count (amount does not matter) 
	public int defendFarm = 10; // each instance counts
	public int defendStronghold = 10; // each instance counts
	public int defendBarrack = 5; // each instance counts
	public int defendForge = 5; // each instance counts
	public int defendTower = 0; // each instance counts
	// data to calculate the best sector to attack (the higher the better)
	public int attackPeon = 2; // each instance adds
	public int attackWood = 0; // only one count (amount does not matter) 
	public int attackStone = 0; // only one count (amount does not matter) 
	public int attackIron = 10; // only one count (amount does not matter) 
	public int attackFarm = 10; // each instance counts
	public int attackStronghold = 10; // each instance counts
	public int attackBarrack = 5; // each instance counts
	public int attackForge = 5; // each instance counts
	public int attackTower = -2; // each instance counts, <0 might be useful
	
	// the time to wait till new orders are possible [ms] (give time to execute)
	public long OrderInterval = 3000; 
	
	public boolean needScouting;
	public long scoutInterval = 30;
	public long firstScout = 5 * OrderInterval;
	
	
	public AIMain(CIClient ciClient, AIHelper aiHelper, AiModelAnalyzer aiModel, User currentUser) {
		super();
		this.ciClient = ciClient;
		this.aiHelper = aiHelper;
		this.aiModel = aiModel;
		this.currentUser = currentUser;
		aiModel.setAIMain(this);
		
		this.aiModel.initValues();
		
		combatUnitsPriority = false;
		catapultPriority = false;
		level2Priority = false;
		level3Priority = false;
		needFarm = false;
	}

	@Override
	public void run() {
		
//		if(this.currentUser != null) {
//			
//			this.rushAI = new RushAI(ciClient.getChainMaster().getCommandHelper(), currentUser.getUserAssets().getStartSector(), currentUser, ciClient);
//			rushAI.run();			
//		}
		
		this.setName("AIMain-thread");
		MovePeons movePeon = new MovePeons(ciClient, aiHelper, aiModel);
		MoveCombatants moveCombatants = new MoveCombatants(ciClient, aiHelper, aiModel);
		CreateUnits createUnits = new CreateUnits(ciClient, aiHelper, aiModel);
		CreateBuildings createBuilding = new CreateBuildings(ciClient, aiHelper, aiModel, this.currentUser);
		ScoutMap scoutMap = new ScoutMap(ciClient, aiHelper, aiModel);
		
		while (true) {
			movePeon.decideMovePeon(this);
			createUnits.decideCreateUnits(this);
			createBuilding.decideCreateBuilding(this);
			moveCombatants.decideMoveCombatants(this);
			scoutMap.decideScoutMap(this);
		}
	}

}
