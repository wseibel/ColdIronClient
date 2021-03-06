package model.lobby;

import model.game.CIClient;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * a thread that is permanently trying to receive clear-text-messages and passes
 * them for consumption to ServerConnection.consumeMessage() or is blocking
 * while there is nothing to receive
 */
public class NonJSONMessageReceiver extends Thread {
	private BufferedReader bufferedReader;
	/**
	 * <pre>
	 *           1..1     1..1
	 * NonJSONMessageReceiver ------------------------- CIClient
	 *           nonJSONMessageReceiver        &gt;       cIClient
	 * </pre>
	 */

	private CIClient cIClient;

	public boolean setCIClient(CIClient value) {
		boolean changed = false;

		if (this.cIClient != value) {
			CIClient oldValue = this.cIClient;
			if (this.cIClient != null) {
				this.cIClient = null;
				oldValue.setNonJSONMessageReceiver(null);
			}
			this.cIClient = value;

			if (value != null) {
				value.setNonJSONMessageReceiver(this);
			}
			changed = true;
		}
		return changed;
	}

	public CIClient getCIClient() {
		return this.cIClient;
	}

	public boolean run = true;

	@Override
	public void run() {
		this.setName("nonJSONMessageReceiver-thread");
   		try {
   			while (run) {
   				String message = "";
   				message += bufferedReader.readLine();
   
   				// filters message "Sending x elements" out
   				if (message.length() > 7
   						&& message.substring(0, 7).toUpperCase()
   								.equals("SENDING")) {
   					// send jsonmessagereceiver the number of sending elements
   					// by joining
   					String[] splittedString = message.split(" ");
   					Integer number = Integer.parseInt(splittedString[1]);
   					
   					cIClient.setNumberFirstSendingEvents(number);
   				} else {
   					cIClient.getServerConnection().consumeMessage(message);
   				}
   
   			}
   		} catch (IOException e) {
   			e.printStackTrace();
   		}
   	}

	public NonJSONMessageReceiver(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	public void removeYou() {
		this.setCIClient(null);
	}

}
