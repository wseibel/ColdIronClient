package model.game;

import java.util.*;
import de.upb.tools.fca.*;

public class ModelElementTable{
   /**
    * <pre>
    *           0..1     0..1
    * ModelElementTable ------------------------- ModelElement
    *           modelElementTable        &gt;       modelElement
    * </pre>
    */
   
   private FHashMap modelElement;
   
   public boolean removeFromModelElement (ModelElement value)	
   {
      boolean changed = false;
   
      if (this.modelElement != null)
      {
         Iterator iter = this.entriesOfModelElement ();
         Map.Entry entry;
         while (iter.hasNext ())
         {
            entry = (Map.Entry) iter.next ();
            if (entry.getValue () == value)
            {
               if (this.removeFromModelElement ((String) entry.getKey (), value))
               {
                  changed = true;
               }
            }
         }
      }
      return changed;
   }
   
   public void removeAllFromModelElement ()
   {
      Iterator iter = entriesOfModelElement ();
      Map.Entry entry;
      while (iter.hasNext ())
      {
         entry = (Map.Entry) iter.next ();
         removeFromModelElement ((String) entry.getKey (), (ModelElement) entry.getValue ());
      }
   }
   
   public boolean hasInModelElement (ModelElement value)
   {
      return ((this.modelElement != null) &&
              this.modelElement.containsValue (value));
   }
   
   public Iterator iteratorOfModelElement ()
   {
      return ((this.modelElement == null)
              ? FEmptyIterator.get ()
              : this.modelElement.values ().iterator ());
   
   }
   
   public int sizeOfModelElement ()
   {
      return ((this.modelElement == null)
              ? 0
              : this.modelElement.size ());
   }
   
   public boolean hasKeyInModelElement (String key)
   {
      return ((this.modelElement != null) &&
              this.modelElement.containsKey (key));
   }
   
   public Iterator keysOfModelElement ()
   {
      return ((this.modelElement == null)
              ? FEmptyIterator.get ()
              : this.modelElement.keySet ().iterator ());
   }
   
   public Iterator entriesOfModelElement ()
   {
      return ((this.modelElement == null)
              ? FEmptyIterator.get ()
              : this.modelElement.entrySet ().iterator ());
   }
   
   public boolean hasInModelElement (String key, ModelElement value)
   {
      return ((this.modelElement != null) &&
              (value != null || this.modelElement.containsKey (key)) && 
              (this.modelElement.get (key) == value));
   }
   
   public boolean addToModelElement (String key, ModelElement value)
   {
      boolean changed = false;
   
      if (this.modelElement == null)
      {
         this.modelElement = new FHashMap ();
      }
   ModelElement oldValue = (ModelElement) this.modelElement.put (key, value);
      if (oldValue != value)
      {
         if (oldValue != null)
         {
            oldValue.setModelElementTable(null, null);
         }
         if (value != null)
         {
            value.setModelElementTable(key, this);
         }
         changed = true;
      }
      return changed;
   }
   
   public boolean addToModelElement (Map.Entry entry)
   {
      return addToModelElement ((String) entry.getKey (), (ModelElement) entry.getValue ());
   }
   
   public boolean removeFromModelElement (String key, ModelElement value)
   {
      boolean changed = false;
   
      if (this.modelElement != null)
      {
   ModelElement oldValue = (ModelElement) this.modelElement.get (key);
         if (oldValue == value && 
             (oldValue != null || this.modelElement.containsKey (key)))
         {
            this.modelElement.remove (key);
            if (value != null)
            {
               value.setModelElementTable(null, null);
            }
            changed = true;
         }
      }
      return changed;
   }
   
   public boolean removeKeyFromModelElement (String key)
   {
      boolean changed = false;
   
      if (this.modelElement != null)
      {
         changed = this.modelElement.containsKey (key);
         if (changed)
         {
   ModelElement tmpValue = (ModelElement) this.modelElement.remove (key);
            if (tmpValue != null)
            {
               tmpValue.setModelElementTable(null, null);
            }
         }
      }
      return changed;
   }
   public ModelElement getFromModelElement (String key)
   {
      return ((this.modelElement == null)
              ? null
              : (ModelElement) this.modelElement.get (key));
   }
   
   
   public void removeYou()
   {
         this.removeAllFromModelElement ();
      }
   
}
