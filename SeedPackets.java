import java.awt.*;
import java.util.*;

public class SeedPackets {
	private ArrayList<Seed> seeds = new ArrayList<Seed>();
	private final int MAX_PLANTS = 2;
	private final int height=100,width=190;
	public SeedPackets() {
		addSeeds();
	}
	private void addSeeds() {
		int y=0;
		for(Type t:Type.values()) {
			seeds.add(new Seed(t,y));
			y+=Seed.height;
		}
	}
	//draw
	//which plant is clicked
//	private void addRectangles() {
//		for(int i=0; i<MAX_PLANTS;i++) {
//			seeds.add(new Rectangle(5,i*height,width,height));
//		}
//	}
	public void draw(Graphics g) {
		for (Rectangle rect:seeds) {
			g.drawRect(rect.x, y, width, height);
			g.drawImage(img, x, y, width, height, observer)
		}
	}
}
