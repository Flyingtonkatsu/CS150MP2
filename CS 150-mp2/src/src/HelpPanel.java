package src;

import javax.swing.ImageIcon;

import src.Util.Button;
import src.Util.Label;
import src.Util.Panel;

@SuppressWarnings("serial")
public class HelpPanel extends Panel{
	
	ImageIcon bgIcon, menuRO, menuORG;
	Label bg;
	Button menu;
	
	public HelpPanel(){
		super(null, Util.rect(0, 0, 800, 600));
		hidePanel();
		
		loadImages();
		initComponenets();
		addComponents();
	}
	
	private void addComponents() {
		add(menu);
		add(bg);
	}

	private void initComponenets() {
		menu = new Button(menuORG, menuRO, Util.rect(602, 500, 197, 99));
		bg = new Label(bgIcon, Util.rect(0,0,800,600));
	}

	private void loadImages() {
		bgIcon = new ImageIcon("images/help/bg.png");
		menuRO=new ImageIcon("images/help/menuRo.png");
		menuORG=new ImageIcon("images/help/menuOrg.png");
	}
}
