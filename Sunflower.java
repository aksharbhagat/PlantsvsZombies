import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Sunflower extends Plant{
	//private ActionListener a=null;
	private final int SUN_SPAWN_RATE = 5000;
	private Timer t;
	private int x,y;
	public Sunflower(int row, int col) {
		super(row, col, 75, "sunflower.png");
		 y=row*(800/5)+60;
		 x = col*(1200/9)+180;
		 cost=25;
		start();
	}
	private void start() {
		t=new Timer(SUN_SPAWN_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				spawn();
			}
		});
		t.start();
	}
	public void spawn() {
		Field.addSun(x,y,25);
	}

}