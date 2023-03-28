package alura.semana.imersao.java;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Properties;

public class StickerMaker {
    Properties env = EnvReader.getEnv(); // Create a .env reader

    public void create(InputStream stream, String filename) throws Exception {

        // TODO: Read image

        BufferedImage originalImage = ImageIO.read(stream); // Get Image

        // TODO: Create a new image in memory with transparency and a new size

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        int newHeight = height + 200; // Add 200 px to the image

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // TODO: Copy the original image for the new image (in memory)

        Graphics2D graphics = (Graphics2D) newImage.getGraphics(); // Create a graphic of a new Image
        graphics.drawImage(originalImage, 0, 0, null); // Add the new image to the original image


        // TODO: Configurate the phase

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 70); // Configure the text
        graphics.setFont(font);
        graphics.setColor(Color.yellow);

        // TODO: Write a phase on image

        String phase = "JAVA É BOM";

        // Get Dimension of String
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(phase);

        // Cacule position of this image
        int x = (width - textWidth) / 2;
        int y = newHeight - 100;

        graphics.drawString(phase, x, y); // Create the text in that image

        // TODO: Write the new image a new file

        String path = env.getProperty("PATH"); // Create a path based in PATH in .env file

        File dir = new File(path); // Create a path based on PATH in the .env file
        dir.mkdirs(); // If not exist this directory, create
        File outputFile = new File(dir, filename); // Add the sticker image to this Path

        ImageIO.write(newImage, "png", outputFile); // Create the sticker image
    }


}
