package ai.helper;

public class AIHelper {
	
	// constants of the game, that might be changed later for balancing reasons
	private int UNITLIMIT = 70;
	// unit_level_trait
	private Integer PEON_1_WOOD = 10;
	private Integer PEON_1_STONE = 0;
	private Integer PEON_1_IRON = 0;
	private Integer PEON_1_TIME = 5;
	private Integer PEON_1_STRENGTH = 1;
	private Integer PEON_1_HP = 20;
	private Integer SWORDSMAN_1_WOOD = 20;
	private Integer SWORDSMAN_1_STONE = 0;
	private Integer SWORDSMAN_1_IRON = 0;
	private Integer SWORDSMAN_1_TIME = 10;
	private Integer SWORDSMAN_1_STRENGTH = 3;
	private Integer SWORDSMAN_1_HP = 30;
	private Integer SWORDSMAN_2_WOOD = 30;
	private Integer SWORDSMAN_2_STONE = 0;
	private Integer SWORDSMAN_2_IRON = 20;
	private Integer SWORDSMAN_2_TIME = 20;
	private Integer SWORDSMAN_2_STRENGTH = 6;
	private Integer SWORDSMAN_2_HP = 70;
	private Integer SWORDSMAN_3_WOOD = 40;
	private Integer SWORDSMAN_3_STONE = 0;
	private Integer SWORDSMAN_3_IRON = 50;
	private Integer SWORDSMAN_3_TIME = 40;
	private Integer SWORDSMAN_3_STRENGTH = 9;
	private Integer SWORDSMAN_3_HP = 100;
	private Integer ARCHER_1_WOOD = 20;
	private Integer ARCHER_1_STONE = 0;
	private Integer ARCHER_1_IRON = 0;
	private Integer ARCHER_1_TIME = 10;
	private Integer ARCHER_1_STRENGTH = 4;
	private Integer ARCHER_1_HP = 20;
	private Integer ARCHER_2_WOOD = 30;
	private Integer ARCHER_2_STONE = 0;
	private Integer ARCHER_2_IRON = 20;
	private Integer ARCHER_2_TIME = 20;
	private Integer ARCHER_2_STRENGTH = 7;
	private Integer ARCHER_2_HP = 50;
	private Integer ARCHER_3_WOOD = 40;
	private Integer ARCHER_3_STONE = 0;
	private Integer ARCHER_3_IRON = 50;
	private Integer ARCHER_3_TIME = 40;
	private Integer ARCHER_3_STRENGTH = 10;
	private Integer ARCHER_3_HP = 70;
	private Integer KNIGHT_1_WOOD = 30;
	private Integer KNIGHT_1_STONE = 0;
	private Integer KNIGHT_1_IRON = 30;
	private Integer KNIGHT_1_TIME = 20;
	private Integer KNIGHT_1_STRENGTH = 5;
	private Integer KNIGHT_1_HP = 40;
	private Integer KNIGHT_2_WOOD = 40;
	private Integer KNIGHT_2_STONE = 0;
	private Integer KNIGHT_2_IRON = 40;
	private Integer KNIGHT_2_TIME = 40;
	private Integer KNIGHT_2_STRENGTH = 8;
	private Integer KNIGHT_2_HP = 80;
	private Integer KNIGHT_3_WOOD = 50;
	private Integer KNIGHT_3_STONE = 0;
	private Integer KNIGHT_3_IRON = 70;
	private Integer KNIGHT_3_TIME = 60;
	private Integer KNIGHT_3_STRENGTH = 11;
	private Integer KNIGHT_3_HP = 120;
	private Integer CATAPULT_1_WOOD = 100;
	private Integer CATAPULT_1_STONE = 150;
	private Integer CATAPULT_1_IRON = 150;
	private Integer CATAPULT_1_TIME = 60;
	private Integer CATAPULT_1_STRENGTH = 20;
	private Integer CATAPULT_1_HP = 200;
	// TODO: integrate a faktor for higher catapult-damage against buildings?
	
	// building_level_trait
	private Integer STRONGHOLD_1_HP = 1000;
	private Integer STRONGHOLD_1_TIME = 50;
	private Integer STRONGHOLD_1_WOOD = 200;
	private Integer STRONGHOLD_1_STONE = 0;
	private Integer STRONGHOLD_1_IRON = 0;
	private Integer STRONGHOLD_2_HP = 2000;
	private Integer STRONGHOLD_2_TIME = 70;
	private Integer STRONGHOLD_2_WOOD = 200;
	private Integer STRONGHOLD_2_STONE = 100;
	private Integer STRONGHOLD_2_IRON = 0;
	private Integer STRONGHOLD_3_HP = 5000;
	private Integer STRONGHOLD_3_TIME = 90;
	private Integer STRONGHOLD_3_WOOD = 250;
	private Integer STRONGHOLD_3_STONE = 250;
	private Integer STRONGHOLD_3_IRON = 0;
	private Integer FARM_1_HP = 400;
	private Integer FARM_1_TIME = 20;
	private Integer FARM_1_WOOD = 80;
	private Integer FARM_1_STONE = 0;
	private Integer FARM_1_IRON = 0;
	private Integer BARRACK_1_HP = 500;
	private Integer BARRACK_1_TIME = 30;
	private Integer BARRACK_1_WOOD = 120;
	private Integer BARRACK_1_STONE = 0;
	private Integer BARRACK_1_IRON = 0;
	private Integer BARRACK_2_HP = 750;
	private Integer BARRACK_2_TIME = 40;
	private Integer BARRACK_2_WOOD = 100;
	private Integer BARRACK_2_STONE = 100;
	private Integer BARRACK_2_IRON = 0;
	private Integer BARRACK_3_HP = 1000;
	private Integer BARRACK_3_TIME = 50;
	private Integer BARRACK_3_WOOD = 150;
	private Integer BARRACK_3_STONE = 150;
	private Integer BARRACK_3_IRON = 0;
	private Integer FORGE_1_HP = 800;
	private Integer FORGE_1_TIME = 90;
	private Integer FORGE_1_WOOD = 0;
	private Integer FORGE_1_STONE = 200;
	private Integer FORGE_1_IRON = 300;
	private Integer TOWER_1_HP = 800;
	private Integer TOWER_1_TIME = 40;
	private Integer TOWER_1_WOOD = 80;
	private Integer TOWER_1_STONE = 100;
	private Integer TOWER_1_IRON = 0;
	private Integer TOWER_2_HP = 1200;
	private Integer TOWER_2_TIME = 60;
	private Integer TOWER_2_WOOD = 100;
	private Integer TOWER_2_STONE = 150;
	private Integer TOWER_2_IRON = 0;
	private Integer TOWER_3_HP = 2000;
	private Integer TOWER_3_TIME = 80;
	private Integer TOWER_3_WOOD = 120;
	private Integer TOWER_3_STONE = 2000;
	private Integer TOWER_3_IRON = 0;
	
	
	/**
	 * @param wood
	 * 	the amount of wood that could be invested e.g. 1000
	 * @param stone
	 * 	the amount of stone that could be invested e.g. 1000
	 * @param iron
	 * 	the amount of iron that could be invested e.g. 1000
	 * @param currentUnitCount
	 *  the count of own units in game, must be < 70 (= Limit) e.g. 20 
	 * @return
	 *  "unitType:level:count:productionTime:combatValue" 
	 *  -> unitType is the class-name of the best unit e.g. "Archer"
	 *  	or "noUnitPossible" if no unit is possible at all
	 *  -> level is the units level e.g. "2"
	 *  -> count is the number of units of the best type that could be 
	 *  produced from the given input of resources e.g. "200"
	 *  -> productionTime is the time to produce the count with only one
	 *   building (Barrack or Forge) in seconds e.g. "3000"
	 *  -> combatValue is the unitType-specific product count*HP*strength 
	 */
	public String getBestUnitType(int wood, int stone, int iron, int currentUnitCount) {
		String result = null;
		String unitType = "";
		int level = 0;
		int productionTime = 0;
		int tempMaxCount = 0;
		int bestMaxCount = 0;
		int tempMaxValue = 0; // combatValue of a unitType-level-army 
		
		
		// check if unit-limit is limiting
		tempMaxCount = UNITLIMIT - currentUnitCount;
		if (tempMaxCount > 0 && wood >= 10) {
			// at least one unit can be build, go on and set the first as best
			unitType = "Peon";
			level = 1;
		} else {
			return "noUnitPossible";
		}
		
		// now check all unit-level-combinations and set the better as best 
		
		// peons of level 1
		// check if wood is limiting
		if (PEON_1_WOOD > 0) { //prevent division by zero
			if (wood/PEON_1_WOOD < tempMaxCount) {
				tempMaxCount = wood/PEON_1_WOOD;
			}
		}
		// check if stone is limiting
		if (PEON_1_STONE > 0) { 
			if (stone/PEON_1_STONE < tempMaxCount) {
				tempMaxCount = stone/PEON_1_STONE;
			}
		}
		// check if iron is limiting
		if (PEON_1_IRON > 0) { 
			if (iron/PEON_1_IRON < tempMaxCount) {
				tempMaxCount = iron/PEON_1_IRON;
			}
		}
		// store the combat-value of a unitType-level-army for later comparison
		tempMaxValue = tempMaxCount * PEON_1_STRENGTH * PEON_1_HP;
		bestMaxCount = tempMaxCount;
		productionTime = tempMaxCount * PEON_1_TIME;
		
		// swordsman of level 1
		tempMaxCount = UNITLIMIT - currentUnitCount;
		if (SWORDSMAN_1_WOOD > 0) { 
			if (wood/SWORDSMAN_1_WOOD < tempMaxCount) {
				tempMaxCount = wood/SWORDSMAN_1_WOOD;
			}
		}
		if (SWORDSMAN_1_STONE > 0) { 
			if (stone/SWORDSMAN_1_STONE < tempMaxCount) {
				tempMaxCount = stone/SWORDSMAN_1_STONE;
			}
		}
		if (SWORDSMAN_1_IRON > 0) { 
			if (iron/SWORDSMAN_1_IRON < tempMaxCount) {
				tempMaxCount = iron/SWORDSMAN_1_IRON;
			}
		}
		if (tempMaxValue < tempMaxCount * SWORDSMAN_1_STRENGTH * SWORDSMAN_1_HP) {
			// new combination is better than predecessors, set as best
			tempMaxValue = tempMaxCount * SWORDSMAN_1_STRENGTH * SWORDSMAN_1_HP;
			bestMaxCount = tempMaxCount;
			productionTime = tempMaxCount * SWORDSMAN_1_TIME;
			unitType = "Swordsman";
			level = 1;
		}
		
		// swordsman of level 2
		tempMaxCount = UNITLIMIT - currentUnitCount;
		if (SWORDSMAN_2_WOOD > 0) { 
			if (wood/SWORDSMAN_2_WOOD < tempMaxCount) {
				tempMaxCount = wood/SWORDSMAN_2_WOOD;
			}
		}
		if (SWORDSMAN_2_STONE > 0) { 
			if (stone/SWORDSMAN_2_STONE < tempMaxCount) {
				tempMaxCount = stone/SWORDSMAN_1_STONE;
			}
		}
		if (SWORDSMAN_2_IRON > 0) { 
			if (iron/SWORDSMAN_2_IRON < tempMaxCount) {
				tempMaxCount = iron/SWORDSMAN_2_IRON;
			}
		}
		if (tempMaxValue < tempMaxCount * SWORDSMAN_2_STRENGTH * SWORDSMAN_2_HP) {
			tempMaxValue = tempMaxCount * SWORDSMAN_2_STRENGTH * SWORDSMAN_2_HP;
			bestMaxCount = tempMaxCount;
			productionTime = tempMaxCount * SWORDSMAN_2_TIME;
			unitType = "Swordsman";
			level = 2;
		}
		
		// swordsman of level 3
		tempMaxCount = UNITLIMIT - currentUnitCount;
		if (SWORDSMAN_3_WOOD > 0) { 
			if (wood/SWORDSMAN_3_WOOD < tempMaxCount) {
				tempMaxCount = wood/SWORDSMAN_3_WOOD;
			}
		}
		if (SWORDSMAN_3_STONE > 0) { 
			if (stone/SWORDSMAN_3_STONE < tempMaxCount) {
				tempMaxCount = stone/SWORDSMAN_3_STONE;
			}
		}
		if (SWORDSMAN_3_IRON > 0) { 
			if (iron/SWORDSMAN_3_IRON < tempMaxCount) {
				tempMaxCount = iron/SWORDSMAN_3_IRON;
			}
		}
		if (tempMaxValue < tempMaxCount * SWORDSMAN_3_STRENGTH * SWORDSMAN_3_HP) {
			tempMaxValue = tempMaxCount * SWORDSMAN_3_STRENGTH * SWORDSMAN_3_HP;
			bestMaxCount = tempMaxCount;
			productionTime = tempMaxCount * SWORDSMAN_3_TIME;
			unitType = "Swordsman";
			level = 3;
		}
		
		// archer of level 1
		tempMaxCount = UNITLIMIT - currentUnitCount;
		if (ARCHER_1_WOOD > 0) { 
			if (wood/ARCHER_1_WOOD < tempMaxCount) {
				tempMaxCount = wood/ARCHER_1_WOOD;
			}
		}
		if (ARCHER_1_STONE > 0) { 
			if (stone/ARCHER_1_STONE < tempMaxCount) {
				tempMaxCount = stone/ARCHER_1_STONE;
			}
		}
		if (ARCHER_1_IRON > 0) { 
			if (iron/ARCHER_1_IRON < tempMaxCount) {
				tempMaxCount = iron/ARCHER_1_IRON;
			}
		}
		if (tempMaxValue < tempMaxCount * ARCHER_1_STRENGTH * ARCHER_1_HP) {
			tempMaxValue = tempMaxCount * ARCHER_1_STRENGTH * ARCHER_1_HP;
			bestMaxCount = tempMaxCount;
			productionTime = tempMaxCount * ARCHER_1_TIME;
			unitType = "Archer";
			level = 1;
		}
		
		// archer of level 2
		tempMaxCount = UNITLIMIT - currentUnitCount;
		if (ARCHER_2_WOOD > 0) { 
			if (wood/ARCHER_2_WOOD < tempMaxCount) {
				tempMaxCount = wood/ARCHER_2_WOOD;
			}
		}
		if (ARCHER_2_STONE > 0) { 
			if (stone/ARCHER_2_STONE < tempMaxCount) {
				tempMaxCount = stone/ARCHER_2_STONE;
			}
		}
		if (ARCHER_2_IRON > 0) { 
			if (iron/ARCHER_2_IRON < tempMaxCount) {
				tempMaxCount = iron/ARCHER_2_IRON;
			}
		}
		if (tempMaxValue < tempMaxCount * ARCHER_2_STRENGTH * ARCHER_2_HP) {
			tempMaxValue = tempMaxCount * ARCHER_2_STRENGTH * ARCHER_2_HP;
			bestMaxCount = tempMaxCount;
			productionTime = tempMaxCount * ARCHER_2_TIME;
			unitType = "Archer";
			level = 2;
		}
		
		// archer of level 3
		tempMaxCount = UNITLIMIT - currentUnitCount;
		if (ARCHER_3_WOOD > 0) { 
			if (wood/ARCHER_3_WOOD < tempMaxCount) {
				tempMaxCount = wood/ARCHER_3_WOOD;
			}
		}
		if (ARCHER_3_STONE > 0) { 
			if (stone/ARCHER_3_STONE < tempMaxCount) {
				tempMaxCount = stone/ARCHER_3_STONE;
			}
		}
		if (ARCHER_3_IRON > 0) { 
			if (iron/ARCHER_3_IRON < tempMaxCount) {
				tempMaxCount = iron/ARCHER_3_IRON;
			}
		}
		if (tempMaxValue < tempMaxCount * ARCHER_3_STRENGTH * ARCHER_3_HP) {
			tempMaxValue = tempMaxCount * ARCHER_3_STRENGTH * ARCHER_3_HP;
			bestMaxCount = tempMaxCount;
			productionTime = tempMaxCount * ARCHER_3_TIME;
			unitType = "Archer";
			level = 3;
		}
		
		// knight of level 1
		tempMaxCount = UNITLIMIT - currentUnitCount;
		if (KNIGHT_1_WOOD > 0) { 
			if (wood/KNIGHT_1_WOOD < tempMaxCount) {
				tempMaxCount = wood/KNIGHT_1_WOOD;
			}
		}
		if (KNIGHT_1_STONE > 0) { 
			if (stone/KNIGHT_1_STONE < tempMaxCount) {
				tempMaxCount = stone/KNIGHT_1_STONE;
			}
		}
		if (KNIGHT_1_IRON > 0) { 
			if (iron/KNIGHT_1_IRON < tempMaxCount) {
				tempMaxCount = iron/KNIGHT_1_IRON;
			}
		}
		if (tempMaxValue < tempMaxCount * KNIGHT_1_STRENGTH * KNIGHT_1_HP) {
			tempMaxValue = tempMaxCount * KNIGHT_1_STRENGTH * KNIGHT_1_HP;
			bestMaxCount = tempMaxCount;
			productionTime = tempMaxCount * KNIGHT_1_TIME;
			unitType = "Knight";
			level = 1;
		}
		
		// knight of level 2
		tempMaxCount = UNITLIMIT - currentUnitCount;
		if (KNIGHT_2_WOOD > 0) { 
			if (wood/KNIGHT_2_WOOD < tempMaxCount) {
				tempMaxCount = wood/KNIGHT_2_WOOD;
			}
		}
		if (KNIGHT_2_STONE > 0) { 
			if (stone/KNIGHT_2_STONE < tempMaxCount) {
				tempMaxCount = stone/KNIGHT_2_STONE;
			}
		}
		if (KNIGHT_2_IRON > 0) { 
			if (iron/KNIGHT_2_IRON < tempMaxCount) {
				tempMaxCount = iron/KNIGHT_2_IRON;
			}
		}
		if (tempMaxValue < tempMaxCount * KNIGHT_2_STRENGTH * KNIGHT_2_HP) {
			tempMaxValue = tempMaxCount * KNIGHT_2_STRENGTH * KNIGHT_2_HP;
			bestMaxCount = tempMaxCount;
			productionTime = tempMaxCount * KNIGHT_2_TIME;
			unitType = "Knight";
			level = 2;
		}
		
		// knight of level 3
		tempMaxCount = UNITLIMIT - currentUnitCount;
		if (KNIGHT_3_WOOD > 0) { 
			if (wood/KNIGHT_3_WOOD < tempMaxCount) {
				tempMaxCount = wood/KNIGHT_3_WOOD;
			}
		}
		if (KNIGHT_3_STONE > 0) { 
			if (stone/KNIGHT_3_STONE < tempMaxCount) {
				tempMaxCount = stone/KNIGHT_3_STONE;
			}
		}
		if (KNIGHT_3_IRON > 0) { 
			if (iron/KNIGHT_3_IRON < tempMaxCount) {
				tempMaxCount = iron/KNIGHT_3_IRON;
			}
		}
		if (tempMaxValue < tempMaxCount * KNIGHT_3_STRENGTH * KNIGHT_3_HP) {
			tempMaxValue = tempMaxCount * KNIGHT_3_STRENGTH * KNIGHT_3_HP;
			bestMaxCount = tempMaxCount;
			productionTime = tempMaxCount * KNIGHT_3_TIME;
			unitType = "Knight";
			level = 3;
		}
		
		// catapult of level 1
		tempMaxCount = UNITLIMIT - currentUnitCount;
		if (CATAPULT_1_WOOD > 0) { 
			if (wood/CATAPULT_1_WOOD < tempMaxCount) {
				tempMaxCount = wood/CATAPULT_1_WOOD;
			}
		}
		if (CATAPULT_1_STONE > 0) { 
			if (stone/CATAPULT_1_STONE < tempMaxCount) {
				tempMaxCount = stone/CATAPULT_1_STONE;
			}
		}
		if (CATAPULT_1_IRON > 0) { 
			if (iron/CATAPULT_1_IRON < tempMaxCount) {
				tempMaxCount = iron/CATAPULT_1_IRON;
			}
		}
		if (tempMaxValue < tempMaxCount * CATAPULT_1_STRENGTH * CATAPULT_1_HP) {
			tempMaxValue = tempMaxCount * CATAPULT_1_STRENGTH * CATAPULT_1_HP;
			bestMaxCount = tempMaxCount;
			productionTime = tempMaxCount * CATAPULT_1_TIME;
			unitType = "Catapult";
			level = 1;
		}
	
		// best combination has been found
		result = unitType + ":" + level + ":" + bestMaxCount + ":"
				 + productionTime + ":" + tempMaxValue;
		return result;
	}

	public Integer getUNITLIMIT() {
		return UNITLIMIT;
	}

	public Integer getPEON_1_WOOD() {
		return PEON_1_WOOD;
	}

	public Integer getPEON_1_STONE() {
		return PEON_1_STONE;
	}

	public Integer getPEON_1_IRON() {
		return PEON_1_IRON;
	}

	public Integer getPEON_1_TIME() {
		return PEON_1_TIME;
	}

	public Integer getPEON_1_STRENGTH() {
		return PEON_1_STRENGTH;
	}

	public Integer getPEON_1_HP() {
		return PEON_1_HP;
	}

	public Integer getSWORDSMAN_1_WOOD() {
		return SWORDSMAN_1_WOOD;
	}

	public Integer getSWORDSMAN_1_STONE() {
		return SWORDSMAN_1_STONE;
	}

	public Integer getSWORDSMAN_1_IRON() {
		return SWORDSMAN_1_IRON;
	}

	public Integer getSWORDSMAN_1_TIME() {
		return SWORDSMAN_1_TIME;
	}

	public Integer getSWORDSMAN_1_STRENGTH() {
		return SWORDSMAN_1_STRENGTH;
	}

	public Integer getSWORDSMAN_1_HP() {
		return SWORDSMAN_1_HP;
	}

	public Integer getSWORDSMAN_2_WOOD() {
		return SWORDSMAN_2_WOOD;
	}

	public Integer getSWORDSMAN_2_STONE() {
		return SWORDSMAN_2_STONE;
	}

	public Integer getSWORDSMAN_2_IRON() {
		return SWORDSMAN_2_IRON;
	}

	public Integer getSWORDSMAN_2_TIME() {
		return SWORDSMAN_2_TIME;
	}

	public Integer getSWORDSMAN_2_STRENGTH() {
		return SWORDSMAN_2_STRENGTH;
	}

	public Integer getSWORDSMAN_2_HP() {
		return SWORDSMAN_2_HP;
	}

	public Integer getSWORDSMAN_3_WOOD() {
		return SWORDSMAN_3_WOOD;
	}

	public Integer getSWORDSMAN_3_STONE() {
		return SWORDSMAN_3_STONE;
	}

	public Integer getSWORDSMAN_3_IRON() {
		return SWORDSMAN_3_IRON;
	}

	public Integer getSWORDSMAN_3_TIME() {
		return SWORDSMAN_3_TIME;
	}

	public Integer getSWORDSMAN_3_STRENGTH() {
		return SWORDSMAN_3_STRENGTH;
	}

	public Integer getSWORDSMAN_3_HP() {
		return SWORDSMAN_3_HP;
	}

	public Integer getARCHER_1_WOOD() {
		return ARCHER_1_WOOD;
	}

	public Integer getARCHER_1_STONE() {
		return ARCHER_1_STONE;
	}

	public Integer getARCHER_1_IRON() {
		return ARCHER_1_IRON;
	}

	public Integer getARCHER_1_TIME() {
		return ARCHER_1_TIME;
	}

	public Integer getARCHER_1_STRENGTH() {
		return ARCHER_1_STRENGTH;
	}

	public Integer getARCHER_1_HP() {
		return ARCHER_1_HP;
	}

	public Integer getARCHER_2_WOOD() {
		return ARCHER_2_WOOD;
	}

	public Integer getARCHER_2_STONE() {
		return ARCHER_2_STONE;
	}

	public Integer getARCHER_2_IRON() {
		return ARCHER_2_IRON;
	}

	public Integer getARCHER_2_TIME() {
		return ARCHER_2_TIME;
	}

	public Integer getARCHER_2_STRENGTH() {
		return ARCHER_2_STRENGTH;
	}

	public Integer getARCHER_2_HP() {
		return ARCHER_2_HP;
	}

	public Integer getARCHER_3_WOOD() {
		return ARCHER_3_WOOD;
	}

	public Integer getARCHER_3_STONE() {
		return ARCHER_3_STONE;
	}

	public Integer getARCHER_3_IRON() {
		return ARCHER_3_IRON;
	}

	public Integer getARCHER_3_TIME() {
		return ARCHER_3_TIME;
	}

	public Integer getARCHER_3_STRENGTH() {
		return ARCHER_3_STRENGTH;
	}

	public Integer getARCHER_3_HP() {
		return ARCHER_3_HP;
	}

	public Integer getKNIGHT_1_WOOD() {
		return KNIGHT_1_WOOD;
	}

	public Integer getKNIGHT_1_STONE() {
		return KNIGHT_1_STONE;
	}

	public Integer getKNIGHT_1_IRON() {
		return KNIGHT_1_IRON;
	}

	public Integer getKNIGHT_1_TIME() {
		return KNIGHT_1_TIME;
	}

	public Integer getKNIGHT_1_STRENGTH() {
		return KNIGHT_1_STRENGTH;
	}

	public Integer getKNIGHT_1_HP() {
		return KNIGHT_1_HP;
	}

	public Integer getKNIGHT_2_WOOD() {
		return KNIGHT_2_WOOD;
	}

	public Integer getKNIGHT_2_STONE() {
		return KNIGHT_2_STONE;
	}

	public Integer getKNIGHT_2_IRON() {
		return KNIGHT_2_IRON;
	}

	public Integer getKNIGHT_2_TIME() {
		return KNIGHT_2_TIME;
	}

	public Integer getKNIGHT_2_STRENGTH() {
		return KNIGHT_2_STRENGTH;
	}

	public Integer getKNIGHT_2_HP() {
		return KNIGHT_2_HP;
	}

	public Integer getKNIGHT_3_WOOD() {
		return KNIGHT_3_WOOD;
	}

	public Integer getKNIGHT_3_STONE() {
		return KNIGHT_3_STONE;
	}

	public Integer getKNIGHT_3_IRON() {
		return KNIGHT_3_IRON;
	}

	public Integer getKNIGHT_3_TIME() {
		return KNIGHT_3_TIME;
	}

	public Integer getKNIGHT_3_STRENGTH() {
		return KNIGHT_3_STRENGTH;
	}

	public Integer getKNIGHT_3_HP() {
		return KNIGHT_3_HP;
	}

	public Integer getCATAPULT_1_WOOD() {
		return CATAPULT_1_WOOD;
	}

	public Integer getCATAPULT_1_STONE() {
		return CATAPULT_1_STONE;
	}

	public Integer getCATAPULT_1_IRON() {
		return CATAPULT_1_IRON;
	}

	public Integer getCATAPULT_1_TIME() {
		return CATAPULT_1_TIME;
	}

	public Integer getCATAPULT_1_STRENGTH() {
		return CATAPULT_1_STRENGTH;
	}

	public Integer getCATAPULT_1_HP() {
		return CATAPULT_1_HP;
	}

	public Integer getSTRONGHOLD_1_HP() {
		return STRONGHOLD_1_HP;
	}

	public Integer getSTRONGHOLD_1_TIME() {
		return STRONGHOLD_1_TIME;
	}

	public Integer getSTRONGHOLD_1_WOOD() {
		return STRONGHOLD_1_WOOD;
	}

	public Integer getSTRONGHOLD_1_STONE() {
		return STRONGHOLD_1_STONE;
	}

	public Integer getSTRONGHOLD_1_IRON() {
		return STRONGHOLD_1_IRON;
	}

	public Integer getSTRONGHOLD_2_HP() {
		return STRONGHOLD_2_HP;
	}

	public Integer getSTRONGHOLD_2_TIME() {
		return STRONGHOLD_2_TIME;
	}

	public Integer getSTRONGHOLD_2_WOOD() {
		return STRONGHOLD_2_WOOD;
	}

	public Integer getSTRONGHOLD_2_STONE() {
		return STRONGHOLD_2_STONE;
	}

	public Integer getSTRONGHOLD_2_IRON() {
		return STRONGHOLD_2_IRON;
	}

	public Integer getSTRONGHOLD_3_HP() {
		return STRONGHOLD_3_HP;
	}

	public Integer getSTRONGHOLD_3_TIME() {
		return STRONGHOLD_3_TIME;
	}

	public Integer getSTRONGHOLD_3_WOOD() {
		return STRONGHOLD_3_WOOD;
	}

	public Integer getSTRONGHOLD_3_STONE() {
		return STRONGHOLD_3_STONE;
	}

	public Integer getSTRONGHOLD_3_IRON() {
		return STRONGHOLD_3_IRON;
	}

	public Integer getFARM_1_HP() {
		return FARM_1_HP;
	}

	public Integer getFARM_1_TIME() {
		return FARM_1_TIME;
	}

	public Integer getFARM_1_WOOD() {
		return FARM_1_WOOD;
	}

	public Integer getFARM_1_STONE() {
		return FARM_1_STONE;
	}

	public Integer getFARM_1_IRON() {
		return FARM_1_IRON;
	}

	public Integer getBARRACK_1_HP() {
		return BARRACK_1_HP;
	}

	public Integer getBARRACK_1_TIME() {
		return BARRACK_1_TIME;
	}

	public Integer getBARRACK_1_WOOD() {
		return BARRACK_1_WOOD;
	}

	public Integer getBARRACK_1_STONE() {
		return BARRACK_1_STONE;
	}

	public Integer getBARRACK_1_IRON() {
		return BARRACK_1_IRON;
	}

	public Integer getBARRACK_2_HP() {
		return BARRACK_2_HP;
	}

	public Integer getBARRACK_2_TIME() {
		return BARRACK_2_TIME;
	}

	public Integer getBARRACK_2_WOOD() {
		return BARRACK_2_WOOD;
	}

	public Integer getBARRACK_2_STONE() {
		return BARRACK_2_STONE;
	}

	public Integer getBARRACK_2_IRON() {
		return BARRACK_2_IRON;
	}

	public Integer getBARRACK_3_HP() {
		return BARRACK_3_HP;
	}

	public Integer getBARRACK_3_TIME() {
		return BARRACK_3_TIME;
	}

	public Integer getBARRACK_3_WOOD() {
		return BARRACK_3_WOOD;
	}

	public Integer getBARRACK_3_STONE() {
		return BARRACK_3_STONE;
	}

	public Integer getBARRACK_3_IRON() {
		return BARRACK_3_IRON;
	}

	public Integer getFORGE_1_HP() {
		return FORGE_1_HP;
	}

	public Integer getFORGE_1_TIME() {
		return FORGE_1_TIME;
	}

	public Integer getFORGE_1_WOOD() {
		return FORGE_1_WOOD;
	}

	public Integer getFORGE_1_STONE() {
		return FORGE_1_STONE;
	}

	public Integer getFORGE_1_IRON() {
		return FORGE_1_IRON;
	}

	public Integer getTOWER_1_HP() {
		return TOWER_1_HP;
	}

	public Integer getTOWER_1_TIME() {
		return TOWER_1_TIME;
	}

	public Integer getTOWER_1_WOOD() {
		return TOWER_1_WOOD;
	}

	public Integer getTOWER_1_STONE() {
		return TOWER_1_STONE;
	}

	public Integer getTOWER_1_IRON() {
		return TOWER_1_IRON;
	}

	public Integer getTOWER_2_HP() {
		return TOWER_2_HP;
	}

	public Integer getTOWER_2_TIME() {
		return TOWER_2_TIME;
	}

	public Integer getTOWER_2_WOOD() {
		return TOWER_2_WOOD;
	}

	public Integer getTOWER_2_STONE() {
		return TOWER_2_STONE;
	}

	public Integer getTOWER_2_IRON() {
		return TOWER_2_IRON;
	}

	public Integer getTOWER_3_HP() {
		return TOWER_3_HP;
	}

	public Integer getTOWER_3_TIME() {
		return TOWER_3_TIME;
	}

	public Integer getTOWER_3_WOOD() {
		return TOWER_3_WOOD;
	}

	public Integer getTOWER_3_STONE() {
		return TOWER_3_STONE;
	}

	public Integer getTOWER_3_IRON() {
		return TOWER_3_IRON;
	}

}
