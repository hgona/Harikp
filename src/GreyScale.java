/**
 * Created by gonah on 9/24/2017.
 */
public class GreyScale {
    import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;
import java.io.File;

    public class BatchGreyscale {


        //TODO: select files
        public void selectImages() {

            DirectoryResource dir = new DirectoryResource();

            for (File f : dir.selectedFiles()) {
                ImageResource newImage = convertToGreyscale(new ImageResource(f));
                newImage.setFileName("grey-" + f.getName());
                newImage.draw();
                newImage.save();
            }


        }

        private ImageResource convertToGreyscale(ImageResource inImage) {

            ImageResource newImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
            for (Pixel px : inImage.pixels()) {

                int rbgMean = getRBGMean(px);
                Pixel modifiedPixel = newImage.getPixel(px.getX(), px.getY());
                modifiedPixel.setRed(rbgMean);
                modifiedPixel.setBlue(rbgMean);
                modifiedPixel.setGreen(rbgMean);
            }

            return newImage;
        }