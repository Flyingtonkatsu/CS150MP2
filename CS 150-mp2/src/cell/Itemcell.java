package cell;
import data.*;

public class Itemcell extends Cell{
	
	public Itemcell(char celltype, int x, int y){
		super(celltype, x, y);
	}
	
	@Override
	public void onLand(){
		//give item to player
	}
	
}
