import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class LevelTemp {
	private final int ZOMBIE_SPAWN_RATE = 5000;
	private Timer t;
	private int totalZombies=0;
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
		int col = (int) (Math.random()*5);
		int type = (int)(Math.random()*5);
		if(type<2) {
			f.addZombie(new ConeZombie(col));
			
		}
		else {
			f.addZombie(new Zombie(col));
		}
		totalZombies++;
		
		//t.setDelay(t.getDelay()-(int) Math.pow((Math.sqrt(totalZombies)),3.0));
		
		
	}
}
