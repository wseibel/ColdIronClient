package model.lobby;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatUtilities {
	
	/* split the words with the space symbol and make a stringbuffer
	 * then append to the stringbuffer the actual time, username of the sended msg
	 * and the msg. we differentiate btw. the 3 kinds of msgs
	 * YOU, ALL and TEAM. at last we convert the sb to a string and return this
	 */
	public String splitReceivedMsg(String rcvMsg){
		
		Date currentTime = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat ("HH:mm ");

		String[] temp = rcvMsg.split(" ");
		StringBuffer sb = new StringBuffer();
		
		//remove the " symbols of the first word and the last word
		temp[5] = temp[5].substring(1);
		temp[temp.length-1] = temp[temp.length-1].substring(0, temp[temp.length-1].length()-1);
		
		if(temp[4].equals("ALL:")){
			sb.append(timeFormat.format(currentTime) + "<" + temp[2] + "> ");
			for(int i = 5; i < temp.length; i++){
				sb.append(temp[i]);
				sb.append(" ");
			}
		}
		
		if(temp[4].equals("YOU:")){
			sb.append(timeFormat.format(currentTime) + "<" + temp[2] + " whispers> ");
			for(int i = 5; i < temp.length; i++){
				sb.append(temp[i]);
				sb.append(" ");
			}
		}
		
		if(temp[4].equals("TEAM:")){
			sb.append(timeFormat.format(currentTime) + "<" + temp[2] + " to your Team> ");
			for(int i = 5; i < temp.length; i++){
				sb.append(temp[i]);
				sb.append(" ");
			}
		}
		
		
		return sb.toString();
	}
	
	/* this method getting a msg and save this in a logfile, that
	 * will be created by daily time. the name of the logfile is the current date
	 * if the file doesnt exists, the file will be created, if this exists
	 * the file will be used for the log
	 * 
	 */
	public void chatLog(String msg){
		
		Date currentDate = new Date();
		SimpleDateFormat actualDateFormat = new SimpleDateFormat("dd_MM_yyyy");
		
		String actualDate = actualDateFormat.format(currentDate);
		
		try
		{
			File dailyLog = new File("log/"+ actualDate + "_Chat.log");
			PrintWriter pw = new PrintWriter(new FileWriter(dailyLog, true));
			
			pw.println(msg);
			
			pw.flush();
			pw.close();

		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		
	}

}
