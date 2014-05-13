package controller.game.handler;

import model.game.SectorElement;

public abstract class PropertyHandler{
/**
    * <pre>
    *           0..n     1..1
    * PropertyHandler ------------------------- MessageHandler
    *           propertyHandler        &lt;       messageHandler
    * </pre>
    */
   
   private MessageHandler messageHandler;
   
   public boolean setMessageHandler (MessageHandler value)		
   {
      boolean changed = false;
   
      if (this.messageHandler != value)
      {
   MessageHandler oldValue = this.messageHandler;
         if (this.messageHandler != null)
         {
            this.messageHandler = null;
            oldValue.removeFromPropertyHandler(this);
         }
         this.messageHandler = value;
   
         if (value != null)
         {
            value.addToPropertyHandler(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public MessageHandler getMessageHandler ()	
   {
      return this.messageHandler;
   }
public abstract void setValue(String newValue, String oldValue, SectorElement sectorElement);
   
   
   public void removeYou()
   {
   this.setMessageHandler (null);
         }
   
}
