package model.lobby;

import model.game.CIClient;

/**
 * stores informations about a game that is currently existing on the server
 */
public class GameInfo{
   /**
    * <pre>
    *           0..n     0..1
    * GameInfo ------------------------- CIClient
    *           gameInfo        &gt;       cIClient
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
            oldValue.removeFromGameInfo(this);
         }
         this.cIClient = value;
   
         if (value != null)
         {
            value.addToGameInfo(this);
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
   private int eventCount;
   
   public void setEventCount(int value) {
      this.eventCount = value;
   }
   
   public int getEventCount() {
      return this.eventCount;
   }
   private String mapName;
   
   public void setMapName(String value) {
      this.mapName = value;
   }
   
   public String getMapName() {
      return this.mapName;
   }
   private int sectorCount;
   
   public void setSectorCount(int value) {
      this.sectorCount = value;
   }
   
   public int getSectorCount() {
      return this.sectorCount;
   }
   private int joinedPlayerCount;
   
   public void setJoinedPlayerCount(int value) {
      this.joinedPlayerCount = value;
   }
   
   public int getJoinedPlayerCount() {
      return this.joinedPlayerCount;
   }
   private int maxPlayerCount;
   
   public void setMaxPlayerCount(int value) {
      this.maxPlayerCount = value;
   }
   
   public int getMaxPlayerCount() {
      return this.maxPlayerCount;
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
