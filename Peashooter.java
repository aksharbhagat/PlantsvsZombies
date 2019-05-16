import java.util.ArrayList;


public class Peashooter extends Plant implements Shoot{
	private ArrayList<Bullet> Bullets = new ArrayList<Bullet>();
	public Peashooter(int x, int y) {
		super(x, y, 100, "peashooter2.png");
		cost=100;
		// TODO Auto-generated constructor stub
	}
	@Override
	public Bullet fire() {
		return new Bullet(this.getRow(),this.getCol());
	}
}
