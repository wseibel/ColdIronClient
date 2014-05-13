package model.game;

import de.upb.tools.pcs.PropertyChangeClient;
import de.upb.tools.sdm.*;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class SectorElement implements PropertyChangeClient{
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
   /**
    * <pre>
    *           0..n     1..1
    * SectorElement ------------------------- Game
    *           sectorElement        &lt;       game
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
            oldValue.removeFromSectorElement(this);
         }
         this.game = value;
   
         if (value != null)
         {
            value.addToSectorElement(this);
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
   
   public void removeYou()
   {
         this.setGame (null);
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
   
   
}
