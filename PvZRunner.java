import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PvZRunner {
	private static final int REFRESH_RATE = 10;
	JFrame frame;
	JPanel panel;
	Timer timer;
	private final int WIDTH = 1500;
	private final int HEIGHT = 800;
	private int ticks=0;
	private Field f;
	public PvZRunner() {
		start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("yee");
		new PvZRunner();

	}

	private void start() {
		f = new Field();
		f.addPlant(new Peashooter(0,0));
		f.addPlant(new Sunflower(1,1));
		f.addZombie(new Zombie(0));
		JFrame frame = new JFrame("PvZ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawGame(g);
				Toolkit.getDefaultToolkit().sync();
			}
		};
		// random color to the background
		panel.setBackground(new Color(20, 15, 120));

		// so that the frame isn't minimized
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));

		// so that the frame is placed a little way from top and left side
		frame.setLocation(WIDTH/50, HEIGHT/50);


		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		// after setting visible to true, you can get focus.  You need focus to consume
		// the keystrokes hit by the user
		panel.requestFocusInWindow();

		// this timer controls the actions in the game and then repaints after each update to data
		timer = new Timer(REFRESH_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateGame();
				panel.repaint();
			}
		});
		timer.start();
	}

	protected void drawGame(Graphics g) {
		// TODO Auto-generated method stub
		f.draw(g);

	}

	// this method is called every time the timer goes off (which right now is every 10 milliseconds = 100 times per second
	protected void updateGame() {
		ticks++;// keeps track of the number of times the timer has gone off
		int hurts = 100/REFRESH_RATE;
		if(ticks%hurts == 0) {
			f.moveZombies();
		}
		if(ticks%(hurts/5)==0) {
			f.moveBullets();
		}
		if(ticks%(hurts*20)==0) {
			f.shoot();
			
		}
	}
}
