package model.game;

import java.util.*;
import de.upb.tools.fca.*;

public class Alliance{
/**
    * <pre>
    *           0..1     0..1
    * Alliance ------------------------- Game
    *           allianceWinner        &lt;       gameForWinner
    * </pre>
    */
   
   private Game gameForWinner;
   
   public boolean setGameForWinner (Game value)		
   {
      boolean changed = false;
   
      if (this.gameForWinner != value)
      {
   Game oldValue = this.gameForWinner;
         if (this.gameForWinner != null)
         {
            this.gameForWinner = null;
            oldValue.setAllianceWinner(null);
         }
         this.gameForWinner = value;
   
         if (value != null)
         {
            value.setAllianceWinner(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public Game getGameForWinner ()	
   {
      return this.gameForWinner;
   }
private String color;
   
   public void setColor(String value) {
      this.color = value;
   }
   
   public String getColor() {
      return this.color;
   }
   private String name;
   
   public void setName(String value) {
      this.name = value;
   }
   
   public String getName() {
      return this.name;
   }
   /**
    * <pre>
    *           0..1     0..n
    * Alliance ------------------------- UserAssets
    *           alliance        &gt;       userAssets
    * </pre>
    */
   
   private FHashSet userAssets;
   
   public boolean addToUserAssets (UserAssets value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.userAssets == null)
         {
            this.userAssets = new FHashSet ();
   
         }
         changed = this.userAssets.add (value);
         if (changed)
         {
            value.setAlliance(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromUserAssets (UserAssets value)	
   {
      boolean changed = false;
   
      if ((this.userAssets != null) && (value != null))
      {
         changed = this.userAssets.remove (value);
         if (changed)
         {
            value.setAlliance(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromUserAssets ()
   {
   UserAssets tmpValue;
      Iterator iter = this.iteratorOfUserAssets ();
   
      while (iter.hasNext ())
      {
         tmpValue = (UserAssets) iter.next ();
         this.removeFromUserAssets (tmpValue);
      }
   }
   
   public boolean hasInUserAssets (UserAssets value)
   {
      return ((this.userAssets != null) &&
              (value != null) &&
              this.userAssets.contains (value));
   }
   
   public Iterator iteratorOfUserAssets ()
   {
      return ((this.userAssets == null)
              ? FEmptyIterator.get ()
              : this.userAssets.iterator ());
   
   }
   
   public int sizeOfUserAssets ()
   {
      return ((this.userAssets == null)
              ? 0
              : this.userAssets.size ());
   }
   /**
    * <pre>
    *           0..n     1..1
    * Alliance ------------------------- Game
    *           alliance        &lt;       game
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
            oldValue.removeFromAlliance(this);
         }
         this.game = value;
   
         if (value != null)
         {
            value.addToAlliance(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public Game getGame ()	
   {
      return this.game;
   }
   private String id;
   
   public void setId(String value) {
      this.id = value;
   }
   
   public String getId() {
      return this.id;
   }
   
   public void removeYou()
   {
   this.setGameForWinner (null);
         this.removeAllFromUserAssets ();
         this.setGame (null);
      }
   
}
