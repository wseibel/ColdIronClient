package model.lobby;

import java.io.BufferedReader;

import model.game.CIClient;

/**
 * a thread that is permanently trying to receive JSON-messages and
 * passes them to their handlers or 
 * is blocking while there is nothing to receive
 */
public class JSONMessageReceiver extends Thread{
   /**
    * <pre>
    *           1..1     1..1
    * JSONMessageReceiver ------------------------- CIClient
    *           jSONMessageReceiver        &gt;       cIClient
    * </pre>
    */
   private CIClient cIClient;
   private BufferedReader bufferedReader;
   
   public void setCIClient(CIClient value) {
      this.cIClient = value;
   }
   
   public CIClient getCIClient() {
      return this.cIClient;
   }
   
   public JSONMessageReceiver (BufferedReader bufferedReader) {
	      this.bufferedReader = bufferedReader;
	      	}
   
   
   @Override
   public void run () 
   {
	   this.setName("JSONMessageReceiver-thread");
   }
   
   public void removeYou()
   {
   }
   
}
