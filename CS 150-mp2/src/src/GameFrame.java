package src;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	
	MenuPanel menupanel;
	GamePanel gamepanel;
	CreditsPanel creditspanel;
	HelpPanel helppanel;

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
		setVisible(true);
	}

	public void addListener(Controller controller) {
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
	}

	private void initComponents() {
		self = this;
		
		creditspanel = new CreditsPanel();
		helppanel = new HelpPanel();
		gamepanel = new GamePanel();
		menupanel = new MenuPanel();
		
		container = new Container();
		window = new InputWindow();
		window.setSize(480, 240);
		new Controller(this);
	}
	
class InputWindow extends JDialog{
		
		public InputWindow(){
			super(self, true);
			setLayout(new BorderLayout());
			setTitle("Input Box");
			setVisible(false);
			setResizable(false);
		}
		
		public void showWindow(){
			
			add(container);
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		}
	}
}
