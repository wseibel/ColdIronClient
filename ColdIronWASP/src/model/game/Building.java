package model.game;

import de.upb.tools.sdm.*;

import java.util.*;
import de.upb.tools.fca.*;

public class Building extends LevelHPElement {
public static final String PROPERTY_REPAIRABLE = "repairable";
   
   private boolean repairable;
   
   public void setRepairable(boolean value) {
      if ( this.repairable != value )
      {
      boolean oldValue = this.repairable;
      this.repairable = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_REPAIRABLE, oldValue, value);
      }
   }
   
   public boolean getRepairable() {
      return this.repairable;
   }
   public static final String PROPERTY_CONSTRUCTABLE = "constructable";
   
   private boolean constructable;
   
   public void setConstructable(boolean value) {
      if ( this.constructable != value )
      {
      boolean oldValue = this.constructable;
      this.constructable = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CONSTRUCTABLE, oldValue, value);
      }
   }
   
   public boolean getConstructable() {
      return this.constructable;
   }
/**
    * <pre>
    *           0..n     0..1
    * Building ------------------------- Sector
    *           repairableBuilding        &gt;       sectorForRepairableBuilding
    * </pre>
    */
   
   public static final String PROPERTY_SECTOR_FOR_REPAIRABLE_BUILDING = "sectorForRepairableBuilding";
   private Sector sectorForRepairableBuilding;
   
   public boolean setSectorForRepairableBuilding (Sector value)		
   {
      boolean changed = false;
   
      if (this.sectorForRepairableBuilding != value)
      {
   Sector oldValue = this.sectorForRepairableBuilding;
         if (this.sectorForRepairableBuilding != null)
         {
            this.sectorForRepairableBuilding = null;
            oldValue.removeFromRepairableBuilding(this);
         }
         this.sectorForRepairableBuilding = value;
   
         if (value != null)
         {
            value.addToRepairableBuilding(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SECTOR_FOR_REPAIRABLE_BUILDING, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public Sector getSectorForRepairableBuilding ()	
   {
      return this.sectorForRepairableBuilding;
   }
   /**
    * <pre>
    *           0..n     0..1
    * Building ------------------------- Sector
    *           constructableBuilding        &lt;       sectorForConstructableBuilding
    * </pre>
    */
   
   public static final String PROPERTY_SECTOR_FOR_CONSTRUCTABLE_BUILDING = "sectorForConstructableBuilding";
   private Sector sectorForConstructableBuilding;
   
   public boolean setSectorForConstructableBuilding (Sector value)		
   {
      boolean changed = false;
   
      if (this.sectorForConstructableBuilding != value)
      {
   Sector oldValue = this.sectorForConstructableBuilding;
         if (this.sectorForConstructableBuilding != null)
         {
            this.sectorForConstructableBuilding = null;
            oldValue.removeFromConstructableBuilding(this);
         }
         this.sectorForConstructableBuilding = value;
   
         if (value != null)
         {
            value.addToConstructableBuilding(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SECTOR_FOR_CONSTRUCTABLE_BUILDING, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public Sector getSectorForConstructableBuilding ()	
   {
      return this.sectorForConstructableBuilding;
   }
	/**
	 * <pre>
	 *           0..n     0..1
	 * Building ------------------------- Sector
	 *           sectorBuildings        &lt;       sector
	 * </pre>
	 */

	public static final String PROPERTY_SECTOR = "sector";
	private Sector sector;

	public boolean setSector(Sector value) {
		boolean changed = false;

		if (this.sector != value) {
			Sector oldValue = this.sector;
			if (this.sector != null) {
				this.sector = null;
				oldValue.removeFromSectorBuildings(this);
			}
			this.sector = value;

			if (value != null) {
				value.addToSectorBuildings(this);
			}
			getPropertyChangeSupport().firePropertyChange(PROPERTY_SECTOR,
					oldValue, value);
			changed = true;
		}
		return changed;
	}

	public Sector getSector() {
		return this.sector;
	}

	public static final String PROPERTY_UNIT_TYPE_IN_CREATION = "unitTypeInCreation";

	private String unitTypeInCreation;

	public void setUnitTypeInCreation(String value) {
		if (JavaSDM.stringCompare(this.unitTypeInCreation, value) != 0) {
			String oldValue = this.unitTypeInCreation;
			this.unitTypeInCreation = value;
			getPropertyChangeSupport().firePropertyChange(
					PROPERTY_UNIT_TYPE_IN_CREATION, oldValue, value);
		}
	}

	public String getUnitTypeInCreation() {
		return this.unitTypeInCreation;
	}

	public static final String PROPERTY_UNIT_CREATION_PROGRESS = "unitCreationProgress";

	private Double unitCreationProgress;

	public void setUnitCreationProgress(Double value) {
		if (this.unitCreationProgress != value) {
			Double oldValue = this.unitCreationProgress;
			this.unitCreationProgress = value;
			getPropertyChangeSupport().firePropertyChange(
					PROPERTY_UNIT_CREATION_PROGRESS, oldValue, value);
		}
	}

	public Double getUnitCreationProgress() {
		return this.unitCreationProgress;
	}

	public static final String PROPERTY_UNIT_LEVEL_IN_CREATION = "unitLevelInCreation";

	private Integer unitLevelInCreation;

	public void setUnitLevelInCreation(Integer value) {
		if (this.unitLevelInCreation != value) {
			Integer oldValue = this.unitLevelInCreation;
			this.unitLevelInCreation = value;
			getPropertyChangeSupport().firePropertyChange(
					PROPERTY_UNIT_LEVEL_IN_CREATION, oldValue, value);
		}
	}

	public Integer getUnitLevelInCreation() {
		return this.unitLevelInCreation;
	}

	/**
	 * <pre>
	 *           0..n     0..1
	 * Building ------------------------- UserAssets
	 *           buildings        &gt;       userAssets
	 * </pre>
	 */

	public static final String PROPERTY_USER_ASSETS = "userAssets";
	private UserAssets userAssets;

	public boolean setUserAssets(UserAssets value) {
		boolean changed = false;

		if (this.userAssets != value) {
			UserAssets oldValue = this.userAssets;
			if (this.userAssets != null) {
				this.userAssets = null;
				oldValue.removeFromBuildings(this);
			}
			this.userAssets = value;

			if (value != null) {
				value.addToBuildings(this);
			}
			getPropertyChangeSupport().firePropertyChange(PROPERTY_USER_ASSETS,
					oldValue, value);
			changed = true;
		}
		return changed;
	}

	public UserAssets getUserAssets() {
		return this.userAssets;
	}

	/**
	 * <pre>
	 *           0..1     0..n
	 * Building ------------------------- Peon
	 *           workingOn        &lt;       peon
	 * </pre>
	 */

	public static final String PROPERTY_PEON = "peon";
	private FPropHashSet peon;

	public boolean addToPeon(Peon value) {
		boolean changed = false;

		if (value != null) {
			if (this.peon == null) {
				this.peon = new FPropHashSet(this, PROPERTY_PEON);

			}
			changed = this.peon.add(value);
			if (changed) {
				value.setWorkingOn(this);
			}
		}
		return changed;
	}

	public boolean removeFromPeon(Peon value) {
		boolean changed = false;

		if ((this.peon != null) && (value != null)) {
			changed = this.peon.remove(value);
			if (changed) {
				value.setWorkingOn(null);
			}
		}
		return changed;
	}

	public void removeAllFromPeon() {
		Peon tmpValue;
		Iterator iter = this.iteratorOfPeon();

		while (iter.hasNext()) {
			tmpValue = (Peon) iter.next();
			this.removeFromPeon(tmpValue);
		}
	}

	public boolean hasInPeon(Peon value) {
		return ((this.peon != null) && (value != null) && this.peon
				.contains(value));
	}

	public Iterator iteratorOfPeon() {
		return ((this.peon == null) ? FEmptyIterator.get() : this.peon
				.iterator());

	}

	public int sizeOfPeon() {
		return ((this.peon == null) ? 0 : this.peon.size());
	}

	public static final String PROPERTY_BUILD_PROGRESS = "buildProgress";

	private Double buildProgress;

	public void setBuildProgress(Double value) {
		if (this.buildProgress != value) {
			Double oldValue = this.buildProgress;
			this.buildProgress = value;
			getPropertyChangeSupport().firePropertyChange(
					PROPERTY_BUILD_PROGRESS, oldValue, value);
		}
	}

	public Double getBuildProgress() {
		return this.buildProgress;
	}

	public void removeYou() {
   this.setSectorForRepairableBuilding (null);
      this.setSectorForConstructableBuilding (null);
		this.setSector(null);
		this.setUserAssets(null);
		this.removeAllFromPeon();
		super.removeYou();
	}

}
