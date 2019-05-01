import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Plant {
	private int health;
	private int row;
	private int col;
	private Image image;
	private boolean dead = false;
	public Plant(int xtemp, int ytemp, int h, String s) {
		// TODO Auto-generated constructor stub
		row = xtemp;
		col = ytemp;
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
		int y=row*(800/5)+20;
		int x = col*(1200/9)+205;
		g.drawImage(image, x, y, 100, 110, null);
	}
	public int getCol() {
		return col;
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
