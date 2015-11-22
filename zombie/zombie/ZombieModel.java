package zombie;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZombieModel {
	
	private int tempx;
	private int tempy;
	
	private Human[] users = new Human[30];
	private Zombie[] zombies = new Zombie[30];
	private final Color[][] matrix;
	private final int width;
	private final int height;
	private final int dotSize;
	private Human human;
	private Zombie zombie;
	
	public ZombieModel(int widthArg, int heightArg, int dotSizeArg) {
		width = widthArg;
		height = heightArg;
		dotSize = dotSizeArg;
		matrix = new Color[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				matrix[i][j] = Color.BLACK;
			}
		}
	}
	public Human getHuman() { return human; }
	public Zombie getZombie() { return zombie; }
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getDotSize() { return dotSize; }
	public Color getColor(int x, int y) { return matrix[x][y]; }
	public void setColor(int x, int y, Color color) { matrix[x][y] = color; }
	
	
	public void initialize() {
		//======INIT RIVER====//
		Random ranx= new Random();
		int x =ranx.nextInt(width-5);
		for(int i=x; i<x+5;i++){
			for(int j=0;j<height;j++){
				matrix[i][j]=Color.BLUE;
			}
		}
		//====INIT ROCKS====//
		Random rockx= new Random();
		Random rocky= new Random();
		Random diam= new Random();
		for(int i=0; i<6;){
			double rx=rockx.nextInt(width-5);
			double ry=rocky.nextInt(height-5);
			double diameter=diam.nextInt(4)+5;
			boolean boo=true;
			for(int j=0;j<width;j++){
				for(int k=0;k<height;k++){	
					double dist=Math.sqrt((j-rx)*(j-rx) +(k-ry)*(k-ry));
					if((dist<diameter) && (matrix[j][k]==Color.GRAY)){
						boo=false;
					}
				}
			}
			if(boo){
				i++;
				for(int j=0;j<width;j++){
					for(int k=0;k<height;k++){	
						double dist=Math.sqrt((j-rx)*(j-rx) +(k-ry)*(k-ry));
						if(dist<diameter && matrix[j][k]==Color.BLACK && boo){
							matrix[j][k]=Color.GRAY;
						}
					}
				}
			}
		}	
		//=====INIT TREES====//
		Random treex= new Random();
		Random treey= new Random();
		for(int i=0; i<40; ){
			int tx= treex.nextInt(width-2)+1;
			int ty= treey.nextInt(height-2)+1;		
			if(this.getColor(tx,ty)== Color.BLACK&&this.getColor(tx+1,ty)== Color.BLACK&&
					this.getColor(tx-1,ty)== Color.BLACK&&this.getColor(tx,ty+1)== Color.BLACK&&
					this.getColor(tx,ty-1)== Color.BLACK){
				matrix[tx][ty]= Color.GREEN;
				matrix[tx-1][ty]= Color.GREEN;
				matrix[tx+1][ty]= Color.GREEN;
				matrix[tx][ty-1]= Color.GREEN;
				matrix[tx][ty+1]= Color.GREEN;
				i++;
			}
		}

		for(int i=0;i<30;i++){   
		    Human newUser = new Human(this);
		    users[i]=newUser;
		}
		//for(int i=0;i<30;i++){   
		    Zombie newZombie = new Zombie(this);
		    zombie=newZombie;
		//}
	}
	public void update() {
		for(int i=0;i<30;i++){   
		    users[i].update();
		}
		   
			zombie.update();
		
		
	}
}