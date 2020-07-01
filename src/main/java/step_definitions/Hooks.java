package step_definitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.CommonActions;
import helpers.Constant;
import helpers.Log;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

public class Hooks {
    protected static WebDriver webDriver;

    @Before
    public WebDriver setupDriver() {
        Log.info("Opening browser...");
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setJavascriptEnabled(true);
        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capability.setCapability(ChromeOptions.CAPABILITY, options);
        String browser = System.getProperty("webdriver");

        if (browser == null || browser.equalsIgnoreCase("")) {
            ChromeDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver(capability);
        }
        openHomePage();
        return webDriver;
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + webDriver.getCurrentUrl());
                zoomBrowser("50");
                byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
                zoomBrowser("100");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }

        if (webDriver != null) {
            webDriver.manage().deleteAllCookies();
            webDriver.close();
            webDriver.quit();
        }
    }

    private void openHomePage() {
        webDriver.navigate().to(Constant.URL);
        if (CommonActions.getCurrentOperation().contains("Mac")) {
            webDriver.manage().window().fullscreen();
        } else {
            webDriver.manage().window().maximize();
        }
    }

    public void zoomBrowser(String percent) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("document.body.style.zoom='" + percent + "%'");
    }

}
