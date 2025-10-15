package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(id = "firstName") private WebElement firstNameInput;
    @FindBy(id = "lastName") private WebElement lastNameInput;
    @FindBy(id = "email") private WebElement emailInput;
    @FindBy(id = "password") private WebElement passwordInput;
    @FindBy(id = "submit") private WebElement submitBtn;

    public RegisterPage open(String baseUrl) {
        driver.get(baseUrl + "addUser");
        return this;
    }

    public RegisterPage enterFirstName(String name) {
        firstNameInput.sendKeys(name);
        return this;
    }

    public RegisterPage enterLastName(String name) {
        lastNameInput.sendKeys(name);
        return this;
    }

    public RegisterPage enterEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public RegisterPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public ContactListPage submit() {
        submitBtn.click();
        return new ContactListPage();
    }
}
