import java.awt.*;

public class Seed {
	private Type s;
	private Rectangle rect;
	public final static int height=50;
	public final static int width=200;
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
	
}
