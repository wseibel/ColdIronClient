package model.lobby;

import org.json.JSONObject;

import model.game.CIClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;

/**
 * a thread that is permanently trying to receive JSON-messages and passes them
 * to their handlers or is blocking while there is nothing to receive
 */
public class JSONMessageReceiver extends Thread {
	/**
	 * <pre>
	 *           1..1     1..1
	 * JSONMessageReceiver ------------------------- CIClient
	 *           jSONMessageReceiver        &gt;       cIClient
	 * </pre>
	 */

	/**
	 * <pre>
	 *           1..1     1..1
	 * JSONMessageReceiver ------------------------- CIClient
	 *           jSONMessageReceiver        &gt;       cIClient
	 * </pre>
	 */

	private CIClient cIClient;

	public CIClient getCIClient() {
		return this.cIClient;
	}

	public void removeYou() {
		this.setCIClient(null);
	}

	public boolean setCIClient(CIClient value) {
		boolean changed = false;

		if (this.cIClient != value) {
			CIClient oldValue = this.cIClient;
			if (this.cIClient != null) {
				this.cIClient = null;
				oldValue.setJSONMessageReceiver(null);
			}
			this.cIClient = value;

			if (value != null) {
				value.setJSONMessageReceiver(this);
			}
			changed = true;
		}
		return changed;
	}

	private JSONObject jsonObject;

	public void setJsonObject(JSONObject value) {
		this.jsonObject = value;
	}

	public JSONObject getJsonObject() {
		return this.jsonObject;
	}

	private BufferedReader bufferedReader;

	public JSONMessageReceiver(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	@Override
	public void run() {
		
		// create logFile
		String fileName = "log/logFile.txt";
		File file = new File(fileName);
		
		if(file.exists()) {
			file.delete();
		}
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		String message = null;
		Integer firstSendElements = 0;
		Integer numberFirstSendingElements = 0;
		this.setName("JSONMessageReceiver-thread");
		try {
			while (true) {
				message = bufferedReader.readLine();
System.out.println(message);
				if (message.substring(0, 1).equals("{")) {
					jsonObject = new JSONObject(message);

					// counts first send elements
					if (firstSendElements < cIClient
							.getNumberFirstSendingEvents()) {
						firstSendElements++;
						cIClient.setFirstSendEvents(firstSendElements);
					} 
					cIClient.getChainMaster().consumeMessage(jsonObject);
				}
				// get number of sending elements by joining game
				else if (message.length() > 7) {
					if (message.substring(0, 7).toUpperCase().equals("SENDING")) {
						String[] splittedString = message.split(" ");
						numberFirstSendingElements = Integer
								.parseInt(splittedString[1]);
						cIClient.setNumberFirstSendingEvents(numberFirstSendingElements);
					}
				}
				
				// write log
				try {
					PrintStream printStream = new PrintStream(new FileOutputStream(file, true));
					printStream.println(message);
					printStream.close();
				} catch (Exception e) {
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
