package src;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import src.Util.Button;
import src.Util.Label;
import src.Util.Panel;


@SuppressWarnings("serial")
public class GamePanel extends Panel{
	
	ImageIcon bg, pauseRO, pauseORG;
	Label bgLbl;
	Board board;
	Button pause;
	
	public GamePanel(){
		super(null, Util.rect(0, 0, 800, 600));
		hidePanel();
		loadImages();
		initComponents();
		addComponents();
	}

	private void loadImages() {
		pauseORG = new ImageIcon("images/play/pauseORG.png");
		pauseRO = new ImageIcon("images/play/pauseRO.png");
		bg = new ImageIcon("images/play/game board.png");
	}

	private void addComponents() {
		add(pause);
		add(board);
		add(bgLbl);
	}

	private void initComponents() {
		pause = new Button(pauseORG, pauseRO, Util.rect(602, 500, 197, 99));
		board = new Board();
		bgLbl = new Label(bg, Util.rect(0,0,800,600));
	}
	
	class Board extends Panel{
		
		ImageIcon tile;
		JLabel tiles[][] = new JLabel[17][11];
		final int ROW=17, COL=11;
		
		public Board(){
			super(new GridLayout(17, 11, 1, 1), Util.rect(67, 2, 388, 600));
			showPanel();
			loadImage();
			initComponents();
		}

		private void loadImage() {
			tile = new ImageIcon("images/play/tile.png");
		}

		private void initComponents() {
			for(int i=0; i<ROW; i++){
				for(int j=0; j<COL; j++){
					tiles[i][j] = new JLabel(tile);
					add(tiles[i][j]);
				}
			}
		}
	}
}