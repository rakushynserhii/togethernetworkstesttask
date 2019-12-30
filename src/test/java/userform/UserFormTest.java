package userform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.Constants;
import pageobject.userform.UserForm;

public class UserFormTest {

    private UserForm userForm;
    private WebDriver driver;

    private void writeText(By element, String text) { driver.findElement(element).sendKeys(text); }

    private void click(By element) { driver.findElement(element).click(); }

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedrivermac");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "start-maximized", "--disable-dev-shm-usage", "disable-infobars", "--disable-extensions");
        driver = new ChromeDriver(options);
        driver.get(Constants.webForm);
        userForm = new UserForm(driver);
    }

    @Test(description = "C77 - Отправить форму без заполнения поля 'Email'" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/77")
    public void sendFormWithoutEmail() {
        userForm.fillInTextFields("", "21", "Ivan");
        click(userForm.excellentMood);
        click(userForm.sendButton);
        Assert.assertTrue(driver.findElement(userForm.errorRedBackgroundEmailField).isDisplayed());
        Assert.assertEquals(driver.findElement(userForm.errorRedLineEmailField).getCssValue("background-color"), "rgba(217, 48, 37, 1)");
        Assert.assertTrue(driver.findElement(userForm.errorMessageEmailField).isDisplayed());
    }

    @Test(description = "C78 - Отправить форму без заполнения поля 'Age'" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/78")
    public void sendFormWithoutAge() {
        userForm.fillInTextFields("email@gmail.com", "", "Ivan");
        click(userForm.excellentMood);
        click(userForm.sendButton);
        Assert.assertTrue(driver.findElement(userForm.errorRedBackgroundAgeField).isDisplayed());
        Assert.assertEquals(driver.findElement(userForm.errorRedLineAgeField).getCssValue("background-color"), "rgba(217, 48, 37, 1)");
        Assert.assertTrue(driver.findElement(userForm.errorMessageAgeField).isDisplayed());
    }

    @Test(description = "C79 - Отправить форму без заполнения поля 'Name'" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/79")
    public void sendFormWithoutName() {
        userForm.fillInTextFields("email@gmail.com", "21", "");
        click(userForm.excellentMood);
        click(userForm.sendButton);
        Assert.assertTrue(driver.findElement(userForm.errorRedBackgroundNameField).isDisplayed());
        Assert.assertTrue(driver.findElement(userForm.errorMessageNameField).isDisplayed());
    }

    @Test(description = "C80 - Отправить форму с заполнением всех обязательных полей" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/80")
    public void sendFormWithRequiredFields() {
        userForm.fillInTextFields("email@gmail.com", "21", "Ivan");
        click(userForm.excellentMood);
        click(userForm.sendButton);
        Assert.assertTrue(driver.findElement(userForm.sendFormSuccess).isDisplayed());
    }

    @Test(description = "C6 - Проверить правильность перевода и наличие орфографических ошибок в заголовке формы" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/6")
    public void checkHeaderForm() {
        Assert.assertTrue(driver.findElement(userForm.titleForm).isDisplayed());
    }

    @Test(description = "C7 - Проверить правильность перевода и наличие орфографических ошибок в описании формы" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/7")
    public void checkDescriptionForm() {
        Assert.assertTrue(driver.findElement(userForm.descriptionForForm).isDisplayed());
    }

    @Test(description = "C8 - Проверить правильность перевода и наличие орфографических ошибок" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/8")
    public void checkHeaderEmailField() {
        Assert.assertTrue(driver.findElement(userForm.titleEmail).isDisplayed());
    }

    @Test(description = "C9 - Проверить обозначение обязательного поля рядом с заголовком" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/9")
    public void checkObligatoryEmailField() {
        Assert.assertTrue(driver.findElement(userForm.obligatoryEmail).isDisplayed());
    }

    @Test(description = "C42 - Проверить отображение предупреждающего сообщения и выделение блока при пустом поле 'Email'" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/42")
    public void emailFieldIsEmpty() {
        writeText(userForm.emailField, "email");
        driver.findElement(userForm.emailField).clear();
        Assert.assertTrue(driver.findElement(userForm.errorRedBackgroundEmailField).isDisplayed());
        Assert.assertEquals(driver.findElement(userForm.errorRedLineEmailField).getCssValue("background-color"), "rgba(217, 48, 37, 1)");
        Assert.assertTrue(driver.findElement(userForm.errorMessageEmailField).isDisplayed());
    }

    @Test(description = "C21 - Проверить отображение предупреждающего сообщения и выделение блока без точек в доменной части поля 'Email'" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/21")
    public void emailFieldWithoutDotsInTheDomain() {
        writeText(userForm.emailField, "email@gmailcom");
        Assert.assertTrue(driver.findElement(userForm.errorRedBackgroundEmailField).isDisplayed());
        Assert.assertEquals(driver.findElement(userForm.errorRedLineEmailField).getCssValue("background-color"), "rgba(217, 48, 37, 1)");
        Assert.assertTrue(driver.findElement(userForm.errorMessageEmailField).isDisplayed());
    }

    @Test(description = "C22 - Проверить отображение предупреждающего сообщения и выделение блока без точек c отсутствием @ в 'Email'" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/22")
    public void emailFieldWithoutAtInTheEmail() {
        writeText(userForm.emailField, "emailgmail.com");
        Assert.assertTrue(driver.findElement(userForm.errorRedBackgroundEmailField).isDisplayed());
        Assert.assertEquals(driver.findElement(userForm.errorRedLineEmailField).getCssValue("background-color"), "rgba(217, 48, 37, 1)");
        Assert.assertTrue(driver.findElement(userForm.errorMessageEmailField).isDisplayed());
    }

    @Test(description = "C26 - Проверить отображение предупреждающего сообщения и выделение блока без точек без доменной части в 'Email'" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/26")
    public void emailFieldWithoutDomainInTheEmail() {
        writeText(userForm.emailField, "email@");
        driver.findElement(userForm.emailField).clear();
        Assert.assertTrue(driver.findElement(userForm.errorRedBackgroundEmailField).isDisplayed());
        Assert.assertEquals(driver.findElement(userForm.errorRedLineEmailField).getCssValue("background-color"), "rgba(217, 48, 37, 1)");
        Assert.assertTrue(driver.findElement(userForm.errorMessageEmailField).isDisplayed());
    }

    @Test(description = "C40 - Проверить корректную отправку данных c пробелом перед введением Email" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/40")
    public void emailFieldWithSpaceBeforeTheValue() {
        userForm.fillInTextFields("    email@gmail.com", "21", "Ivan");
        click(userForm.excellentMood);
        click(userForm.sendButton);
        Assert.assertTrue(userForm.isSendSuccess());
    }

    @Test(description = "C41 - Проверить корректную отправку данных c пробелом после введения Email" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/41")
    public void emailFieldWithSpaceAfterTheValue() {
        userForm.fillInTextFields("email@gmail.com     ", "21", "Ivan");
        click(userForm.excellentMood);
        click(userForm.sendButton);
        Assert.assertTrue(userForm.isSendSuccess());
    }

    @Test(description = "C10 - Проверить правильность перевода и наличие орфографических ошибок в заголовке поля 'Age'" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/10")
    public void checkHeaderAgeField() {
        Assert.assertTrue(driver.findElement(userForm.ageField).isDisplayed());
    }

    @Test(description = "C44 - Проверить отображение предупреждающего сообщения и выделение блока при пустом поле 'Age'" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/44")
    public void ageFieldIsEmpty() {
        writeText(userForm.ageField, "21");
        driver.findElement(userForm.ageField).clear();
        Assert.assertTrue(driver.findElement(userForm.errorRedBackgroundAgeField).isDisplayed());
        Assert.assertEquals(driver.findElement(userForm.errorRedLineAgeField).getCssValue("background-color"), "rgba(217, 48, 37, 1)");
        Assert.assertTrue(driver.findElement(userForm.errorMessageAgeField).isDisplayed());
    }

    @Test(description = "C57 - Проверить отображение предупреждающего сообщения и выделение блока при пустом поле 'Name'" +
            "https://togethernetworkstest.testrail.io/index.php?/cases/view/57")
    public void nameFieldIsEmpty() {
        writeText(userForm.nameField, "Ivan");
        driver.findElement(userForm.nameField).clear();
        Assert.assertTrue(driver.findElement(userForm.errorRedBackgroundNameField).isDisplayed());
        Assert.assertTrue(driver.findElement(userForm.errorMessageNameField).isDisplayed());
    }

    @AfterMethod
    public void afterMethod() { driver.quit(); }

}
