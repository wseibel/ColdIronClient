/**
    * <pre>
    *           0..*     0..*
    * Resource ------------------------- CIClient
    *           resource        &gt;       cIClient
    * </pre>
    */package model.game;
import de.upb.tools.fca.*;

import java.util.*;
import de.upb.tools.sdm.*;

import java.util.Set;
import java.util.HashSet;

public class Resource extends SectorElement {
/**
    * <pre>
    *           0..n     0..1
    * Resource ------------------------- Sector
    *           sectorResources        &lt;       sector
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
            oldValue.removeFromSectorResources(this);
         }
         this.sector = value;
   
         if (value != null)
         {
            value.addToSectorResources(this);
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
    * Resource ------------------------- UserAssets
    *           collectedResources        &lt;       userAssets
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
            oldValue.removeFromCollectedResources(this);
         }
         this.userAssets = value;
   
         if (value != null)
         {
            value.addToCollectedResources(this);
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
/**
    * <pre>
    *           0..1     0..n
    * Resource ------------------------- Peon
    *           collecting        &lt;       peon
    * </pre>
    */
   
   public static final String PROPERTY_PEON = "peon";
   private FPropHashSet peon;
   
   public boolean addToPeon (Peon value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.peon == null)
         {
            this.peon = new FPropHashSet (this, PROPERTY_PEON);
   
         }
         changed = this.peon.add (value);
         if (changed)
         {
            value.setCollecting(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromPeon (Peon value)	
   {
      boolean changed = false;
   
      if ((this.peon != null) && (value != null))
      {
         changed = this.peon.remove (value);
         if (changed)
         {
            value.setCollecting(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromPeon ()
   {
   Peon tmpValue;
      Iterator iter = this.iteratorOfPeon ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Peon) iter.next ();
         this.removeFromPeon (tmpValue);
      }
   }
   
   public boolean hasInPeon (Peon value)
   {
      return ((this.peon != null) &&
              (value != null) &&
              this.peon.contains (value));
   }
   
   public Iterator iteratorOfPeon ()
   {
      return ((this.peon == null)
              ? FEmptyIterator.get ()
              : this.peon.iterator ());
   
   }
   
   public int sizeOfPeon ()
   {
      return ((this.peon == null)
              ? 0
              : this.peon.size ());
   }
   
   public void removeYou()
   {
   this.setSector (null);
      this.setUserAssets (null);
      this.removeAllFromPeon ();
      super.removeYou();
}
public static final String PROPERTY_TYPE = "type";
   
   public static final String PROPERTY_QUANTITY = "quantity";
   
   
   private String type;
   
   public void setType(String value) {
   if ( JavaSDM.stringCompare (this.type, value) != 0 )
   {
String oldValue = this.type;
      this.type = value;
      getPropertyChangeSupport().firePropertyChange(PROPERTY_TYPE, oldValue, value);
   }
   }
   
   public String getType() {
      return this.type;
   }
   
   private String quantity;
   
   public void setQuantity(String value) {
   if ( JavaSDM.stringCompare (this.quantity, value) != 0 )
   {
String oldValue = this.quantity;
      this.quantity = value;
      getPropertyChangeSupport().firePropertyChange(PROPERTY_QUANTITY, oldValue, value);
   }
   }
   
   public String getQuantity() {
      return this.quantity;
   }

   public  FPropHashSet getPeons() {
	return peon;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * Resource ------------------------>Sector
    *                   &lt;       sector
    * </pre>
    */
   
   }
