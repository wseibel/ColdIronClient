package model.game;
import de.upb.tools.sdm.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import de.upb.tools.pcs.PropertyChangeClient;
import de.upb.tools.fca.*;

import java.util.*;

import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class Game implements PropertyChangeClient {
public static final String PROPERTY_PROPERT_Y_RUNNING = "PROPERTY_RUNNING";
   
public static final String PROPERTY_ID = "id";
   
   private String id;
   
   public void setId(String value) {
      if ( JavaSDM.stringCompare (this.id, value) != 0 )
      {
      String oldValue = this.id;
      this.id = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ID, oldValue, value);
      }
   }
   
   public String getId() {
      return this.id;
   }
   /**
    * <pre>
    *           0..1     0..1
    * Game ------------------------- Alliance
    *           gameForWinner        &gt;       allianceWinner
    * </pre>
    */
   
   public static final String PROPERTY_ALLIANCE_WINNER = "allianceWinner";
   private Alliance allianceWinner;
   
   public boolean setAllianceWinner (Alliance value)		
   {
      boolean changed = false;
   
      if (this.allianceWinner != value)
      {
   Alliance oldValue = this.allianceWinner;
         if (this.allianceWinner != null)
         {
            this.allianceWinner = null;
            oldValue.setGameForWinner(null);
         }
         this.allianceWinner = value;
   
         if (value != null)
         {
            value.setGameForWinner(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ALLIANCE_WINNER, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public Alliance getAllianceWinner ()	
   {
      return this.allianceWinner;
   }
   /**
    * <pre>
    *           0..1     0..1
    * Game ------------------------- User
    *           gameForWinner        &gt;       userWinner
    * </pre>
    */
   
   public static final String PROPERTY_USER_WINNER = "userWinner";
   private User userWinner;
   
   public boolean setUserWinner (User value)		
   {
      boolean changed = false;
   
      if (this.userWinner != value)
      {
   User oldValue = this.userWinner;
         if (this.userWinner != null)
         {
            this.userWinner = null;
            oldValue.setGameForWinner(null);
         }
         this.userWinner = value;
   
         if (value != null)
         {
            value.setGameForWinner(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_USER_WINNER, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public User getUserWinner ()	
   {
      return this.userWinner;
   }
/**
    * <pre>
    *           1..1     0..1
    * Game ------------------------- Sector
    *           game1        &gt;       currentSector
    * </pre>
    */
   
   public static final String PROPERTY_CURRENT_SECTOR = "currentSector";
   private Sector currentSector;
   
   public boolean setCurrentSector (Sector value)		
   {
      boolean changed = false;
   
      if (this.currentSector != value)
      {
   Sector oldValue = this.currentSector;
         if (this.currentSector != null)
         {
            this.currentSector = null;
            oldValue.setGame1(null);
         }
         this.currentSector = value;
   
         if (value != null)
         {
            value.setGame1(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENT_SECTOR, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public Sector getCurrentSector ()	
   {
      return this.currentSector;
   }
/**
    * <pre>
    *           1..1     0..n
    * Game ------------------------- User
    *           game        &gt;       user
    * </pre>
    */
   
   public static final String PROPERTY_USER = "user";
   private FPropHashSet user;
   
   public boolean addToUser (User value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.user == null)
         {
            this.user = new FPropHashSet (this, PROPERTY_USER);
   
         }

         listeners.firePropertyChange(PROPERTY_USER, null, value);
         changed = this.user.add (value);
         if (changed)
         {
            value.setGame(this);
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

         listeners.firePropertyChange(PROPERTY_USER, value, null);
         if (changed)
         {
            value.setGame(null);
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
   /**
    * <pre>
    *           1..1     0..n
    * Game ------------------------- UserAssets
    *           game        &lt;       userAssets
    * </pre>
    */
   
   public static final String PROPERTY_USER_ASSETS = "userAssets";
   private FPropHashSet userAssets;
   
   public boolean addToUserAssets (UserAssets value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.userAssets == null)
         {
            this.userAssets = new FPropHashSet (this, PROPERTY_USER_ASSETS);
   
         }
         listeners.firePropertyChange(PROPERTY_USER_ASSETS, null, value);
         changed = this.userAssets.add (value);
         if (changed)
         {
            value.setGame(this);
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

         listeners.firePropertyChange(PROPERTY_USER_ASSETS, value, null);
         if (changed)
         {
            value.setGame(null);
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
    *           1..1     0..n
    * Game ------------------------- SectorElement
    *           game        &gt;       sectorElement
    * </pre>
    */
   
   public static final String PROPERTY_SECTOR_ELEMENT = "sectorElement";
   private FPropHashSet sectorElement;
   
   public boolean addToSectorElement (SectorElement value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.sectorElement == null)
         {
            this.sectorElement = new FPropHashSet (this, PROPERTY_SECTOR_ELEMENT);
   
         }
         changed = this.sectorElement.add (value);
         if (changed)
         {
            value.setGame(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromSectorElement (SectorElement value)	
   {
      boolean changed = false;
   
      if ((this.sectorElement != null) && (value != null))
      {
         changed = this.sectorElement.remove (value);
         if (changed)
         {
            value.setGame(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromSectorElement ()
   {
   SectorElement tmpValue;
      Iterator iter = this.iteratorOfSectorElement ();
   
      while (iter.hasNext ())
      {
         tmpValue = (SectorElement) iter.next ();
         this.removeFromSectorElement (tmpValue);
      }
   }
   
   public boolean hasInSectorElement (SectorElement value)
   {
      return ((this.sectorElement != null) &&
              (value != null) &&
              this.sectorElement.contains (value));
   }
   
   public Iterator iteratorOfSectorElement ()
   {
      return ((this.sectorElement == null)
              ? FEmptyIterator.get ()
              : this.sectorElement.iterator ());
   
   }
   
   public int sizeOfSectorElement ()
   {
      return ((this.sectorElement == null)
              ? 0
              : this.sectorElement.size ());
   }
   /**
    * <pre>
    *           1..1     0..n
    * Game ------------------------- Message
    *           game        &lt;       message
    * </pre>
    */
   
   public static final String PROPERTY_MESSAGE = "message";
   private FPropHashSet message;
   
   public boolean addToMessage (Message value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.message == null)
         {
            this.message = new FPropHashSet (this, PROPERTY_MESSAGE);
   
         }
         changed = this.message.add (value);
         if (changed)
         {
            value.setGame(this);
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
            value.setGame(null);
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
    *           1..1     0..n
    * Game ------------------------- Sector
    *           game        &lt;       sector
    * </pre>
    */
   
   public static final String PROPERTY_SECTOR = "sector";
   private FPropHashSet sector;
   
   public boolean addToSector (Sector value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.sector == null)
         {
            this.sector = new FPropHashSet (this, PROPERTY_SECTOR);
   
         }
         changed = this.sector.add (value);
         if (changed)
         {
            value.setGame(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromSector (Sector value)	
   {
      boolean changed = false;
   
      if ((this.sector != null) && (value != null))
      {
         changed = this.sector.remove (value);
         if (changed)
         {
            value.setGame(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromSector ()
   {
   Sector tmpValue;
      Iterator iter = this.iteratorOfSector ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Sector) iter.next ();
         this.removeFromSector (tmpValue);
      }
   }
   
   public boolean hasInSector (Sector value)
   {
      return ((this.sector != null) &&
              (value != null) &&
              this.sector.contains (value));
   }
   
   public Iterator iteratorOfSector ()
   {
      return ((this.sector == null)
              ? FEmptyIterator.get ()
              : this.sector.iterator ());
   
   }
   
   public int sizeOfSector ()
   {
      return ((this.sector == null)
              ? 0
              : this.sector.size ());
   }
   public static final String PROPERTY_NAME = "name";
   
   private String name;
   
   public void setName(String value) {
      if ( JavaSDM.stringCompare (this.name, value) != 0 )
      {
      String oldValue = this.name;
      this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public String getName() {
      return this.name;
   }
   public static final String PROPERTY_USERS_TO_START = "usersToStart";
   
   private Integer usersToStart;
   
   public void setUsersToStart(Integer value) {
      if ( this.usersToStart != value )
      {
      Integer oldValue = this.usersToStart;
      this.usersToStart = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_USERS_TO_START, oldValue, value);
      }
   }
   
   public Integer getUsersToStart() {
      return this.usersToStart;
   }
   /**
    * <pre>
    *           1..1     0..n
    * Game ------------------------- Alliance
    *           game        &gt;       alliance
    * </pre>
    */
   
   public static final String PROPERTY_ALLIANCE = "alliance";
   private FPropHashSet alliance;
   
   public boolean addToAlliance (Alliance value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.alliance == null)
         {
            this.alliance = new FPropHashSet (this, PROPERTY_ALLIANCE);
   
         }
         changed = this.alliance.add (value);
         if (changed)
         {
            value.setGame(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromAlliance (Alliance value)	
   {
      boolean changed = false;
   
      if ((this.alliance != null) && (value != null))
      {
         changed = this.alliance.remove (value);
         if (changed)
         {
            value.setGame(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromAlliance ()
   {
   Alliance tmpValue;
      Iterator iter = this.iteratorOfAlliance ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Alliance) iter.next ();
         this.removeFromAlliance (tmpValue);
      }
   }
   
   public boolean hasInAlliance (Alliance value)
   {
      return ((this.alliance != null) &&
              (value != null) &&
              this.alliance.contains (value));
   }
   
   public Iterator iteratorOfAlliance ()
   {
      return ((this.alliance == null)
              ? FEmptyIterator.get ()
              : this.alliance.iterator ());
   
   }
   
   public int sizeOfAlliance ()
   {
      return ((this.alliance == null)
              ? 0
              : this.alliance.size ());
   }
public static final String PROPERTY_RUNNING = "running";
   
   private boolean running;
   
   public void setRunning(boolean value) {
      if ( this.running != value )
      {
      boolean oldValue = this.running;
      this.running = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_RUNNING, oldValue, value);
      }
   }
   
   public boolean isRunning() {
      return this.running;
   }
   /**
    * <pre>
    *           1..1     0..n
    * Game ------------------------- Team
    *           game        &gt;       team
    * </pre>
    */
   
   public static final String PROPERTY_TEAM = "team";
   private FPropHashSet team;
   
   public boolean addToTeam (Team value)
   {
      boolean changed = false;
   
      if (value != null)
      {
         if (this.team == null)
         {
            this.team = new FPropHashSet (this, PROPERTY_TEAM);
   
         }
         changed = this.team.add (value);
         if (changed)
         {
            value.setGame(this);
         }
      }
      return changed;
   }
   
   public boolean removeFromTeam (Team value)	
   {
      boolean changed = false;
   
      if ((this.team != null) && (value != null))
      {
         changed = this.team.remove (value);
         if (changed)
         {
            value.setGame(null);
         }
      }
      return changed;
   }
   
   public void removeAllFromTeam ()
   {
   Team tmpValue;
      Iterator iter = this.iteratorOfTeam ();
   
      while (iter.hasNext ())
      {
         tmpValue = (Team) iter.next ();
         this.removeFromTeam (tmpValue);
      }
   }
   
   public boolean hasInTeam (Team value)
   {
      return ((this.team != null) &&
              (value != null) &&
              this.team.contains (value));
   }
   
   public Iterator iteratorOfTeam ()
   {
      return ((this.team == null)
              ? FEmptyIterator.get ()
              : this.team.iterator ());
   
   }
   
   public int sizeOfTeam ()
   {
      return ((this.team == null)
              ? 0
              : this.team.size ());
   }
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
    * Game ------------------------- Map
    *           game        &lt;       map
    * </pre>
    */
public static final String PROPERTY_MAP = "map";
   
   private Map map;
   
   public boolean setMap (Map value)		
   {
      boolean changed = false;
   
      if (this.map != value)
      {
   Map oldValue = this.map;
         if (this.map != null)
         {
            this.map = null;
            oldValue.setGame(null);
         }
         this.map = value;
   
         if (value != null)
         {
            value.setGame(this);
         }
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MAP, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public Map getMap ()	
   {
      return this.map;
   }
   /**
    * <pre>
    *           1..1     1..1
    * Game ------------------------- CIClient
    *           game        &gt;       cIClient
    * </pre>
    */
public static final String PROPERTY_CI_CLIENT = "cIClient";
   
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
            oldValue.setGame(null);
         }
         this.cIClient = value;
   
         if (value != null)
         {
            value.setGame(this);
         }
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CI_CLIENT, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public CIClient getCIClient ()	
   {
      return this.cIClient;
   }
   public void removeYou()
   {
	   this.setAllianceWinner (null);
	   this.setUserWinner (null);
	   this.setCurrentSector (null);
	   this.removeAllFromUser ();
	   this.removeAllFromUserAssets ();
	   this.removeAllFromSectorElement ();
	   this.removeAllFromMessage ();
	   this.removeAllFromSector ();
	   this.removeAllFromAlliance ();
	   this.removeAllFromTeam ();
	   this.setMap (null);
	   this.setCIClient (null);
   }
   /**
    * <pre>
    *           1..1     1..1
    * Game ------------------------- Map
    *           game        &lt;       map
    * </pre>
    */

   public FPropHashSet getSectors() {
	   return sector;
   }



   }
