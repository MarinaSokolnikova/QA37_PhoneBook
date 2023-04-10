package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openAddForm() {
        click(By.xpath("//a[@href='/add']"));
    }

    public void fillAddForm(Contact contact) {
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
    }

    public void submitSave() {
        click(By.xpath("//div[@class='add_form__2rsm2']/button"));
    }


    public boolean isAddPageStillDisplayed() {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }


    public void openContactsForm() {
        click(By.cssSelector("[href='/contacts']"));
    }

    public void clickFirstContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
    }

    public void submitRemove() {
        click(By.xpath("//button[text()='Remove']"));
    }

    public int getContactsCount() {
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
    }


    public void removeContact() {
        clickFirstContact();
        submitRemove();
    }

    public void provideContacts() {
        if (getContactsCount() < 3)
        {
            while (getContactsCount() < 3)
            {
                addContact();
            }
        }
    }

    public void addContact() {
        Random random = new Random();
        int random2 = random.nextInt(1000);
        Contact contact = Contact.builder()
                .name("Cris")
                .lastName("Braun")
                .phone("917201736102"+ random2)
                .email("cris" + random2 + "@gmail.com")
                .address("Rehovot")
                .build();
        openAddForm();
        fillAddForm(contact);
        submitSave();

    }
}
