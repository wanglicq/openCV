package com.TemplateMatch;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class Task implements Runnable{

    TakeScreenshot screenshot = new TakeScreenshot();
    Matching matching = new Matching();
    AppiumDriver wd;

    public Task(AppiumDriver wd){
        this.wd = wd;
    }


    @Override
    public void run() {
        {
            screenshot.take(wd, "screenshot_find_redpacket");
            try {
                int location[] = matching.getMatchingLocation2("screenshots/screenshot_find_redpacket.png", "temp/redpacket.png", "compare/find_compared.png");
                System.out.println(location);
                if(location != null) {
                    wd.tap(1, location[0], location[1], 100);
                    Thread.sleep(3000);
                    screenshot.take(wd, "screenshot_open_redpacket");
                    int openLocation[] = matching.getMatchingLocation2("screenshots/screenshot_open_redpacket.png", "temp/openRedpacket.png", "compare/open_compared.png");
                    if(openLocation != null) {
                        wd.tap(1, openLocation[0], openLocation[1], 100);
                        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ImageView[1]")).click();
                    }
                    else{
                        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.view.View[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ImageView[1]")).click();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
