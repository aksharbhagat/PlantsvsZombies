import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Zombie {
	private final static int WALKING_SPEED = 1;
	protected int health;
	protected int startinghealth;
	protected int row;
	protected int x;
	protected final int height;
	private int dx=this.WALKING_SPEED;
	protected Image image;
	private boolean dead = false;
	public Zombie(int ytemp) {
		// TODO Auto-generated constructor stub
		x = 1300;
		row = ytemp;
		health = 100;
		startinghealth=health;
		image = getImage("zombie.png");		
		height=140;
	}
	public Zombie(int ytemp, int h, String fn, int hei) {
		x = 1300;
		row = ytemp;
		health = h;
		startinghealth=h;
		image = getImage(fn);	
		height=hei;
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
		g.drawImage(image, x, y-10, 100, height, null);
		g.setColor(Color.RED);
		g.fillRect(x-10, y-15, (health*100)/startinghealth, 5);
	}
	public void walk() {
		x-=dx;
	}
	public void eating() {
		dx=0;
	}
	public void ate() {
		dx=this.WALKING_SPEED;
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
