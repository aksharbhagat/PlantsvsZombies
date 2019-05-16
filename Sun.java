import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sun {
	private Rectangle rect;
	private Image img;
	private int worth;
	public Sun(int x, int y, int w) {
		rect=new Rectangle(x,y,90,90);
		img=getImage("sun.png");
		worth=w;

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
		g.drawImage(img, rect.x, rect.y, rect.width, rect.height, null);
	}
	public int getWorth() {
		return worth;
	}
	public Rectangle getRect() {
		return rect;
	}
}
