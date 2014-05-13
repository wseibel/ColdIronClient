package model.game;


public class Message{
   /**
    * <pre>
    *           0..n     0..1
    * Message ------------------------- User
    *           message        &lt;       owner
    * </pre>
    */
   
   private User owner;
   
   public boolean setOwner (User value)		
   {
      boolean changed = false;
   
      if (this.owner != value)
      {
   User oldValue = this.owner;
         if (this.owner != null)
         {
            this.owner = null;
            oldValue.removeFromMessage(this);
         }
         this.owner = value;
   
         if (value != null)
         {
            value.addToMessage(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public User getOwner ()	
   {
      return this.owner;
   }
   private String type;
   
   public void setType(String value) {
      this.type = value;
   }
   
   public String getType() {
      return this.type;
   }
   private String text;
   
   public void setText(String value) {
      this.text = value;
   }
   
   public String getText() {
      return this.text;
   }
   /**
    * <pre>
    *           0..n     1..1
    * Message ------------------------- Game
    *           message        &gt;       game
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
            oldValue.removeFromMessage(this);
         }
         this.game = value;
   
         if (value != null)
         {
            value.addToMessage(this);
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
         this.setOwner (null);
         this.setGame (null);
      }
   
}
