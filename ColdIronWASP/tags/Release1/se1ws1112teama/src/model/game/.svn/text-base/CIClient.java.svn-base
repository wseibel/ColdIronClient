package model.game;
import de.upb.tools.sdm.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import de.upb.tools.pcs.PropertyChangeClient;

import model.lobby.NonJSONMessageReceiver;
import model.lobby.ServerConnection;
import model.lobby.JSONMessageReceiver;
import model.lobby.PlayerInfo;
import java.util.*;
import de.upb.tools.fca.*;
import model.lobby.GameInfo;
import model.lobby.MapInfo;

public class CIClient implements PropertyChangeClient{
public static final String PROPERTY_NEW_RCV_CHAT_MESSAGE = "newRcvChatMessage";
   
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
   
   /**
    * <pre>
    *           1..1     1..1
    * CIClient ------------------------- NonJSONMessageReceiver
    *           cIClient        &lt;       nonJSONMessageReceiver
    * </pre>
    */
public static final String PROPERTY_NONJSON_MESSAGE_RECEIVER = "nonJSONMessageReceiver";
   
   private NonJSONMessageReceiver nonJSONMessageReceiver;
   
   public boolean setNonJSONMessageReceiver (NonJSONMessageReceiver value)		
   {
      boolean changed = false;
   
      if (this.nonJSONMessageReceiver != value)
      {
   NonJSONMessageReceiver oldValue = this.nonJSONMessageReceiver;
         if (this.nonJSONMessageReceiver != null)
         {
            this.nonJSONMessageReceiver = null;
            oldValue.setCIClient(null);
         }
         this.nonJSONMessageReceiver = value;
   
         if (value != null)
         {
            value.setCIClient(this);
         }
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NONJSON_MESSAGE_RECEIVER, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public NonJSONMessageReceiver getNonJSONMessageReceiver ()	
   {
      return this.nonJSONMessageReceiver;
   }
   /**
    * <pre>
    *           1..1     1..1
    * CIClient ------------------------- ServerConnection
    *           cIClient        &gt;       serverConnection
    * </pre>
    */
public static final String PROPERTY_SERVER_CONNECTION = "serverConnection";
   
   private ServerConnection serverConnection;
   
   public boolean setServerConnection (ServerConnection value)		
   {
      boolean changed = false;
   
      if (this.serverConnection != value)
      {
   ServerConnection oldValue = this.serverConnection;
         if (this.serverConnection != null)
         {
            this.serverConnection = null;
            oldValue.setCIClient(null);
         }
         this.serverConnection = value;
   
         if (value != null)
         {
            value.setCIClient(this);
         }
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SERVER_CONNECTION, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public ServerConnection getServerConnection ()	
   {
      return this.serverConnection;
   }
   /**
    * <pre>
    *           1..1     1..1
    * CIClient ------------------------- JSONMessageReceiver
    *           cIClient        &lt;       jSONMessageReceiver
    * </pre>
    */
public static final String PROPERTY_JSON_MESSAGE_RECEIVER = "jSONMessageReceiver";
   
   private JSONMessageReceiver jSONMessageReceiver;
   
   public boolean setJSONMessageReceiver (JSONMessageReceiver value)		
   {
      boolean changed = false;
   
      if (this.jSONMessageReceiver != value)
      {
   JSONMessageReceiver oldValue = this.jSONMessageReceiver;
         if (this.jSONMessageReceiver != null)
         {
            this.jSONMessageReceiver = null;
            oldValue.setCIClient(null);
         }
         this.jSONMessageReceiver = value;
   
         if (value != null)
         {
            value.setCIClient(this);
         }
         getPropertyChangeSupport().firePropertyChange(PROPERTY_JSON_MESSAGE_RECEIVER, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public JSONMessageReceiver getJSONMessageReceiver ()	
   {
      return this.jSONMessageReceiver;
   }

   private String newRcvChatMessage;
 
   /*oldValue is always "Space", cause if u send messages with the same value
   u dont get this on the screen, cause firepropertychange can only activated by new 
   different messages between old and new value.
   so oldvalue is always "Space" and u get always the new message on ur screen
    */ 
   public void setNewRcvChatMessage(String value) {

      String oldValue = " ";
      this.newRcvChatMessage = value;
      getPropertyChangeSupport().firePropertyChange(PROPERTY_NEW_RCV_CHAT_MESSAGE, oldValue, value);
   
   }
   
   public String getNewRcvChatMessage() {
      return this.newRcvChatMessage;
   }
   /**
    * <pre>
    *           0..1     0..n
    * CIClient ------------------------- PlayerInfo
    *           cIClient        &lt;       playerInfo
    * </pre>
    */
public static final String PROPERTY_PLAYER_INFO = "playerInfo";
   
   private FPropHashSet playerInfo;
   
   public boolean addToPlayerInfo (PlayerInfo value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.playerInfo == null)
         {
            this.playerInfo = new FPropHashSet (this, PROPERTY_PLAYER_INFO);
   
         }
         changed = this.playerInfo.add (value);
         if (changed)
         {
            value.setCIClient(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromPlayerInfo (PlayerInfo value)	
   {
      boolean changed = false;
   
      if ((this.playerInfo != null) && (value != null))
      {
         changed = this.playerInfo.remove (value);
         if (changed)
         {
            value.setCIClient(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromPlayerInfo ()
   {
   PlayerInfo tmpValue;
      Iterator iter = this.iteratorOfPlayerInfo ();
   
      while (iter.hasNext ())
      {
         tmpValue = (PlayerInfo) iter.next ();
         this.removeFromPlayerInfo (tmpValue);
      }
   }
   
   public boolean hasInPlayerInfo (PlayerInfo value)
   {
      return ((this.playerInfo != null) &&
              (value != null) &&
              this.playerInfo.contains (value));
   }
   
   public Iterator iteratorOfPlayerInfo ()
   {
      return ((this.playerInfo == null)
              ? FEmptyIterator.get ()
              : this.playerInfo.iterator ());
   
   }
   
   public int sizeOfPlayerInfo ()
   {
      return ((this.playerInfo == null)
              ? 0
              : this.playerInfo.size ());
   }
   /**
    * <pre>
    *           0..1     0..n
    * CIClient ------------------------- GameInfo
    *           cIClient        &lt;       gameInfo
    * </pre>
    */
public static final String PROPERTY_GAME_INFO = "gameInfo";
   
   private FPropHashSet gameInfo;
   
   public boolean addToGameInfo (GameInfo value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.gameInfo == null)
         {
            this.gameInfo = new FPropHashSet (this, PROPERTY_GAME_INFO);
   
         }
         changed = this.gameInfo.add (value);
         if (changed)
         {
            value.setCIClient(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromGameInfo (GameInfo value)	
   {
      boolean changed = false;
   
      if ((this.gameInfo != null) && (value != null))
      {
         changed = this.gameInfo.remove (value);
         if (changed)
         {
            value.setCIClient(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromGameInfo ()
   {
   GameInfo tmpValue;
      Iterator iter = this.iteratorOfGameInfo ();
   
      while (iter.hasNext ())
      {
         tmpValue = (GameInfo) iter.next ();
         this.removeFromGameInfo (tmpValue);
      }
   }
   
   public boolean hasInGameInfo (GameInfo value)
   {
      return ((this.gameInfo != null) &&
              (value != null) &&
              this.gameInfo.contains (value));
   }
   
   public Iterator iteratorOfGameInfo ()
   {
      return ((this.gameInfo == null)
              ? FEmptyIterator.get ()
              : this.gameInfo.iterator ());
   
   }
   
   public int sizeOfGameInfo ()
   {
      return ((this.gameInfo == null)
              ? 0
              : this.gameInfo.size ());
   }
   /**
    * <pre>
    *           0..1     0..n
    * CIClient ------------------------- MapInfo
    *           cIClient        &lt;       mapInfo
    * </pre>
    */
public static final String PROPERTY_MAP_INFO = "mapInfo";
   
   private FPropHashSet mapInfo;
   
   public boolean addToMapInfo (MapInfo value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.mapInfo == null)
         {
            this.mapInfo = new FPropHashSet (this, PROPERTY_MAP_INFO);
   
         }
         changed = this.mapInfo.add (value);
         if (changed)
         {
            value.setCIClient(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromMapInfo (MapInfo value)	
   {
      boolean changed = false;
   
      if ((this.mapInfo != null) && (value != null))
      {
         changed = this.mapInfo.remove (value);
         if (changed)
         {
            value.setCIClient(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromMapInfo ()
   {
   MapInfo tmpValue;
      Iterator iter = this.iteratorOfMapInfo ();
   
      while (iter.hasNext ())
      {
         tmpValue = (MapInfo) iter.next ();
         this.removeFromMapInfo (tmpValue);
      }
   }
   
   public boolean hasInMapInfo (MapInfo value)
   {
      return ((this.mapInfo != null) &&
              (value != null) &&
              this.mapInfo.contains (value));
   }
   
   public Iterator iteratorOfMapInfo ()
   {
      return ((this.mapInfo == null)
              ? FEmptyIterator.get ()
              : this.mapInfo.iterator ());
   
   }
   
   public int sizeOfMapInfo ()
   {
      return ((this.mapInfo == null)
              ? 0
              : this.mapInfo.size ());
   }
      
   public void removeYou()
   {
         this.setNonJSONMessageReceiver (null);
         this.setServerConnection (null);
         this.setJSONMessageReceiver (null);
         this.removeAllFromPlayerInfo ();
         this.removeAllFromGameInfo ();
         this.removeAllFromMapInfo ();
      }
   
}
