package model.game;

import java.util.*;
import de.upb.tools.fca.*;

public class Tower extends Building{
   /**
    * <pre>
    *           0..1     0..n
    * Tower ------------------------- Archer
    *           tower        &lt;       archer
    * </pre>
    */
   
   public static final String PROPERTY_ARCHER = "archer";
   private FPropHashSet archer;
   
   public boolean addToArcher (Archer value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.archer == null)
         {
            this.archer = new FPropHashSet (this, PROPERTY_ARCHER);
   
         }
         changed = this.archer.add (value);
         if (changed)
         {
            value.setTower(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromArcher (Archer value)	
   {
      boolean changed = false;
   
      if ((this.archer != null) && (value != null))
      {
         changed = this.archer.remove (value);
         if (changed)
         {
            value.setTower(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromArcher ()
   {
   Archer tmpValue;
      Iterator iter = this.iteratorOfArcher ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Archer) iter.next ();
         this.removeFromArcher (tmpValue);
      }
   }
   
   public boolean hasInArcher (Archer value)
   {
      return ((this.archer != null) &&
              (value != null) &&
              this.archer.contains (value));
   }
   
   public Iterator iteratorOfArcher ()
   {
      return ((this.archer == null)
              ? FEmptyIterator.get ()
              : this.archer.iterator ());
   
   }
   
   public int sizeOfArcher ()
   {
      return ((this.archer == null)
              ? 0
              : this.archer.size ());
   }
   
   public void removeYou()
   {
         this.removeAllFromArcher ();
         super.removeYou();
   }
   
}
