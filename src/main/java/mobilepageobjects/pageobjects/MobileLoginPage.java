package mobilepageobjects.pageobjects;

import helpers.Constant;
import helpers.MobileCommonActions;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import step_definitions.MobileHooks;

import java.util.List;

import static helpers.WaitUtils.*;


public class MobileLoginPage extends MobileHooks {

    public MobileLoginPage() {
        PageFactory.initElements(new AppiumFieldDecorator(this.androidDriver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Skip']")
    private AndroidElement skipButton;

    @AndroidFindBy(className = "android.widget.EditText")
    private List<AndroidElement> activationCodes;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Password']")
    private AndroidElement password;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sign In']")
    private AndroidElement signInButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SECURITY CODE']")
    private AndroidElement securityCode;

    @AndroidFindBy(className = "android.widget.EditText")
    private List<AndroidElement> opts;


    public void clickOnSkipButton() {
        MobileCommonActions.tapByElement(androidDriver, this.skipButton);
    }

    public void enterActivationCode(String activationCodeValue) {
        waitForElementPresent(androidDriver, this.activationCodes.get(1));
        int i = 0;
        for (AndroidElement activationCode : this.activationCodes) {
            char[] activationCodeCharacter = activationCodeValue.toCharArray();
            MobileCommonActions.inputText(androidDriver, activationCode, String.valueOf(activationCodeCharacter[i]));
            i++;
        }
    }

    public void enterPassword() {
        MobileCommonActions.inputText(androidDriver, this.password, Constant.PASSWORD) ;
    }

    public void clickOnSignInButton() {
        MobileCommonActions.tapByElement(androidDriver, this.signInButton);
        waitForElementPresent(androidDriver, this.securityCode);
    }

    public void enterOPT() {
        int i = 0;
        for (AndroidElement opt : this.opts) {
            char[] optCharacter = Constant.OTP.toCharArray();
            MobileCommonActions.inputText(androidDriver, opt, String.valueOf(optCharacter[i]));
            i++;
        }
    }
}
