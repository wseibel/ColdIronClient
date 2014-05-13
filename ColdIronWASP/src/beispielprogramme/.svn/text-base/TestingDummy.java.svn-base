package beispielprogramme;

import java.util.ArrayList;
import java.util.Iterator;

import model.game.*;

public class TestingDummy {

	public static void showUnitsOfUsers(CIClient ciClient){
		
		Iterator<User> uIter = ciClient.getGame().iteratorOfUser();
		while(uIter.hasNext()){
			User player = uIter.next();
			Iterator<Unit> unitIter = player.getUserAssets().iteratorOfUnits();
					
			int countPeon = 0;
			int countArcherLv1 = 0;
			int countArcherLv2 = 0;
			int countArcherLv3 = 0;
			int countSwordsmanLv1 = 0;
			int countSwordsmanLv2 = 0;
			int countSwordsmanLv3 = 0;
			int countKnightLv1 = 0;
			int countKnightLv2 = 0;
			int countKnightLv3 = 0;
			int countCatapult = 0;

			while(unitIter.hasNext()){
				Unit unit = unitIter.next();
				
				String[] unitId = unit.getId().split("@");
				String id = unitId[0];
						
				if(id.equals("Peon")){
					countPeon++;
				}else if(id.equals("Catapult")){
					countCatapult++;
				}else if(id.equals("Archer") && unit.getLevel() == 1){
					countArcherLv1++;
				}else if(id.equals("Archer") && unit.getLevel() == 2){
					countArcherLv2++;
				}else if(id.equals("Archer") && unit.getLevel() == 3){
					countArcherLv3++;
				}else if(id.equals("Swordsman") && unit.getLevel() == 1){
					countSwordsmanLv1++;
				}else if(id.equals("Swordsman") && unit.getLevel() == 2){
					countSwordsmanLv2++;
				}else if(id.equals("Swordsman") && unit.getLevel() == 3){
					countSwordsmanLv3++;
				}else if(id.equals("Knight") && unit.getLevel() == 1){
					countKnightLv1++;
				}else if(id.equals("Knight") && unit.getLevel() == 2){
					countKnightLv2++;
				}else if(id.equals("Knight") && unit.getLevel() == 3){
					countKnightLv3++;
				}
						
				System.out.println("Einheiten vom Spieler " + player.getNickname() + ": " + id);
			}
			System.out.println("Peon: " + countPeon + " mal vorhanden");
			System.out.println("Catapult: " + countCatapult + " mal vorhanden");
			System.out.println("ArcherLv1: " + countArcherLv1 + " mal vorhanden");
			System.out.println("ArcherLv2: " + countArcherLv2 + " mal vorhanden");
			System.out.println("ArcherLv3: " + countArcherLv3 + " mal vorhanden");
			System.out.println("SwordsmanLv1: " + countSwordsmanLv1 + " mal vorhanden");
			System.out.println("SwordsmanLv2: " + countSwordsmanLv2 + " mal vorhanden");
			System.out.println("SwordsmanLv3: " + countSwordsmanLv3 + " mal vorhanden");
			System.out.println("KnightLv1: " + countKnightLv1 + " mal vorhanden");
			System.out.println("KnightLv2: " + countKnightLv2 + " mal vorhanden");
			System.out.println("KnightLv3: " + countKnightLv3 + " mal vorhanden");
			System.out.println("\n");
			
		}
	}
	
	public static void showBuildingsOfUsers(CIClient ciClient){
		
		Iterator<User> uIter = ciClient.getGame().iteratorOfUser();
		while(uIter.hasNext()){
			User player = uIter.next();
			Iterator<Building> bIter = player.getUserAssets().iteratorOfBuildings();
			
			int countFarm = 0;
			int countForge = 0;
			int countBarrackLv1 = 0;
			int countBarrackLv2 = 0;
			int countBarrackLv3 = 0;
			int countTowerLv1 = 0;
			int countTowerLv2 = 0;
			int countTowerLv3 = 0;

			//players buildings
			while(bIter.hasNext()){
				Building build = bIter.next();
				
				String[] buildId = build.getId().split("@");
				String id = buildId[0];

				if(id.equals("Farm")){
					countFarm++;
				}else if(id.equals("Forge")){
					countForge++;
				}else if(id.equals("Barrack") && build.getLevel() == 1){
					countBarrackLv1++;
				}else if(id.equals("Barrack") && build.getLevel() == 2){
					countBarrackLv2++;
				}else if(id.equals("Barrack") && build.getLevel() == 3){
					countBarrackLv3++;
				}else if(id.equals("Tower") && build.getLevel() == 1){
					countTowerLv1++;
				}else if(id.equals("Tower") && build.getLevel() == 2){
					countTowerLv2++;
				}else if(id.equals("Tower")&& build.getLevel() == 3){
					countTowerLv3++;
				}
				
				System.out.println("Gebäude vom Spieler " + player.getNickname() + ": " + id);
			}
			System.out.println("Farm: " + countFarm + " mal vorhanden");
			System.out.println("TowerLv1: " + countTowerLv1 + " mal vorhanden");
			System.out.println("TowerLv2: " + countTowerLv2 + " mal vorhanden");
			System.out.println("TowerLv3: " + countTowerLv3 + " mal vorhanden");
			System.out.println("BarrackLv1: " + countBarrackLv1 + " mal vorhanden");
			System.out.println("Forge: " + countForge + " mal vorhanden");
			System.out.println("");

		}
	}
	
	public static void showCurrentPlayer(CIClient ciClient){
		
		Iterator<User> userIter = ciClient.getGame().iteratorOfUser();
		while(userIter.hasNext()){
			User player = userIter.next();
			if(player.getStartUser() == true){
				System.out.println("\nAktueller Spieler: " + player.getNickname());
			}
		}
	}
	
	public static void showUsersOwnSector(CIClient ciClient){
		
		Iterator<User> uIter = ciClient.getGame().iteratorOfUser();
		while(uIter.hasNext()){
			User player = uIter.next();
			Iterator<Sector> pSectors= player.getUserAssets().iteratorOfSector();
			
			//players sectors
			while(pSectors.hasNext()){
				Sector actual = pSectors.next();
				System.out.println("Sektor vom Spieler " + player.getNickname() + ": " + actual.getId());
			}

		}
	}
	
	public static void showYourOwnSector(CIClient ciClient){
		
		Sector ownSector;
		
		Iterator<User> userIter = ciClient.getGame().iteratorOfUser();
		while(userIter.hasNext()){
			User player = userIter.next();
			if(player.getStartUser()){
				System.out.println("\nAktueller Spieler: " + player.getNickname());
				ownSector = player.getUserAssets().getStartSector();
				System.out.println("Eigener Sektor: " + ownSector.getId());
			}
		}
	}
	
	public static void showPlayersSectors(CIClient ciClient){
		
		Iterator<User> userIter = ciClient.getGame().iteratorOfUser();
		
		while(userIter.hasNext()){
			User player = userIter.next();
			
			System.out.println("Spieler: " + player.getNickname() + " besitzt Sektoren: ");
			
			Iterator<Sector> sectorIter = player.getUserAssets().iteratorOfSector();
			while(sectorIter.hasNext()){
				Sector sector = sectorIter.next();
				System.out.println(sector.getId());
			}
		}
		
	}
	
	public static void showAllFromSectors(CIClient ciClient){
		
		ArrayList<Sector> sectors = new ArrayList<Sector>();
		
		int amountOfSectors = 0;
		
		System.out.println("Game Name: " + ciClient.getGame().getName());
		System.out.println("Map Name: " + ciClient.getGame().getMap().getName());
		
		//method to count the sectors of a map
		if(ciClient.getGame().getMap().iteratorOfSector() != null){
			Iterator<Sector> sIter = ciClient.getGame().getMap().iteratorOfSector();
			while(sIter.hasNext()){
				sectors.add(sIter.next());
				amountOfSectors++;
			}
		}
		
		//method to get info from sector, how many res the sector has, or the buildings
		for(int i = 0; i < amountOfSectors; i++){
			Iterator<Resource> resIter = sectors.get(i).iteratorOfSectorResources();
			Iterator<Building> buildIter = sectors.get(i).iteratorOfSectorBuildings();
			Iterator<Unit> unitIter = sectors.get(i).iteratorOfSectorUnits();
		
			System.out.println("Sektor " + (i+1) + " ist Startsektor!? " + sectors.get(i).isStartSector());
			
			while(resIter.hasNext()){
				Resource res = resIter.next();
				System.out.println("Ressourcen von Sektor " + (i+1) + ": " + res.getType() + " Menge: " + res.getQuantity());
			}
			//here u can see the buildings of different sectors
			while(buildIter.hasNext()){
				Building build = buildIter.next();
				
				String[] buildId = build.getId().split("@");
				String id = buildId[0];
				
				System.out.println("Gebäude von Sektor " + (i+1) + ": " + id);
			}
			//here u can see the units of different sectors
			while(unitIter.hasNext()){
				Unit unit = unitIter.next();
				
				String[] unitId = unit.getId().split("@");
				String id = unitId[0];
				
				System.out.println("Einheiten von Sektor " + (i+1) + ": " + id);
			}
			System.out.println("");
		
		}
	}
	
	public static void main(String[] args) {


		CiDummy dummy = new CiDummy();
		dummy.generateMap();
		dummy.generateUsers();
		dummy.generateBuildings();
		dummy.generateUnits();
		CIClient ciClient = dummy.getCiClient();

		//showUnitsOfUsers(ciClient);
		//showBuildingsOfUsers(ciClient);
		//showCurrentPlayer(ciClient);
		//showAllFromSectors(ciClient);
		//showYourOwnSector(ciClient);
		showPlayersSectors(ciClient);
	}

}
