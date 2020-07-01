package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class WaitUtils {

    public static void waitForElementPresent(WebDriver aDriver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(aDriver, Constant.TIME_OUT_SECOND);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementPresent(WebDriver aDriver, WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(aDriver, time);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForLoadingSuccess(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Constant.TIME_OUT_SECOND);
        try {
            waitForElementPresent(driver, driver.findElement(By.id("loading-bar-spinner")));
        } catch (Exception e) {
            e.getMessage();
        }
        wait.until(invisibilityOfElementLocated(By.id("loading-bar-spinner")));
    }

    public static void waitForElementClickable(WebDriver aDriver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(aDriver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementClickable(WebDriver aDriver, WebElement element) {
        waitForElementClickable(aDriver, element, Constant.TIME_OUT_SECOND);
    }

    public static void waitForElementClickableBy(WebDriver aDriver, By by) {
        WebDriverWait wait = new WebDriverWait(aDriver, Constant.TIME_OUT_SECOND);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementIsInvisible(WebDriver driver, int second, By by) {
        WebDriverWait wait = new WebDriverWait(driver, second);
        wait.until(invisibilityOfElementLocated(by));
    }
}
