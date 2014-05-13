package model.lobby;
import model.game.CIClient;

/**
 * stores informations about a user who was online on the server
 * 	 *  (user might go offline, what wouldn`t be noticed here yet)
 */
public class PlayerInfo{
   /**
    * <pre>
    *           0..n     0..1
    * PlayerInfo ------------------------- CIClient
    *           playerInfo        &gt;       cIClient
    * </pre>
    */
   
   private CIClient cIClient;
   
   public boolean setCIClient (CIClient value)		
   {
      boolean changed = false;
   
      if (this.cIClient != value)
      {
   CIClient oldValue = this.cIClient;
         if (this.cIClient != null)
         {
            this.cIClient = null;
            oldValue.removeFromPlayerInfo(this);
         }
         this.cIClient = value;
   
         if (value != null)
         {
            value.addToPlayerInfo(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public CIClient getCIClient ()	
   {
      return this.cIClient;
   }
   private String name;
   
   public void setName(String value) {
      this.name = value;
   }
   
   public String getName() {
      return this.name;
   }
   private String team;
   
   public void setTeam(String value) {
      this.team = value;
   }
   
   public String getTeam() {
      return this.team;
   }
   private String status;
   
   public void setStatus(String value) {
      this.status = value;
   }
   
   public String getStatus() {
      return this.status;
   }
   
   public void removeYou()
   {
         this.setCIClient (null);
      }
   
}
