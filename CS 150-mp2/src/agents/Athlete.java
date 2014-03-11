package agents;

import data.Agent;

//Agent: Athlete
//	- Action Ability: Trackstar - Gets 2 AP when stress is below 3.
//		"Point A to point B, 50 meters, 3.8 seconds."

public class Athlete extends Agent{
	
	public Athlete(int startx, int starty){
		super(11, "Athlete", startx, starty);
	}
	
	@Override
	public void actionProcess(){
		setAP(5);
		processBuffs();
		recover();
		abil_Trackstar();
	}
	
	private void abil_Trackstar(){
		if(getStress() <= 3) addAP(2);
	}
}
