import java.awt.event.MouseEvent;

public class ClickHandler {
	protected void clickedAt(MouseEvent me) {
		if(me.getX()>200&&me.getX()<200+Field.WIDTH) {
			fieldClick(me.getX(),me.getY());
		}
		else if(me.getX()<200) {
			seedClick(me.getX(),me.getY());
		}
	}
	public void fieldClick(int x, int y) {
		
	}
	public void seedClick(int x, int y) {
		
	}
}
