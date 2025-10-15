package tests;

import com.github.javafaker.Faker;
import utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import utilities.ConfigReader;

public class ContactListTest {

    RegisterPage registerPage;
    ContactListPage contactListPage;
    Faker faker = new Faker();

    String baseUrl = ConfigReader.getProperty("baseUrl");
    String randomEmail = faker.internet().emailAddress();

    @BeforeClass
    public void setup() {
        registerPage = new RegisterPage();
    }

    @Test(priority = 1)
    public void createUser() {
        contactListPage = registerPage
                .open(baseUrl)
                .enterFirstName("John")
                .enterLastName("Doe")
                .enterEmail(randomEmail)
                .enterPassword("Password123!")
                .submit();

        Assert.assertTrue(contactListPage.getPageTitle().contains("Contact"), "User registration failed");

    }

    @Test(priority = 2)
    public void addContact() {
        contactListPage = contactListPage
                .clickAddContact()
                .enterFirstName("Alice")
                .enterLastName("Smith")
                .saveContact();

        Assert.assertTrue(contactListPage.isContactPresent("Alice"), "Contact not added");
    }

    @Test(priority = 3)
    public void editContact() {
        contactListPage.selectFirstContact()
                .clickEdit()
                .updateFirstName("Alicia")
                .saveChanges();

        Assert.assertTrue(contactListPage.isContactPresent("Alicia"), "Contact not edited");
    }

    @Test(priority = 4)
    public void deleteContact() {
        contactListPage.deleteFirstContact();
        Assert.assertFalse(contactListPage.isContactPresent("Alicia"), "Contact not deleted");
    }

    @AfterClass
    public void teardown() {
        Driver.quitDriver();
    }
}
