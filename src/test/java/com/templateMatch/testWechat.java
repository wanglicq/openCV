package com.TemplateMatch;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class testWechat {
    AppiumDriver wd;
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Before
    public void setup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "1.0");
        wd = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }


    @Test
    public void opencvLoginTest() throws Exception {
        TakeScreenshot screenshot = new TakeScreenshot();
        Matching matching = new Matching();
        wd.findElement(By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.Button[2]"));
        screenshot.take(wd, "screenshot_login");
        int location[] = matching.getMatchingLocation1("screenshots/screenshot_login.png", "temp/Login.png", "compare/login_compared.png");
        wd.tap(1, location[0], location[1], 100);
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[2]")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[1]")).sendKeys("account");
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]")).sendKeys("password");
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[1]")).click();
        wd.findElement(By.xpath("//android.widget.FrameLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.support.v4.view.ViewPager[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]")).click();
    }

    @Test
    public void opencvGetRedpacketTest() throws Exception {
        opencvLoginTest();
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
        Task task = new Task(wd);
        executorService.scheduleWithFixedDelay(task, 5000, 5000, TimeUnit.MILLISECONDS);
        while (!executorService.isShutdown()) {
            Thread.sleep(1000);
        }
    }

    @After
    public void shutDown() throws Exception{
        wd.quit();
    }

}
