import java.awt.*;
import java.util.*;

public class SeedPackets {
	private ArrayList<Seed> seeds = new ArrayList<Seed>();
	private final int height=100,width=190;
	public SeedPackets() {
		addSeeds();
	}
	private void addSeeds() {
		int y=5;
		for(Type t:Type.values()) {
			seeds.add(new Seed(t,y));
			y+=Seed.height+1;
		}
	}
	//draw
	//which plant is clicked
	public void draw(Graphics g) {
		for (Seed s:seeds) {
			//g.drawRect(s.getRect().x,s.getRect().y, width, height);
			g.drawImage(s.getType().i, s.getRect().x+5, s.getRect().y, width, height, null);
		}
	}
	public Seed click(int x, int y) {
		for(Seed s:seeds) {
			if (s.getRect().contains(new Point(x,y))) {
				return s;
			}
		}
		return null;
		
	}
}
