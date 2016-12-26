package com.TemplateMatch;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Matching {

    public static int[] getMatchingLocation1(String... args) throws Exception {
        // create input and output mat
        Mat src = Imgcodecs.imread(args[0], 0);
        Mat tmp = Imgcodecs.imread(args[1], 0);

        // Create the result matrix
        Mat result = createResultMatrix(src, tmp);

        // Match Template Function from OpenCV
        Core.MinMaxLocResult mmr = matchAndFindLoc(src, tmp, result, Imgproc.TM_SQDIFF_NORMED);

        // Got location
        Point matchLoc = getCorrectLoc(mmr, Imgproc.TM_SQDIFF_NORMED);

        //show what we got
        getMatchArea(src, tmp,  matchLoc, args);

        //return location

        int myLocation[] = getClickLocation(matchLoc, tmp);

        System.out.println("x= " + myLocation[0]);
        System.out.println("y= " + myLocation[1]);

        return myLocation;
    }


    public static int[] getMatchingLocation2(String... args) throws Exception {
        // create input and output mat
        Mat src = readImage(0, args);
        Mat tmp = readImage(1, args);
        // Create the result matrix
        Mat result = createResultMatrix(src, tmp);

        // Match Template Function from OpenCV
        Core.MinMaxLocResult mmr = matchAndFindLoc(src, tmp, result, Imgproc.TM_CCOEFF_NORMED);

        // Got location
        Point matchLoc = getCorrectLoc(mmr, Imgproc.TM_CCOEFF_NORMED);

        //got match percentage
        double matchPercentage = getMatchPercentage(mmr, Imgproc.TM_CCOEFF_NORMED);

        if(matchPercentage >= 0.95) {
            //show what we got
            getMatchArea(src, tmp,  matchLoc, args);

            int myLocation[] = getClickLocation(matchLoc, tmp);

            System.out.println("x= " + myLocation[0]);
            System.out.println("y= " + myLocation[1]);

            return myLocation;
        }
        else {
            return null;
        }
    }


    private static Mat readImage(int index, String... args){
        return Imgcodecs.imread(args[index], 0);
    }

    private static Mat createResultMatrix(Mat src, Mat tmp) {
        int result_cols = src.cols() - tmp.cols() +1;
        int result_rows = src.rows() - tmp.rows() +1;
        Mat result = new Mat(result_cols,result_rows, CvType.CV_32FC1);
        return result;
    }

    //get min max location
    private static Core.MinMaxLocResult matchAndFindLoc(Mat src, Mat tmp, Mat result, int method) {
        Imgproc.matchTemplate(src, tmp, result, method);
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
        return mmr;
    }


    private static Point getCorrectLoc(Core.MinMaxLocResult mmr, int method) {
        if(method == Imgproc.TM_SQDIFF_NORMED || method == Imgproc.TM_SQDIFF){
            return mmr.minLoc;
        }
        else {
            return mmr.maxLoc;
        }

    }

    private static double getMatchPercentage(Core.MinMaxLocResult mmr, int method) {
        if(method == Imgproc.TM_SQDIFF_NORMED || method == Imgproc.TM_SQDIFF){
            return 1 - mmr.minVal;
        }
        else {
            return mmr.maxVal;
        }
    }

    //draw
    private static void getMatchArea(Mat src, Mat tmp, Point matchLoc, String... args) {
        Point endPoint = new Point(matchLoc.x + tmp.cols(), matchLoc.y + tmp.rows());
        Imgproc.rectangle(src, matchLoc, endPoint, new Scalar(0,255,0));

        Imgcodecs.imwrite(args[2],src);
    }


    private static int[] getClickLocation(Point matchLoc, Mat tmp) {
        Double x1 = matchLoc.x;
        Double y1 = matchLoc.y;
        int x = x1.intValue() + tmp.cols()/2;
        int y = y1.intValue() + tmp.rows()/2;
        return new int[]{x, y};
    }


}
