
//	- Board is a 17x11 2D array generated from board.txt
//	- value of each cell corresponds to type of cell
//	: 0 - normal
//	: 1..4 - spawn/start point for players
// 	: e0 - enlistment cell, where (0) is replaced by the type of subject (1..5)
//	: f - food cell
//	: i - item cell
//	: j - jeepney terminal
//	: ' ' - unpassable

package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class GameBoard {
	public Cell[][] cell;
	int NUMBER_OF_TERMINALS = 0;

	static final char ENLIST_CHARACTER = 'e';
	static final char JEEP_CHARACTER = 'j';
	static final char FOOD_CHARACTER = 'f';
	static final char ITEM_CHARACTER = 'i';
	static final char WALL_CHARACTER = '_';
	
	public GameBoard() {
		boolean meta = false;
		Scanner s = null;
		
		try{
		FileReader reader = new FileReader("src/data/board.txt");
		s = new Scanner(new BufferedReader(reader));
		cell = new Cell[11][17];
		int x=0, y=0;
		Cell c;
		
		while(s.hasNext()){
			for(char input : s.next().toCharArray()){
				if(meta){
					meta = false;
					cell[x][y].setMeta(Character.getNumericValue(input));
					x++;
				}
				else{
					c = cell[x][y] = new Cell(input, x, y);
					if(input == WALL_CHARACTER){ c.celltype = ' '; c.passable = false; }
					
					if (input == ENLIST_CHARACTER) meta = true;
					else if( input == JEEP_CHARACTER){ meta = true; NUMBER_OF_TERMINALS++;}
					else x++;
					
				}
				
			}
			y++;
			x=0;
		}
		
		} catch (Exception e){}
	}
	
	
	
	public boolean isCellPassable(int x, int y){
		return cell[x][y].passable;
	}
	
	public boolean isCellOccupied(int x, int y){
		if(cell[x][y].getOccupant() != null) return true;
		return false;
	}
	
	
	public Cell getNextTerminal(Cell current){
		Cell c = null;
		int destination;
		int meta = current.getMeta();
		if(meta == NUMBER_OF_TERMINALS) destination = 1;
		else destination = meta + 1;
		
		for(int y=0; y<17; y++){
			for(int x=0; x<11; x++){
				c = cell[x][y];
				if(c.getMeta() == destination & c.celltype == JEEP_CHARACTER) return c;
			}
		}
		return null;
		
	}
	
	public void agentMove(Agent a, int x, int y){
		if(a == null | x < 0 | x > 11 | y < 0 | y > 16){ 
			printf("Invalid cell: " + coors(x,y)); 
			return;
		}
		
		if(!isCellOccupied(x,y) && isCellPassable(x,y)){
			cell[a.x][a.y].Occupy(null);
			cell[x][y].Occupy(a);
			a.setPos(x,y);
			a.traveling = false;
			printf("Move command for "+ a.name + ":" + coors(x,y));
		}
		
		// Collision handling:
		else if(isCellOccupied(x,y)) agentCollide(a, cell[x][y].getOccupant());
		
		//Impassable cell:
		else if(!isCellPassable(x,y)) printf("Impassable cell" + coors(x,y));
	}
	
	public void agentJeep(Agent a){
		Cell c = cell[a.x][a.y];
		Cell t = getNextTerminal(c);
		agentMove(a, t.x, t.y);
		a.traveling = false;
	}
	
	private void agentCollide(Agent bully, Agent target){
		int x = target.x, y = target.y;
		printf("Collision detected: " + bully.name + " vs. " + target.name);
		cell[bully.x][bully.y].Occupy(target);
		cell[target.x][target.y].Occupy(bully);
		target.setPos(bully.x, bully.y);
		bully.setPos(x, y);
		
		target.applyStress(1);
	}
	
	public void agentInteract(Agent a){
		Cell c = cell[a.x][a.y];
		int meta = c.meta;
		char celltype = c.celltype;
		
		if(meta == 0) printf("Uninteractable cell");
		else{
			if (celltype == ENLIST_CHARACTER){ 
				a.enlistClass(meta); 
				printf("Enlisted to class " + celltype);
			}
			else if(celltype == JEEP_CHARACTER){
				if(a.rideJeep()){
					c.Occupy(null);
					printf(a.name + ": Traveling to next terminal.");
				}
				else printf("You require additional pylons.");
			}
		}
	}
	
	
	
	// Lahat ng functions sa baba pang print lang ng board sa console
	//	Sobrang gulo tingnan pero promise, gumagana yan. Tiwala lang.
	public void debug_printBoard(){
		printf("\n----------------------------------");
		System.out.print("\t");
		for(int x=0; x<11; x++){
			System.out.print(x + " ");
		}
		printf("\n");
		for(int y = 0; y < 17; y++){
			System.out.print(y + "\t");
			for (int x = 0; x < 11; x++){
				Cell c = cell[x][y];
				if(!isCellOccupied(x,y)) System.out.print(c.celltype + " ");
				else System.out.print(c.occupant.name.charAt(0) + " ");
			}
			printf("");
		}
		printf("\n----------------------------------");
	}
	
	public void printf(String s){
		System.out.println(s);
	}
	
	public String coors(int x, int y){
		return "(" + x + "," + y + ")";
	}
}
