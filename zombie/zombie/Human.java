package zombie;

import java.awt.Color;
import java.util.Random;

public class Human {
	
	private Color color;
	private Direction dir;
	private Color last=Color.BLACK;
	private Random rand = new Random();
	private ZombieModel model;
	private boolean seeZombie;
	private boolean isZombie=false;
	private boolean isRunning=false;
	private boolean isStopped=false;
	private boolean isWalking=true;
	private boolean seeHuman=false;
	//coordinates of Human Start
	private int x;
	private int y;
	private int count;
	
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
	//======HUMAN======//
		if(isWalking){
			if(!isZombie&&!isRunning){
				if((dir == dir.NORTH) ) {
					if(y>10){
						for(int i=0;i<10;i++){
							if(model.getColor(x, y-i)==Color.MAGENTA||model.getColor(x, y-i)==Color.RED){
								dir=dir.SOUTH;
								isRunning=true;
								count=0;
							}
						}
					}
					if(y != 0 && ((model.getColor(x, y-1)== Color.BLACK)||(model.getColor(x, y-1)== Color.darkGray))) {
						model.setColor(x, y, last);
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
					if(x<model.getWidth()-10){
						for(int i=0;i<10;i++){
							if(model.getColor(x+i, y)==Color.MAGENTA||model.getColor(x+i, y)==Color.RED){
								dir=dir.WEST;
								isRunning=true;
								count=0;
							}
						}
					}
					if(x != model.getWidth() - 1 && ((model.getColor(x+1, y)== Color.BLACK)||(model.getColor(x+1, y)== Color.darkGray))) {
		
						model.setColor(x, y, Color.BLACK);
						model.setColor(x, y, last);
						last=model.getColor(x+1,y);
						model.setColor(x+1, y, Color.WHITE);
						x++;
					}
					if(random<5) dir= dir.WEST;
					if(random<15&&random>=5)dir=dir.NORTH;
					if(random<25&&random>=15)dir=dir.SOUTH;
				} 
				else if((dir == dir.SOUTH )) {
					if(y<model.getHeight()-10){
						for(int i=0;i<10;i++){
							if(model.getColor(x, y+i)==Color.MAGENTA||model.getColor(x, y+i)==Color.RED){
								dir=dir.NORTH;
								isRunning=true;
								count=0;
							}
						}
					}
					if(y != model.getHeight() - 1 && (model.getColor(x, y+1)== Color.BLACK||model.getColor(x, y+1)== Color.darkGray)) {
						model.setColor(x, y, Color.BLACK);
						model.setColor(x, y, last);
						last=model.getColor(x,y+1);
						model.setColor(x, y+1, Color.WHITE);
						y++;
					}
					if(random<5) dir= dir.NORTH;
					if(random<15&&random>=5)dir=dir.WEST;
					if(random<25&&random>=15)dir=dir.EAST;
				} 
				else if((dir == dir.WEST)) {
					if(x>10){
						for(int i=0;i<10;i++){
							if(model.getColor(x-i, y)==Color.MAGENTA||model.getColor(x-i, y)==Color.RED){
								dir=dir.EAST;
								isRunning=true;
								count=0;
							}
						}
					}
					if(x != 0 && (model.getColor(x-1, y)== Color.BLACK||model.getColor(x-1, y)== Color.darkGray)) {
						model.setColor(x, y, Color.BLACK);
						model.setColor(x, y, last);
						last=model.getColor(x-1,y);
						model.setColor(x-1, y, Color.WHITE);
						x--;
					}
					if(random<5) dir= dir.EAST;
					if(random<15&&random>=5)dir=dir.NORTH;
					if(random<25&&random>=15)dir=dir.SOUTH;
				} 
				if(x!=0&&x!=model.getWidth()-1&&y!=0&&y<model.getHeight()-2){
					if(model.getColor(x, y+2)==Color.MAGENTA||model.getColor(x, y+2)==Color.RED||model.getColor(x, y-1)==Color.MAGENTA||model.getColor(x, y-1)==Color.RED||
					model.getColor(x+1, y)==Color.MAGENTA||model.getColor(x+1, y)==Color.RED||
					model.getColor(x-1, y)==Color.MAGENTA||model.getColor(x-1, y)==Color.RED){					
						isZombie=true;
						isRunning=false;
						isWalking=false;
					}
				}	
			}	
		}	
	//=======RUNNING======//
		if(isRunning){
			count++;
			if(count==3) {
				isRunning=false;
				isWalking=false;
				isStopped=true;
			}
			//for(int i=0;i<3;i++){
			if((dir == dir.NORTH) ) {
				if(y > 2 && (model.getColor(x, y-1)== Color.BLACK)&& (model.getColor(x, y-2)== Color.BLACK)) {
					model.setColor(x, y, last);
					last=model.getColor(x,y-1);
					model.setColor(x, y-2, Color.YELLOW);
					y=y-2;
				}
				if(random<5) dir=dir.SOUTH;
				if(random<15&&random>=5)dir=dir.EAST;
				if(random<25&&random>=15)dir=dir.WEST;
			} 
			else if((dir == dir.EAST )) {
				if(x < model.getWidth() - 2 && (model.getColor(x+1, y)== Color.BLACK)&& (model.getColor(x+2, y)== Color.BLACK)) {
	
					model.setColor(x, y, Color.BLACK);
					last=model.getColor(x+1,y);
					model.setColor(x+2, y, Color.YELLOW);
					x=x+2;
				}
				if(random<5) dir= dir.WEST;
				if(random<15&&random>=5)dir=dir.NORTH;
				if(random<25&&random>=15)dir=dir.SOUTH;
			} 
			else if((dir == dir.SOUTH )) {
				if(y < model.getHeight() - 2 && model.getColor(x, y+1)== Color.BLACK&& model.getColor(x, y+2)== Color.BLACK) {
					model.setColor(x, y, Color.BLACK);
					last=model.getColor(x,y+1);
					model.setColor(x, y+2, Color.YELLOW);
					y=y+2;
				}
				if(random<5) dir= dir.NORTH;
				if(random<15&&random>=5)dir=dir.WEST;
				if(random<25&&random>=15)dir=dir.EAST;
			} 
			else if((dir == dir.WEST)) {
				if(x > 1 && model.getColor(x-1, y)== Color.BLACK&& model.getColor(x-2, y)== Color.BLACK) {
					model.setColor(x, y, Color.BLACK);
					last=model.getColor(x-1,y);
					model.setColor(x-2, y, Color.YELLOW);
					x=x-2;
				}
				if(random<5) dir= dir.EAST;
				if(random<15&&random>=5)dir=dir.NORTH;
				if(random<25&&random>=15)dir=dir.SOUTH;
			} 
			if(x!=0&&x!=model.getWidth()-1&&y!=0&&y<model.getHeight()-2){
				if(model.getColor(x, y+2)==Color.MAGENTA||model.getColor(x, y+2)==Color.RED||model.getColor(x, y-1)==Color.MAGENTA||model.getColor(x, y-1)==Color.RED||
				model.getColor(x+1, y)==Color.MAGENTA||model.getColor(x+1, y)==Color.RED||
				model.getColor(x-1, y)==Color.MAGENTA||model.getColor(x-1, y)==Color.RED){					
					isZombie=true;
					isStopped=false;
					isRunning=false;
				}
			}
			//}
			//isRunning=false;
		}
	//=======STOPPED=======//
		if(isStopped){
			if(count>=5){
				isStopped=false;
				isWalking=true;
				count=0;
			}
			if(x!=0&&x!=model.getWidth()-1&&y!=0&&y<model.getHeight()-2){
				if(model.getColor(x, y+2)==Color.MAGENTA||model.getColor(x, y+2)==Color.RED||
				model.getColor(x, y-1)==Color.MAGENTA||model.getColor(x, y-1)==Color.RED||
				model.getColor(x+1, y)==Color.MAGENTA||model.getColor(x+1, y)==Color.RED||
				model.getColor(x-1, y)==Color.MAGENTA||model.getColor(x-1, y)==Color.RED){					
					isZombie=true;
					isStopped=false;
					isRunning=false;
				}
			}
			count++;
		}
	//=======ZOMBIE========//
		if(isZombie){
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
					model.setColor(x, y-1, Color.MAGENTA);
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
					model.setColor(x+1, y, Color.MAGENTA);
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
					model.setColor(x, y+1, Color.MAGENTA);
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
					model.setColor(x-1, y, Color.MAGENTA);
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
}
