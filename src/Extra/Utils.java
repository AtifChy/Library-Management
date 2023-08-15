package Extra;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utils {
    public static Font INTRO_FONT = new Font("Century Gothic", Font.BOLD, 25);
    public static Font NORMAL_FONT = new Font("Inter", Font.PLAIN, 16);
    public static Font NORMAL_BOLD_FONT = new Font("Inter", Font.BOLD, 16);
    public static Font SMALL_FONT = new Font("Inter", Font.PLAIN, 14);
    public static Font SMALL_BOLD_FONT = new Font("Inter", Font.BOLD, 14);
    public static Font BIG_FONT = new Font("Inter", Font.PLAIN, 18);
    public static Font BIG_BOLD_FONT = new Font("Inter", Font.BOLD, 18);
    public static Font HUGE_FONT = new Font("Inter", Font.PLAIN, 70);
    public static Color BACKGROUND_COLOR = new Color(220, 220, 225);
    public static Color LIGHTER_BLUE = new Color(208, 228, 255);
    public static Color LIGHT_BLUE = new Color(165, 200, 254);
    public static Color BLUE = new Color(0, 125, 255);

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
