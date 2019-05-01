import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Field {
	private Image i;
	Plant[][] p = new Plant[5][9];
	Zombie[][] z = new Zombie[5][9];
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
				if (z[r][c]!=null&&z[r][c].dead()) {
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
	}
	public void addZombie(Zombie zee) {
		z[zee.getRow()][8]=zee;
	}
	public void addPlant(Plant pee) {
		p[pee.getRow()][pee.getCol()]=pee;
	}
	public Zombie[][] getZombies() {
		return z;
	}
}
