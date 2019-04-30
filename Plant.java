import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Plant {
	private int health;
	private int x;
	private int y;
	private Image image;
	private boolean dead = false;
	public Plant(int xtemp, int ytemp, int h, String s) {
		// TODO Auto-generated constructor stub
		x = xtemp;
		y = ytemp;
		health = h;
		image = getImage(s);		
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
		g.drawImage(image, x, y, 100, 140, null);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
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
