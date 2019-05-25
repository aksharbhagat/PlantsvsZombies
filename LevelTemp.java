import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class LevelTemp {
	private final int ZOMBIE_SPAWN_RATE = 10000;
	private Timer t;
	private Timer modifier;
	//private int totalZombies=0;
	ArrayList<Integer>rows = new ArrayList<Integer>();
	public void start(Field f) {
		refillrows();
		t=new Timer(ZOMBIE_SPAWN_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addZombies(f);
				System.out.println(t.getDelay());
			}
		});
		modifier=new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg1) {
				if(t.getDelay()>500) {
					t.setDelay(t.getDelay()-10);
				}
			}
		});
		t.start();
		modifier.start();
	}

	public void addZombies(Field f) {
		if(rows.size()==0) {
			refillrows();
		}
		int col = (int) (Math.random()*rows.size());
		int type = (int)(Math.random()*5);
		if(type<2) {
			f.addZombie(new ConeZombie(rows.get(col)));
			rows.remove(col);
		}
		else {
			f.addZombie(new Zombie(rows.get(col)));
			rows.remove(col);
		}
		//totalZombies++;

		//t.setDelay(t.getDelay()-(int) Math.pow((Math.sqrt(totalZombies)),3.0));


	}

	private void refillrows() {
		// TODO Auto-generated method stub
		for(int i = 0;i<5;i++) {
			rows.add(i);
		}
	}
}
