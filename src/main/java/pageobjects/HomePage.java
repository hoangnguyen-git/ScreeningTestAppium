package pageobjects;

import helpers.CommonActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import step_definitions.Hooks;

import static helpers.WaitUtils.waitForElementPresent;

public class HomePage extends Hooks {
    public HomePage() {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'ProfileButton')]/a/div")
    private WebElement avatarIcon;

    @FindBy(xpath = "//div[text() = 'Devices']")
    private WebElement devicesTab;

    @FindBy(xpath = "//div[text() = 'Link Device']")
    private WebElement linkDevice;

    @FindBy(xpath = "//div[text() = 'Request Another Code']")
    private WebElement requestAnotherCode;

    @FindBy(xpath = "//div[contains(@class, 'code-name')]")
    private WebElement activationCode;

    public void waitForPageIsDisplayed(){
        waitForElementPresent(webDriver, avatarIcon);
    }

    public void clickOnAvatar(){
        CommonActions.click(webDriver, this.avatarIcon);
    }

    public void clickOnDeviceTab() {
        CommonActions.click(webDriver, this.devicesTab);
    }

    public void clickOnLinkDevice() {
        CommonActions.click(webDriver, this.linkDevice);
    }

    public void clickOnRequestAnotherCode() {
        CommonActions.click(webDriver, this.requestAnotherCode);
    }

    public String getActivationCode() {
        return CommonActions.getText(webDriver, this.activationCode);
    }
}
