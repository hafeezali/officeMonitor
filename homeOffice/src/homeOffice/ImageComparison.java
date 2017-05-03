package homeOffice;

import java.awt.Image;
import java.awt.image.PixelGrabber;

public class ImageComparison {
    
    public boolean compareImage(Image currentImage, Image nextImage, double percLimit){
        if(currentImage != null && nextImage != null){
            try{
                PixelGrabber currentPg = new PixelGrabber(currentImage, 0, 0, currentImage.getWidth(null), currentImage.getHeight(null), false);
                PixelGrabber nextPg = new PixelGrabber(nextImage, 0, 0, nextImage.getWidth(null), nextImage.getHeight(null), false);
                int[] currImgData = null;
                int[] nextImgData = null;
                if(currentPg.grabPixels()){
                    int width = currentPg.getWidth();
                    int height = currentPg.getHeight();
                    currImgData = new int[width*height];
                    currImgData = (int[]) currentPg.getPixels();
                }
                if(nextPg.grabPixels()){
                    int width = nextPg.getWidth();
                    int height = nextPg.getHeight();
                    nextImgData = new int[width*height];
                    nextImgData = (int[]) nextPg.getPixels();
                }
                int tolerance = 0;
                for(int i = 0; i< currImgData.length && i < nextImgData.length; i++){
                    if(currImgData[i] != nextImgData[i]){
                        tolerance++;
                    }
                }
                double percentage = ((double)tolerance/(double)currImgData.length)*100;
                if(percentage > percLimit){
                    return false;
                }
                else
                    return true;
            }
            catch(InterruptedException ex){
                return false;
            }
        }
        else{
            return false;
        }
    }
}
