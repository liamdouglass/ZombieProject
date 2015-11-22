package zombie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

public class ZombieController implements MouseListener {

	private final ZombieModel model;
	private final ZombieView view;
	private final int delay;
	
	public ZombieController(ZombieModel modelArg, ZombieView viewArg, int sleepTimeArg) {
		model = modelArg;
		view = viewArg;
		delay = sleepTimeArg;
	}

	public void beginSimulation() {
		model.initialize();
		view.draw();
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.update();
				view.draw();
			}
		};
		new Timer(delay, taskPerformer).start();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int dot = 15 * model.getDotSize();
		int dot1 = 45 * model.getDotSize();
		int dot2 = 40 * model.getDotSize();
		if(e.getX() < dot2 && e.getY() > dot && e.getY() < dot1) model.getZombie().setDirection(Direction.WEST);
		else if(e.getX() > dot2 && e.getY() > dot && e.getY() < dot1) model.getZombie().setDirection(Direction.EAST);
		else if(e.getY() < dot1) model.getZombie().setDirection(Direction.NORTH);
		else if(e.getY() > dot) model.getZombie().setDirection(Direction.SOUTH);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}