package mobilepageobjects.pageobjects;

import helpers.MobileCommonActions;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import step_definitions.MobileHooks;

public class MobileContactPage extends MobileHooks {

    public MobileContactPage() {
        PageFactory.initElements(new AppiumFieldDecorator(this.androidDriver), this);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='bottomTab_contact']")
    private AndroidElement contactMenu;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='contact_inviteClientMenu']")
    private AndroidElement contactIcon;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='contact_inviteNewClient']")
    private AndroidElement inviteNewClient;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='inviteClient_codeName']")
    private AndroidElement clientCodeName;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone Number*']/following-sibling::android.view.ViewGroup/android.widget.EditText")
    private AndroidElement phone;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='inviteClient_firstName']")
    private AndroidElement firstName;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='inviteClient_lastName']")
    private AndroidElement lastName;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='inviteClient_email']")
    private AndroidElement email;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='inviteClient_confirm']")
    private AndroidElement inviteButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='contact_search']")
    private AndroidElement searchBox;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]")
    private AndroidElement firstContactInList;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Username']/following-sibling::android.widget.TextView[1]")
    private AndroidElement userName;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='profile_firstName']")
    private AndroidElement firstNameDetail;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='profile_lastName']")
    private AndroidElement lastNameDetail;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='profile_company']")
    private AndroidElement company;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='profile_phoneNumber']")
    private AndroidElement phoneDetail;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='profile_email']")
    private AndroidElement emailDetail;


    public void clickOnContactMenu() {
        MobileCommonActions.tapByElement(androidDriver, this.contactMenu);
    }

    public void clickOnContactIcon() {
        MobileCommonActions.tapByElement(androidDriver, this.contactIcon);
    }

    public void selectInviteNewClient() {
        MobileCommonActions.tapByElement(androidDriver, this.inviteNewClient);
    }

    public void inputInformationForNewClient(String codeName, String phone, String firstName, String lastName, String email) {
        MobileCommonActions.inputText(androidDriver, this.clientCodeName, codeName);
        MobileCommonActions.inputText(androidDriver, this.phone, phone);
        MobileCommonActions.inputText(androidDriver, this.firstName, firstName);
        MobileCommonActions.inputText(androidDriver, this.lastName, lastName);
        MobileCommonActions.inputText(androidDriver, this.email, email);
    }

    public void enterSearchBox(String value) {
        MobileCommonActions.inputText(androidDriver, this.searchBox, value);
    }

    public void clickOnTheFirstResult() {
        MobileCommonActions.tapByElement(androidDriver, this.firstContactInList);
    }

    public String getUserName() {
        return MobileCommonActions.getText(androidDriver, this.userName);
    }

    public String getFirstName() {
        return MobileCommonActions.getText(androidDriver, this.firstNameDetail);
    }

    public String getLastName() {
        return MobileCommonActions.getText(androidDriver, this.lastNameDetail);
    }

    public String getCompany() {
        MobileCommonActions.scrollTo(androidDriver, "down", 2);
        return MobileCommonActions.getText(androidDriver, this.company);
    }

    public String getPhoneNumber() {
        MobileCommonActions.scrollTo(androidDriver, "down", 2);
        return MobileCommonActions.getText(androidDriver, this.phoneDetail);
    }

    public String getEmail() {
        MobileCommonActions.scrollTo(androidDriver, "down", 2);
        return MobileCommonActions.getText(androidDriver, this.emailDetail);
    }

    public void clickOnInviteButton() {
        MobileCommonActions.tapByElement(androidDriver, this.inviteButton);
    }
}
