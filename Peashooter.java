import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;


public class Peashooter extends Plant implements Timed{
	private final int BULLET_SPAWN_RATE = 1000;
	private Timer t;
	public Peashooter(int row, int col) {
		super(row, col, 100, "peashooter2.png");
		cost=100;
		// TODO Auto-generated constructor stub
	}
	public void start() {
		t=new Timer(BULLET_SPAWN_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(inRow())spawn();
			}
		});
		t.start();
	}
	private void spawn() {
		Field.addBullet(new Bullet(this.getRow(),this.getCol()));
	}
	public void stop() {
		t.stop();
	}
	private boolean inRow() {
		return Field.checkIfInRow(this.getRow());
	}
}
