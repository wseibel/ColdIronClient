package model.game;

import java.util.*;
import de.upb.tools.fca.*;

public class User{
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
    * User ------------------------- Team
    *           user        &lt;       team
    * </pre>
    */
   
   private Team team;
   
   public boolean setTeam (Team value)		
   {
      boolean changed = false;
   
      if (this.team != value)
      {
   Team oldValue = this.team;
         if (this.team != null)
         {
            this.team = null;
            oldValue.removeFromUser(this);
         }
         this.team = value;
   
         if (value != null)
         {
            value.addToUser(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public Team getTeam ()	
   {
      return this.team;
   }
   /**
    * <pre>
    *           0..1     0..1
    * User ------------------------- UserAssets
    *           user        &gt;       userAssets
    * </pre>
    */
   
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
            oldValue.setUser(null);
         }
         this.userAssets = value;
   
         if (value != null)
         {
            value.setUser(this);
         }
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
    * User ------------------------- Message
    *           owner        &gt;       message
    * </pre>
    */
   
   private FHashSet message;
   
   public boolean addToMessage (Message value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.message == null)
         {
            this.message = new FHashSet ();
   
         }
         changed = this.message.add (value);
         if (changed)
         {
            value.setOwner(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromMessage (Message value)	
   {
      boolean changed = false;
   
      if ((this.message != null) && (value != null))
      {
         changed = this.message.remove (value);
         if (changed)
         {
            value.setOwner(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromMessage ()
   {
   Message tmpValue;
      Iterator iter = this.iteratorOfMessage ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Message) iter.next ();
         this.removeFromMessage (tmpValue);
      }
   }
   
   public boolean hasInMessage (Message value)
   {
      return ((this.message != null) &&
              (value != null) &&
              this.message.contains (value));
   }
   
   public Iterator iteratorOfMessage ()
   {
      return ((this.message == null)
              ? FEmptyIterator.get ()
              : this.message.iterator ());
   
   }
   
   public int sizeOfMessage ()
   {
      return ((this.message == null)
              ? 0
              : this.message.size ());
   }
   /**
    * <pre>
    *           0..n     1..1
    * User ------------------------- Game
    *           user        &lt;       game
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
            oldValue.removeFromUser(this);
         }
         this.game = value;
   
         if (value != null)
         {
            value.addToUser(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public Game getGame ()	
   {
      return this.game;
   }
   private String nickname;
   
   public void setNickname(String value) {
      this.nickname = value;
   }
   
   public String getNickname() {
      return this.nickname;
   }
   private Boolean startUser;
   
   public void setStartUser(Boolean value) {
      this.startUser = value;
   }
   
   public Boolean getStartUser() {
      return this.startUser;
   }
   /**
    * <pre>
    *           0..1     0..1
    * User ------------------------- Game
    *           userWinner        &lt;       gameForWinner
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
            oldValue.setUserWinner(null);
         }
         this.gameForWinner = value;
   
         if (value != null)
         {
            value.setUserWinner(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public Game getGameForWinner ()	
   {
      return this.gameForWinner;
   }
   
   public void removeYou()
   {
         this.setTeam (null);
         this.setUserAssets (null);
         this.removeAllFromMessage ();
         this.setGame (null);
         this.setGameForWinner (null);
      }
   
}
