import java.awt.Color;
import java.awt.Graphics;

public class BucketZombie extends Zombie {
	public BucketZombie(int ytemp) {
		super(ytemp,250,"bucketzombie.png",175);
	}
	@Override
	public void draw(Graphics g) {
		int y=row*(800/5)+20;
		g.drawImage(image, x, y-50, 120, height+5, null);
		g.setColor(Color.RED);
		g.fillRect(x-10, y-15, (health*100)/startinghealth, 5);
	}
}
