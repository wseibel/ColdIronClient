package tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import junit.framework.Assert;

import model.game.CIClient;
import model.lobby.NonJSONMessageReceiver;
import model.lobby.ServerConnection;
import org.junit.Test;


public class ServerAccessTest {

	@Test
	/**
	 * @param args
	 */
	public void main() {
		Socket socket = null;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try {
			socket = new Socket("se1.cs.uni-kassel.de", 5000);
			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					 inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);

			OutputStream outputStream = socket.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					 outputStream);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
		} catch (UnknownHostException e) {
			System.err.println("no access to internet at all (se1.cs.uni-kassel.de is unknown host)");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("no access to SE1 ColdIron-Server (socket to se1.cs.uni-kassel.de is not connectable)");
			e.printStackTrace();
		}
		try {
			Thread.currentThread().sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			String greeting = bufferedReader.readLine();
			Assert.assertTrue(greeting.startsWith("SE1 ColdIron-Server"));
		} catch (IOException e) {
			System.err.println("SE1 ColdIron-Server seems to be down (there is access to the internet but the server doesn`t reply with the expected greeting)");
			return;
		}
		try {
			socket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
