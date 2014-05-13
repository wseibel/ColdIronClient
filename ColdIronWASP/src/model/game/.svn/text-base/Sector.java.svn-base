package model.game;

import java.util.*;
import de.upb.tools.fca.*;

public class Sector{
   private Integer yCoordinate;
   
   public void setYCoordinate(Integer value) {
      this.yCoordinate = value;
   }
   
   public Integer getYCoordinate() {
      return this.yCoordinate;
   }
   private Integer xCoordinate;
   
   public void setXCoordinate(Integer value) {
      this.xCoordinate = value;
   }
   
   public Integer getXCoordinate() {
      return this.xCoordinate;
   }
   private String id;
   
   public void setId(String value) {
      this.id = value;
   }
   
   public String getId() {
      return this.id;
   }
   
   private Boolean needsRefresh = false;

   public void setNeedsRefresh(Boolean needsRefresh) {
	   this.needsRefresh = needsRefresh;
   }

   public Boolean needsRefresh() {
	   return this.needsRefresh;
   }
   
   /**
    * <pre>
    *           0..n     1..1
    * Sector ------------------------- Map
    *           sector        &gt;       map
    * </pre>
    */
   
   private Map map;
   
   public boolean setMap (Map value)		
   {
      boolean changed = false;
   
      if (this.map != value)
      {
   Map oldValue = this.map;
         if (this.map != null)
         {
            this.map = null;
            oldValue.removeFromSector(this);
         }
         this.map = value;
   
         if (value != null)
         {
            value.addToSector(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public Map getMap ()	
   {
      return this.map;
   }
   /**
    * <pre>
    *           1..1     0..1
    * Sector ------------------------- UserAssets
    *           startSector        &gt;       startingUser
    * </pre>
    */
   
   private UserAssets startingUser;
   
   public boolean setStartingUser (UserAssets value)		
   {
      boolean changed = false;
   
      if (this.startingUser != value)
      {
   UserAssets oldValue = this.startingUser;
         if (this.startingUser != null)
         {
            this.startingUser = null;
            oldValue.setStartSector(null);
         }
         this.startingUser = value;
   
         if (value != null)
         {
            value.setStartSector(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public UserAssets getStartingUser ()	
   {
      return this.startingUser;
   }
   private boolean startSector;
   
   public void setStartSector(boolean value) {
      this.startSector = value;
   }
   
   public boolean isStartSector() {
      return this.startSector;
   }
   /**
    * <pre>
    *           0..n     0..1
    * Sector ------------------------- UserAssets
    *           sector        &lt;       userAssets
    * </pre>
    */
   
   private UserAssets userAssets;
   
   public boolean setUserAssets (UserAssets value)		
   {
      boolean changed = false;
   
      if (this.userAssets != value)
      {
   UserAssets oldValue = this.userAssets;
         if (this.userAssets != null)
         {
            this.userAssets = null;
            oldValue.removeFromSector(this);
         }
         this.userAssets = value;
   
         if (value != null)
         {
            value.addToSector(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public UserAssets getUserAssets ()	
   {
      return this.userAssets;
   }
   /**
    * <pre>
    *           0..n     1..1
    * Sector ------------------------- Game
    *           sector        &gt;       game
    * </pre>
    */
   
   private Game game;
   
   public boolean setGame (Game value)		
   {
      boolean changed = false;
   
      if (this.game != value)
      {
   Game oldValue = this.game;
         if (this.game != null)
         {
            this.game = null;
            oldValue.removeFromSector(this);
         }
         this.game = value;
   
         if (value != null)
         {
            value.addToSector(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public Game getGame ()	
   {
      return this.game;
   }
   /**
    * <pre>
    *           0..1     0..n
    * Sector ------------------------- Unit
    *           sector        &gt;       sectorUnits
    * </pre>
    */
   
   private FHashSet sectorUnits;
   
   public boolean addToSectorUnits (Unit value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.sectorUnits == null)
         {
            this.sectorUnits = new FHashSet ();
   
         }
         changed = this.sectorUnits.add (value);
         if (changed)
         {
            value.setSector(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromSectorUnits (Unit value)	
   {
      boolean changed = false;
   
      if ((this.sectorUnits != null) && (value != null))
      {
         changed = this.sectorUnits.remove (value);
         if (changed)
         {
            value.setSector(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromSectorUnits ()
   {
   Unit tmpValue;
      Iterator iter = this.iteratorOfSectorUnits ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Unit) iter.next ();
         this.removeFromSectorUnits (tmpValue);
      }
   }
   
   public boolean hasInSectorUnits (Unit value)
   {
      return ((this.sectorUnits != null) &&
              (value != null) &&
              this.sectorUnits.contains (value));
   }
   
   public Iterator iteratorOfSectorUnits ()
   {
      return ((this.sectorUnits == null)
              ? FEmptyIterator.get ()
              : this.sectorUnits.iterator ());
   
   }
   
   public int sizeOfSectorUnits ()
   {
      return ((this.sectorUnits == null)
              ? 0
              : this.sectorUnits.size ());
   }
   /**
    * <pre>
    *           0..1     0..n
    * Sector ------------------------- Building
    *           sector        &gt;       sectorBuildings
    * </pre>
    */
   
   private FHashSet sectorBuildings;
   
   public boolean addToSectorBuildings (Building value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.sectorBuildings == null)
         {
            this.sectorBuildings = new FHashSet ();
   
         }
         changed = this.sectorBuildings.add (value);
         if (changed)
         {
            value.setSector(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromSectorBuildings (Building value)	
   {
      boolean changed = false;
   
      if ((this.sectorBuildings != null) && (value != null))
      {
         changed = this.sectorBuildings.remove (value);
         if (changed)
         {
            value.setSector(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromSectorBuildings ()
   {
   Building tmpValue;
      Iterator iter = this.iteratorOfSectorBuildings ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Building) iter.next ();
         this.removeFromSectorBuildings (tmpValue);
      }
   }
   
   public boolean hasInSectorBuildings (Building value)
   {
      return ((this.sectorBuildings != null) &&
              (value != null) &&
              this.sectorBuildings.contains (value));
   }
   
   public Iterator iteratorOfSectorBuildings ()
   {
      return ((this.sectorBuildings == null)
              ? FEmptyIterator.get ()
              : this.sectorBuildings.iterator ());
   
   }
   
   public int sizeOfSectorBuildings ()
   {
      return ((this.sectorBuildings == null)
              ? 0
              : this.sectorBuildings.size ());
   }
   /**
    * <pre>
    *           0..1     0..n
    * Sector ------------------------- Resource
    *           sector        &gt;       sectorResources
    * </pre>
    */
   
   private FHashSet sectorResources;
   
   public boolean addToSectorResources (Resource value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.sectorResources == null)
         {
            this.sectorResources = new FHashSet ();
   
         }
         changed = this.sectorResources.add (value);
         if (changed)
         {
            value.setSector(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromSectorResources (Resource value)	
   {
      boolean changed = false;
   
      if ((this.sectorResources != null) && (value != null))
      {
         changed = this.sectorResources.remove (value);
         if (changed)
         {
            value.setSector(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromSectorResources ()
   {
   Resource tmpValue;
      Iterator iter = this.iteratorOfSectorResources ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Resource) iter.next ();
         this.removeFromSectorResources (tmpValue);
      }
   }
   
   public boolean hasInSectorResources (Resource value)
   {
      return ((this.sectorResources != null) &&
              (value != null) &&
              this.sectorResources.contains (value));
   }
   
   public Iterator iteratorOfSectorResources ()
   {
      return ((this.sectorResources == null)
              ? FEmptyIterator.get ()
              : this.sectorResources.iterator ());
   
   }
   
   public int sizeOfSectorResources ()
   {
      return ((this.sectorResources == null)
              ? 0
              : this.sectorResources.size ());
   }
   /**
    * <pre>
    *           0..1     1..1
    * Sector ------------------------- Game
    *           currentSector        &lt;       game1
    * </pre>
    */
   
   private Game game1;
   
   public boolean setGame1 (Game value)		
   {
      boolean changed = false;
   
      if (this.game1 != value)
      {
   Game oldValue = this.game1;
         if (this.game1 != null)
         {
            this.game1 = null;
            oldValue.setCurrentSector(null);
         }
         this.game1 = value;
   
         if (value != null)
         {
            value.setCurrentSector(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public Game getGame1 ()	
   {
      return this.game1;
   }
   /**
    * <pre>
    *           0..1     0..n
    * Sector ------------------------- Building
    *           sectorForRepairableBuilding        &lt;       repairableBuilding
    * </pre>
    */
   
   private FHashSet repairableBuilding;
   
   public boolean addToRepairableBuilding (Building value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.repairableBuilding == null)
         {
            this.repairableBuilding = new FHashSet ();
   
         }
         changed = this.repairableBuilding.add (value);
         if (changed)
         {
            value.setSectorForRepairableBuilding(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromRepairableBuilding (Building value)	
   {
      boolean changed = false;
   
      if ((this.repairableBuilding != null) && (value != null))
      {
         changed = this.repairableBuilding.remove (value);
         if (changed)
         {
            value.setSectorForRepairableBuilding(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromRepairableBuilding ()
   {
   Building tmpValue;
      Iterator iter = this.iteratorOfRepairableBuilding ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Building) iter.next ();
         this.removeFromRepairableBuilding (tmpValue);
      }
   }
   
   public boolean hasInRepairableBuilding (Building value)
   {
      return ((this.repairableBuilding != null) &&
              (value != null) &&
              this.repairableBuilding.contains (value));
   }
   
   public Iterator iteratorOfRepairableBuilding ()
   {
      return ((this.repairableBuilding == null)
              ? FEmptyIterator.get ()
              : this.repairableBuilding.iterator ());
   
   }
   
   public int sizeOfRepairableBuilding ()
   {
      return ((this.repairableBuilding == null)
              ? 0
              : this.repairableBuilding.size ());
   }
   /**
    * <pre>
    *           0..1     0..n
    * Sector ------------------------- Building
    *           sectorForConstructableBuilding        &gt;       constructableBuilding
    * </pre>
    */
   
   private FHashSet constructableBuilding;
   
   public boolean addToConstructableBuilding (Building value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.constructableBuilding == null)
         {
            this.constructableBuilding = new FHashSet ();
   
         }
         changed = this.constructableBuilding.add (value);
         if (changed)
         {
            value.setSectorForConstructableBuilding(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromConstructableBuilding (Building value)	
   {
      boolean changed = false;
   
      if ((this.constructableBuilding != null) && (value != null))
      {
         changed = this.constructableBuilding.remove (value);
         if (changed)
         {
            value.setSectorForConstructableBuilding(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromConstructableBuilding ()
   {
   Building tmpValue;
      Iterator iter = this.iteratorOfConstructableBuilding ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Building) iter.next ();
         this.removeFromConstructableBuilding (tmpValue);
      }
   }
   
   public boolean hasInConstructableBuilding (Building value)
   {
      return ((this.constructableBuilding != null) &&
              (value != null) &&
              this.constructableBuilding.contains (value));
   }
   
   public Iterator iteratorOfConstructableBuilding ()
   {
      return ((this.constructableBuilding == null)
              ? FEmptyIterator.get ()
              : this.constructableBuilding.iterator ());
   
   }
   
   public int sizeOfConstructableBuilding ()
   {
      return ((this.constructableBuilding == null)
              ? 0
              : this.constructableBuilding.size ());
   }
   /**
    * <pre>
    *           0..1     0..n
    * Sector ------------------------- Unit
    *           sectorForSelected        &gt;       selectedUnit
    * </pre>
    */
   
   private FHashSet selectedUnit;
   
   public boolean addToSelectedUnit (Unit value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.selectedUnit == null)
         {
            this.selectedUnit = new FHashSet ();
   
         }
         changed = this.selectedUnit.add (value);
         if (changed)
         {
            value.setSectorForSelected(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromSelectedUnit (Unit value)	
   {
      boolean changed = false;
   
      if ((this.selectedUnit != null) && (value != null))
      {
         changed = this.selectedUnit.remove (value);
         if (changed)
         {
            value.setSectorForSelected(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromSelectedUnit ()
   {
   Unit tmpValue;
      Iterator iter = this.iteratorOfSelectedUnit ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Unit) iter.next ();
         this.removeFromSelectedUnit (tmpValue);
      }
   }
   
   public boolean hasInSelectedUnit (Unit value)
   {
      return ((this.selectedUnit != null) &&
              (value != null) &&
              this.selectedUnit.contains (value));
   }
   
   public Iterator iteratorOfSelectedUnit ()
   {
      return ((this.selectedUnit == null)
              ? FEmptyIterator.get ()
              : this.selectedUnit.iterator ());
   
   }
   
   public int sizeOfSelectedUnit ()
   {
      return ((this.selectedUnit == null)
              ? 0
              : this.selectedUnit.size ());
   }
   
   public void removeYou()
   {
         this.setMap (null);
         this.setStartingUser (null);
         this.setUserAssets (null);
         this.setGame (null);
         this.removeAllFromSectorUnits ();
         this.removeAllFromSectorBuildings ();
         this.removeAllFromSectorResources ();
         this.setGame1 (null);
         this.removeAllFromRepairableBuilding ();
         this.removeAllFromConstructableBuilding ();
         this.removeAllFromSelectedUnit ();
      }

   public  FHashSet getUnits() {
	   return sectorUnits;
   }

   public  FHashSet getResources() {
	return sectorResources;
}

   public  FHashSet getBuildings() {
	   return sectorBuildings;
   }
   
}
