package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactListPage extends BasePage {

    @FindBy(id = "add-contact") private WebElement addContactBtn;
    @FindBy(xpath = "//table//tr") private List<WebElement> contactRows;
//list pages methodes
    public AddContactPage clickAddContact() {
        addContactBtn.click();
        return new AddContactPage();
    }

    public EditContactPage selectFirstContact() {
        if (contactRows.size() > 1)
            contactRows.get(1).click(); // skip header
        return new EditContactPage();
    }

    public boolean isContactPresent(String name) {
        return contactRows.stream().anyMatch(r -> r.getText().contains(name));
    }

    public ContactListPage deleteFirstContact() {
        selectFirstContact().clickDelete();
        return this;
    }
}
