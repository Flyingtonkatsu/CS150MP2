package main;

import javax.swing.JFrame;
import src.GameFrame;
import src.GameLogic;


public class MainClass {

	public static void main(String args[]){

		boolean debugg = true;
		
		if(debugg) new GameLogic();
		
		else{
			GameFrame frame = new GameFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setUndecorated(true);
			frame.setSize(800, 600);
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);
			frame.setVisible(true);
		}
	}
}