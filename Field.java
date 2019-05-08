import java.awt.*;
import java.awt.List;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Field {
	private Image i;
	private Plant[][] p = new Plant[5][9];
	private ArrayList <Zombie> z = new ArrayList<Zombie>();
	private ArrayList <Bullet> b = new ArrayList<Bullet>();
	private ArrayList <Sun> s = new ArrayList<Sun>();
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
		for(Zombie zee:z) {
			if(zee!=null) {
				//int col = 1200/zee.getX(); 
				int col = ((zee.getX())/(1200/9))-1;
				int row = zee.getRow();
				//System.out.println(zee.getX());
				//System.out.println(col);
				//System.out.println(row);
				if (p[row][col]!=null) {
					p[row][col].dying();
					zee.eating();
					checkPlantDeath(zee);
				}
			}
		}
	}
	public void checkBulletCollision() {
	
		for(int i = 0; i<b.size(); i++) {
			for(int c = 0; c< z.size(); c++) {
				if(b.get(i).getRow() == z.get(c).getRow()) {
					if(Math.abs(b.get(i).getRect().x-z.get(c).getX())<5) {
						z.get(c).dying();
						b.remove(i);
						checkZombieDeath();
						//System.out.println(b.size());
					}
				}
			}
		}
	}
	private void checkZombieDeath() {
		for(int r=0;r<z.size();r++) {
			if(z.get(r).dead()==true) {
				System.out.println(z.get(r).dead());

				z.remove(r);
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
			}
		}
		
	}
	

	private void removePlant(int r, int c) {
		p[r][c]=null;
	}

	public void draw (Graphics g) {
		g.drawImage(i, 200, 0,1200,800, null);
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
	}
	public void addZombie(Zombie zee) {
		z.add(zee);
	}
	public void addPlant(Plant pee) {
		p[pee.getRow()][pee.getCol()]=pee;
	}
	public void addBullet(Bullet bee) {
		b.add(bee);
	}
	public void moveZombies() {
		for(Zombie zo:z) {
			if(zo != null) {
				zo.walk();
			}
		}
	}

	public void moveBullets() {
		for(Bullet bee:b) {
			bee.move();
		}
	}
	public void shoot() {
		for(Plant[]big:p) {
			for (Plant pee:big) {
				if(pee!=null&&pee instanceof Shoot) {
					Shoot s = (Shoot)pee;
					this.addBullet(s.fire());
				}
			}
		}
	}
	//	public Zombie[][] getZombies() {
	//		return z;
	//	}
	//	public Plant[][] getPlants() {
	//		return p;
	//	}
}
