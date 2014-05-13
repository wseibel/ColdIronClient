package model.game;

import model.lobby.ServerConnection;

public class CiDummy {

   public void removeYou()
   {
   }
	
	public class DemoThread extends Thread{

   public void removeYou()
   {
   }
		 public void run() {
			 
			 try {
		          sleep(10000);
		        }
		        catch(InterruptedException e) {
		        }
			 sector5.removeFromRepairableBuilding(tower3);
			 
			 //stronghold
	/*		 stronghold1.setUnitTypeInCreation("PEON");
			 stronghold1.setUnitCreationProgress(0.00);
			 
			 for(int i = 0; i < 5; i++){
				 try {
			          sleep(1000);
			        }
			        catch(InterruptedException e) {
			        }
				 stronghold1.setUnitCreationProgress((double)i);
			 }
			 stronghold1.setUnitTypeInCreation("UKNOWN");
	*/	
			 
			 //forge
			 barrack1.setUnitTypeInCreation("KNIGHT");
			 barrack1.setUnitCreationProgress(0.00);
			 
			 barrack3.setUnitTypeInCreation("ARCHER");
			 barrack3.setUnitCreationProgress(0.00);
			 
			 for(int i = 0; i < 40; i++){
				 try {
			          sleep(1000);
			        }
			        catch(InterruptedException e) {
			        }
				 barrack1.setUnitCreationProgress((double)i);
				 barrack3.setUnitCreationProgress((double)i);
			 }
			 barrack1.setUnitTypeInCreation("UKNOWN");
			 barrack3.setUnitTypeInCreation("UKNOWN");
			 
			 
			 
			 
			 barrack1.setUnitTypeInCreation("ARCHER");
			 barrack1.setUnitCreationProgress(0.00);
			 
			 for(int i = 0; i < 20; i++){
				 try {
			          sleep(1000);
			        }
			        catch(InterruptedException e) {
			        }
				 barrack1.setUnitCreationProgress((double)i);
			 }
			 barrack1.setUnitTypeInCreation("UKNOWN");
				
				stronghold1.setUpgrading(true);
		     
			 for(int i = 0; i < 20; i++){
				 try {
			          sleep(1000);
			        }
			        catch(InterruptedException e) {
			        }
				 stronghold1.setBuildProgress((double)i);
			 }

			stronghold1.setUpgrading(false);
			 
		        try {
		          sleep(10000);
		        }
		        catch(InterruptedException e) {
		        }
				barrack2.setGame(newGame);
				barrack2.setId("Barrack@sadf13");
				newGame.addToSectorElement(barrack2);
				barrack2.setSector(sector1);
				sector1.addToSectorBuildings(barrack2);
				barrack2.setUserAssets(userAs1);
				userAs1.addToBuildings(barrack2);
				barrack2.setLevel(2);
				barrack2.setMaxHP(1000);
				barrack2.setHp(1000);
				
				barrack2.setBuildingTime(30);
				barrack2.setBuildProgress(5.76);
				barrack2.setUpgrading(false);
				barrack2.setUnitTypeInCreation("UNKNOWN");
				
				 try {
			          sleep(10000);
			        }
			        catch(InterruptedException e) {
			        }
			 		farm2.setGame(newGame);
					farm2.setId("Farm@asf1");
					newGame.addToSectorElement(farm2);
					farm2.setSector(sector1);
					sector1.addToSectorBuildings(farm2);
					farm2.setUserAssets(userAs1);
					userAs1.addToBuildings(farm2);
					farm2.setMaxHP(500);
					farm2.setHp(500);
					
					 try {
				          sleep(5000);
				        }
				        catch(InterruptedException e) {
				        }
						tower2.setGame(newGame);
						tower2.setId("Tower@asf312");
						newGame.addToSectorElement(tower2);
						tower2.setSector(sector1);
						sector1.addToSectorBuildings(tower2);
						tower2.setUserAssets(userAs2);
						userAs2.addToBuildings(tower2);
						tower2.setLevel(2);
						tower2.setMaxHP(100);
						tower2.setHp(100);
		 }
		 
		 		
		    
	}
	
	CIClient ciClient = new CIClient();
//	ServerConnection servCon = new ServerConnection();
	Game newGame = new Game();
	User player1;
	User player2;
	UserAssets userAs1, userAs2;
	Alliance ally1, ally2;
	Map map;
	Resource wood1, wood2, wood3, wood4, wood5;
	Resource stone1, stone2, stone3, stone4, stone5;
	Resource iron1, iron2, iron3, iron4;
	Sector sector1;
	Sector sector2;
	Sector sector3;
	Sector sector4;
	Sector sector5;
	Sector sector6;
	Sector sector7;
	
	//buildings
	Stronghold stronghold1, stronghold2;
	Forge forge;
	Farm farm1, farm2, farm3, farm4;
	Barrack barrack1, barrack2, barrack3;
	Tower tower1, tower2, tower3, tower4;
	
	//units
	Peon peon1, peon2, peon3, peon4, peon5, peon6, peon7;
	Swordsman swordsman1, swordsman2, swordsman3;
	Archer archer1, archer2, archer3, archer4;
	Knight knight1, knight2;
	Catapult catapult;
	
	public void generateMap(){
		map = new Map();
		
		sector1 = new Sector();
		sector2 = new Sector();
		sector3 = new Sector();
		sector4 = new Sector();
		sector5 = new Sector();
		sector6 = new Sector();
		sector7 = new Sector();
		
		wood1 = new Resource();
		wood2 = new Resource();
		wood3 = new Resource();
		wood4 = new Resource();
		wood5 = new Resource();
		stone1 = new Resource();
		stone2 = new Resource();
		stone3 = new Resource();
		stone4 = new Resource();
		stone5 = new Resource();
		iron1 = new Resource();
		iron2 = new Resource();
		iron3 = new Resource();
		iron4 = new Resource();
		
		ciClient.setGame(newGame);
		newGame.setName("MUAHAHAH Game");
		newGame.setCIClient(ciClient);
		
	   ciClient.setServerConnection(new ServerConnection());
		
		map.setName("Aruba_Island");
		map.setGame(newGame);
		newGame.setMap(map);
		
		//setting up res
		//wood
		wood1.setGame(newGame);
		wood1.setType("WOOD");
		wood1.setQuantity("5000");
		wood1.setId("testWoodID");
		
		wood2.setGame(newGame);
		wood2.setType("WOOD");
		wood2.setQuantity("4500");
		wood2.setId("testWoodID");
		
		wood3.setGame(newGame);
		wood3.setType("WOOD");
		wood3.setQuantity("3500");
		wood3.setId("testWoodID");
		
		wood4.setGame(newGame);
		wood4.setType("WOOD");
		wood4.setQuantity("6000");
		wood4.setId("testWoodID");
		
		wood5.setGame(newGame);
		wood5.setType("WOOD");
		wood5.setQuantity("5500");
		wood5.setId("testWoodID");
		//stone
		stone1.setGame(newGame);
		stone1.setType("STONE");
		stone1.setQuantity("2000");
		stone1.setId("testStoneID");
		
		stone2.setGame(newGame);
		stone2.setType("STONE");
		stone2.setQuantity("2200");
		stone2.setId("testStoneID");
		
		stone3.setGame(newGame);
		stone3.setType("STONE");
		stone3.setQuantity("1800");
		stone3.setId("testStoneID");
		
		stone4.setGame(newGame);
		stone4.setType("STONE");
		stone4.setQuantity("1500");
		stone4.setId("testStoneID");
		
		stone5.setGame(newGame);
		stone5.setType("STONE");
		stone5.setQuantity("1850");
		stone5.setId("testStoneID");
		//iron
		iron1.setGame(newGame);
		iron1.setType("IRON");
		iron1.setQuantity("1500");
		iron1.setId("testIronID");
		
		iron2.setGame(newGame);
		iron2.setType("IRON");
		iron2.setQuantity("1250");
		iron2.setId("testIronID");
		
		iron3.setGame(newGame);
		iron3.setType("IRON");
		iron3.setQuantity("1000");
		iron3.setId("testIronID");
		
		iron4.setGame(newGame);
		iron4.setType("IRON");
		iron4.setQuantity("2000");
		iron4.setId("testIronID");

		
		/* setting up the sectors
		 * S1 | -  | S5
		 * ------------- 
		 * S2 | S4 | S6
		 * -------------
		 * S3 | -  | S7
		 * 
		 * StartSectors are: S1, S3, S4, S5
		 */
		sector1.setId("Sektor1");
		sector1.setStartSector(true);
		sector1.setXCoordinate(0);
		sector1.setYCoordinate(0);
		sector1.addToSectorResources(wood1);
		sector1.addToSectorResources(stone1);
		sector1.addToSectorResources(iron1);

		sector2.setId("Sektor2");
		sector2.setStartSector(false);
		sector2.setXCoordinate(0);
		sector2.setYCoordinate(1);
		sector2.addToSectorResources(wood2);
		sector2.addToSectorResources(stone2);
		
		sector3.setId("Sektor3");
		sector3.setStartSector(true);
		sector3.setXCoordinate(0);
		sector3.setYCoordinate(2);
		sector3.addToSectorResources(wood3);
		sector3.addToSectorResources(stone3);
		
		sector4.setId("Sektor4");
		sector4.setStartSector(true);
		sector4.setXCoordinate(1);
		sector4.setYCoordinate(1);
		sector4.addToSectorResources(stone4);
		sector4.addToSectorResources(iron2);
		
		sector5.setId("Sektor5");
		sector5.setStartSector(true);
		sector5.setXCoordinate(2);
		sector5.setYCoordinate(0);
		sector5.addToSectorResources(wood4);
		sector5.addToSectorResources(iron2);
		
		sector6.setId("Sektor6");
		sector6.setStartSector(false);
		sector6.setXCoordinate(2);
		sector6.setYCoordinate(1);
		sector6.addToSectorResources(wood5);
		sector6.addToSectorResources(iron3);
		
		sector7.setId("Sektor7");
		sector7.setStartSector(false);
		sector7.setXCoordinate(2);
		sector7.setYCoordinate(2);
		sector7.addToSectorResources(stone5);
		sector7.addToSectorResources(iron4);
		
		//setting game
		sector1.setGame(newGame);
		sector2.setGame(newGame);
		sector3.setGame(newGame);
		sector4.setGame(newGame);
		sector5.setGame(newGame);
		sector6.setGame(newGame);
		sector7.setGame(newGame);
		//add game to the sector
		newGame.addToSector(sector1);
		newGame.addToSector(sector2);
		newGame.addToSector(sector3);
		newGame.addToSector(sector4);
		newGame.addToSector(sector5);
		newGame.addToSector(sector6);
		newGame.addToSector(sector7);
		//setting map
		sector1.setMap(map);
		sector2.setMap(map);
		sector3.setMap(map);
		sector4.setMap(map);
		sector5.setMap(map);
		sector6.setMap(map);
		sector7.setMap(map);
		//add map to the sector
		map.addToSector(sector1);
		map.addToSector(sector2);
		map.addToSector(sector3);
		map.addToSector(sector4);
		map.addToSector(sector5);
		map.addToSector(sector6);
		map.addToSector(sector7);
		
	}
	
	public void generateUsers(){
		player1 = new User();
		player2 = new User();
		userAs1 = new UserAssets();
		userAs2 = new UserAssets();
		ally1 = new Alliance();
		ally2 = new Alliance();
		
		//add to game
		ally1.setGame(newGame);
		ally2.setGame(newGame);
		player1.setGame(newGame);
		player2.setGame(newGame);
		userAs1.setGame(newGame);
		userAs2.setGame(newGame);
		
		newGame.addToUser(player1);
		newGame.addToUser(player2);
		newGame.addToUserAssets(userAs1);
		newGame.addToUserAssets(userAs2);
		
		//all about players, nickname, ally, starsector, userassists, ally color
		player1.setStartUser(true);
		player1.setNickname("Hans");
		
		player1.setUserAssets(userAs1);
		userAs1.setUser(player1);
		userAs1.setStartSector(sector1);
		userAs1.setAlliance(ally1);
		userAs1.setColor("Red");
		userAs1.addToSector(sector1);
		ally1.addToUserAssets(userAs1);
		ally1.setId("Unbreakeable");
		ally1.setColor("FFEE11");
		
		sector1.setStartingUser(userAs1);
		sector1.setUserAssets(userAs1);
		
		
		player2.setNickname("Peter");
		player2.setStartUser(false);
		player2.setUserAssets(userAs2);
		userAs2.setUser(player2);
		userAs2.setStartSector(sector5);
		userAs2.setColor("Yellow");
		userAs2.setAlliance(ally2);
		userAs2.addToSector(sector5);
		ally2.addToUserAssets(userAs2);
		ally2.setId("Dead Mountains");
		ally2.setColor("AACC55");
		sector5.setStartingUser(userAs2);
		sector5.setUserAssets(userAs2);
		
		//the 2nd player got 2 sectors
		userAs2.addToSector(sector3);
		sector3.setUserAssets(userAs2);
		
	}
	
	public void generateBuildings(){
		
		stronghold1 = new Stronghold();
		stronghold2 = new Stronghold();
		farm1 = new Farm();
		farm2 = new Farm();
		farm3 = new Farm();
		farm4 = new Farm();
		forge = new Forge();
		barrack1 = new Barrack();
		barrack2 = new Barrack();
		barrack3 = new Barrack();
		tower1 = new Tower();
		tower2 = new Tower();
		tower3 = new Tower();
		tower4 = new Tower();
		
		//for player1
		stronghold1.setGame(newGame);
		stronghold1.setId("Stronghold@3124as");
		newGame.addToSectorElement(stronghold1);
		stronghold1.setSector(sector1);
		sector1.addToSectorBuildings(stronghold1);
		stronghold1.setUserAssets(userAs1);
		userAs1.addToBuildings(stronghold1);
		stronghold1.setLevel(1);
		stronghold1.setMaxHP(1000);
		stronghold1.setHp(929);
		sector1.addToRepairableBuilding(stronghold1);
		
		stronghold1.setBuildingTime(20);
		stronghold1.setBuildProgress(0.00);
		stronghold1.setUnitTypeInCreation("UNKNOWN");
		
		
		farm1.setGame(newGame);
		farm1.setId("Farm@sdf231");
		farm4.setGame(newGame);
		farm4.setId("Farm@sdf235");
		newGame.addToSectorElement(farm1);
		farm1.setSector(sector1);
		sector1.addToSectorBuildings(farm1);
		farm4.setSector(sector1);
		farm1.setUserAssets(userAs1);
		userAs1.addToBuildings(farm1);
		farm4.setUserAssets(userAs1);
		farm1.setMaxHP(500);
		farm1.setHp(100);
		farm1.setLevel(1);
		farm4.setMaxHP(500);
		farm4.setHp(0);
		farm4.setLevel(1);
		farm4.setSectorForConstructableBuilding(sector1);
		
		DemoThread t1 = new DemoThread();
		
		t1.start();
		
		barrack1.setGame(newGame);
		barrack1.setId("Barrack@123eas");
		newGame.addToSectorElement(barrack1);
		barrack1.setSector(sector1);
		sector1.addToSectorBuildings(barrack1);
		barrack1.setUserAssets(userAs1);
		userAs1.addToBuildings(barrack1);
		barrack1.setLevel(2);
		barrack1.setMaxHP(1000);
		barrack1.setHp(600);
		sector1.addToRepairableBuilding(barrack1);
		
		barrack1.setBuildingTime(20);
		barrack1.setBuildProgress(0.00);
		barrack1.setUpgrading(false);
		barrack1.setUnitTypeInCreation("UNKNOWN");
		barrack1.setUnitCreationProgress(0.00);
		
		barrack3.setGame(newGame);
		barrack3.setId("Barrack@1532eas");
		newGame.addToSectorElement(barrack3);
		barrack3.setSector(sector1);
		sector1.addToSectorBuildings(barrack3);
		barrack3.setUserAssets(userAs1);
		userAs1.addToBuildings(barrack3);
		barrack3.setLevel(3);
		barrack3.setMaxHP(1000);
		barrack3.setHp(1000);
		
		barrack3.setUnitTypeInCreation("UNKNOWN");
		barrack3.setUnitCreationProgress(0.00);
		
		tower4.setGame(newGame);
		tower4.setId("Tower@asdf7");
		tower4.setSector(sector1);
		tower4.setUserAssets(userAs1);
		tower4.setLevel(3);
		tower4.setMaxHP(100);
		tower4.setHp(15);
		tower4.setSectorForRepairableBuilding(sector1);
		
		//for player2 peter
		stronghold2.setGame(newGame);
		stronghold2.setId("Stronghold@sdf13");
		newGame.addToSectorElement(stronghold2);
		stronghold2.setSector(sector5);
		sector5.addToSectorBuildings(stronghold2);
		sector5.addToRepairableBuilding(stronghold2);
		stronghold2.setUserAssets(userAs2);
		userAs2.addToBuildings(stronghold2);
		stronghold2.setLevel(2);
		stronghold2.setMaxHP(1000);
		stronghold2.setHp(99);
		
		stronghold2.setBuildingTime(90);
		stronghold2.setBuildProgress(20.00);
		stronghold2.setUpgrading(false);
		stronghold2.setUnitTypeInCreation("UNKNOWN");
				
		forge.setGame(newGame);
		forge.setId("Forge@435rts");
		newGame.addToSectorElement(forge);
		forge.setSector(sector5);
		sector5.addToSectorBuildings(forge);
		forge.setUserAssets(userAs2);
		userAs2.addToBuildings(forge);
		forge.setMaxHP(1000);
		forge.setHp(333);
		sector5.addToRepairableBuilding(forge);
		forge.setLevel(1);
		
		forge.setUnitTypeInCreation("UNKNOWN");
		forge.setUnitCreationProgress(0.00);
		
		farm3.setGame(newGame);
		farm3.setId("Farm@sdf1");
		newGame.addToSectorElement(farm3);
		farm3.setSector(sector5);
		sector5.addToSectorBuildings(farm3);
		farm3.setUserAssets(userAs2);
		userAs2.addToBuildings(farm3);
		farm3.setMaxHP(500);
		farm3.setHp(200);
		sector5.addToRepairableBuilding(farm3);
		farm3.setLevel(1);
		
		tower1.setGame(newGame);
		tower1.setId("Tower@asdf1");
		newGame.addToSectorElement(tower1);
		tower1.setSector(sector5);
		sector5.addToSectorBuildings(tower1);
		tower1.setUserAssets(userAs2);
		userAs2.addToBuildings(tower1);
		tower1.setLevel(3);
		tower1.setMaxHP(100);
		tower1.setHp(50);
				
		
		tower3.setGame(newGame);
		tower3.setId("Tower@345we");
		newGame.addToSectorElement(tower3);
		tower3.setSector(sector5);
		sector5.addToSectorBuildings(tower3);
		tower3.setUserAssets(userAs2);
		userAs2.addToBuildings(tower3);
		tower3.setLevel(2);
		//tower have to be repaired
		tower3.setSectorForRepairableBuilding(sector5);
		tower3.setMaxHP(100);
		tower3.setHp(50);
		sector5.addToRepairableBuilding(tower3);
		//tower on upgrade
		tower3.setBuildingTime(10);
		tower3.setBuildProgress(0.00);
		tower3.setUpgrading(false);
		
		
		tower2.setGame(newGame);
		tower2.setId("Tower@asf312");
		newGame.addToSectorElement(tower2);
		tower2.setSector(sector5);
		sector5.addToSectorBuildings(tower2);
		tower2.setUserAssets(userAs2);
		userAs2.addToBuildings(tower2);
		tower2.setLevel(1);
		tower2.setMaxHP(100);
		tower2.setHp(50);
		tower2.setSectorForRepairableBuilding(sector5);
		
		//tower on upgrade
		tower2.setBuildingTime(20);
		tower2.setBuildProgress(5.37);
		tower2.setUpgrading(false);
	}
	
	public void generateUnits(){
		peon1 = new Peon();
		peon2 = new Peon();
		peon3 = new Peon();
		peon4 = new Peon(); 
		peon5 = new Peon();
		peon6 = new Peon(); 
		peon7 = new Peon();
		
		swordsman1 = new Swordsman();
		swordsman2 = new Swordsman();
		swordsman3 = new Swordsman();
		
		archer1 = new Archer();
		archer2 = new Archer();
		archer3 = new Archer();
		archer4 = new Archer();
		
		knight1 = new Knight();
		knight2 = new Knight();
		
		catapult = new Catapult();
		
		//for player 1 Hans
		peon1.setGame(newGame);
		newGame.addToSectorElement(peon1);
		peon1.setId("Peon@sdg23");
		peon1.setUserAssets(userAs1);
		userAs1.addToUnits(peon1);
		peon1.setSector(sector1);
		sector1.addToSectorUnits(peon1);
		peon1.setMaxHP(10);
		peon1.setHp(10);
		
		peon1.setBuildingTime(5);
//		peon1.setCollecting(wood1);
		
		peon2.setGame(newGame);
		newGame.addToSectorElement(peon2);
		peon2.setId("Peon@asdf123");
		peon2.setUserAssets(userAs1);
		userAs1.addToUnits(peon2);
		peon2.setSector(sector1);
		sector1.addToSectorUnits(peon2);
		peon2.setMaxHP(10);
		peon2.setHp(5);
		
	//	peon2.setWorkingOn(farm1);
	//	peon2.setCollecting(stone1);
		
		peon3.setGame(newGame);
		newGame.addToSectorElement(peon3);
		peon3.setId("Peon@asdf123");
		peon3.setUserAssets(userAs1);
		userAs1.addToUnits(peon3);
		peon3.setSector(sector1);
		sector1.addToSectorUnits(peon3);
		peon3.setMaxHP(10);
		peon3.setHp(10);
		
		peon3.setCollecting(iron1);
		
		archer1.setGame(newGame);
		newGame.addToSectorElement(archer1);
		archer1.setId("Archer@sdf23");
		archer1.setUserAssets(userAs1);
		userAs1.addToUnits(archer1);
		archer1.setSector(sector1);
		sector1.addToSectorUnits(archer1);
		archer1.setLevel(1);
		archer1.setMaxHP(10);
		archer1.setHp(7);
		archer1.setTower(tower4);
		
		archer2.setGame(newGame);
		newGame.addToSectorElement(archer2);
		archer2.setId("Archer@sf23");
		archer2.setUserAssets(userAs1);
		userAs1.addToUnits(archer2);
		archer2.setSector(sector1);
		sector1.addToSectorUnits(archer2);
		archer2.setLevel(2);
		archer2.setMaxHP(10);
		archer2.setHp(7);
		
		swordsman1.setGame(newGame);
		newGame.addToSectorElement(swordsman1);
		swordsman1.setId("Swordsman@sdg34");
		swordsman1.setUserAssets(userAs1);
		userAs1.addToUnits(swordsman1);
		swordsman1.setSector(sector1);
		sector1.addToSectorUnits(swordsman1);
		swordsman1.setLevel(3);
		swordsman1.setMaxHP(10);
		swordsman1.setHp(10);
		
		knight1.setGame(newGame);
		newGame.addToSectorElement(knight1);
		knight1.setId("Knight@sg24");
		knight1.setUserAssets(userAs1);
		userAs1.addToUnits(knight1);
		knight1.setSector(sector1);
		sector1.addToSectorUnits(knight1);
		knight1.setLevel(2);
		knight1.setMaxHP(10);
		knight1.setHp(10);
		
		catapult.setGame(newGame);
		newGame.addToSectorElement(catapult);
		catapult.setId("Catapult@sdg34");
		catapult.setUserAssets(userAs1);
		userAs1.addToUnits(catapult);
		catapult.setSector(sector1);
		sector1.addToSectorUnits(catapult);
		catapult.setMaxHP(10);
		catapult.setHp(10);
		
		//for player 2 Peter
		peon4.setGame(newGame);
		newGame.addToSectorElement(peon4);
		peon4.setId("Peon@eg34");
		peon4.setUserAssets(userAs2);
		userAs2.addToUnits(peon4);
		peon4.setSector(sector5);
		sector5.addToSectorUnits(peon4);
		peon4.setMaxHP(10);
		peon4.setHp(10);
		
		peon5.setGame(newGame);
		newGame.addToSectorElement(peon5);
		peon5.setId("Peon@asdf23");
		peon5.setUserAssets(userAs2);
		userAs2.addToUnits(peon5);
		peon5.setSector(sector5);
		sector5.addToSectorUnits(peon5);
		peon5.setMaxHP(10);
		peon5.setHp(10);
		
		peon6.setGame(newGame);
		newGame.addToSectorElement(peon6);
		peon6.setId("Peon@saf43");
		peon6.setUserAssets(userAs2);
		userAs2.addToUnits(peon6);
		peon6.setSector(sector5);
		sector5.addToSectorUnits(peon6);
		peon6.setMaxHP(10);
		peon6.setHp(10);
		
		peon7.setGame(newGame);
		newGame.addToSectorElement(peon7);
		peon7.setId("Peon@asf23");
		peon7.setUserAssets(userAs2);
		userAs2.addToUnits(peon7);
		peon7.setSector(sector5);
		sector5.addToSectorUnits(peon7);
		peon7.setMaxHP(10);
		peon7.setHp(10);
		
		archer3.setGame(newGame);
		newGame.addToSectorElement(archer3);
		archer3.setId("Archer@asd345");
		archer3.setUserAssets(userAs1);
		userAs1.addToUnits(archer3);
		archer3.setSector(sector1);
		sector1.addToSectorUnits(archer3);
		archer3.setLevel(3);
		archer3.setMaxHP(10);
		archer3.setHp(10);
		
		
	//	archer3.setTower(tower2);
	//	tower2.addToArcher(archer3);
		
		archer4.setGame(newGame);
		archer4.setId("Archer@asd349");
		archer4.setUserAssets(userAs2);
		archer4.setSector(sector5);
		archer4.setLevel(2);
		archer4.setMaxHP(10);
		archer4.setHp(4);
		
		
		swordsman2.setGame(newGame);
		newGame.addToSectorElement(swordsman2);
		swordsman2.setId("Swordsman@da12e");
		swordsman2.setUserAssets(userAs2);
		userAs2.addToUnits(swordsman2);
		swordsman2.setSector(sector5);
		sector5.addToSectorUnits(swordsman2);
		swordsman2.setLevel(1);
		swordsman2.setMaxHP(10);
		swordsman2.setHp(10);
		
		swordsman3.setGame(newGame);
		newGame.addToSectorElement(swordsman3);
		swordsman3.setId("Swordsman@asd113");
		swordsman3.setUserAssets(userAs2);
		userAs2.addToUnits(swordsman3);
		swordsman3.setSector(sector5);
		sector5.addToSectorUnits(swordsman3);
		swordsman3.setLevel(2);
		swordsman3.setMaxHP(10);
		swordsman3.setHp(10);
		
		knight2.setGame(newGame);
		newGame.addToSectorElement(knight2);
		knight2.setId("Knight@21jd");
		knight2.setUserAssets(userAs2);
		userAs2.addToUnits(knight2);
		knight2.setSector(sector5);
		sector5.addToSectorUnits(knight2);
		knight2.setLevel(1);
		knight2.setMaxHP(10);
		knight2.setHp(10);
	}

	public CIClient getCiClient() {
		return ciClient;
	}

	public void setCiClient(CIClient ciClient) {
		this.ciClient = ciClient;
	}

}
