package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditContactPage extends BasePage {
//edit to contact
    @FindBy(id = "edit-contact") private WebElement editBtn;
    @FindBy(id = "firstName") private WebElement firstNameInput;
    @FindBy(id = "submit") private WebElement saveBtn;
    @FindBy(id = "delete") private WebElement deleteBtn;

    public EditContactPage clickEdit() {
        editBtn.click();
        return this;
    }

    public EditContactPage updateFirstName(String newName) {
        firstNameInput.clear();
        firstNameInput.sendKeys(newName);
        return this;
    }

    public ContactListPage saveChanges() {
        saveBtn.click();
        return new ContactListPage();
    }

    public ContactListPage clickDelete() {
        deleteBtn.click();
        driver.switchTo().alert().accept();
        return new ContactListPage();
    }
}
