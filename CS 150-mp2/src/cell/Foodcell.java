package cell;
import data.*;

public class Foodcell extends Cell{
	
	public Foodcell(char celltype, int x, int y){
		super(celltype, x, y);
	}
	
	@Override
	public void onLand(){
		Agent a = this.getOccupant();
		int mod = a.getStress() / 2;
		a.addStress(-mod);
	}
	
}
