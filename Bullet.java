import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet {
	private Rectangle rect;
	private int dx=5;
	private Image img;
	public Bullet(int row, int col){
		int x = col*(1200/9)+210;
		int y=row*(800/5)+25;
		rect=new Rectangle(x,y,25,25);
		img=getImage("bullet.png");
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
	public void move() {
		rect.setLocation(rect.x+dx, rect.y);
		
	}
	public void draw(Graphics g) {
		g.drawImage(img, rect.x, rect.y, rect.width, rect.height, null);
	}
}