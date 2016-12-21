package com.templateMatch.test;


import org.junit.Test;
import org.opencv.core.Core;

/**
 * Created by wangli on 21/12/2016.
 */
public class TemplateMatching {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    Matching matching =new Matching();


    @Test
    public void FindLenaFace() throws Exception {
        int location[] = matching.getMatchingLocation1("screenshots/lena.png", "temp/temp_lena_face.png", "compare/recpacket_compared.png");

        System.out.println("x= "+location[0]+",  y="+location[1]);
    }
}
