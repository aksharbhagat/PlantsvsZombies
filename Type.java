import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Type {
	PEASHOOTER("peashooterseed.png",2),SUNFLOWER("sunflowerseed.png",1);
	public Image i;
	public int sunCost;
	private Type(String fn, int sun) {
		Image img = null;
		try {

			img = ImageIO.read(this.getClass().getResource(fn));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i=img;
		sunCost=sun;
	}
}



