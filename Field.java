import java.awt.*;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Field {
	private Image i;
	private Plant[][] p = new Plant[5][9];
	private static ArrayList <Zombie> z = new ArrayList<Zombie>();
	private static ArrayList <Bullet> b = new ArrayList<Bullet>();
	private static ArrayList <Sun> s = new ArrayList<Sun>();
	private int collectedSuns=150;
	private static final Rectangle SHOVEL = new Rectangle(50, 600, 100, 100);
	public final static int WIDTH=1200,HEIGHT=800;
	private int score = 0;
	public Field() {
		i = getImage("Frontyard.png");
	}
	protected Image getImage(String fn) {
		Image img = null;
		try {
			img = ImageIO.read(this.getClass().getResource(fn));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	public void checkPlantCollision() {
		//		for(Zombie zee:z) {
		//			if(zee!=null) {
		//				//int col = 1200/zee.getX(); 
		//				int col = ((zee.getX())/(WIDTH/9))-1;
		//				int row = zee.getRow();
		//				//System.out.println(zee.getX());
		//				//System.out.println(col);
		//				//System.out.println(row);
		//				if (p[row][col]!=null) {
		//					p[row][col].dying();
		//					zee.eating();
		//					checkPlantDeath(zee);
		//				}
		//			}
		//		}
		for(int r=0;r<p.length;r++) {
			for (int c=0;c<p[0].length;c++) {

				int x = c*(WIDTH/9)+275;
				for(Zombie zee:z) {
					//System.out.println(Math.abs(zee.getX()-x));
					if((zee.getRow()==r)&&Math.abs(zee.getX()-x)<=10) {
						checkPlantDeath(zee);
						if(p[r][c]!=null) {
							p[r][c].dying();
							zee.eating();
						}
					}
				}

			}
		}
	}
	public void checkBulletCollision() {
		if(!b.isEmpty()&&!z.isEmpty()) {
			for(int i = b.size()-1; i>=0; i--) {
				Bullet temp = b.get(i);
				for(int c = z.size()-1; c>=0; c--) {			
					Zombie ztemp = z.get(c);
					if(temp.getRow() == ztemp.getRow()) {
						if(Math.abs(temp.getRect().x-z.get(c).getX())<10) {
							z.get(c).dying();
							b.remove(i);
							checkZombieDeath();
							break;
						}
					}
				}
			}
		}
	}
	private void checkZombieDeath() {
		for(int c = z.size()-1; c>=0; c--) {
			if(z.get(c).dead()==true) {
				if(z.get(c) instanceof ConeZombie) {
					score+=75;
				}
				else if(z.get(c) instanceof BucketZombie) {
					score+=100;
				}
				else {
					score+=50;
				}
				z.remove(c);
			}		

		}
	}
	private void checkPlantDeath(Zombie zee) {
		for(int r=0;r<p.length;r++) {
			for (int c=0;c<p[0].length;c++) {
				if (p[r][c]!=null&&p[r][c].dead()) {
					this.removePlant(r, c);
					zee.ate();

				}
				else if(p[r][c]==null) {
					zee.ate();
				}
			}
		}

	}


	private void removePlant(int r, int c) {
		if(p[r][c] instanceof Timed) {
			((Timed) p[r][c]).stop();
		}
		p[r][c]=null;
	}

	public void draw (Graphics g) {
		g.drawImage(i, 200, 0,WIDTH,HEIGHT, null);
		for(int r=0;r<p.length;r++) {
			for (int c=0;c<p[0].length;c++) {
				if(p[r][c]!=null) {
					p[r][c].draw(g);
				}
			}
		}
		for(Zombie zee:z) {
			if(zee!=null) {
				zee.draw(g);
			}
		}
		for(Bullet bee:b) {
			bee.draw(g);
		}
		for(Sun see:s) {
			see.draw(g);
		}
		Image shove = this.getImage("Shovel.jpg");
		g.drawImage(shove, SHOVEL.x, SHOVEL.y, SHOVEL.width, SHOVEL.height, null);
		if(PvZRunner.shovelSelected) {
			g.setColor(Color.YELLOW);
			int thickNess=4;
			for (int i = 0; i < thickNess; i++) {
				g.drawRect(SHOVEL.x + i, SHOVEL.y + i, SHOVEL.width - 2 * i, SHOVEL.height - 2 * i);
			}
		}

		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SANS_SERIF, 1, 50));
		g.drawString(""+collectedSuns, 45, 745);
		g.setFont(new Font(Font.SANS_SERIF, 1, 40));
		g.drawString("Score:", 45, 525);
		g.drawString(""+score, 45, 575);

	}
	public void addZombie(Zombie zee) {
		z.add(zee);
	}
	public void addPlant(Plant pee) {
		if(collectedSuns>=pee.getCost()&&p[pee.getRow()][pee.getCol()]==null) {
			p[pee.getRow()][pee.getCol()]=pee;
			if(pee instanceof Timed) {
				((Timed) pee).start();
			}
			collectedSuns-=pee.getCost();
		}
	}
	public static void addBullet(Bullet bee) {
		b.add(bee);
	}
	public void moveZombies() {
		for(Zombie zo:z) {
			if(zo != null) {
				zo.walk();
				if(zo.getX()<205) {
					PvZRunner.gameOver();
				}
			}
		}
	}

	public void moveBullets() {
		for(int i = b.size()-1; i>=0; i--) {
			b.get(i).move();
			if(b.get(i).getRect().x>1450) {
				b.remove(i);
			}
		}
	}
	//	public void shoot() {
	//		for(Plant[]big:p) {
	//			for (Plant pee:big) {
	//				for(Zombie zee:z) {
	//					if(pee!=null&&pee instanceof Shoot&&zee.getRow()==pee.getRow()) {
	//						Shoot s = (Shoot)pee;
	//						this.addBullet(s.fire());
	//					}
	//				}
	//			}
	//		}
	//	}
	//	public Zombie[][] getZombies() {
	//		return z;
	//	}
	//	public Plant[][] getPlants() {
	//		return p;
	//	}
	public static void addSun(int x, int y,int worth) {
		s.add(new Sun(x,y,worth));
	}
	public void collectSun(int x, int y) {
		for(int i=0;i<s.size();i++) {
			if(s.get(i).getRect().contains(new Point(x,y))) {
				collectedSuns+=s.get(i).getWorth();
				s.remove(i);
				return;
			}
		}
	}
	public void shovel(int row, int col) {
		if(p[row][col]!=null) {

			if(p[row][col] instanceof Timed) {
				((Timed) (p[row][col])).stop();
			}
			p[row][col]=null;
		}
	}
	public Rectangle getShovel() {
		return SHOVEL;
	}
	//	public void checkIfInRow() {
	//		for(Plant[]big:p) {
	//			for (Plant pee:big) {
	//				if(pee!=null&&pee instanceof Peashooter) {
	//					boolean inRow=false;
	//					for(Zombie zee:z) {
	//						if(pee.getRow()==zee.getRow()) {
	//							((Peashooter) pee).setinRow(true);
	//							inRow=true;
	//							break;
	//						}
	//					}
	//					if(!inRow) {
	//						((Peashooter) pee).setinRow(false);
	//					}
	//
	//				}
	//			}
	//		}
	//	}
	public static boolean checkIfInRow(int plantRow) {
		for(Zombie zee:z) {
			if(plantRow==zee.getRow()) {
				return true;
			}
		}
		return false;
	}
	public void stopSpawning() {
		for(Plant[] temp:p) {
			for (Plant pee:temp) {
				if(pee instanceof Timed) {
					((Timed) (pee)).stop();
				}
			}
		}
	}
}


