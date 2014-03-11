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
		super(null, Util.rect(0,0,400,200));

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
		txtArea = new TextArea(Util.rect(8, 38, 265, 135));
		bg = new Label(compilerBG, Util.rect(0, 0, 400, 200));
	}
}