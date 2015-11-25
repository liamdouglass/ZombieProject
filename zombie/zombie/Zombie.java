package zombie;

import java.awt.Color;
import java.util.Random;

public class Zombie {
	
	private Direction dir;
	private Color last=Color.BLACK;
	private Random rand = new Random();
	private ZombieModel model;
	private boolean seeHuman=false;
	
	//coordinates of Zombie Start
	private int x;
	private int y;
	
	public Zombie(ZombieModel modelAvg) {
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
		model.setColor(x,y,Color.RED);
		dir = dir.NORTH;
		
	}
	public Direction getDirection() { return dir; }
	public void setDirection(Direction dirAvg) {
		dir = dirAvg;
	}
	public void update() { 
		int random = rand.nextInt(100);
//		
//		if((dir == dir.NORTH) ) {
//			if(y != 0 && (model.getColor(x, y-1)== Color.BLACK||
//					model.getColor(x, y-1)== Color.BLUE)) {
//				model.setColor(x, y, last);
//				last=model.getColor(x,y-1);
//				model.setColor(x, y-1, Color.RED);
//				y--;
//			}	
//		} 
//		else if((dir == dir.EAST )) {
//			if(x != model.getWidth() - 1 && (model.getColor(x+1, y)== Color.BLACK||
//					model.getColor(x+1, y)== Color.BLUE)) {
//				model.setColor(x, y, last);
//				last=model.getColor(x+1,y);
//				model.setColor(x+1, y, Color.RED);
//				x++;
//			}
//		} 
//		else if((dir == dir.SOUTH )) {
//			if(y != model.getHeight() - 1 && (model.getColor(x, y+1)== Color.BLACK||
//				model.getColor(x, y+1)== Color.BLUE)) {
//				model.setColor(x, y, last);
//				last=model.getColor(x,y+1);
//				model.setColor(x, y+1, Color.RED);
//				y++;
//			}
//		} 
//		else if((dir == dir.WEST)) {
//			if(x != 0&& (model.getColor(x-1, y)== Color.BLACK||
//				model.getColor(x-1, y)== Color.BLUE)) {
//				model.setColor(x, y, last);
//				last=model.getColor(x-1,y);
//				model.setColor(x-1, y, Color.RED);
//				x--;
//			}
//		} 
		if((dir == dir.NORTH) ) {
			if(y>10){
				for(int i=0;i<10;i++){
					if(model.getColor(x, y-i)==Color.WHITE||model.getColor(x, y-i)==Color.YELLOW){
						dir=dir.NORTH;
						seeHuman=true;
					}
					else seeHuman=false;
				}
			}
			if(y != 0 && (model.getColor(x, y-1)== Color.BLACK||
					model.getColor(x, y-1)== Color.BLUE)) {
				model.setColor(x, y, last);
				model.setColor(x, y, last);
				last=model.getColor(x,y-1);
				model.setColor(x, y-1, Color.RED);
				y--;
			}
			if(random<10) dir= dir.SOUTH;
			if(random<30&&random>=10)dir=dir.EAST;
			if(random<50&&random>=30)dir=dir.WEST;
		} 
		else if((dir == dir.EAST )) {
			if(x<model.getWidth()-10){
				for(int i=0;i<10;i++){
					if(model.getColor(x+i, y)==Color.WHITE||model.getColor(x+i, y)==Color.YELLOW){
						dir=dir.EAST;
						seeHuman=true;
					}
					else seeHuman=false;
				}
			}
			if(x != model.getWidth() - 1 && (model.getColor(x+1, y)== Color.BLACK||
					model.getColor(x+1, y)== Color.BLUE)) {

				model.setColor(x, y, Color.BLACK);
				model.setColor(x, y, last);
				last=model.getColor(x+1,y);
				model.setColor(x+1, y, Color.RED);
				x++;
			}
			if(random<10) dir= dir.WEST;
			if(random<30&&random>=10)dir=dir.NORTH;
			if(random<50&&random>=30)dir=dir.SOUTH;
		} 
		else if((dir == dir.SOUTH )) {
			if(y<model.getHeight()-10){
				for(int i=0;i<10;i++){
					if(model.getColor(x, y+i)==Color.WHITE||model.getColor(x, y+i)==Color.YELLOW){
						dir=dir.SOUTH;
						seeHuman=true;
					}
					else seeHuman=false;
				}
			}
			if(y != model.getHeight() - 1 && (model.getColor(x, y+1)== Color.BLACK||
					model.getColor(x, y+1)== Color.BLUE)) {
				model.setColor(x, y, Color.BLACK);
				model.setColor(x, y, last);
				last=model.getColor(x,y+1);
				model.setColor(x, y+1, Color.RED);
				y++;
			}
			if(random<10) dir= dir.NORTH;
			if(random<30&&random>=10)dir=dir.WEST;
			if(random<50&&random>=30)dir=dir.EAST;
		} 
		else if((dir == dir.WEST)) {
			if(x>10){
				for(int i=0;i<10;i++){
					if(model.getColor(x-i, y)==Color.WHITE||model.getColor(x-i, y)==Color.YELLOW){
						dir=dir.WEST;
						seeHuman=true;
					}
					else seeHuman=false;
				}
			}
			if(x !=0&& (model.getColor(x-1, y)== Color.BLACK||
					model.getColor(x-1, y)== Color.BLUE)) {
				model.setColor(x, y, Color.BLACK);
				model.setColor(x, y, last);
				last=model.getColor(x-1,y);
				model.setColor(x-1, y, Color.RED);
				x--;
			}
			if(!seeHuman){
				if(random<10) dir= dir.EAST;
				if(random<30&&random>=10)dir=dir.NORTH;
				if(random<50&&random>=30)dir=dir.SOUTH;
			}
		} 
	}
}