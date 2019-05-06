import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Zombie {
	private int health;
	private int row;
	private int x;
	private int dx=1;
	private Image image;
	private boolean dead = false;
	public Zombie(int ytemp) {
		// TODO Auto-generated constructor stub
		x = 1200;
		row = ytemp;
		health = 100;
		image = getImage("zombie.png");		
	}
	protected  Image getImage(String fn) {
		Image img = null;
		try {
			
			img = ImageIO.read(this.getClass().getResource(fn));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	public void draw(Graphics g) {
		int y=row*(800/5)+20;
		g.drawImage(image, x, y-10, 100, 140, null);
	}
	public void walk() {
		x-=dx;
	}
	public void eating() {
		dx=0;
	}
	public void ate() {
		dx=1;
	}
	public int getX() {
		return x;
	}
	public int getRow() {
		return row;
	}
	public void dying() {
		health-=10;
		if (health<=0) {
			dead = true;
		}
	}
	public boolean dead() {
		return dead;
	}
	
}
