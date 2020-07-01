package pageobjects;

import helpers.CommonActions;
import helpers.Constant;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import step_definitions.Hooks;

import java.util.List;

import static helpers.WaitUtils.waitForElementPresent;

public class LoginPage extends Hooks {
    public LoginPage() {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "input[placeholder='Company']")
    private WebElement companyInput;

    @FindBy(xpath = "//span[text() = 'Next']")
    private WebElement nextButton;

    @FindBy(css = "input[placeholder='Username']")
    private WebElement username;

    @FindBy(css = "input[placeholder='Password']")
    private WebElement password;

    @FindBy(xpath = "//span[text() = 'Login' and contains(@class, 'button')]")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@type='tel']")
    private List<WebElement> opts;

    @FindBy(xpath = "//span[text() = 'Confirm']")
    private WebElement confirmButton;

    public void waitForPageIsDisplayed() {
        waitForElementPresent(webDriver, companyInput);
    }

    public void enterCompany() {
        CommonActions.setText(webDriver, this.companyInput, Constant.COMPANY);
    }

    public void clickOnNextButton() {
        CommonActions.click(webDriver, this.nextButton);
    }

    public void enterUsername() {
        CommonActions.setText(webDriver, this.username, Constant.USERNAME);
    }

    public void enterPassword() {
        CommonActions.setText(webDriver, this.password, Constant.PASSWORD);
    }

    public void clickOnLoginButton() {
        CommonActions.click(webDriver, this.loginButton);
    }

    public void enterOPT() {
        waitForElementPresent(webDriver, this.confirmButton);
        int i = 0;
        for (WebElement opt : this.opts) {
            char[] optValues = Constant.OTP.toCharArray();
            CommonActions.setText(webDriver, opt, String.valueOf(optValues[i]));
            i++;
        }
    }

    public void clickOnConfirmButton() {
        CommonActions.click(webDriver, this.confirmButton);
    }
}
