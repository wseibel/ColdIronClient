package ai.controlCenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;

import model.game.CIClient;
import model.game.Peon;
import model.game.Sector;
import model.game.Unit;
import model.game.User;
import model.game.UserAssets;
import ai.helper.AIHelper;
import ai.helper.AiModelAnalyzer;

public class ScoutMap {

	// The AI's strategy is manipulated by these values
	private static final int PEON_PUFFER = 0;
	private static final int RETRY_AFTER = 2;
	private static final boolean DEBUG = false;

	private CIClient ciClient;
	private AIHelper aiHelper;
	private AiModelAnalyzer aiModel;
	private ArrayList<visitedSector> visitedSectors = new ArrayList<visitedSector>();
	private ArrayList<ignoredSector> ignoreSectors = new ArrayList<ignoredSector>();
	private Map map;
	private String myUserAssetsID;
	private UserAssets userAssets;
	private LinkedList<Peon> freePeons = new LinkedList<Peon>();
	private LinkedList<Peon> allPeons;
	private scoutPeon scoutPeon;
	private long timeOfLastOrder = System.currentTimeMillis();
	private AIMain aiMain;

	public ScoutMap(CIClient ciClient, AIHelper aiHelper, AiModelAnalyzer aiModel) {
		this.ciClient = ciClient;
		this.aiHelper = aiHelper;
		this.aiModel = aiModel;

		Iterator<User> it = ciClient.getGame().iteratorOfUser();
		while (it.hasNext()) {
			User user = it.next();
			if (user.getUserAssets() != null && ciClient.getCurrentPlayer().equals(user.getNickname())) {
				this.myUserAssetsID = user.getUserAssets().getId();
				userAssets = user.getUserAssets();
			}
		}

		buildMap();

	}

	private void buildMap() {
		int maxX = 0;
		int maxY = 0;
		int sectorCount = 0;
		Iterator<Sector> iteratorOfSector = ciClient.getGame().iteratorOfSector();
		while (iteratorOfSector.hasNext()) {
			sectorCount++;
			Sector next = iteratorOfSector.next();
			if (next.getXCoordinate() > maxX) {
				maxX = next.getXCoordinate();
			}
			if (next.getYCoordinate() > maxY) {
				maxY = next.getYCoordinate();
			}
		}

		if (DEBUG) {
			System.out.println("maxX: " + maxX + ", maxY: " + maxY);
			System.out.println("sectorCount: " + sectorCount);
		}
		map = new Map(maxX, maxY);

		sectorCount = 0;
		iteratorOfSector = ciClient.getGame().iteratorOfSector();
		while (iteratorOfSector.hasNext()) {
			sectorCount++;
			Sector next = iteratorOfSector.next();
			if (DEBUG) {
				System.out.println(next.getXCoordinate() + "/" + next.getYCoordinate());
			}
			map.getMap()[next.getXCoordinate()][next.getYCoordinate()] = next;
		}
		if (DEBUG) {
			System.out.println("sectorCount: " + sectorCount);
		}
	}

	public void decideScoutMap(AIMain aiMain) {

		this.aiMain = aiMain;

		if (DEBUG) {
			// System.out.println("Timediff: " + (System.currentTimeMillis() -
			// timeOfLastOrder));
		}

		if (this.aiMain.needScouting || (scoutPeon != null && ((System.currentTimeMillis() - timeOfLastOrder) > (aiMain.OrderInterval)))
				|| ((System.currentTimeMillis() - timeOfLastOrder) > (aiMain.scoutInterval * aiMain.OrderInterval))
				|| ((System.currentTimeMillis() - timeOfLastOrder) > aiMain.firstScout)) {

			timeOfLastOrder = System.currentTimeMillis();
			aiMain.firstScout = 999999999;

			if (scoutPeon != null && freePeons.contains(scoutPeon.getPeon())) {
				// Peon alive and free
				if (DEBUG) {
					System.out.println("scoutPean alive and free");
				}
				moveToNextSector();
			} else {
				// scoutPeon dead or no scoutPeon choosen
				if (DEBUG) {
					System.out.println("scoutPeon dead or no scoutPeon choosen");
				}

				freePeons = new LinkedList<Peon>();
				allPeons = new LinkedList<Peon>();

				Iterator<Unit> iteratorOfUnits = userAssets.iteratorOfUnits();
				// collecting all free peons
				while (iteratorOfUnits.hasNext()) {
					Unit next = iteratorOfUnits.next();
					if (next instanceof Peon) {
						Peon tempPeon = (Peon) next;
						allPeons.add(tempPeon);
						if (tempPeon.getWorkingOn() == null && tempPeon.getCollecting() == null) {
							freePeons.add(tempPeon);
						}
					}
				}

				if (aiMain.lastMovedPeon != null) {
					scoutPeon = new scoutPeon(aiMain.lastMovedPeon);
				} else if (freePeons.size() > PEON_PUFFER) {
					// set first free Peon as new scoutPeon
					scoutPeon = new scoutPeon(freePeons.get(0));
				} else if (allPeons.size() > 0) {
					scoutPeon = new scoutPeon(allPeons.get(0));
				}
				if(scoutPeon != null) {
				scoutPeon.getPeon().SetIsScouting(true);
					visitedSectors = new ArrayList<visitedSector>();
					if (scoutPeon.getPeon().getCollecting() != null) {
						System.out.println(scoutPeon.getPeon().getCollecting().getSector());
						visitedSector visitedSector = new visitedSector(scoutPeon.getPeon().getCollecting().getSector(), null);
						visitedSectors.add(visitedSector);
					} else if (scoutPeon.getPeon().getSector() != null) {
						System.out.println(scoutPeon.getPeon().getSector());
						visitedSector visitedSector = new visitedSector(scoutPeon.getPeon().getSector(), null);
						visitedSectors.add(visitedSector);
					}
					if (scoutPeon.getPeon() != null) {
						if (DEBUG) {
							System.out.println("New scoutPeon found");
						}
					}
					moveToNextSector();
				}
			}

		}

	}

	private void moveToNextSector() {

		if (scoutPeon.getPeon().getHp() < scoutPeon.getPeonOldHp()) {
			ignoreSectors.add(new ignoredSector(scoutPeon.getPeon().getSector()));
			scoutPeon.setPeonOldHp(scoutPeon.getPeon().getHp());
		}

		Sector currentSector = new Sector();
		if (scoutPeon.getPeon().getCollecting() != null) {
			currentSector = scoutPeon.getPeon().getCollecting().getSector();
		} else if (scoutPeon.getPeon().getSector() != null) {
			currentSector = scoutPeon.getPeon().getSector();
		}

		Sector topNeighbor = map.getTopNeighbor(currentSector);
		if (topNeighbor != null) {
			// has top neighbor

			boolean visited = false;
			Iterator<visitedSector> iterator = visitedSectors.iterator();
			while (iterator.hasNext()) {
				visitedSector next = iterator.next();
				if (next.getSector() == topNeighbor) {
					visited = true;
				}
			}

			if (!visited) {
				// neighbor not visited already
				if (DEBUG) {
					System.out.println("topNeighbor not visited");
				}

				ignoredSector ignored = null;
				Iterator<ignoredSector> iterator2 = ignoreSectors.iterator();
				while (iterator2.hasNext()) {
					ignoredSector next = iterator2.next();
					if (next.getSector() == topNeighbor) {
						ignored = next;
					}
				}

				if (ignored == null) {
					// neighbor not ignored
					if (DEBUG) {
						System.out.println("topNeighbor not ignored");
						System.out.println("move to topNeighbor");
					}
					ciClient.getServerConnection().move_units(scoutPeon.getPeon().getId(), topNeighbor.getId());
					visitedSectors.add(new visitedSector(topNeighbor, currentSector));
					return;
				} else {
					if (DEBUG) {
						System.out.println("topNeighbor ignored");
					}
					ignored.setIgnoredCount(ignored.getIgnoredCount() + 1);
					if (ignored.getIgnoredCount() >= RETRY_AFTER) {
						if (DEBUG) {
							System.out.println("topNeighbor removed from ignoreSectors");
						}
						ignoreSectors.remove(ignored);
					}
				}
			} else {
				if (DEBUG) {
					System.out.println("topNeighbor already visited");
				}
			}
		} else {
			if (DEBUG) {
				System.out.println("no top neighbor - check next");
			}
		}

		Sector rightNeighbor = map.getRightNeighbor(currentSector);
		if (rightNeighbor != null) {
			// has right neighbor

			boolean visited = false;
			Iterator<visitedSector> iterator = visitedSectors.iterator();
			while (iterator.hasNext()) {
				visitedSector next = iterator.next();
				if (next.getSector() == rightNeighbor) {
					visited = true;
				}
			}

			if (!visited) {
				// neighbor not visited already
				if (DEBUG) {
					System.out.println("rightNeighbor not visited");
				}

				ignoredSector ignored = null;
				Iterator<ignoredSector> iterator2 = ignoreSectors.iterator();
				while (iterator2.hasNext()) {
					ignoredSector next = iterator2.next();
					if (next.getSector() == rightNeighbor) {
						ignored = next;
					}
				}

				if (ignored == null) {
					// neighbor not ignored
					if (DEBUG) {
						System.out.println("rightNeighbor not ignored");
						System.out.println("move to rightNeighbor");
					}
					ciClient.getServerConnection().move_units(scoutPeon.getPeon().getId(), rightNeighbor.getId());
					visitedSectors.add(new visitedSector(rightNeighbor, currentSector));
					return;
				} else {
					if (DEBUG) {
						System.out.println("rightNeighbor ignored");
					}
					ignored.setIgnoredCount(ignored.getIgnoredCount() + 1);
					if (ignored.getIgnoredCount() >= RETRY_AFTER) {
						if (DEBUG) {
							System.out.println("rightNeighbor removed from ignoreSectors");
						}
						ignoreSectors.remove(ignored);
					}
				}
			} else {
				if (DEBUG) {
					System.out.println("rightNeighbor already visited");
				}
			}
		} else {
			if (DEBUG) {
				System.out.println("no right neighbor - check next");
			}
		}

		Sector bottomNeighbor = map.getBottomNeighbor(currentSector);
		if (bottomNeighbor != null) {
			// has bottom neighbor

			boolean visited = false;
			Iterator<visitedSector> iterator = visitedSectors.iterator();
			while (iterator.hasNext()) {
				visitedSector next = iterator.next();
				if (next.getSector() == bottomNeighbor) {
					visited = true;
				}
			}

			if (!visited) {
				// neighbor not visited already
				if (DEBUG) {
					System.out.println("bottomNeighbor not visited");
				}

				ignoredSector ignored = null;
				Iterator<ignoredSector> iterator2 = ignoreSectors.iterator();
				while (iterator2.hasNext()) {
					ignoredSector next = iterator2.next();
					if (next.getSector() == bottomNeighbor) {
						ignored = next;
					}
				}

				if (ignored == null) {
					// neighbor not ignored
					if (DEBUG) {
						System.out.println("bottomNeighbor not ignored");
						System.out.println("move to bottomNeighbor");
					}
					ciClient.getServerConnection().move_units(scoutPeon.getPeon().getId(), bottomNeighbor.getId());
					visitedSectors.add(new visitedSector(bottomNeighbor, currentSector));
					return;
				} else {
					if (DEBUG) {
						System.out.println("bottomNeighbor ignored");
					}
					ignored.setIgnoredCount(ignored.getIgnoredCount() + 1);
					if (ignored.getIgnoredCount() >= RETRY_AFTER) {
						if (DEBUG) {
							System.out.println("bottomNeighbor removed from ignoreSectors");
						}
						ignoreSectors.remove(ignored);
					}
				}
			} else {
				if (DEBUG) {
					System.out.println("bottomNeighbor already visited");
				}
			}
		} else {
			if (DEBUG) {
				System.out.println("no bottom neighbor - check next");
			}
		}

		Sector leftNeighbor = map.getLeftNeighbor(currentSector);
		if (leftNeighbor != null) {
			// has left neighbor

			boolean visited = false;
			Iterator<visitedSector> iterator = visitedSectors.iterator();
			while (iterator.hasNext()) {
				visitedSector next = iterator.next();
				if (next.getSector() == leftNeighbor) {
					visited = true;
				}
			}

			if (!visited) {
				// neighbor not visited already
				if (DEBUG) {
					System.out.println("leftNeighbor not visited");
				}

				ignoredSector ignored = null;
				Iterator<ignoredSector> iterator2 = ignoreSectors.iterator();
				while (iterator2.hasNext()) {
					ignoredSector next = iterator2.next();
					if (next.getSector() == leftNeighbor) {
						ignored = next;
					}
				}

				if (ignored == null) {
					// neighbor not ignored
					if (DEBUG) {
						System.out.println("leftNeighbor not ignored");
						System.out.println("move to leftNeighbor");
					}
					ciClient.getServerConnection().move_units(scoutPeon.getPeon().getId(), leftNeighbor.getId());
					visitedSectors.add(new visitedSector(leftNeighbor, currentSector));
					return;
				} else {
					if (DEBUG) {
						System.out.println("leftNeighbor ignored");
					}
					ignored.setIgnoredCount(ignored.getIgnoredCount() + 1);
					if (ignored.getIgnoredCount() >= RETRY_AFTER) {
						if (DEBUG) {
							System.out.println("leftNeighbor removed from ignoreSectors");
						}
						ignoreSectors.remove(ignored);
					}
				}
			} else {
				if (DEBUG) {
					System.out.println("leftNeighbor already visited");
				}
			}
		} else {
			if (DEBUG) {
				System.out.println("no left neighbor - go back");
			}
		}

		// dead-end street reached - go back one sector

		visitedSector tmpSector = null;
		Iterator<visitedSector> iterator = visitedSectors.iterator();
		while (iterator.hasNext()) {
			visitedSector next = iterator.next();
			if (next.getSector() == currentSector) {
				tmpSector = next;
			}
		}
		if (tmpSector != null && tmpSector.getCameFrom() != null) {
			ciClient.getServerConnection().move_units(scoutPeon.getPeon().getId(), tmpSector.getCameFrom().getId());
		} else {
			// scouting finished
			scoutPeon.getPeon().SetIsScouting(false);
			scoutPeon = null;
			aiMain.needScouting = false;
		}

	}

	private class Map {
		private Sector[][] map;

		public Sector[][] getMap() {
			return map;
		}

		public void setMap(Sector[][] map) {
			this.map = map;
		}

		public Sector getRightNeighbor(Sector sector) {
			try {
				if (map[sector.getXCoordinate() + 1][sector.getYCoordinate()] != null) {
					return map[sector.getXCoordinate() + 1][sector.getYCoordinate()];
				}
			} catch (Exception e) {
			}
			return null;
		}

		public Sector getLeftNeighbor(Sector sector) {
			try {
				if (map[sector.getXCoordinate() - 1][sector.getYCoordinate()] != null) {
					return map[sector.getXCoordinate() - 1][sector.getYCoordinate()];
				}
			} catch (Exception e) {
			}
			return null;
		}

		public Sector getTopNeighbor(Sector sector) {
			try {
				if (map[sector.getXCoordinate()][sector.getYCoordinate() - 1] != null) {
					return map[sector.getXCoordinate()][sector.getYCoordinate() - 1];
				}
			} catch (Exception e) {
			}
			return null;
		}

		public Sector getBottomNeighbor(Sector sector) {
			try {
				if (map[sector.getXCoordinate()][sector.getYCoordinate() + 1] != null) {
					return map[sector.getXCoordinate()][sector.getYCoordinate() + 1];
				}
			} catch (Exception e) {
			}
			return null;
		}

		public Map(int x, int y) {
			map = new Sector[x + 1][y + 1];
		}
	}

	private class scoutPeon {

		private Peon peon;
		private Integer peonOldHp;

		public Peon getPeon() {
			return peon;
		}

		public void setPeon(Peon peon) {
			this.peon = peon;
		}

		public Integer getPeonOldHp() {
			return peonOldHp;
		}

		public void setPeonOldHp(Integer peonOldHp) {
			this.peonOldHp = peonOldHp;
		}

		public scoutPeon(Peon peon) {
			this.peon = peon;
			this.peonOldHp = peon.getHp();
		}

	}

	private class visitedSector {
		private Sector sector;
		private Sector cameFrom;

		public Sector getSector() {
			return sector;
		}

		public void setSector(Sector sector) {
			this.sector = sector;
		}

		public Sector getCameFrom() {
			return cameFrom;
		}

		public void setCameFrom(Sector sector) {
			this.cameFrom = cameFrom;
		}

		public visitedSector(Sector secotr, Sector cameFrom) {
			this.sector = secotr;
			this.cameFrom = cameFrom;
		}
	}

	private class ignoredSector {
		private Sector sector;
		private int ignoredCount;

		public Sector getSector() {
			return sector;
		}

		public void setSector(Sector sector) {
			this.sector = sector;
		}

		public int getIgnoredCount() {
			return ignoredCount;
		}

		public void setIgnoredCount(int ignoredCount) {
			this.ignoredCount = ignoredCount;
		}

		public ignoredSector(Sector sector) {
			this.sector = sector;
			this.ignoredCount = 0;
		}
	}

}
