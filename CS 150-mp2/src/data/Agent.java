package data;

public class Agent {
	
	int cash;							//pera
	int ap;								//remaining number of actions of agent
	int startpos;						//starting point of agent (1..4)
	String name;						//agent name
	int stress;							//current stress
	int maxstress;						//max stress bago mag-faint
	int incstress;						//incoming stress, evaluated on Exec. phase
	boolean traveling;					//marked true kung sasakay ng jeep yung agent
	int x;								//x position of agent
	int y;								//y position of agent
	boolean mst, ssp, ah, pe, nstp;		//listahan ng enlisted classes
	Item inventory[];					//Inventory ng agent, size 2
	Buff bufflist[] = {};				//List of buffs; explain ko next time
	
	public Agent(int maxstress, String name, int startpos){
		this.cash = 35;
		this.ap = 0;
		this.stress = 0;
		this.name = name;
		this.x = 0;
		this.y = 0;
		this.maxstress = maxstress;
		this.inventory = new Item[2];
		this.traveling = false;
		this.incstress = 0;
		this.mst = false;
		this.ah = false;
		this.ssp = false;
		this.pe = false;
		this.nstp = false;
		this.startpos = startpos;
	}
	
	public void setPos(int newx, int newy){
		x = newx;
		y = newy;
	}
	
	public void processStress(){
		stress =+ incstress;
		incstress = 0;
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
}
