package src;
import javax.swing.ImageIcon;

import src.Util.Label;
import src.Util.Panel;


@SuppressWarnings("serial")
public class GamePanel extends Panel{
	
	ImageIcon bg;
	Label bgLbl;
	
	public GamePanel(){
		super(null, Util.rect(0, 0, 800, 600));
		hidePanel();
		loadImages();
		initComponents();
		addComponents();
	}

	private void loadImages() {
		bg = new ImageIcon("images/play/gameplay.png");
	}

	private void addComponents() {
		add(bgLbl);
	}

	private void initComponents() {
		bgLbl = new Label(bg, Util.rect(0,0,800,600));
	}
	
}