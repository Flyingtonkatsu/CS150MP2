package data;

public class Cell {
	static final char WALL_CHARACTER = ' ';
	
	boolean passable;
	Agent occupied;
	char celltype;
	int meta = 0;
	
	public Cell(char value){
		occupied = null;
		celltype = value;
		
		if(value == WALL_CHARACTER) passable = false;
		else passable = true;
	}
	
}
