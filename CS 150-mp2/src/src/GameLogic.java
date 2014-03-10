package src;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import data.*;

public class GameLogic {
	
	Agent player[];
	Board board;
	
	public GameLogic(){
		initBoard();
		initPlayers();
		gameStart();
	}
	
	public void initPlayers(){
		player = new Agent[4];
		player[0] = new Agent(11, "Athlete", 1);
		player[1] = new Agent(9, "Slacker", 2);
		player[2] = new Agent(10, "Genius", 3);
		player[3] = new Agent(10, "Rich Girl", 4);
		
		//Set starting locations for players:
		board.agentMove(player[1], 3, 0);
		board.agentMove(player[2], 7, 0);
		board.agentMove(player[3], 10, 0);
		board.agentMove(player[0], 0, 0);
		
		System.out.println("\nGame start!");
		board.debug_printBoard();
	}
	
	public void initBoard(){
		board = new Board();
	}
	
	public void gameStart(){
		while(!mainloop());
	}
	
	private boolean mainloop(){
		board.debug_printBoard();
		CommandPhase();
		ActionPhase();
		return ExecutionPhase();
	}
	
	private void CommandPhase(){
		/*	show console/compiler - Inputbox.class
		//	take input
		for(int i=0; i < 4; i++){
			// open compiler for player[i]
		}
		// form actlist here
		 * 
		 */
		
		// Pang test lang ng move commands:
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			System.out.print("Input command: ");
			String input = br.readLine();
			if(input.contentEquals("move")) move(br);
			else if(input.contentEquals("interact")) interact(br);
			
		}catch(Exception e){}
		// Endtest
	}
	
	private void ActionPhase(){
		// for each action in actlist:
		//	- execute action
		//		- move1
		//		- move2
		//		- move3
		//		- item
		//		- power
		//		- interact
		// for each agent, check bufflist; modify AP of agent, reduce duration in bufflist
		// define actlist = list of player actions in order
		// for each action in actlist do:
		// 	execute actions
		//		Item commands: refer to item table
		//		Admin commands: refer to admin table
	}
	
	private boolean ExecutionPhase(){
		// process jeepneys
		//		- for each player marked traveling:
		//			- reduce cash by 7, transport to next terminal
		//			- set player.traveling to false
		// enlistment
		// incoming stress
		// check for faint
		// check win con:
		// 		returns true pag may nanalo na
		return false;
	}
	
	private void move(BufferedReader br) throws Exception{
		System.out.print("Input player (0-3): ");
		int i = Integer.parseInt(br.readLine());
		System.out.print("Input x: ");
		int x = Integer.parseInt(br.readLine());
		System.out.print("Input y: ");
		int y = Integer.parseInt(br.readLine());
		board.agentMove(player[i], x, y);
	}
	
	private void interact(BufferedReader br) throws Exception{
		System.out.print("Input player (0-3): ");
		int i = Integer.parseInt(br.readLine());
		board.agentInteract(player[i]);
	}
}