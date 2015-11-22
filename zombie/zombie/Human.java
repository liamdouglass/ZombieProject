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
		int random = rand.nextInt(100);
		boolean seeZombie=false;
		if((dir == dir.NORTH) ) {
			if(y != 0 && (model.getColor(x, y-1)== Color.BLACK)) {
//				for(int i=0;i<10;i++){
//					if(model.getColor(x, y-i)== Color.RED){
//						seeZombie=true;
//					}
//				}
				model.setColor(x, y, last);
				last=model.getColor(x,y-1);
				model.setColor(x, y-1, Color.WHITE);
				y--;
			}
			if(random<5) dir= dir.SOUTH;
			if(random<15&&random>=5)dir=dir.EAST;
			if(random<25&&random>=15)dir=dir.WEST;
		} 
		else if((dir == dir.EAST )) {
			if(x != model.getWidth() - 1 && (model.getColor(x+1, y)== Color.BLACK)) {
//				for(int i=0;i<10;i++){
//					if(model.getColor(x+i, y)== Color.RED){
//						seeZombie=true;
//					}
//				}
				model.setColor(x, y, Color.BLACK);
				last=model.getColor(x+1,y);
				model.setColor(x+1, y, Color.WHITE);
				x++;
			}
			if(random<5) dir= dir.WEST;
			if(random<15&&random>=5)dir=dir.NORTH;
			if(random<25&&random>=15)dir=dir.SOUTH;
		} 
		else if((dir == dir.SOUTH )) {
			if(y != model.getHeight() - 1 && model.getColor(x, y+1)== Color.BLACK) {
//				for(int i=0;i<10;i++){
//					if(model.getColor(x, y+i)== Color.RED){
//						seeZombie=true;
//					}
//				}
				model.setColor(x, y, Color.BLACK);
				last=model.getColor(x,y+1);
				model.setColor(x, y+1, Color.WHITE);
				y++;
			}
			if(random<5) dir= dir.NORTH;
			if(random<15&&random>=5)dir=dir.WEST;
			if(random<25&&random>=15)dir=dir.EAST;
		} 
		else if((dir == dir.WEST)) {
			if(x != 0 && model.getColor(x-1, y)== Color.BLACK) {
				model.setColor(x, y, Color.BLACK);
				last=model.getColor(x-1,y);
				model.setColor(x-1, y, Color.WHITE);
				x--;
			}
			if(random<5) dir= dir.EAST;
			if(random<15&&random>=5)dir=dir.NORTH;
			if(random<25&&random>=15)dir=dir.SOUTH;
		} 
	}
	
}


