import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PvZRunner {
	private static final int REFRESH_RATE = 100;
	JFrame frame;
	JPanel panel;
	Timer timer;
	private final int WIDTH = 1500;
	private final int HEIGHT = 800;
	private int ticks=0;

	public PvZRunner() {
		start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("yee");
		new PvZRunner();
		
	}

	private void start() {
		JFrame frame = new JFrame("PvZ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawGame(g);
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
		Field f = new Field(g);
		
	}

	// this method is called every time the timer goes off (which right now is every 10 milliseconds = 100 times per second
	protected void updateGame() {
		ticks++;// keeps track of the number of times the timer has gone off
		
		int hurts = 1000/REFRESH_RATE;
		if(ticks %hurts == 0) {
			System.out.println(ticks/hurts+" seconds");
		}
	}
}
