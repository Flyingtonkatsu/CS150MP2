package src;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import data.*;

public class GameLogic {
	
	Agent player[];		//list of players
	GameBoard board;
	String[] cmdlist;
	
	public GameLogic(){
		initGameElements();
		gameStart();
	}
	
	public void initGameElements(){
		board = new GameBoard();
		player = new Agent[4];
		
		player[0] = new Agent(11, "Athlete", 0, 0);
		player[1] = new Agent(9, "Slacker", 3, 0);
		player[2] = new Agent(10, "Genius", 7, 0);
		player[3] = new Agent(10, "Rich Girl", 10, 0);
		
		//Set starting locations for players:
		board.agentMove(player[1], 3, 0);
		board.agentMove(player[2], 7, 0);
		board.agentMove(player[3], 10, 0);
		board.agentMove(player[0], 0, 0);
	}
	
	public void gameStart(){
		printf("\nGame start!");
		while(!mainloop());
	}
	
	private boolean mainloop(){
		board.debug_printBoard();
		CommandPhase();
		ActionPhase();
		return ExecutionPhase();
	}
	
	private void CommandPhase(){
		/*
		//	take input
		for(int i=0; i < 4; i++){
			// open compiler for player[i]
		}
		// form actlist here
		 	each element in the String[] actlist should have format:
		 	<playernumber>.<command>.<arguments>
		 		<playernumber> = int from 0..3
		 		<command> = "move", "item", "interact", "power"
		 */
		
		
	}
	
	private void ActionPhase(){
		for(int i=0; i<4; i++){
			if(!player[i].isFainted()) player[i].setAP(5);
			else player[i].setAP(0);
		}
		// for each agent, check bufflist; modify AP of agent, reduce duration in bufflist
		// define actlist = list of player actions in order
		// for each action in actlist do:
		// 	execute actions
		//		Item commands: refer to item table
		//		Admin commands: refer to admin table
		//		- move
		//		- item
		//		- power
		//		- interact
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		do{
			System.out.print("Input command for player "+ 0 +": ");
			try{
			input = br.readLine();
			} catch(IOException e){}
		} while(!processCommand(player[0], input));
	}
	
	private boolean ExecutionPhase(){
		for(int i=0; i<4; i++){
		// process jeepneys;
			if(player[i].isTraveling()) exeJeep(player[i]);
		// enlistment
		// incoming stress:
			exeAgent(player[i]);
		// check for faint
		// check win con:
		// 		returns true pag may nanalo na
		}
		return false;
	}
	
	
	// Language definition and processing:
	//	takes the current Agent's turn, and input string from user
	private boolean processCommand(Agent a, String input){
		String i;
		int num, dx = 0, dy = 0;
		Scanner s = new Scanner(input).useDelimiter("[.]");
		i = s.next();
				
		if(i.matches("move")){
			if(!s.hasNextInt()){ printf("Missing arguments"); return false;}
			num = s.nextInt(); 
			if(num > 3 || num <= 0){ printf("Invalid number of steps"); return false;}
			if(!s.hasNext()){ printf("Missing arguments"); return false;}
			i = s.next();
			
			if(i.trim().matches("u")) dy = -1;
			else if(i.trim().matches("d")) dy = 1;
			else if(i.trim().matches("r")) dx = 1;
			else if(i.trim().matches("l")) dx = -1;
			else{ printf("Invalid argument for move command.");  return false;}
			
			for(int spaces=0; spaces < num; spaces++){
				actMove(a, a.x + dx, a.y + dy);
			}
		}
		else if(i.matches("interact")){
			actInteract(a);
		}
		else if(i.matches("item")){

		}
		else if(i.matches("power")){

		}
		else if(i.matches("showpos")){
			printf("Player positions: ");
			for(int j = 0; j<4; j++) printf("P["+j+"]:" + player[j].x + "," + player[j].y);
		}
		else{
			printf("Invalid command.");
			return false;
		}
		return true;
	}
	// - - - - - - - - - - - - - - - - - - - - - - -
	
	
	private void exeJeep(Agent a){
		board.agentJeep(a);
	}
	
	private void exeAgent(Agent a){
		a.executionProcess();
	}
	
	private void actMove(Agent a, int x, int y){
		board.agentMove(a, x, y);
	}
	
	private void actInteract(Agent a){
		board.agentInteract(a);
	}
	
	
	
	
	public void printf(String s){
		System.out.println(s);
	}
	
	public String coors(int x, int y){
		return "(" + x + "," + y + ")";
	}
}