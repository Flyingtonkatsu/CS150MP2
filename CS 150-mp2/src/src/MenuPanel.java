package src;


import javax.swing.ImageIcon;

import src.Util.Button;
import src.Util.Label;
import src.Util.Panel;

@SuppressWarnings("serial")
public class MenuPanel extends Panel{
	
	ImageIcon bg, playORG, playRO, helpORG, helpRO, creditsORG, creditsRO, exitORG, exitRO, infoIcon[] = new ImageIcon[4];
	Label menubg, infos;
	Button play, help, credits, exit;
	
	public MenuPanel(){
		super(null, Util.rect(0,0,800,600));
		
		loadImages();
		initComponents();
		addComponents();
	}

	private void addComponents() {
		add(exit);
		add(credits);
		add(help);
		add(play);
		
		add(infos);
		add(menubg);
	}

	private void initComponents() {
		exit = new Button(exitORG, exitRO, Util.rect(425, 427, 99, 25));
		credits = new Button(creditsORG, creditsRO, Util.rect(286, 369, 128, 25));
		help = new Button(helpORG, helpRO, Util.rect(388, 305, 100, 28));
		play = new Button(playORG, playRO, Util.rect(300, 228, 95, 23));
		
		infos = new Label(infoIcon[0], Util.rect(234, 538, 354, 46));
		infos.setVisible(false);
		menubg = new Label(bg, Util.rect(0, 0,800, 600));
	}

	private void loadImages() {
		bg = new ImageIcon("images/menu/menu.gif");
		playORG = new ImageIcon("images/menu/playORG.png");	
		playRO = new ImageIcon("images/menu/playRO.png");
		helpORG = new ImageIcon("images/menu/helpORG.png");
		helpRO = new ImageIcon("images/menu/helpRO.png");
		creditsORG = new ImageIcon("images/menu/creditsORG.png");
		creditsRO = new ImageIcon("images/menu/creditsRO.png");
		exitORG = new ImageIcon("images/menu/exitORG.png");
		exitRO = new ImageIcon("images/menu/exitRO.png");
		for(int i=0; i<4; i++)
			infoIcon[i] = new ImageIcon("images/menu/info"+(i+1)+".png");
	}
}