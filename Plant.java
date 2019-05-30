import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Plant {
	private int startinghealth;
	private int health;
	private int row;
	private int col;
	private Image image;
	protected boolean dead = false;
	protected int cost;
	public Plant(int rw, int cl, int h, String s) {
		// TODO Auto-generated constructor stub
		row = rw;
		col = cl;
		startinghealth= h;
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
		g.setColor(Color.BLUE);
		g.fillRect(x, y-10, (health*100)/startinghealth, 5);
	}
	public int getCol() {
		return col;
	}
	public int getRow() {
		return row;
	}
	public int getCost() {
		return cost;
	}
	public void dying() {
		health-=2;
		if (health<=0) {
			dead = true;
		}
	}
	public boolean dead() {
		return dead;
	}
}
