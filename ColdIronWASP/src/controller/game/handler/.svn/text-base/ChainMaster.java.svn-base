package controller.game.handler;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import de.upb.tools.pcs.PropertyChangeClient;

import de.upb.tools.fca.*;
import java.util.*;

import model.game.CIClient;
import model.game.CommandHelper;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import controller.game.handler.MessageHandler;
import controller.game.handler.source.AllianceHandler;
import controller.game.handler.source.ArcherHandler;
import controller.game.handler.source.BarrackHandler;
import controller.game.handler.source.CatapultHandler;
import controller.game.handler.source.FarmHandler;
import controller.game.handler.source.ForgeHandler;
import controller.game.handler.source.GameHandler;
import controller.game.handler.source.IngameMessageHandler;
import controller.game.handler.source.KnightHandler;
import controller.game.handler.source.LevelHPElementToIntegerMapHandler;
import controller.game.handler.source.MapHandler;
import controller.game.handler.source.PeonHandler;
import controller.game.handler.source.ResourceHandler;
import controller.game.handler.source.SectorHandler;
import controller.game.handler.source.StrongholdHandler;
import controller.game.handler.source.SwordsmanHandler;
import controller.game.handler.source.TeamHandler;
import controller.game.handler.source.TowerHandler;
import controller.game.handler.source.UserAssetsHandler;
import controller.game.handler.source.UserHandler;

public class ChainMaster implements PropertyChangeClient {
	public static final String PROPERTY_PROPERT_Y_CHA_T_STRING = "PROPERTY_CHAT_STRING";

	public static final String PROPERTY_CHAT_STRING = "chatString";

	protected final PropertyChangeSupport listeners = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().removePropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String property,
			PropertyChangeListener listener) {
		getPropertyChangeSupport()
				.addPropertyChangeListener(property, listener);
	}

	public void removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		getPropertyChangeSupport().removePropertyChangeListener(property,
				listener);
	}

	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

	/**
	 * <pre>
	 *           1..1     0..n
	 * ChainMaster ------------------------- MessageHandler
	 *           chainMaster        &gt;       messageHandler
	 * </pre>
	 */
	public static final String PROPERTY_MESSAGE_HANDLER = "messageHandler";

	private FPropHashSet messageHandler;

	public boolean addToMessageHandler(MessageHandler value) {
		boolean changed = false;

		if (value != null) {
			if (this.messageHandler == null) {
				this.messageHandler = new FPropHashSet(this,
						PROPERTY_MESSAGE_HANDLER);

			}
			changed = this.messageHandler.add(value);
			if (changed) {
				value.setChainMaster(this);
			}
		}
		return changed;
	}

	public boolean removeFromMessageHandler(MessageHandler value) {
		boolean changed = false;

		if ((this.messageHandler != null) && (value != null)) {
			changed = this.messageHandler.remove(value);
			if (changed) {
				value.setChainMaster(null);
			}
		}
		return changed;
	}

	public void removeAllFromMessageHandler() {
		MessageHandler tmpValue;
		Iterator iter = this.iteratorOfMessageHandler();

		while (iter.hasNext()) {
			tmpValue = (MessageHandler) iter.next();
			this.removeFromMessageHandler(tmpValue);
		}
	}

	public boolean hasInMessageHandler(MessageHandler value) {
		return ((this.messageHandler != null) && (value != null) && this.messageHandler
				.contains(value));
	}

	public Iterator iteratorOfMessageHandler() {
		return ((this.messageHandler == null) ? FEmptyIterator.get()
				: this.messageHandler.iterator());

	}

	public int sizeOfMessageHandler() {
		return ((this.messageHandler == null) ? 0 : this.messageHandler.size());
	}

	/**
	 * <pre>
	 *           1..1     1..1
	 * ChainMaster ------------------------- CommandHelper
	 *           chainMaster        &gt;       commandHelper
	 * </pre>
	 */
	public static final String PROPERTY_COMMAND_HELPER = "cIClient";
	
	private CommandHelper commandHelper;
	
	public CommandHelper getCommandHelper() {
		return commandHelper;
	}

	public boolean setCommandHelper(CommandHelper commandHelper) {
		boolean changed = false;

		if (this.commandHelper != commandHelper) {
			CommandHelper oldValue = this.commandHelper;
			if (this.commandHelper != null) {
				this.commandHelper = null;
				oldValue.setChainMaster(null);
			}
			this.commandHelper = commandHelper;

			if (commandHelper != null) {
				commandHelper.setChainMaster(this);
			}
			getPropertyChangeSupport().firePropertyChange(PROPERTY_CI_CLIENT,
					oldValue, commandHelper);
			changed = true;
		}
		return changed;
	}
		
	/**
	 * <pre>
	 *           1..1     1..1
	 * ChainMaster ------------------------- CIClient
	 *           chainMaster        &gt;       cIClient
	 * </pre>
	 */
	public static final String PROPERTY_CI_CLIENT = "cIClient";

	private CIClient cIClient;

	public boolean setCIClient(CIClient value) {
		boolean changed = false;

		if (this.cIClient != value) {
			CIClient oldValue = this.cIClient;
			if (this.cIClient != null) {
				this.cIClient = null;
				oldValue.setChainMaster(null);
			}
			this.cIClient = value;

			if (value != null) {
				value.setChainMaster(this);
			}
			getPropertyChangeSupport().firePropertyChange(PROPERTY_CI_CLIENT,
					oldValue, value);
			changed = true;
		}
		return changed;
	}

	public CIClient getCIClient() {
		return this.cIClient;
	}

	public void removeYou() {
		this.removeAllFromMessageHandler();
		this.setCIClient(null);
		this.setCommandHelper(null);
	}

	private HashMap<String, MessageHandler> messageHandlerMap;

	public String chatString;

	public HashMap<String, MessageHandler> getMessageHandlerMap() {
		if (this.messageHandlerMap == null) {
			this.messageHandlerMap = new HashMap<String, MessageHandler>();
		}
		return this.messageHandlerMap;
	}

	/**
	 * <pre>
	 *           1..1     0..*
	 * ChainMaster ------------------------- MessageHandler
	 *           chainMaster        &lt;       messageHandler
	 * </pre>
	 */

	/**
	 * Constructor, which initializes massage handler for different game objects
	 */
	public ChainMaster() {
		if (this.messageHandlerMap == null) {
			this.messageHandlerMap = new HashMap<String, MessageHandler>();
		}

		// Initlize your property handler here
		messageHandlerMap.put("Resource", new ResourceHandler());
		messageHandlerMap.put("Archer", new ArcherHandler());
		messageHandlerMap.put("Knight", new KnightHandler());
		messageHandlerMap.put("Catapult", new CatapultHandler());
		messageHandlerMap.put("Barrack", new BarrackHandler());
		messageHandlerMap.put("Farm", new FarmHandler());
		messageHandlerMap.put("Forge", new ForgeHandler());
		messageHandlerMap.put("Knight", new KnightHandler());
		messageHandlerMap.put("Peon", new PeonHandler());
		messageHandlerMap.put("Stronghold", new StrongholdHandler());
		messageHandlerMap.put("Swordsman", new SwordsmanHandler());
		messageHandlerMap.put("Tower", new TowerHandler());
		messageHandlerMap.put("ColdIronGame", new GameHandler());
		messageHandlerMap.put("Map", new MapHandler());
		messageHandlerMap.put("Team", new TeamHandler());
		messageHandlerMap.put("UserAssets", new UserAssetsHandler());
		messageHandlerMap.put("User", new UserHandler());
		messageHandlerMap.put("Message", new IngameMessageHandler());
		messageHandlerMap.put("Sector", new SectorHandler());
		messageHandlerMap.put("LevelHPElementToIntegerMap",
				new LevelHPElementToIntegerMapHandler());
		messageHandlerMap.put("Alliance", new AllianceHandler());

		// Set this as chain master for every entry in the hash map
		for (Object value : messageHandlerMap.values()) {
			((MessageHandler) value).setChainMaster(this);
		}
	}
	
	private String firstTimeStamp = null;
	private long startTime = 0;
	private long cycle = 0;
	private boolean gotCycle = false;

	/**
	 * Forwarding jsonObject to specialized MessageHandler
	 * 
	 * @param jsonObject
	 * @throws JSONException
	 */
	public void consumeMessage(JSONObject jsonObject) throws JSONException {

		// Chain of responsibility
		try {
//			if (!gotCycle){
//				// Calculate the time for a cycle
//				String timeStamp = jsonObject.getString("@ts");
//				if(firstTimeStamp == null){
//					startTime = System.currentTimeMillis();
//					firstTimeStamp = timeStamp;
//				}
//				else if (!timeStamp.equals(firstTimeStamp)){
//					setCycle(System.currentTimeMillis() - startTime);
//					gotCycle = true;
//				}
//			}
			if (jsonObject.getString("@prop").contains("MESSAGE")) {
				String oldValue = chatString;
				chatString = dispatchMessage(jsonObject);
				listeners.firePropertyChange(PROPERTY_CHAT_STRING, oldValue,
						chatString);

			} else {				
				String substring = jsonObject.getString("@src");
				int substringEndIndex = substring.indexOf('@');
				String substringMessageType = substring.substring(0,
						substringEndIndex);
				MessageHandler handler = messageHandlerMap
						.get(substringMessageType);
				if (jsonObject.getString("@prop").equals("visitor")){
					return;
				}
				if (handler != null)
					handler.handle(jsonObject);
				else if (!substringMessageType.equals("Guide")
						&& !substringMessageType
								.equals("LevelHPElementToResourceMap")) {
					throw new Exception();
				}
			}
		} catch (Exception e) {
			System.err.println("Unknown event or handler: "
					+ jsonObject.toString());
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public String getChatString() {
		return chatString;
	}

	public void setChatString(String chatString) {
		String oldValue = this.chatString;
		this.chatString = chatString;
		listeners.firePropertyChange(PROPERTY_CHAT_STRING, oldValue,
				chatString);
	}

	private String dispatchMessage(JSONObject jsonObject) throws JSONException {
		String messageType = null;
		String messageOwner = null;
		String message = null;
		if (jsonObject.has("@prop"))
			messageType = jsonObject.getString("@prop");
		if (jsonObject.has("@src"))
			messageOwner = jsonObject.getString("@src");
		if (jsonObject.has("@nv"))
			message = jsonObject.getString("@nv");
		return messageType + ":" + messageOwner + ":" + message;
	}

	public long getCycle() {
		return cycle;
	}

	public void setCycle(long cycle) {
		this.cycle = cycle;
	}

}
