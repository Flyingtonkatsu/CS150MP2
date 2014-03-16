package src;
import java.util.Scanner;
import data.*;
import agents.*;


public class GameLogic extends Thread{
	
	Agent players[];		//list of players
	GameBoard board;
	String[][] cmdlist;
	boolean running = false;
	public GameFrame frame;
	private int player; 
	int[] order = {0, 1, 2, 3};
	
	public GameLogic(GameFrame f){
		frame = f;
		initGameElements();
	}
	
	public void stopRunning(){
		running = false;
	}
	
	public void run(){
		gameStart();
	}
	
	public void initGameElements(){
		board = new GameBoard();
		players = new Agent[4];
		
		running = true;
		
		cmdlist = new String[4][];
		
		players[2] = new Genius(this, 7, 0, frame.gamepanel.genius);
		players[1] = new Slacker(this, 3, 0, frame.gamepanel.slacker);
		players[0] = new Athlete(this, 0, 0, frame.gamepanel.athlete);
		players[3] = new RichKid(this, 10, 0, frame.gamepanel.richKid);
		
		//Set starting locations for playerss:
		board.agentMove(players[1], 3, 0);
		board.agentMove(players[2], 7, 0);
		board.agentMove(players[3], 10, 0);
		board.agentMove(players[0], 0, 0);
	}
	
	public void gameStart(){
		printf("\nGame start!");
		while(!mainloop() & running);
	}
	
	private boolean mainloop(){
		board.debug_printBoard();
		CommandPhase();
		ActionPhase();
		return ExecutionPhase();
	}
	
	private void CommandPhase(){
		
		for(int i=0; i < 4; i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			cmdlist[i] = new String[100];
			GameFrame.getWindow().setTitle("Input Box  (Player "+(i+1)+")");
			player = i;
			GameFrame.getWindow().showWindow();
		}
		
		for(int i=0; i<4; i++){
			for(int j = i+1; j<4; j++){
				if(players[order[j]].getStress() > players[order[i]].getStress()){
					int temp = order[i];
					order[i] = order[j];
					order[j] = temp;
				}
			}
		}
		
	}
	
	private void ActionPhase(){
		int maxln = -1;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		
		
		for(int i=0; i<4; i++){
			actAgent(players[order[i]]);
			maxln = maxln < cmdlist[i].length ? cmdlist[i].length : maxln;
		}
		
		printf("maxln = " + maxln);
		
		for(int i=0; i<maxln; i++){
			for(int j=0; j<4; j++){
				if(cmdlist[j].length > i)
					processCommand(players[j], cmdlist[j][i]);
			}
		}
		/* for each agent, check bufflist; modify AP of agent, reduce duration in bufflist
		// define actlist = list of players actions in order
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
			System.out.print("Input command for players "+ 0 +": ");
			try{
			input = br.readLine();
			} catch(IOException e){}
		} while(!processCommand(players[0], input));
		*/
	}
	
	private boolean ExecutionPhase(){
		for(int i=0; i<4; i++){
			Agent a = players[i];
		// process jeepneys;
			if(a.isTraveling()) exeJeep(a);
		// enlistment
		// incoming stress:
			exeAgent(a);
		// check for faint:
			if(a.isFainted()) {
				a.traveling = true;
				board.agentMove(a, a.startx, a.starty);
			}
		// check win con:
		// 		returns true pag may nanalo na
		}
		return false;
	}
	

	public void getCommands(String text) {
		cmdlist[player] = Compiler.parser(text);
		for(int i=0; i<cmdlist[player].length ; i++){
			printf("Command " + i + " for player " + player + ": " + cmdlist[player][i]);
		}
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
			
			if(i.trim().matches("up")) dy = -1;
			else if(i.trim().matches("down")) dy = 1;
			else if(i.trim().matches("right")) dx = 1;
			else if(i.trim().matches("left")) dx = -1;
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
		a.execProcess();
	}
	
	private void actAgent(Agent a){
		a.actionProcess();
	}
	
	private void actMove(Agent a, int x, int y){
		a.animateMovement(x, y);
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