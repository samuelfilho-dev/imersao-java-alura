package alura.semana.imersao.java;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StickerMaker {
    Properties env = EnvReader.getEnv(); // Create a .env reader

    public void create(InputStream stream, String filename, String phase,InputStream inputPosition) throws Exception {

        // Read image
        BufferedImage originalImage = ImageIO.read(stream);
        BufferedImage superpositionImage = ImageIO.read(inputPosition);

        // Create a new image in memory with transparency and a new size
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        int newHeight = addPixelsH(height,200);

        BufferedImage newImage = new BufferedImage(width, newHeight, Transparency.TRANSLUCENT);

        // Copy the original image for the new image (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics(); // Create a graphic of a new Image
        graphics.drawImage(originalImage, 0, 0, null); // Add the new image to the original image
        graphics.drawImage(superpositionImage, 0, newHeight - superpositionImage.getHeight(), null);



        // Configurate the phase
        Font font = createFont(graphics, "Impact", 80);


        // Get Dimension of String
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(phase);

        // Cacule position of this image
        int x = calculateX(width,textWidth);
        int y = calculateY(newHeight,100);

        graphics.drawString(phase, x, y); // Create the text in that image

        // Create the Outline Of Phase
        createShapeText(graphics,x,y,width,phase,font);

        // Write the new image a new file
        saveImage(newImage, filename);
    }

    private void saveImage(BufferedImage newImage, String filename) {

        String path = env.getProperty("PATH");

        File dir = new File(path);
        dir.mkdirs();
        File outputFile = new File(dir, filename);

        try {
            ImageIO.write(newImage, "png", outputFile);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }

    }

    private void createShapeText(Graphics2D graphics, int x, int y, int width, String phase, Font font) {

        FontRenderContext renderContext = graphics.getFontRenderContext();
        TextLayout layout = new TextLayout(phase, font, renderContext);

        Shape outline = layout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(x, y);
        graphics.setTransform(transform);

        BasicStroke stroke = new BasicStroke(width * 0.004f);
        graphics.setStroke(stroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

    }

    private int calculateX(int width, int textWidth){
        return (width - textWidth) / 2;
    }

    private int calculateY(int newHeight, int value){
        return newHeight - value;
    }

    private int addPixelsH(int height,int px){
        return height + px;
    }


    private Font createFont(Graphics2D graphics,String fontName, int size){

        Font font = new Font(fontName, Font.BOLD, size);
        graphics.setFont(font);
        graphics.setColor(Color.yellow);
        return font;

    }



}
