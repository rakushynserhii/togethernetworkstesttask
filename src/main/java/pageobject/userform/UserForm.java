package pageobject.userform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageobject.BasePage;

public class UserForm extends BasePage {

    public By titleForm = By.xpath("//div[text()='Test Form']");
    public By descriptionForForm = By.className("freebirdFormviewerViewHeaderRequiredLegend");

    public By titleEmail = By.id("i1");
    public By obligatoryEmail = By.id("i3");
    public By emailField = By.name("emailAddress");
    public By errorRedBackgroundEmailField = By.cssSelector("div.freebirdFormviewerViewItemsItemItem." +
            "freebirdFormviewerViewItemsTextTextItem.freebirdFormviewerViewEmailCollectionField.HasError");
    public By errorRedLineEmailField = By.cssSelector("div.quantumWizTextinputPaperinputUnderline.exportUnderline");
    public By errorMessageEmailField = By.id("i2");

    public By titleAge = By.xpath("//div[text()='Age: ']");
    public By ageField = By.id("ageField");
    public By errorRedBackgroundAgeField = By.cssSelector("");
    public By errorRedLineAgeField = By.cssSelector("");
    public By errorMessageAgeField = By.id("");

    public By titleName = By.xpath("//div[text()='Name: ']");
    public By nameField = By.name("entry.260210294");
    public By errorRedBackgroundNameField = By.cssSelector("div.freebirdFormviewerViewItemsItemItem." +
            "freebirdFormviewerViewItemsTextTextItem.HasError");
    public By errorMessageNameField = By.id("i.err.404367803");

    public By titleMood = By.xpath("//div[text()='How is your mood? ']");
    public By excellentMood = By.xpath("//span[text()='Excellent']");
    public By goodEnoughMood = By.xpath("//span[text()='Good enough']");
    public By couldBeBetterMood = By.xpath("//span[text()='Could be better']");
    public By veryBadMood = By.xpath("//span[text()='Very bad']");
    public By otherMood = By.xpath("//span[text()='Other:']");
    public By otherField = By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[4]/div/div[2]/div[5]/div/div/div/div");
    public By sendButton = By.xpath("//span[text()='Надіслати']");

    public By sendFormSuccess = By.xpath("//div[text()='Вашу відповідь було записано.']");

    public UserForm(WebDriver driver) {
        super(driver);
    }

    private boolean isPageLoaded() {
        return titleFormIsDisplayed()
                && textForFormIsDisplayed()
                && titleEmailIsDisplayed()
                && emailFieldIsDisplayed()
                && nameFieldIsDisplayed();
    }

    private boolean titleFormIsDisplayed() {
        waitVisibility(titleForm);
        return isDisplayed(titleForm);
    }

    private boolean textForFormIsDisplayed() {
        waitVisibility(descriptionForForm);
        return isDisplayed(descriptionForForm);
    }

    private boolean titleEmailIsDisplayed() {
        waitVisibility(titleEmail);
        return isDisplayed(titleEmail);
    }

    private boolean emailFieldIsDisplayed() {
        waitVisibility(emailField);
        return isDisplayed(emailField);
    }

    private boolean nameFieldIsDisplayed() {
        waitVisibility(nameField);
        return isDisplayed(nameField);
    }

    public boolean isSendSuccess() {
        waitVisibility(sendFormSuccess);
        return isDisplayed(sendFormSuccess);
    }

    public void fillInTextFields(String email, String age, String name) {
        Assert.assertTrue(isPageLoaded());
        writeText(emailField, email);
        writeText(ageField, age);
        writeText(nameField, name);
    }

}
