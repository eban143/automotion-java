package net.itarray.automotion.internal;

import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static net.itarray.automotion.validation.Constants.*;

public class DrawableScreenshot {

    private final File screenshot;
    private final DrawingConfiguration drawingConfiguration;
    private BufferedImage img;
    private TransformedGraphics graphics;
    private File output;

    public DrawableScreenshot(DriverFacade driver, SimpleTransform transform, DrawingConfiguration drawingConfiguration) {
        screenshot = driver.takeScreenshot();
        this.drawingConfiguration = drawingConfiguration;
        try {
            img = ImageIO.read(screenshot);
            Graphics2D g = img.createGraphics();

            graphics = new TransformedGraphics(g, transform);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create screenshot file: " + screenshot, e);
        }
    }

    public File getOutput() {
        return output;
    }

    public void drawOffsets(UIElement rootElement, OffsetLineCommands offsetLineCommands) {
        offsetLineCommands.draw(graphics, img, rootElement, drawingConfiguration);
    }

    public void drawScreenshot(String rootElementReadableName, Errors errors) {
        for (Object obj : errors.getMessages()) {
            JSONObject det = (JSONObject) obj;
            JSONObject details = (JSONObject) det.get(REASON);
            JSONObject numE = (JSONObject) details.get(ELEMENT);

            if (numE != null) {
                int x = (int) (float) numE.get(X);
                int y = (int) (float) numE.get(Y);
                int width = (int) (float) numE.get(WIDTH);
                int height = (int) (float) numE.get(HEIGHT);

                drawingConfiguration.setHighlightedElementStyle(graphics);
                graphics.drawRectByExtend(x, y, width, height);
            }
        }

        output = new File(TARGET_AUTOMOTION_IMG + rootElementReadableName.replace(" ", "") + "-" + screenshot.getName());
        output.getParentFile().mkdirs();
        try {
            ImageIO.write(img, "png", output);
        } catch (IOException e) {
            throw new RuntimeException("Writing file failed for " + screenshot , e);
        }
    }

    public void drawRootElement(UIElement rootElement) {
        drawingConfiguration.setRootElementStyle(graphics);
        int x = rootElement.getX();
        int y = rootElement.getY();
        graphics.drawRectByExtend(x, y, rootElement.getWidth(), rootElement.getHeight());
    }
}