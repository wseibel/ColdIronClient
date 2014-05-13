package ai.helper;

/**
 * 
 * This class holds just the important values for every user assets in game (no
 * methods are allowed in this class, except getter, setter -> thats the class
 * you get the informations from). It has the same id the assigned user assets
 * have
 * 
 */
public class UserAssetsCounter {

	private String id;

	private Integer sectorsCount = 0;

	private Integer peonCount = 0;
	private Integer swordsmanLevel1Count = 0;
	private Integer swordsmanLevel2Count = 0;
	private Integer swordsmanLevel3Count = 0;
	private Integer archerLevel1Count = 0;
	private Integer archerLevel2Count = 0;
	private Integer archerLevel3Count = 0;
	private Integer knightLevel1Count = 0;
	private Integer knightLevel2Count = 0;
	private Integer knightLevel3Count = 0;
	private Integer catapultCount = 0;

	private Integer seriouslyHitPeonCount = 0;
	private Integer seriouslyHitSwordsmanLevel1Count = 0;
	private Integer seriouslyHitSwordsmanLevel2Count = 0;
	private Integer seriouslyHitSwordsmanLevel3Count = 0;
	private Integer seriouslyHitArcherLevel1Count = 0;
	private Integer seriouslyHitArcherLevel2Count = 0;
	private Integer seriouslyHitArcherLevel3Count = 0;
	private Integer seriouslyHitKnightLevel1Count = 0;
	private Integer seriouslyHitKnightLevel2Count = 0;
	private Integer seriouslyHitKnightLevel3Count = 0;
	private Integer seriouslyHitCatapultCount = 0;

	private Integer farmCount = 0;
	private Integer strongholdLevel1Count = 0;
	private Integer strongholdLevel2Count = 0;
	private Integer strongholdLevel3Count = 0;
	private Integer barrackLevel1Count = 0;
	private Integer barrackLevel2Count = 0;
	private Integer barrackLevel3Count = 0;
	private Integer towerLevel1Count = 0;
	private Integer towerLevel2Count = 0;
	private Integer towerLevel3Count = 0;
	private Integer forgeCount = 0;

	private Integer woodResourcesCount = 0;
	private Integer stoneResourcesCount = 0;
	private Integer ironResourcesCount = 0;

	public Integer getSeriouslyHitPeonCount() {
		return seriouslyHitPeonCount;
	}

	public void setSeriouslyHitPeonCount(Integer seriouslyHitPeonCount) {
		this.seriouslyHitPeonCount = seriouslyHitPeonCount;
	}

	public Integer getSeriouslyHitSwordsmanLevel1Count() {
		return seriouslyHitSwordsmanLevel1Count;
	}

	public void setSeriouslyHitSwordsmanLevel1Count(
			Integer seriouslyHitSwordsmanLevel1Count) {
		this.seriouslyHitSwordsmanLevel1Count = seriouslyHitSwordsmanLevel1Count;
	}

	public Integer getSeriouslyHitSwordsmanLevel2Count() {
		return seriouslyHitSwordsmanLevel2Count;
	}

	public void setSeriouslyHitSwordsmanLevel2Count(
			Integer seriouslyHitSwordsmanLevel2Count) {
		this.seriouslyHitSwordsmanLevel2Count = seriouslyHitSwordsmanLevel2Count;
	}

	public Integer getSeriouslyHitSwordsmanLevel3Count() {
		return seriouslyHitSwordsmanLevel3Count;
	}

	public void setSeriouslyHitSwordsmanLevel3Count(
			Integer seriouslyHitSwordsmanLevel3Count) {
		this.seriouslyHitSwordsmanLevel3Count = seriouslyHitSwordsmanLevel3Count;
	}

	public Integer getSeriouslyHitArcherLevel1Count() {
		return seriouslyHitArcherLevel1Count;
	}

	public void setSeriouslyHitArcherLevel1Count(
			Integer seriouslyHitArcherLevel1Count) {
		this.seriouslyHitArcherLevel1Count = seriouslyHitArcherLevel1Count;
	}

	public Integer getSeriouslyHitArcherLevel2Count() {
		return seriouslyHitArcherLevel2Count;
	}

	public void setSeriouslyHitArcherLevel2Count(
			Integer seriouslyHitArcherLevel2Count) {
		this.seriouslyHitArcherLevel2Count = seriouslyHitArcherLevel2Count;
	}

	public Integer getSeriouslyHitArcherLevel3Count() {
		return seriouslyHitArcherLevel3Count;
	}

	public void setSeriouslyHitArcherLevel3Count(
			Integer seriouslyHitArcherLevel3Count) {
		this.seriouslyHitArcherLevel3Count = seriouslyHitArcherLevel3Count;
	}

	public Integer getSeriouslyHitKnightLevel1Count() {
		return seriouslyHitKnightLevel1Count;
	}

	public void setSeriouslyHitKnightLevel1Count(
			Integer seriouslyHitKnightLevel1Count) {
		this.seriouslyHitKnightLevel1Count = seriouslyHitKnightLevel1Count;
	}

	public Integer getSeriouslyHitKnightLevel2Count() {
		return seriouslyHitKnightLevel2Count;
	}

	public void setSeriouslyHitKnightLevel2Count(
			Integer seriouslyHitKnightLevel2Count) {
		this.seriouslyHitKnightLevel2Count = seriouslyHitKnightLevel2Count;
	}

	public Integer getSeriouslyHitKnightLevel3Count() {
		return seriouslyHitKnightLevel3Count;
	}

	public void setSeriouslyHitKnightLevel3Count(
			Integer seriouslyHitKnightLevel3Count) {
		this.seriouslyHitKnightLevel3Count = seriouslyHitKnightLevel3Count;
	}

	public Integer getSeriouslyHitCatapultCount() {
		return seriouslyHitCatapultCount;
	}

	public void setSeriouslyHitCatapultCount(Integer seriouslyHitCatapultCount) {
		this.seriouslyHitCatapultCount = seriouslyHitCatapultCount;
	}

	public Integer getWoodResourcesCount() {
		return woodResourcesCount;
	}

	public void setWoodResourcesCount(Integer woodResourcesCount) {
		this.woodResourcesCount = woodResourcesCount;
	}

	public Integer getStoneResourcesCount() {
		return stoneResourcesCount;
	}

	public void setStoneResourcesCount(Integer stoneResourcesCount) {
		this.stoneResourcesCount = stoneResourcesCount;
	}

	public Integer getIronResourcesCount() {
		return ironResourcesCount;
	}

	public void setIronResourcesCount(Integer ironResourcesCount) {
		this.ironResourcesCount = ironResourcesCount;
	}

	public Integer getFarmCount() {
		return farmCount;
	}

	public void setFarmCount(Integer farmCount) {
		this.farmCount = farmCount;
	}

	public Integer getStrongholdLevel1Count() {
		return strongholdLevel1Count;
	}

	public void setStrongholdLevel1Count(Integer strongholdLevel1Count) {
		this.strongholdLevel1Count = strongholdLevel1Count;
	}

	public Integer getStrongholdLevel2Count() {
		return strongholdLevel2Count;
	}

	public void setStrongholdLevel2Count(Integer strongholdLevel2Count) {
		this.strongholdLevel2Count = strongholdLevel2Count;
	}

	public Integer getStrongholdLevel3Count() {
		return strongholdLevel3Count;
	}

	public void setStrongholdLevel3Count(Integer strongholdLevel3Count) {
		this.strongholdLevel3Count = strongholdLevel3Count;
	}

	public Integer getBarrackLevel1Count() {
		return barrackLevel1Count;
	}

	public void setBarrackLevel1Count(Integer barrackLevel1Count) {
		this.barrackLevel1Count = barrackLevel1Count;
	}

	public Integer getBarrackLevel2Count() {
		return barrackLevel2Count;
	}

	public void setBarrackLevel2Count(Integer barrackLevel2Count) {
		this.barrackLevel2Count = barrackLevel2Count;
	}

	public Integer getBarrackLevel3Count() {
		return barrackLevel3Count;
	}

	public void setBarrackLevel3Count(Integer barrackLevel3Count) {
		this.barrackLevel3Count = barrackLevel3Count;
	}

	public Integer getTowerLevel1Count() {
		return towerLevel1Count;
	}

	public void setTowerLevel1Count(Integer towerLevel1Count) {
		this.towerLevel1Count = towerLevel1Count;
	}

	public Integer getTowerLevel2Count() {
		return towerLevel2Count;
	}

	public void setTowerLevel2Count(Integer towerLevel2Count) {
		this.towerLevel2Count = towerLevel2Count;
	}

	public Integer getTowerLevel3Count() {
		return towerLevel3Count;
	}

	public void setTowerLevel3Count(Integer towerLevel3Count) {
		this.towerLevel3Count = towerLevel3Count;
	}

	public Integer getForgeCount() {
		return forgeCount;
	}

	public void setForgeCount(Integer forgeCount) {
		this.forgeCount = forgeCount;
	}

	public UserAssetsCounter(String id) {
		this.setId(id);
	}

	public Integer getPeonCount() {
		return peonCount;
	}

	public void setPeonCount(Integer peonCount) {
		this.peonCount = peonCount;
	}

	public Integer getSwordsmanLevel1Count() {
		return swordsmanLevel1Count;
	}

	public void setSwordsmanLevel1Count(Integer swordsmanLevel1Count) {
		this.swordsmanLevel1Count = swordsmanLevel1Count;
	}

	public Integer getSwordsmanLevel2Count() {
		return swordsmanLevel2Count;
	}

	public void setSwordsmanLevel2Count(Integer swordsmanLevel2Count) {
		this.swordsmanLevel2Count = swordsmanLevel2Count;
	}

	public Integer getSwordsmanLevel3Count() {
		return swordsmanLevel3Count;
	}

	public void setSwordsmanLevel3Count(Integer swordsmanLevel3Count) {
		this.swordsmanLevel3Count = swordsmanLevel3Count;
	}

	public Integer getArcherLevel1Count() {
		return archerLevel1Count;
	}

	public void setArcherLevel1Count(Integer archerLevel1Count) {
		this.archerLevel1Count = archerLevel1Count;
	}

	public Integer getArcherLevel2Count() {
		return archerLevel2Count;
	}

	public void setArcherLevel2Count(Integer archerLevel2Count) {
		this.archerLevel2Count = archerLevel2Count;
	}

	public Integer getArcherLevel3Count() {
		return archerLevel3Count;
	}

	public void setArcherLevel3Count(Integer archerLevel3Count) {
		this.archerLevel3Count = archerLevel3Count;
	}

	public Integer getKnightLevel1Count() {
		return knightLevel1Count;
	}

	public void setKnightLevel1Count(Integer knightLevel1Count) {
		this.knightLevel1Count = knightLevel1Count;
	}

	public Integer getKnightLevel2Count() {
		return knightLevel2Count;
	}

	public void setKnightLevel2Count(Integer knightLevel2Count) {
		this.knightLevel2Count = knightLevel2Count;
	}

	public Integer getKnightLevel3Count() {
		return knightLevel3Count;
	}

	public void setKnightLevel3Count(Integer knightLevel3Count) {
		this.knightLevel3Count = knightLevel3Count;
	}

	public Integer getCatapultCount() {
		return catapultCount;
	}

	public void setCatapultCount(Integer catapultCount) {
		this.catapultCount = catapultCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSectorsCount() {
		return sectorsCount;
	}

	public void setSectorsCount(Integer sectorsCount) {
		this.sectorsCount = sectorsCount;
	}

	public Integer getAllUnitsCount() {
		return peonCount + swordsmanLevel1Count + swordsmanLevel2Count
				+ swordsmanLevel3Count + archerLevel1Count + archerLevel2Count
				+ archerLevel3Count + knightLevel1Count + knightLevel2Count
				+ knightLevel3Count + catapultCount;
	}

	public Integer getAllBuildingsCount() {
		return farmCount + strongholdLevel1Count + strongholdLevel2Count
				+ strongholdLevel3Count + barrackLevel1Count
				+ barrackLevel2Count + barrackLevel3Count + towerLevel1Count
				+ towerLevel2Count + towerLevel3Count + forgeCount;
	}

}
