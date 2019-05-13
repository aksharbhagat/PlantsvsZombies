import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Type {
	PEASHOOTER("peashooterseed.png"),SUNFLOWER("sunflowerseed.png");
	public Image i;
	private Type(String fn) {
		Image img = null;
		try {

			img = ImageIO.read(this.getClass().getResource(fn));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i=img;
	}
}



