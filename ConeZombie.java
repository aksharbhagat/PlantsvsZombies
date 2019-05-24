import java.awt.Color;
import java.awt.Graphics;

public class ConeZombie extends Zombie {

	public ConeZombie(int ytemp) {
		super(ytemp,150,"conezombie.png",175);
	}
	public void draw(Graphics g) {
		int y=row*(800/5)+20;
		g.drawImage(image, x, y-50, 120, height+5, null);
		g.setColor(Color.RED);
		g.fillRect(x-10, y-15, (health*100)/startinghealth, 5);
	}
}