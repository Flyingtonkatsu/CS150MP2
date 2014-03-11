package agents;

import data.Agent;

// Agent: CS STUDENT
//	- Execution Ability: Hacking - Admin powers cool down twice as fast.
//		"There is no spoon. And sleep."

public class CSStudent extends Agent{
	
	public CSStudent(int startx, int starty){
		super(9, "CS Student", startx, starty);
	}
	
	@Override
	public void execProcess(){
		execStress();
		execCooldown();
		abil_Hacking();
	}
	
	private void abil_Hacking(){
		if(getStress() <= 6 & getStress() >= 3) execCooldown();
	}
}
