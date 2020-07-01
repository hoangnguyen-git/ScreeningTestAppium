package helpers;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Dimension;
import step_definitions.MobileHooks;

import java.time.Duration;

import static helpers.WaitUtils.waitForElementPresent;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class MobileCommonActions extends MobileHooks {

    //Tap to an element for 250 milliseconds
    public static void tapByElement(AndroidDriver androidDriver, AndroidElement androidElement) {
        new TouchAction(androidDriver)
                .tap(tapOptions().withElement(element(androidElement)))
                .waitAction(waitOptions(ofMillis(250))).perform();
    }

    public static String getAttribute(AndroidDriver androidDriver, AndroidElement androidElement, String attribute) {
        return androidElement.getAttribute(attribute);
    }

    public static String getText(AndroidDriver androidDriver, AndroidElement androidElement) {
        return getAttribute(androidDriver, androidElement, "text");
    }

    //Tap by coordinates
    public static void inputText(AndroidDriver androidDriver, AndroidElement androidElement, String text) {
        waitForElementPresent(androidDriver, androidElement);
        androidElement.clear();
        androidElement.sendKeys(text);
    }

    public static void scrollTo(AndroidDriver androidDriver, String direction, int times) {
        if (direction.equals("down")) {
            Dimension dim = androidDriver.manage().window().getSize();
            int width = dim.getWidth() / 2;
            for (int i = 0; i < times; i++) {
                int startY = (int) (dim.getHeight() * 0.7);
                int endY = (int) (dim.getHeight() * 0.5);
                new TouchAction(androidDriver).press(point(width, startY)).waitAction(waitOptions(Duration.ofSeconds(1)))
                        .moveTo(point(width, endY)).release().perform();
            }
        }
    }

}
