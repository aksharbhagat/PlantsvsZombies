import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class LevelTemp {
	private final int ZOMBIE_SPAWN_RATE = 7500;
	private Timer t;
	
	public void start(Field f) {
		t=new Timer(ZOMBIE_SPAWN_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addZombies(f);
			}
		});
		t.start();
	}

	public void addZombies(Field f) {
		int x = (int) (Math.random()*5);
		f.addZombie(new Zombie(x));
	}
}
