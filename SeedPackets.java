import java.awt.*;
import java.util.*;

public class SeedPackets {
	private ArrayList<Rectangle> seeds = new ArrayList<Rectangle>();
	private final int MAX_PLANTS = 2;
	private final int height=100,width=190;
	public SeedPackets() {
		addRectangles();
	}
	//draw
	//which plant is clicked
	private void addRectangles() {
		for(int i=0; i<MAX_PLANTS;i++) {
			seeds.add(new Rectangle(5,i*height,width,height));
		}
	}
	public void draw(Graphics g) {
		for (Rectangle rect:seeds) {
			g.drawRect(rect.x, y, width, height);
			g.drawImage(img, x, y, width, height, observer)
		}
	}
}
