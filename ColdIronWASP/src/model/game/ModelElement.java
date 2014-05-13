package model.game;

import java.util.*;
import de.upb.tools.fca.*;

public class ModelElement{
   private String id;
   
   public void setId(String value) {
      this.id = value;
   }
   
   public String getId() {
      return this.id;
   }
   /**
    * <pre>
    *           0..n     0..n
    * ModelElement ------------------------- ModelElementTable
    *           modelElement        &lt;       modelElementTable
    * </pre>
    */
   
   private FHashSet modelElementTable;
   
   public boolean addToModelElementTable (ModelElementTable value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.modelElementTable == null)
         {
            this.modelElementTable = new FHashSet ();
   
         }
         changed = this.modelElementTable.add (value);
         if (changed)
         {
            value.addToModelElement(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromModelElementTable (ModelElementTable value)	
   {
      boolean changed = false;
   
      if ((this.modelElementTable != null) && (value != null))
      {
         changed = this.modelElementTable.remove (value);
         if (changed)
         {
            value.removeFromModelElement(this);
         }
      }
      return changed;
   }
   
   public void removeAllFromModelElementTable ()
   {
   ModelElementTable tmpValue;
      Iterator iter = this.iteratorOfModelElementTable ();
   
      while (iter.hasNext ())
      {
         tmpValue = (ModelElementTable) iter.next ();
         this.removeFromModelElementTable (tmpValue);
      }
   }
   
   public boolean hasInModelElementTable (ModelElementTable value)
   {
      return ((this.modelElementTable != null) &&
              (value != null) &&
              this.modelElementTable.contains (value));
   }
   
   public Iterator iteratorOfModelElementTable ()
   {
      return ((this.modelElementTable == null)
              ? FEmptyIterator.get ()
              : this.modelElementTable.iterator ());
   
   }
   
   public int sizeOfModelElementTable ()
   {
      return ((this.modelElementTable == null)
              ? 0
              : this.modelElementTable.size ());
   }
   
   public void removeYou()
   {
         this.removeAllFromModelElementTable ();
      }
   
}
