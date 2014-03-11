package agents;

import data.Agent;

//Agent: Slacker
//	- Execution Ability: Chill - reduces incoming stress per turn by 1.
//		"I make procrastinating an art form, and this is my masterpiece."

public class Slacker extends Agent{
	
	public Slacker(int startx, int starty){
		super(9, "Slacker", startx, starty);
	}
	
	@Override
	public void execProcess(){
		abil_Chill();
		execStress();
		execCooldown();
	}
	
	private void abil_Chill(){
		if(getIncStress() >= 1) addStress(-1);
	}
}
