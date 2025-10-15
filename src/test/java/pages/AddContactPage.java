package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddContactPage extends BasePage {
//this cod for contact page
    @FindBy(id = "firstName") private WebElement firstName;
    @FindBy(id = "lastName") private WebElement lastName;
    @FindBy(id = "submit") private WebElement submitBtn;

    public AddContactPage enterFirstName(String name) {
        firstName.sendKeys(name);
        return this;
    }


    public AddContactPage enterLastName(String name) {
        lastName.sendKeys(name);
        return this;
    }

    public ContactListPage saveContact() {
        submitBtn.click();
        return new ContactListPage();
    }
}
