import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * The type Colors parser.
 */
public class ColorsParser {
    /**
     * Color from string java . awt . color.
     *
     * @param s the s
     * @return the java . awt . color
     * @throws NoSuchFieldException   the no such field exception
     * @throws IllegalAccessException the illegal access exception
     */
// parse color definition and return the specified color.
    public static Color colorFromString(String s) throws NoSuchFieldException, IllegalAccessException {
        Field field = Color.class.getField(s);
        return (Color) field.get(null);
    }

    /**
     * Block color color.
     *
     * @param s the s
     * @return the color
     */
    public static Color blockColor(String s) {
        Color color = null;

        if (s.startsWith("color(RGB(") && s.endsWith("))")) {
            String indexString = s.substring("color(RGB(".length(), s.length() - "))".length());
            String[] indexes = indexString.split(",");
            int r = Integer.valueOf(indexes[0]);
            int g = Integer.valueOf(indexes[1]);
            int b = Integer.valueOf(indexes[2]);
            color = new Color(r, g, b);
        } else if (s.startsWith("color(") && s.endsWith(")")) {
            String colorName = s.substring("color(".length(), s.length() - ")".length());
            try {
                Field field = Color.class.getField(colorName);
                color = (Color) field.get(null);
            } catch (NoSuchFieldException n) {
                System.out.println("Fail in extract color name");
            } catch (IllegalAccessException i) {
                System.out.println("Fail in extract color name");
            }
        }

        return color;
    }

    /**
     * Gets image.
     *
     * @param s the s
     * @return the image
     */
    public static Image getImage(String s) {
        Image imageBack = null;

        if (s.startsWith("image(") && s.endsWith(")")) {
            s = s.substring("image(".length(), s.length() - ")".length());
        }

        InputStream source;
        try {
            source = new FileInputStream(s);
        } catch (FileNotFoundException e) {
            source = null;
        }
        //InputStream source = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
        try {
            BufferedImage image = ImageIO.read(source);
            imageBack = image;
        } catch (IOException e) {
            System.out.println("Fail load picture background");
        } finally {
            try {
                source.close();
            } catch (IOException e2) {
                System.out.println("Fail close picture background source");
            }
        }


        return imageBack;
    }
}