package agents;

import data.Agent;

//Agent: Genius
//	- Action Ability: Advanced Study - filler.
//		"80% smarts, 1% luck, 19% caffeine."

public class Genius extends Agent{
	
	public Genius(int startx, int starty){
		super(10, "Genius", startx, starty);
	}
	
	@Override
	public void actionProcess(){
		setAP(5);
		processBuffs();
		recover();
		abil_AdvancedStudy();
	}
	
	private void abil_AdvancedStudy(){
		if(getStress() <= 3) addAP(2);
	}
}
