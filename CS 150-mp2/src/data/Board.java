
//	- Gameboard is a 17x11 2D array generated from board.txt
//	- value of each cell corresponds to type of cell
//	: 0 - normal
//	: 1..4 - spawn/start point for players
// 	: e - enlistment cell
//	: f - food cell
//	: i - item cell
//	: x - unpassable

package data;

import java.io.FileReader;

public class Board {
	Cell[][] cell;
	int numberOfTerminals;

	static final char ENLIST_CHARACTER = 'e';
	static final char SPACE_CHARACTER = ':';
	static final char JEEP_CHARACTER = 'j';
	static final char FOOD_CHARACTER = 'f';
	static final char ITEM_CHARACTER = 'i';
	
	public Board() {
		boolean enlist = false;
		int jeepcount = 1;
		try{
		FileReader reader = new FileReader("src/data/board.txt");
		cell = new Cell[11][17];
		char[] input = new char[212];
		reader.read(input);
		int x=0;
		int y=0;
		for(char c: input){
			if(enlist){ enlist = false; cell[x][y].meta = Character.getNumericValue(c);  System.out.println("Meta: " + cell[x][y].meta + ":" + c);x++;}
			else{
				if(x >= 11){ y++; x=0;}
				if(c != SPACE_CHARACTER){
					cell[x][y] = new Cell(c);
					if(c == JEEP_CHARACTER){
						cell[x][y].meta = jeepcount; 
						jeepcount++;
					}
					if(c == ENLIST_CHARACTER) enlist = true;
					else x++;
				}
			}
		}
		numberOfTerminals = jeepcount;
		} catch (Exception e){}
	}

	
	public boolean isCellPassable(int x, int y){
		return cell[x][y].passable;
	}
	
	public boolean isCellOccupied(int x, int y){
		if(cell[x][y].occupied != null) return true;
		return false;
	}
	
	public void agentMove(Agent a, int x, int y){
		if(a == null | x < 0 | x > 11 | y < 0 | y > 16 ){ System.out.println("Invalid cell: (" + x + "," + y +")"); return;}
		if(!isCellOccupied(x,y) && isCellPassable(x,y)){
			cell[a.x][a.y].occupied = null;
			cell[x][y].occupied = a;
			a.setPos(x,y);
			System.out.println("Move command for "+ a.name + ": (" + x + "," + y +")");
		}
	}
	
	public void agentInteract(Agent a){
		Cell c = cell[a.x][a.y];
		int meta = c.meta;
		char celltype = c.celltype;
				
		if(meta == 0) System.out.println("Uninteractable cell");
		else{
			if (celltype == ENLIST_CHARACTER){ a.enlistClass(meta); System.out.println("Enlisted to class " + celltype);}
			else if(celltype == JEEP_CHARACTER){
				int dest = meta + 1;
				if(dest > numberOfTerminals) dest = 1;
				System.out.println("Travelled to terminal " + dest);
			}
		}
	}
	
	
	//Pang print lang ng board sa console
	//	Sobrang gulo tingnan pero promise, gumagana yan. Tiwala lang.
	public void debug_printBoard(){
		
		System.out.print("\t");
		for(int x=0; x<11; x++){
			System.out.print(x + " ");
		}
		System.out.println("\n");
		for(int y = 0; y < 17; y++){
			System.out.print(y + "\t");
			for (int x = 0; x < 11; x++){
				Cell c = cell[x][y];
				if(!isCellOccupied(x,y)) System.out.print(c.celltype + " ");
				else System.out.print(c.occupied.name.charAt(0) + " ");
			}
			System.out.println();
		}
	}
}
