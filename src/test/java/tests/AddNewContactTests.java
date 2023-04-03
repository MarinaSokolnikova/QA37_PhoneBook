package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeClass
    public void preCondition(){
        if (!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("ssa@gmail.com").setPassword("Ssa12345$"));
        }
    }

    @Test
    public void addContactSuccessAllFields() {
        Random random = new Random();
        int randomPhone = random.nextInt(1000);
        int randomEmail = random.nextInt(1000);
        Contact contact = Contact.builder()
                .name("Bob")
                .lastName("Smit")
                .phone("6471746178"+ randomPhone)
                .email("bob" + randomEmail + "@gmail.com")
                .address("Tel Aviv")
                .description("my friend")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isElementPresent(By.xpath("//div[@class='contact-item_card__2SOIM']/h3[text()='"+ contact.getPhone()+ "']")));
    }

    @Test
    public void addContactSuccessRequiredFields() {
        Random random = new Random();
        int randomPhone = random.nextInt(1000);
        int randomEmail = random.nextInt(1000);
        Contact contact = Contact.builder()
                .name("Cris")
                .lastName("Braun")
                .phone("917201736102"+ randomPhone)
                .email("cris" + randomEmail + "@gmail.com")
                .address("Rehovot")
                .build();
        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperUser().isElementPresent(By.xpath("//div[@class='contact-item_card__2SOIM']/h3[text()='"+ contact.getPhone()+ "']")));
    }

    @Test
    public void addNewContactWrongName(){

        Contact contact = Contact.builder()
                .name("")
                .lastName("Smit")
                .phone("6471746178123")
                .email("bob@gmail.com")
                .address("Tel Aviv")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }

    @Test
    public void addNewContactWrongAddress(){
        Contact contact = Contact.builder()
                .name("Bob")
                .lastName("Smit")
                .phone("6471746178123")
                .email("bob@gmail.com")
                .address("")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }

    @Test
    public void addNewContactWrongLastName(){
        Contact contact = Contact.builder()
                .name("Bob")
                .lastName("")
                .phone("6471746178123")
                .email("bob@gmail.com")
                .address("Tel Aviv")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }

    @Test
    public void addNewContactWrongPhone(){
        Contact contact = Contact.builder()
                .name("Bob")
                .lastName("Smit")
                .phone("")
                .email("bob@gmail.com")
                .address("Tel Aviv")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));

    }

    @Test
    public void addNewContactWrongEmail(){
        Contact contact = Contact.builder()
                .name("Bob")
                .lastName("Smit")
                .phone("6471746178123")
                .email("bobgmail.com")
                .address("Tel Aviv")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();
        Assert.assertTrue(app.getHelperUser().isAlertPresent2("Email not valid:"));
    }


}
