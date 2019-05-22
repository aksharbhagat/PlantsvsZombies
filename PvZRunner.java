import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PvZRunner {
	private static final int REFRESH_RATE = 8;
	JFrame frame;
	JPanel panel;
	Timer timer;
	private final int WIDTH = 1500;
	private final int HEIGHT = 800;
	private int ticks=0;
	private Field f;
	private SeedPackets s = new SeedPackets();
	private LevelTemp lt = new LevelTemp();
	private Seed selectedSeed;
	static boolean shovelSelected=false;
	
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
		//f.addPlant(new Peashooter(0,0));
		//f.addPlant(new Peashooter(0,5));
		//f.addPlant(new Sunflower(1,1));
		//f.addZombie(new Zombie(0));
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
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				clickedAt(me);
				panel.repaint();
			}});
		// this timer controls the actions in the game and then repaints after each update to data
		timer = new Timer(REFRESH_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateGame();
				panel.repaint();
			}
		});
		timer.start();
		lt.start(f);
		
	}

	protected void clickedAt(MouseEvent me) {
		if(f.getShovel().contains(me.getPoint())) {
			shovelSelected=!shovelSelected;
			selectedSeed=null;
		}
		else if(me.getX()>200&&me.getX()<200+Field.WIDTH) {
			fieldClick(me.getX(),me.getY());
		}
		else if(me.getX()<200) {
			seedClick(me.getX(),me.getY());
		}
	}
	public void fieldClick(int x, int y) {
		int row=(y-20)/(800/5);
		int col=(x-205)/(1200/9);
		if(selectedSeed!=null) {
			if(selectedSeed.getType()==Type.PEASHOOTER) {
				f.addPlant(new Peashooter(row,col));
				selectedSeed=null;
			}
			else if(selectedSeed.getType()==Type.SUNFLOWER) {
				f.addPlant(new Sunflower(row,col));
				selectedSeed=null;
			}
		}
		else if(shovelSelected) {
			f.shovel(row,col);
			this.shovelSelected=false;
		}
		else {
			f.collectSun(x,y);
		}
	}
	public void seedClick(int x, int y) {
		selectedSeed = s.click(x, y);
		this.shovelSelected=false;
	}

	protected void drawGame(Graphics g) {
		// TODO Auto-generated method stub
		f.draw(g);
		s.draw(g);
		if(selectedSeed!=null) {
			selectedSeed.drawRect(g);
		}
	}

	// this method is called every time the timer goes off (which right now is every 10 milliseconds = 100 times per second
	protected void updateGame() {
		ticks++;// keeps track of the number of times the timer has gone off
		int hurts = 100/REFRESH_RATE;
		if(ticks%hurts == 0) {
			f.moveZombies();
			f.checkPlantCollision();
			
			
		}
		if(ticks%(hurts/5)==0) {
			f.moveBullets();
			f.checkBulletCollision();
		}
		if(ticks%(hurts/2)==0) {
			//f.checkBulletCollision();

		}
		if(ticks%(hurts*20)==0) {
			f.shoot();
			
		}
	}
}
