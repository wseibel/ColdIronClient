package model.lobby;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatUtilities {
	
	/* A Method to get the number of valid and online Teams,
	 * this is neccessary for the total length of the string array
	 * for the Team and Player List
	 */

   public void removeYou()
   {
   }
	public int getTeamCount(String[] team, int length){
		
		if(isTreeTeamEmpty(team)){
			return length;
		}
		else{
			String[] teamb = removeFirstTeam(team);
			
			return getTeamCount(teamb, length+1);
		}
	}
	
	/* A temporary Help Method, which delete the same teams, which are
	 * at the others indexes than the first.
	 * 
	 */
	public String[] removeFirstTeam(String[] teamName){

		int count = 0;
		//zahele die gleichen teamnamen
		for(int i = 0; i < teamName.length; i++){		
				if(!(teamName[0].equals(teamName[i]))){
					count++;

				}

		}
		
		String[] newString = new String[count];
		int innerTimer = 0;
		
		//wenn es nicht drin vorkommt, dann gib mir die anderen sachen zurueck
		for(int i = 0; i < teamName.length; i++){
				if(!(teamName[0].equals(teamName[i]))){
					newString[innerTimer] = teamName[i];
					innerTimer++;
				}
		}
		
		if(newString.length == 0){
			newString = null;
		}
		
		return newString;
	}
	
	/* Method get the Index of the Array from the same Team
	 * 
	 */
	public int[] getPlayerIndexOfFirstTeam(String[] teamName){

		int count = 0;
		//zahele die gleichen teamnamen
		for(int i = 0; i < teamName.length; i++){		
				if(teamName[0].equals(teamName[i])){
					count++;
				}

		}
		
		int[] tempIndex = new int[count];
		int innerTimer = 0;
		//save the array with the index
		for(int i = 0; i < teamName.length; i++){
				if(teamName[0].equals(teamName[i])){
					tempIndex[innerTimer] = i;
					innerTimer++;
				}
			
			
		}
		
		return tempIndex;
	}
	
	/* Check out, if a String is null
	 * 
	 */
	public boolean isTreeTeamEmpty(String[] treeTeam){
		if(treeTeam == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	/* Remove the already exists Players, who are in the total
	 * String Array of Teams and Players.
	 * 
	 */
	public String[] removeExistsPlayers(String[] playerName, int[] index){

		int count = 0;
		
		String[] temp = playerName;
		
		//laenge vom index
		int laenge = index.length;
		
		for(int i = 0; i < index.length; i++){
			temp[index[i]] = null;
			count++;
		}
		int neuelaenge = (playerName.length)-count;
		
		String[] newString = new String[neuelaenge];
		int innerTimer = 0;
		
		for(int i = 0; i < temp.length; i++){
			if(temp[i] != null){
				newString[innerTimer] = temp[i];
				innerTimer++;
			}
		}
		
		
		
		return newString;
	}
	
	/* This is the main function, which make a complete String Array
	 * of Teams and Players. The Direction is first the Team, then the
	 * members of the team and so on, till all teams are in the list.
	 */
	public String[] fillString(String[] team, String[] players, String[] fullList, int start){

		if(isTreeTeamEmpty(team) == false){
			
			fullList[start] = team[0];

			int length = getPlayerIndexOfFirstTeam(team).length;
			int innercount = 0;
			String markPlayers = "- ";

			while(length != 0){
				fullList[start+1] = markPlayers + players[getPlayerIndexOfFirstTeam(team)[innercount]];
				length--;
				start++;
				innercount++;
			}

			String[] playersb = removeExistsPlayers(players, getPlayerIndexOfFirstTeam(team));
			String[] teamb = removeFirstTeam(team);

			return fillString(teamb, playersb, fullList, start+1);
		}
		else
			return fullList;
		
	}
	
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
