package step_definitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helpers.CommonMethods;
import helpers.Constant;
import mobilepageobjects.pageobjects.MobileContactPage;
import mobilepageobjects.pageobjects.MobileLoginPage;
import org.assertj.core.api.SoftAssertions;
import pageobjects.HomePage;
import pageobjects.LoginPage;

import java.util.List;

public class CreateNewClient {
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();
    private MobileLoginPage mobileLoginPage = new MobileLoginPage();
    private MobileContactPage mobileContactPage = new MobileContactPage();
    private SoftAssertions softAssertions = new SoftAssertions();
    private String activationCode, codeName, phone, firstName, lastName, email;

    @Given("^Login on Web page$")
    public void loginOnWebPage() {
        loginPage.waitForPageIsDisplayed();
        loginPage.enterCompany();
        loginPage.clickOnNextButton();
        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.clickOnLoginButton();
        loginPage.enterOPT();
        loginPage.clickOnConfirmButton();
    }

    @And("^Get the QR code$")
    public void getTheQRCode() {
        homePage.clickOnAvatar();
        homePage.clickOnDeviceTab();
        homePage.clickOnLinkDevice();
        homePage.clickOnRequestAnotherCode();
        activationCode = homePage.getActivationCode();
        System.out.printf(activationCode);
    }

    @And("^Open app and enter activation code$")
    public void openAppAndEnterActivationCode() {
        mobileLoginPage.clickOnSkipButton();
        mobileLoginPage.enterActivationCode(activationCode);
        mobileLoginPage.enterPassword();
        mobileLoginPage.clickOnSignInButton();
        mobileLoginPage.enterOPT();
    }

    @And("^User goes to the contact page and invite a new client$")
    public void userGoesToTheContactPageAndInviteANewClient() {
        mobileContactPage.clickOnContactMenu();
        mobileContactPage.clickOnContactIcon();
        mobileContactPage.selectInviteNewClient();
    }

    @And("^Enter all information of new client and click on invite button$")
    public void enterAllInformationOfNewClientAndClickOnInviteButton(DataTable dataTable) {
        getContactInformation(dataTable);
        mobileContactPage.inputInformationForNewClient(codeName, phone, firstName, lastName, email);
        mobileContactPage.clickOnInviteButton();
    }

    @Then("^Invite new contact is successful with correct information$")
    public void inviteNewContactIsSuccessfulWithCorrectInformation() {
        mobileContactPage.enterSearchBox(codeName);
        mobileContactPage.clickOnTheFirstResult();
        softAssertions.assertThat(mobileContactPage.getUserName()).isEqualTo(codeName);
        softAssertions.assertThat(mobileContactPage.getFirstName()).isEqualTo(firstName);
        softAssertions.assertThat(mobileContactPage.getLastName()).isEqualTo(lastName);
        softAssertions.assertThat(mobileContactPage.getCompany()).isEqualTo(Constant.COMPANY);
        softAssertions.assertThat(mobileContactPage.getPhoneNumber()).isEqualTo(phone.substring(1));
        softAssertions.assertThat(mobileContactPage.getEmail()).isEqualTo(email);
    }


    private void getContactInformation(DataTable dataTable) {
        List<String> data = dataTable.raw().get(1);
        codeName = data.get(0) + CommonMethods.getTimestamp();
        phone = data.get(1);
        firstName = data.get(2) + CommonMethods.getTimestamp();
        lastName = data.get(3) + CommonMethods.getTimestamp();
        email = data.get(4) + CommonMethods.getTimestamp() + "@gmail.com";
    }
}
