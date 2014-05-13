package model.game;

import de.upb.tools.pcs.PropertyChangeClient;
import de.upb.tools.sdm.*;
import java.util.*;

import de.upb.tools.fca.*;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class UserAssets implements PropertyChangeClient{
   /**
    * <pre>
    *           0..1     0..1
    * UserAssets ------------------------- User
    *           userAssets        &lt;       user
    * </pre>
    */
   
   public static final String PROPERTY_USER = "user";
   private User user;
   
   public boolean setUser (User value)		
   {
      boolean changed = false;
   
      if (this.user != value)
      {
   User oldValue = this.user;
         if (this.user != null)
         {
            this.user = null;
            oldValue.setUserAssets(null);
         }
         this.user = value;
   
         if (value != null)
         {
            value.setUserAssets(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_USER, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public User getUser ()	
   {
      return this.user;
   }
   public static final String PROPERTY_ID = "id";
   
   private String id;
   
   public void setId(String value) {
      if ( JavaSDM.stringCompare (this.id, value) != 0 )
      {
      String oldValue = this.id;
      this.id = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ID, oldValue, value);
      }
   }
   
   public String getId() {
      return this.id;
   }
   public static final String PROPERTY_COLOR = "color";
   
   private String color;
   
   public void setColor(String value) {
      if ( JavaSDM.stringCompare (this.color, value) != 0 )
      {
      String oldValue = this.color;
      this.color = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_COLOR, oldValue, value);
      }
   }
   
   public String getColor() {
      return this.color;
   }
   /**
    * <pre>
    *           0..1     1..1
    * UserAssets ------------------------- Sector
    *           startingUser        &lt;       startSector
    * </pre>
    */
   
   public static final String PROPERTY_START_SECTOR = "startSector";
   private Sector startSector;
   
   public boolean setStartSector (Sector value)		
   {
      boolean changed = false;
   
      if (this.startSector != value)
      {
   Sector oldValue = this.startSector;
         if (this.startSector != null)
         {
            this.startSector = null;
            oldValue.setStartingUser(null);
         }
         this.startSector = value;
   
         if (value != null)
         {
            value.setStartingUser(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_START_SECTOR, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public Sector getStartSector ()	
   {
      return this.startSector;
   }
   /**
    * <pre>
    *           0..n     1..1
    * UserAssets ------------------------- Game
    *           userAssets        &gt;       game
    * </pre>
    */
   
   public static final String PROPERTY_GAME = "game";
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
            oldValue.removeFromUserAssets(this);
         }
         this.game = value;
   
         if (value != null)
         {
            value.addToUserAssets(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_GAME, oldValue, value);
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
    * UserAssets ------------------------- Sector
    *           userAssets        &gt;       sector
    * </pre>
    */
   
   public static final String PROPERTY_SECTOR = "sector";
   private FPropHashSet sector;
   
   public boolean addToSector (Sector value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.sector == null)
         {
            this.sector = new FPropHashSet (this, PROPERTY_SECTOR);
   
         }
         changed = this.sector.add (value);
         if (changed)
         {
            value.setUserAssets(this);
            
            listeners.firePropertyChange(PROPERTY_SECTOR, null, value);            
            
         }
      }
      return changed;
   }
   
   public boolean removeFromSector (Sector value)	
   {
      boolean changed = false;
   
      if ((this.sector != null) && (value != null))
      {
         changed = this.sector.remove (value);
         if (changed)
         {
            value.setUserAssets(null);

            listeners.firePropertyChange(PROPERTY_SECTOR, value, null);            
            
         }
      }
      return changed;
   }
   
   public void removeAllFromSector ()
   {
   Sector tmpValue;
      Iterator iter = this.iteratorOfSector ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Sector) iter.next ();
         this.removeFromSector (tmpValue);
      }
   }
   
   public boolean hasInSector (Sector value)
   {
      return ((this.sector != null) &&
              (value != null) &&
              this.sector.contains (value));
   }
   
   public Iterator iteratorOfSector ()
   {
      return ((this.sector == null)
              ? FEmptyIterator.get ()
              : this.sector.iterator ());
   
   }
   
   public int sizeOfSector ()
   {
      return ((this.sector == null)
              ? 0
              : this.sector.size ());
   }
   /**
    * <pre>
    *           0..n     0..1
    * UserAssets ------------------------- Alliance
    *           userAssets        &lt;       alliance
    * </pre>
    */
   
   public static final String PROPERTY_ALLIANCE = "alliance";
   private Alliance alliance;
   
   public boolean setAlliance (Alliance value)		
   {
      boolean changed = false;
   
      if (this.alliance != value)
      {
   Alliance oldValue = this.alliance;
         if (this.alliance != null)
         {
            this.alliance = null;
            oldValue.removeFromUserAssets(this);
         }
         this.alliance = value;
   
         if (value != null)
         {
            value.addToUserAssets(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ALLIANCE, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public Alliance getAlliance ()	
   {
      return this.alliance;
   }
   /**
    * <pre>
    *           0..1     0..n
    * UserAssets ------------------------- Building
    *           userAssets        &lt;       buildings
    * </pre>
    */
   
   public static final String PROPERTY_BUILDINGS = "buildings";
   private FPropHashSet buildings;
   
   public boolean addToBuildings (Building value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.buildings == null)
         {
            this.buildings = new FPropHashSet (this, PROPERTY_BUILDINGS);
   
         }
         changed = this.buildings.add (value);
         if (changed)
         {
            value.setUserAssets(this);

            listeners.firePropertyChange(PROPERTY_BUILDINGS, null, value);            
            
         }
      }
      return changed;
   }
   
   public boolean removeFromBuildings (Building value)	
   {
      boolean changed = false;
   
      if ((this.buildings != null) && (value != null))
      {
         changed = this.buildings.remove (value);
         if (changed)
         {
            value.setUserAssets(null);

            listeners.firePropertyChange(PROPERTY_BUILDINGS, value, null);            
            
         }
      }
      return changed;
   }
   
   public void removeAllFromBuildings ()
   {
   Building tmpValue;
      Iterator iter = this.iteratorOfBuildings ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Building) iter.next ();
         this.removeFromBuildings (tmpValue);
      }
   }
   
   public boolean hasInBuildings (Building value)
   {
      return ((this.buildings != null) &&
              (value != null) &&
              this.buildings.contains (value));
   }
   
   public Iterator iteratorOfBuildings ()
   {
      return ((this.buildings == null)
              ? FEmptyIterator.get ()
              : this.buildings.iterator ());
   
   }
   
   public int sizeOfBuildings ()
   {
      return ((this.buildings == null)
              ? 0
              : this.buildings.size ());
   }
   /**
    * <pre>
    *           0..1     0..n
    * UserAssets ------------------------- Unit
    *           userAssets        &lt;       units
    * </pre>
    */
   
   public static final String PROPERTY_UNITS = "units";
   private FPropHashSet units;
   
   public boolean addToUnits (Unit value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.units == null)
         {
            this.units = new FPropHashSet (this, PROPERTY_UNITS);
   
         }
         changed = this.units.add (value);
         if (changed)
         {
            value.setUserAssets(this);

            listeners.firePropertyChange(PROPERTY_UNITS, null, value);            
            
         }
      }
      return changed;
   }
   
   public boolean removeFromUnits (Unit value)	
   {
      boolean changed = false;
   
      if ((this.units != null) && (value != null))
      {
         changed = this.units.remove (value);
         if (changed)
         {
            value.setUserAssets(null);

            listeners.firePropertyChange(PROPERTY_UNITS, value, null);            
            
         }
      }
      return changed;
   }
   
   public void removeAllFromUnits ()
   {
   Unit tmpValue;
      Iterator iter = this.iteratorOfUnits ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Unit) iter.next ();
         this.removeFromUnits (tmpValue);
      }
   }
   
   public boolean hasInUnits (Unit value)
   {
      return ((this.units != null) &&
              (value != null) &&
              this.units.contains (value));
   }
   
   public Iterator iteratorOfUnits ()
   {
      return ((this.units == null)
              ? FEmptyIterator.get ()
              : this.units.iterator ());
   
   }
   
   public int sizeOfUnits ()
   {
      return ((this.units == null)
              ? 0
              : this.units.size ());
   }
   /**
    * <pre>
    *           0..1     0..n
    * UserAssets ------------------------- Resource
    *           userAssets        &gt;       collectedResources
    * </pre>
    */
   
   public static final String PROPERTY_COLLECTED_RESOURCES = "collectedResources";
   private FPropHashSet collectedResources;
   
   public boolean addToCollectedResources (Resource value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.collectedResources == null)
         {
            this.collectedResources = new FPropHashSet (this, PROPERTY_COLLECTED_RESOURCES);
   
         }
         changed = this.collectedResources.add (value);
         if (changed)
         {
            value.setUserAssets(this);

            listeners.firePropertyChange(PROPERTY_COLLECTED_RESOURCES, null, value);            
            
         }
      }
      return changed;
   }
   
   public boolean removeFromCollectedResources (Resource value)	
   {
      boolean changed = false;
   
      if ((this.collectedResources != null) && (value != null))
      {
         changed = this.collectedResources.remove (value);
         if (changed)
         {
            value.setUserAssets(null);

            listeners.firePropertyChange(PROPERTY_COLLECTED_RESOURCES, value, null); 
            
         }
      }
      return changed;
   }
   
   public void removeAllFromCollectedResources ()
   {
   Resource tmpValue;
      Iterator iter = this.iteratorOfCollectedResources ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Resource) iter.next ();
         this.removeFromCollectedResources (tmpValue);
      }
   }
   
   public boolean hasInCollectedResources (Resource value)
   {
      return ((this.collectedResources != null) &&
              (value != null) &&
              this.collectedResources.contains (value));
   }
   
   public Iterator iteratorOfCollectedResources ()
   {
      return ((this.collectedResources == null)
              ? FEmptyIterator.get ()
              : this.collectedResources.iterator ());
   
   }
   
   public int sizeOfCollectedResources ()
   {
      return ((this.collectedResources == null)
              ? 0
              : this.collectedResources.size ());
   }
   
   public void removeYou()
   {
         this.setUser (null);
         this.setStartSector (null);
         this.setGame (null);
         this.removeAllFromSector ();
         this.setAlliance (null);
         this.removeAllFromBuildings ();
         this.removeAllFromUnits ();
         this.removeAllFromCollectedResources ();
      }
      protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
      public void addPropertyChangeListener(PropertyChangeListener listener)
      {
         getPropertyChangeSupport().addPropertyChangeListener(listener);
      }
   
      public void removePropertyChangeListener(PropertyChangeListener listener)
      {
         getPropertyChangeSupport().removePropertyChangeListener(listener);
      }
   
      public void addPropertyChangeListener(String property, PropertyChangeListener listener)
      {
         getPropertyChangeSupport().addPropertyChangeListener(property, listener);
      }
   
      public void removePropertyChangeListener(String property, PropertyChangeListener listener)
      {
         getPropertyChangeSupport().removePropertyChangeListener(property, listener);
      }
   
      public PropertyChangeSupport getPropertyChangeSupport()
      {
         return listeners;
      }

	public FPropHashSet getUnits() {
		return units;
	}
   
   
}
