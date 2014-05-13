package model.game;

import java.util.*;
import de.upb.tools.fca.*;

public class Team{
   private String id;
   
   public void setId(String value) {
      this.id = value;
   }
   
   public String getId() {
      return this.id;
   }
   /**
    * <pre>
    *           0..n     1..1
    * Team ------------------------- Game
    *           team        &lt;       game
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
            oldValue.removeFromTeam(this);
         }
         this.game = value;
   
         if (value != null)
         {
            value.addToTeam(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public Game getGame ()	
   {
      return this.game;
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
    *           1..1     0..n
    * Team ------------------------- User
    *           team        &gt;       user
    * </pre>
    */
   
   private FHashSet user;
   
   public boolean addToUser (User value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.user == null)
         {
            this.user = new FHashSet ();
   
         }
         changed = this.user.add (value);
         if (changed)
         {
            value.setTeam(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromUser (User value)	
   {
      boolean changed = false;
   
      if ((this.user != null) && (value != null))
      {
         changed = this.user.remove (value);
         if (changed)
         {
            value.setTeam(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromUser ()
   {
   User tmpValue;
      Iterator iter = this.iteratorOfUser ();
   
      while (iter.hasNext ())
      {
         tmpValue = (User) iter.next ();
         this.removeFromUser (tmpValue);
      }
   }
   
   public boolean hasInUser (User value)
   {
      return ((this.user != null) &&
              (value != null) &&
              this.user.contains (value));
   }
   
   public Iterator iteratorOfUser ()
   {
      return ((this.user == null)
              ? FEmptyIterator.get ()
              : this.user.iterator ());
   
   }
   
   public int sizeOfUser ()
   {
      return ((this.user == null)
              ? 0
              : this.user.size ());
   }
   
   public void removeYou()
   {
         this.setGame (null);
         this.removeAllFromUser ();
      }
   
}
