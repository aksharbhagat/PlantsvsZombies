import java.awt.*;

public class Seed {
	private Type s;
	private Rectangle rect;
	public final static int height=100;
	public final static int width=188;
	public Seed(Type t,int y) {
		s=t;
		rect= new Rectangle(0,y,width,height);
	}
	public Type getType() {
		return s;
	}
	public Rectangle getRect() {
		return rect;
	}
	public void drawRect(Graphics g) {
		g.setColor(Color.YELLOW);
		int thickNess = 4;
		for (int i = 0; i < thickNess; i++) {
			g.drawRect(rect.x+4 + i, rect.y-2 + i, width - 2 * i, height - 2 * i);
		}

	}

}
