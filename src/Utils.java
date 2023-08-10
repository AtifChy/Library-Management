import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utils {
    public static BufferedImage resizeImage(String filename, int width, int height) throws IOException {
        BufferedImage rawImage = ImageIO.read(new File(filename));
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(rawImage, 0, 0, width, height, null);
        graphics2D.dispose();
        return resizedImage;
    }
}
