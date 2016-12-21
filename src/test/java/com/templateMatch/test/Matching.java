package com.templateMatch.test;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * Created by wangli on 17/12/2016.
 */
public class Matching {

    public static int[] getMatchingLocation(String... args) throws Exception {
        // create input and output mat
        Mat src = readImage(0, args);
        Mat tmp = readImage(1, args);

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

        return myLocation;
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

    //get match min and max location
    private static Core.MinMaxLocResult matchAndFindLoc(Mat src, Mat tmp, Mat result, int method) {

    }


    private static Point getCorrectLoc(Core.MinMaxLocResult mmr, int method) {
        if(method == Imgproc.TM_SQDIFF_NORMED || method == Imgproc.TM_SQDIFF){
            return mmr.minLoc;
        }
        else {
            return mmr.maxLoc;
        }

    }

    //draw a rectangel on matching area
    private static void getMatchArea(Mat src, Mat tmp, Point matchLoc, String... args) {

    }


    private static int[] getClickLocation(Point matchLoc, Mat tmp) {
        Double x1 = matchLoc.x;
        Double y1 = matchLoc.y;
        int x = x1.intValue() + tmp.cols()/2;
        int y = y1.intValue() + tmp.rows()/2;
        return new int[]{x, y};
    }


}
