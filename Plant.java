import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Plant {
	private int health;
	private int row;
	private int col;
	private Image image;
	public boolean dead = false;
	public Plant(int x, int y, int h, String s) {
		// TODO Auto-generated constructor stub
		row = x;
		col = y;
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
	public void dying() {
		health-=10;
		if (health<=0) {
			dead = true;
		}
	}
	
}
