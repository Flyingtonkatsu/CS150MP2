package src;

import javax.swing.ImageIcon;

import src.Util.Label;
import src.Util.Panel;
import src.Util.TextArea;

@SuppressWarnings("serial")
public class Container extends Panel{
	

	ImageIcon compilerBG; 
	TextArea txtArea;
	Label bg;
	
	public Container(){
		super(null, Util.rect(0,0,480,240));

		loadImages();
		initComponents();
		addComponents();
	}
	
	private void addComponents() {
		add(txtArea.pane);
		add(bg);
	}

	private void loadImages() {
		compilerBG = new ImageIcon("images/play/compilerBG.png");
	}
	
	private void initComponents() {
		txtArea = new TextArea(Util.rect(19, 51, 303, 162));
		bg = new Util.Label(compilerBG, Util.rect(0, 0, 480, 240));
	}
}
