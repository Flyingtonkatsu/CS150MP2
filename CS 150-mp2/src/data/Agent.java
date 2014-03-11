package data;

public class Agent {
	
	int cash;							//pera
	int ap;								//remaining number of actions of agent
	int startx;							//starting point of agent
	int starty;
	int cooldown;						//cooldown of admin power;
	public String name;					//agent name
	int stress;							//current stress
	int maxstress;						//max stress bago mag-faint
	int incstress;						//incoming stress, evaluated on Exec. phase
	boolean fainted;
	boolean traveling;					//marked true kung sasakay ng jeep yung agent
	public int x;								//x position of agent
	public int y;								//y position of agent
	boolean mst, ssp, ah, pe, nstp;		//listahan ng enlisted classes
	Item inventory[];					//Inventory ng agent, size 2
	Buff bufflist[] = {};				//List of buffs; explain ko next time
	
	public Agent(int maxstress, String name, int startx, int starty){
		this.cash = 35;
		this.ap = 5;
		this.stress = 0;
		this.name = name;
		this.maxstress = maxstress;
		this.inventory = new Item[2];
		this.traveling = false;
		this.incstress = 0;
		this.mst = false;
		this.ah = false;
		this.ssp = false;
		this.pe = false;
		this.nstp = false;
		this.startx = startx;
		this.starty = starty;
		this.fainted = false;
	}
	
	public void setPos(int newx, int newy){
		x = newx;
		y = newy;
	}
	
	
	public void setAP(int i){
		ap = i;
	}
	
	public int getAP(){
		return ap;
	}
	
	public void executionProcess(){
		stress =+ incstress;
		incstress = 0;
		if(cooldown > 0) cooldown--;
	}
	
	
	public boolean giveItem(Item newitem){
		for(int i=0; i<2; i++){
			if(inventory[i] == null) {
				inventory[i] = newitem;
				return true;
			}
		}
		return false;
	}
	
	
	public void applyStress(int astress){
		incstress =+ astress;
	}
	
	public void enlistClass(int subj){
		switch(subj){
			case 1: mst = true;
			case 2: ah = true;
			case 3: ssp = true;
			case 4: pe = true;
			case 5: nstp = true;
		}
	}
	
	public boolean rideJeep(){
		if(cash >= 7){ 
			cash = cash - 7;
			ap = 0; 
			traveling = true; 
			return true; 
		}
		else return false;
	}
	
	public boolean isTraveling(){
		return traveling;
	}
	
	public boolean isFainted(){
		return fainted;
	}
	
	public void recover(){
		fainted = false;
	}
}
