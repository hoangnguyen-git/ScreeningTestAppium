package helpers;

import org.openqa.selenium.*;
import step_definitions.Hooks;

import static helpers.WaitUtils.*;

public class CommonActions extends Hooks {

    public static void setText(WebDriver driver, WebElement element, String key) {
        waitForElementPresent(driver, element);
        try {
            if (element.isDisplayed() && element.isEnabled()) {
                element.clear();
                element.sendKeys(key);
            } else {
                Log.info("Unable to set text on field");
            }
        } catch (StaleElementReferenceException e) {
            Log.info("#Unable to set text on field: Element is not attached to the page document " + e.getStackTrace());
        } catch (NoSuchElementException e) {
            Log.info("#Unable to set text on field: Element was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            Log.info("Unable to set text on field " + e.getStackTrace());
        }
    }

    public static void selectOptionFromDropdown(WebDriver driver, WebElement element, String value) {
        waitForElementClickable(driver, element);
        clickByJS(driver, element);
        waitForLoadingSuccess(driver);
        waitForLoadingSuccess(driver);
        waitForElementIsInvisible(driver, Constant.TIME_OUT_SECOND, By.id("loading-bar-spinner"));
        String temp;
        String xpath = "(//div[contains(@id,'ui-select')]/span/formio-select-item/span[normalize-space()='" + value + "'])[1]";
        sleep(1);
        int j = 0;
        try {
            do {
                waitForElementClickableBy(driver, By.xpath(xpath));
                clickByJS(driver, driver.findElement(By.xpath(xpath)));
                waitForLoadingSuccess(driver);
                waitForElementIsInvisible(driver, 60, By.cssSelector("ul[class*='dropdown-menu']"));
                temp = driver.findElement(By.cssSelector("formio-select-item>span")).getText();
                if (!temp.equals(value)) {
                    driver.findElement(By.cssSelector("input[type='search']")).sendKeys(value);
                    waitForElementClickableBy(driver, By.xpath(xpath));
                    clickByJS(driver, driver.findElement(By.xpath(xpath)));
                }
                j++;
            } while (j < 4 && !temp.equals(value));

        } catch (Exception e) {
            e.getMessage();
        }
        waitForElementIsInvisible(driver, 60, By.cssSelector("ul[class*='dropdown-menu']"));

    }

    public static String getText(WebDriver driver, WebElement element) {
        waitForElementPresent(driver, element, Constant.TIME_OUT_SECOND);
        return element.getText();
    }

    public static void clickByJS(WebDriver driver, WebElement element, int timeout) {
        waitForElementClickable(driver, element, timeout);
        try {
            if (element.isEnabled() && element.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {
                System.out.println("Unable to click on element");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page DOCUMENT " + e.getStackTrace());
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Element was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to click on element " + e.getStackTrace());
        }
    }

    public static void clickByJS(WebDriver driver, WebElement element) {
        clickByJS(driver, element, Constant.TIME_OUT_SECOND);
    }

    public static boolean isElementDisplayed(WebElement element, boolean expected) {
        try {
            if (expected) {
                waitForElementPresent(webDriver, element);
            }
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void acceptAlert() {
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
        sleep(1);
    }

    public static void click(WebDriver driver, WebElement element) {
        waitForElementClickable(driver, element);
        scrollToElement(element);
        element.click();
    }

    public static void scrollToElement(WebElement el) {
        if (webDriver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) webDriver)
                    .executeScript("arguments[0].scrollIntoView(true);", el);
        }
    }

    public static String getCurrentOperation() {
        return System.getProperty("os.name");
    }

    public static String convertTextWithoutSpace(String text) {
        text = text.replaceAll(" ", "");
        text = text.replaceAll("\\s+", "");
        text = text.replaceAll("(\\r|\\n)", "");
        return text;
    }

    public static void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isExistElementBy(WebDriver driver, By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
