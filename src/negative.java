/**
 * Created by gonah on 09/24/2017.
 */
import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;
import java.io.File;
public class negative {
    public void selectImages() {
            DirectoryResource dir = new DirectoryResource();
            for (File f : dir.selectedFiles()) {
                ImageResource newImage = convertToNegative(new ImageResource(f));
                newImage.setFileName("negative-" + f.getName());
                newImage.draw();
                newImage.save();
            }
        }
    private ImageResource convertToNegative(ImageResource inImage) {
        ImageResource newImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
            for (Pixel px : inImage.pixels()) {
                Pixel modifiedPixel = newImage.getPixel(px.getX(), px.getY());

                modifiedPixel.setRed(negativeColorValue(px.getRed()));
                modifiedPixel.setBlue(negativeColorValue(px.getBlue()));
                modifiedPixel.setGreen(negativeColorValue(px.getGreen()));
            }
            return newImage;
    }
    private int negativeColorValue(int colorValue) {
            final int MAX_RBG = 255;
            return MAX_RBG - colorValue;
        }

    }


