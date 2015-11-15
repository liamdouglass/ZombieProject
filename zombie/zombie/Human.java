package zombie;

import java.awt.Color;
import java.util.Random;

public class Human {
	
	private Direction dir;
	private Color last=Color.BLACK;
	private Random rand = new Random();
	private ZombieModel model;
	
	//coordinates of Human Start
	private int x;
	private int y;
	
	public Human(ZombieModel modelAvg) {
		model = modelAvg;
		//randomize
		x = rand.nextInt(model.getWidth());
		y = rand.nextInt(model.getHeight());
		boolean good = false;
		while(good == false) {
			if(model.getColor(x, y) == Color.BLACK) good = true;
			else {
				x = rand.nextInt(model.getWidth());
				y = rand.nextInt(model.getHeight());
			}
		}
		model.setColor(x,y,Color.WHITE);
		dir = dir.NORTH;
		
	}
	public Direction getDirection() { return dir; }
	public void setDirection(Direction dirAvg) {
		dir = dirAvg;
	}
	public void update() { 
		
		if((dir == dir.NORTH) ) {
			if(y != 0 && model.getColor(x, y-1)== Color.BLACK) {
				model.setColor(x, y, last);
				last=model.getColor(x,y-1);
				model.setColor(x, y-1, Color.WHITE);
				y--;
			}	
		} 
		else if((dir == dir.EAST )) {
			if(x != model.getWidth() - 1 && model.getColor(x+1, y-1)== Color.BLACK) {
				model.setColor(x, y, Color.BLACK);
				last=model.getColor(x+1,y);
				model.setColor(x+1, y, Color.WHITE);
				x++;
			}
		} 
		else if((dir == dir.SOUTH )) {
			if(y != model.getHeight() - 1 && model.getColor(x, y+1)== Color.BLACK) {
				model.setColor(x, y, Color.BLACK);
				last=model.getColor(x,y+1);
				model.setColor(x, y+1, Color.WHITE);
				y++;
			}
		} 
		else if((dir == dir.WEST)) {
			if(y != model.getHeight() - 1 && model.getColor(x-1, y)== Color.BLACK) {
				model.setColor(x, y, Color.BLACK);
				last=model.getColor(x-1,y);
				model.setColor(x-1, y, Color.WHITE);
				x--;
			}
		} 
	}
	
}


