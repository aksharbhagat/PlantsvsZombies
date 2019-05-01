import java.util.ArrayList;


public class Peashooter extends Plant implements Shoot{
	private ArrayList<Bullet> Bullets = new ArrayList<Bullet>();
	public Peashooter(int x, int y) {
		super(x, y, 100, "peashooter2.png");

		// TODO Auto-generated constructor stub
	}
	@Override
	public void fire() {
		Bullet b = new Bullet(this.getRow(),this.getCol());
	}
}
