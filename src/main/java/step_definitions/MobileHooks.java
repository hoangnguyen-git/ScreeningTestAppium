package step_definitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileHooks {
    protected static AndroidDriver androidDriver;

    @Before
    public AndroidDriver setUpAppium() throws MalformedURLException
    {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Hoang Nguyen");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.NO_RESET, true);
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10000);
        cap.setCapability(MobileCapabilityType.UDID, "ce031823108a223604");
        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.leapxpert.manager.qa");
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.leapxpertaccountmanager.MainActivity");
        androidDriver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
        androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return androidDriver;
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }

        if (androidDriver != null) {
            androidDriver.quit();
        }
    }
}
