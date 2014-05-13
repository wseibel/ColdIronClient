package model.game;

import java.util.*;
import de.upb.tools.fca.*;

public class CompModelElement extends ModelElement{
   /**
    * <pre>
    *           0..1     0..n
    * CompModelElement ------------------------- ModelElement
    *           parent        &gt;       kids
    * </pre>
    */
   
   private FHashSet kids;
   
   public boolean addToKids (ModelElement value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.kids == null)
         {
            this.kids = new FHashSet ();
   
         }
         changed = this.kids.add (value);
         if (changed)
         {
            value.setParent(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromKids (ModelElement value)	
   {
      boolean changed = false;
   
      if ((this.kids != null) && (value != null))
      {
         changed = this.kids.remove (value);
         if (changed)
         {
            value.setParent(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromKids ()
   {
   ModelElement tmpValue;
      Iterator iter = this.iteratorOfKids ();
   
      while (iter.hasNext ())
      {
         tmpValue = (ModelElement) iter.next ();
         this.removeFromKids (tmpValue);
      }
   }
   
   public boolean hasInKids (ModelElement value)
   {
      return ((this.kids != null) &&
              (value != null) &&
              this.kids.contains (value));
   }
   
   public Iterator iteratorOfKids ()
   {
      return ((this.kids == null)
              ? FEmptyIterator.get ()
              : this.kids.iterator ());
   
   }
   
   public int sizeOfKids ()
   {
      return ((this.kids == null)
              ? 0
              : this.kids.size ());
   }
   
   public void removeYou()
   {
         this.removeAllFromKids ();
         super.removeYou();
   }
   
}
