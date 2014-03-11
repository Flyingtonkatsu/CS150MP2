package agents;

import data.Agent;

//Agent: Rich Kid
//	- Init Ability: Bourgeoisie - Starts the game with 50 pesos instead of 35.
//		"I can buy you, your friends, and that jeep. WAIT LANG MANONG PARAH POH."

public class RichKid extends Agent{
	
	public RichKid(int startx, int starty){
		super(11, "RichKid", startx, starty);
		this.addCash(15);
	}
}
