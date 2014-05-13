package model.game;


public class Unit extends LevelHPElement {
   /**
    * <pre>
    *           1..1     1..1
    * Figure ------------------------> Game
    *           &lt;       game
    * </pre>
    */
/**
    * <pre>
    *           0..n     0..1
    * Unit ------------------------- Sector
    *           selectedUnit        &lt;       sectorForSelected
    * </pre>
    */
   
   public static final String PROPERTY_SECTOR_FOR_SELECTED = "sectorForSelected";
   private Sector sectorForSelected;
   
   public boolean setSectorForSelected (Sector value)		
   {
      boolean changed = false;
   
      if (this.sectorForSelected != value)
      {
   Sector oldValue = this.sectorForSelected;
         if (this.sectorForSelected != null)
         {
            this.sectorForSelected = null;
            oldValue.removeFromSelectedUnit(this);
         }
         this.sectorForSelected = value;
   
         if (value != null)
         {
            value.addToSelectedUnit(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SECTOR_FOR_SELECTED, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public Sector getSectorForSelected ()	
   {
      return this.sectorForSelected;
   }
/**
    * <pre>
    *           0..n     0..1
    * Unit ------------------------- Sector
    *           sectorUnits        &lt;       sector
    * </pre>
    */
   
   public static final String PROPERTY_SECTOR = "sector";
   private Sector sector;
   
   public boolean setSector (Sector value)		
   {
      boolean changed = false;
   
      if (this.sector != value)
      {
   Sector oldValue = this.sector;
         if (this.sector != null)
         {
            this.sector = null;
            oldValue.removeFromSectorUnits(this);
         }
         this.sector = value;
   
         if (value != null)
         {
            value.addToSectorUnits(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SECTOR, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public Sector getSector ()	
   {
      return this.sector;
   }
/**
    * <pre>
    *           0..n     0..1
    * Unit ------------------------- UserAssets
    *           units        &gt;       userAssets
    * </pre>
    */
   
   public static final String PROPERTY_USER_ASSETS = "userAssets";
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
            oldValue.removeFromUnits(this);
         }
         this.userAssets = value;
   
         if (value != null)
         {
            value.addToUnits(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_USER_ASSETS, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public UserAssets getUserAssets ()	
   {
      return this.userAssets;
   }
   
   public void removeYou()
   {
   this.setSectorForSelected (null);
   this.setSector (null);
      this.setUserAssets (null);
      super.removeYou();
}
public static final String PROPERTY_STRENGTH = "strength";
   
   
   private Integer strength;
   
   public void setStrength(Integer value) {
   if ( this.strength != value )
   {
Integer oldValue = this.strength;
      this.strength = value;
      getPropertyChangeSupport().firePropertyChange(PROPERTY_STRENGTH, oldValue, value);
   }
   }
   
   public Integer getStrength() {
	   return this.strength;
   }

   private Boolean isScouting = false;
   
   public Boolean isScouting() {
	   return isScouting;
   }
   
   public void SetIsScouting(Boolean isScouting) {
	   this.isScouting = isScouting;
   }
   
}


