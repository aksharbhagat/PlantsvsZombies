import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Field {
	Plant[][] p = new Plant[5][9];
	Zombie[][] z = new Zombie[5][9];
	public Field(Graphics g) {
		Image i = getImage("Frontyard.png");
		g.drawImage(i, 200, 0,1200,800, null);
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
					int col = 800/zee.getY();
					int row = 1200/zee.getX();
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
		for(int r=0;r<p.length;r++) {
			for (int c=0;c<p[0].length;c++) {
				
			}
		}
	}

	public void addPlant(Plant pee) {
		int col = 1200/pee.getX();
		int row = 800/pee.getY();
		p[row][col]=pee;
	}
}
