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
	Buff bufflist[];				//List of buffs; explain ko next time
	
	public Agent(int maxstress, String name, int startx, int starty){
		this.cash = 35;
		this.ap = 5;
		this.stress = 0;
		this.name = name;
		this.maxstress = maxstress;
		this.inventory = new Item[2];
		this.bufflist = new Buff[10];
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
	
	public void addCash(int i){
		cash = cash + i;
	}
	
	public void addAP(int i){
		ap = ap + i;
	}
	
	public void setAP(int i){
		ap = i;
	}
	
	public int getAP(){
		return ap;
	}
	
	public int execStress(){
		stress =+ incstress;
		incstress = 0;
		return stress;
	}
	
	public void execProcess(){
		execStress();
		execCooldown();
	}
	
	public int execCooldown(){
		if(cooldown > 0) cooldown--;
		return cooldown;
	}
	
	public void actionProcess(){
		setAP(5);
		processBuffs();
		recover();
	}
	
	public void addBuff(Buff b){
		for(int i = 0; i < 10; i++){
			if (bufflist[i] == null) {
				bufflist[i] = b;
				break;
			}
		}
	}
	
	public void processBuffs(){
		for(int i=0; i<10; i++){
			if(bufflist[i] != null){
				addAP(bufflist[i].value);
				if(bufflist[i].countdown()) bufflist[i] = null;
			}
		}
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
	
	public int getStress(){
		return stress;
	}
	
	public int getIncStress(){
		return incstress;
	}
	
	public void addStress(int astress){
		incstress = incstress + astress;
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
