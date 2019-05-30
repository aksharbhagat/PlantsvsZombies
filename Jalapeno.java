import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Jalapeno extends Plant {
	private Timer t;
	private final int DELAY = 1000;
	public Jalapeno(int rw, int cl) {
		super(rw, cl, Integer.MAX_VALUE, "Hjala.png");
		cost=125;
		start();
	}
	public void start() {
		t=new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				kill();
			}
		});
		t.start();
	}
	protected void kill() {
		// TODO Auto-generated method stub
		Field.killAllinRow(this.getRow());
		this.dead=true;
		t.stop();
	}
}
