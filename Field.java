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
	public void checkCollision() {
		for(Zombie[] temp:z) {
			for(Zombie zee:temp) {
				if(zee!=null) {
					int col = 1200/zee.getX();
					int row = zee.getRow();
					if (p[row][col]!=null) {
						p[row][col].dying();
						zee.eating();
					}
				}
			}
		}
	}
	public void checkDeath() {
		for(int r=0;r<p.length;r++) {
			for (int c=0;c<p[0].length;c++) {
				if (p[r][c]!=null&&p[r][c].dead()) {
					this.removePlant(r, c);
				}
			}
		
			if(z.get(r).dead()) {
				this.removeZombie(r, c);
			}
		}
	}
	}
	private void removePlant(int r, int c) {
		p[r][c]=null;
	}
	private void removeZombie(int r, int c) {
		z[r][c]=null;
	}
	public void draw (Graphics g) {
		g.drawImage(i, 200, 0,1200,800, null);
		for(int r=0;r<p.length;r++) {
			for (int c=0;c<p[0].length;c++) {
				if(p[r][c]!=null) {
					p[r][c].draw(g);
				}
				if(z[r][c]!=null) {
					z[r][c].draw(g);
				}
			}
		}
		for(Bullet bee:b) {
			bee.draw(g);
		}
	}
	public void addZombie(Zombie zee) {
		z[zee.getRow()][8]=zee;
	}
	public void addPlant(Plant pee) {
		p[pee.getRow()][pee.getCol()]=pee;
	}
	public void addBullet(Bullet bee) {
		b.add(bee);
	}
	public void moveZombies() {
		for(Zombie[]big:z) {
			for(Zombie zo:big) {
				if(zo != null) {
					zo.walk();
				}
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
