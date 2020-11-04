package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage {
    private WebDriver driver;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "login_field")
    WebElement loginField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(name = "commit")
    WebElement submitButton;

    public IndexPage typeLogin(String login) {
        loginField.sendKeys(login);
        return this;
    }

    public IndexPage typePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public void clickSubmit() {
        submitButton.click();
    }
}
