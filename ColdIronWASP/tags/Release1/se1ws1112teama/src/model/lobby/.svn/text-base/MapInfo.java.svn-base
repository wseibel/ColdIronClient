package model.lobby;

import model.game.CIClient;

/**
 * stores informations about a map that is available for creating games
 * 	 * on the server
 */
public class MapInfo{
   /**
    * <pre>
    *           0..n     0..1
    * MapInfo ------------------------- CIClient
    *           mapInfo        &gt;       cIClient
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
            oldValue.removeFromMapInfo(this);
         }
         this.cIClient = value;
   
         if (value != null)
         {
            value.addToMapInfo(this);
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
   private int sectorCount;
   
   public void setSectorCount(int value) {
      this.sectorCount = value;
   }
   
   public int getSectorCount() {
      return this.sectorCount;
   }
   
   public void removeYou()
   {
         this.setCIClient (null);
      }
   
}
