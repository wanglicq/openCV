package com.PictureSmoth;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class smooth {

    public static void smooth(String filename) {
        Mat image = new Mat();
        image = Imgcodecs.imread(filename);
        if (image != null) {
            Imgproc.blur(image, image, new Size(3, 3));
            System.out.println("Smooth Done!");
        }
    }

    public static void main(String[] args) {
        String filename = "temp/openRedpacket.png";
        smooth(filename);
    }

}