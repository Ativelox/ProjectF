package projectf.cormag.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource("/textures/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}