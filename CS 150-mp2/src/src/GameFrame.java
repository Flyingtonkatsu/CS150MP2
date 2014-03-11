package src;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import src.Container;
import src.Util.Panel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	
	MenuPanel menupanel;
	GamePanel gamepanel;
	CreditsPanel creditspanel;
	HelpPanel helppanel;
	IntroPanel intropanel;
	
	Container container;
	static InputWindow window;
	static GameFrame self;
	
	public GameFrame(){
		super("Haggard Games");
		
		initComponents();
		addComponents();
		
		setFrame();
	}

	private void setFrame() {
		setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(false);
	}

	public void addListener(Controller controller) {
		gamepanel.pause.addActionListener(controller);
		
		helppanel.menu.addActionListener(controller);
		
		creditspanel.menu.addActionListener(controller);
		
		menupanel.play.addActionListener(controller);
		menupanel.help.addActionListener(controller);
		menupanel.credits.addActionListener(controller);
		menupanel.exit.addActionListener(controller);
		
		menupanel.play.addMouseListener(controller);
		menupanel.help.addMouseListener(controller);
		menupanel.credits.addMouseListener(controller);
		menupanel.exit.addMouseListener(controller);
	}

	private void addComponents() {
		add(gamepanel);
		add(helppanel);
		add(creditspanel);
		add(menupanel);
		add(intropanel);
	}

	private void initComponents() {
		self = this;
		
		creditspanel = new CreditsPanel();
		helppanel = new HelpPanel();
		gamepanel = new GamePanel();
		menupanel = new MenuPanel();
		intropanel = new IntroPanel();
		
		container = new Container();
		window = new InputWindow();
		window.setSize(480, 240);
		new Controller(this);
	}
	
	class InputWindow extends JDialog{
		
		public InputWindow(){
			super(self, true);
			setLayout(new BorderLayout());
			setVisible(false);
			setResizable(false);
		}
		
		public void showWindow(){
			
			add(container);
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
			
		}
	}
	
	class IntroPanel extends Panel implements Runnable{
		Util.Label logo;
		ImageIcon imageLogo[] = new ImageIcon[2];
		
		
		public IntroPanel() {
			super(null, Util.rect(0, 0, 800, 600));
			loadImages();
			load();
		}

		private void loadImages() {
			for(int i=0; i<2; i++)
				imageLogo[i] = new ImageIcon("images/intro/intro"+i+".png");
		}

		private void load(){
			logo = new Util.Label(imageLogo[0], Util.rect(0, 0, 800, 600));
			add(logo);
		}
		
		public void startIntro(){
			new Thread(this).start();
		}
		
		public void run(){
			for(int i = 0; i < 10; i++, Util.delay(0.250))
				logo.setIcon(imageLogo[i%2]);
			self.menupanel.showPanel();
			hidePanel();
			logo = null;
			imageLogo[0] = imageLogo[1] = null;
			Util.collectGarbage();
		}
	}
}