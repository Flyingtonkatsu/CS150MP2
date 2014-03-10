package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements ActionListener, MouseListener{
	
	GameFrame frame;
	
	public Controller(GameFrame frame){
		this.frame = frame;
		frame.addListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		frame.menupanel.infos.setVisible(true);
		checkSource(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		frame.menupanel.infos.setVisible(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
	public void checkSource(MouseEvent e) {
		if(e.getSource() == frame.menupanel.play){
			frame.menupanel.infos.setIcon(frame.menupanel.infoIcon[0]);
			frame.menupanel.infos.setBounds(234, 538, 354, 46);
		}
		
		else if(e.getSource() == frame.menupanel.help){
			frame.menupanel.infos.setIcon(frame.menupanel.infoIcon[1]);
			frame.menupanel.infos.setBounds(247, 530, 322, 65);
		}
		
		else if(e.getSource() == frame.menupanel.credits){
			frame.menupanel.infos.setIcon(frame.menupanel.infoIcon[2]);
			frame.menupanel.infos.setBounds(261, 542, 285, 39);
		}
		
		else if(e.getSource() == frame.menupanel.exit){
			frame.menupanel.infos.setIcon(frame.menupanel.infoIcon[3]);
			frame.menupanel.infos.setBounds(226, 535, 374, 49);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == frame.menupanel.play){
			frame.menupanel.hidePanel();
			frame.gamepanel.showPanel();
			frame.window.showWindow();
		}
		
		else if(event.getSource() == frame.menupanel.help){
			frame.menupanel.hidePanel();
			frame.helppanel.showPanel();
		}
		
		else if(event.getSource() == frame.menupanel.credits){
			frame.menupanel.hidePanel();
			frame.creditspanel.showPanel();
		}
		
		else if(event.getSource() == frame.menupanel.exit){
			System.gc();
			System.exit(0);
		}
		
		else if(event.getSource() == frame.creditspanel.menu){
			frame.creditspanel.hidePanel();
			frame.menupanel.showPanel();
		}
		
		else if(event.getSource() == frame.helppanel.menu){
			frame.helppanel.hidePanel();
			frame.menupanel.showPanel();
		}
	}

}
