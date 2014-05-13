package model.game;

import java.util.*;
import de.upb.tools.fca.*;

public class ModelElementTable{
   /**
    * <pre>
    *           0..n     0..n
    * ModelElementTable ------------------------- ModelElement
    *           modelElementTable        &gt;       modelElement
    * </pre>
    */
   
   private FHashSet modelElement;
   
   public boolean addToModelElement (ModelElement value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.modelElement == null)
         {
            this.modelElement = new FHashSet ();
   
         }
         changed = this.modelElement.add (value);
         if (changed)
         {
            value.addToModelElementTable(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromModelElement (ModelElement value)	
   {
      boolean changed = false;
   
      if ((this.modelElement != null) && (value != null))
      {
         changed = this.modelElement.remove (value);
         if (changed)
         {
            value.removeFromModelElementTable(this);
         }
      }
      return changed;
   }
   
   public void removeAllFromModelElement ()
   {
   ModelElement tmpValue;
      Iterator iter = this.iteratorOfModelElement ();
   
      while (iter.hasNext ())
      {
         tmpValue = (ModelElement) iter.next ();
         this.removeFromModelElement (tmpValue);
      }
   }
   
   public boolean hasInModelElement (ModelElement value)
   {
      return ((this.modelElement != null) &&
              (value != null) &&
              this.modelElement.contains (value));
   }
   
   public Iterator iteratorOfModelElement ()
   {
      return ((this.modelElement == null)
              ? FEmptyIterator.get ()
              : this.modelElement.iterator ());
   
   }
   
   public int sizeOfModelElement ()
   {
      return ((this.modelElement == null)
              ? 0
              : this.modelElement.size ());
   }
   
   public void removeYou()
   {
         this.removeAllFromModelElement ();
      }
   
}
