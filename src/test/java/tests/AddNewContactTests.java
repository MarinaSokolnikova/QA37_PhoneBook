package tests;

import manager.DataProviderContact;
import manager.DataProviderUser;
import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeClass
    public void preCondition(){
        if (!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("ssa@gmail.com").setPassword("Ssa12345$"));
            logger.info("Before class logged in account with data ---> email: 'ssa@gmail.com', password: 'Ssa12345$'");
        }
    }

    @Test(dataProvider = "contactSuccess", dataProviderClass = DataProviderContact.class)
    public void addContactSuccessAllFields(Contact contact) {

//        Random random = new Random();
//        int randomPhone = random.nextInt(1000);
//        int i = random.nextInt(1000);
//        int randomEmail = random.nextInt(1000);
//        Contact contact = Contact.builder()
//                .name("Bob")
//                .lastName("Smit")
//                .phone("6471746178"+ randomPhone)
//                .email("bob" + randomEmail + "@gmail.com")
//                .address("Tel Aviv")
//                .description("all fields")
//                .build();

        logger.info("Test data---> "+contact.toString());

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isContactPresent(contact));
        logger.info("Assert check is Element contact present");
    }

    @Test(dataProvider = "contactCSV", dataProviderClass = DataProviderContact.class)
    public void addContactSuccessAllFieldsCSV(Contact contact) {

        logger.info("Test data---> "+contact.toString());

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isContactPresent(contact));
        logger.info("Assert check is Element contact present");
    }

    @Test
    public void addContactSuccessRequiredFields() {
        Random random = new Random();
        int randomPhone = random.nextInt(1000);
        int randomEmail = random.nextInt(1000);
        Contact contact = Contact.builder()
                .name("CrisReq")
                .lastName("Braun")
                .phone("917201736102"+ randomPhone)
                .email("cris" + randomEmail + "@gmail.com")
                .address("Rehovot")
                .build();

        logger.info("Test data---> "+contact.toString());
        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isContactPresent(contact));
        logger.info("Assert check is Element contact present");
    }

    @Test
    public void addNewContactWrongName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Smit")
                .phone("111111111111")
                .email("bob@gmail.com")
                .address("Tel Aviv")
                .description("wrong name")
                .build();

        logger.info("Test data---> "+contact.toString());

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        logger.info("Assert check is add page still displayed");

    }

    @Test
    public void addNewContactWrongAddress(){
        Contact contact = Contact.builder()
                .name("Bob")
                .lastName("Smit")
                .phone("6471746178123")
                .email("bob@gmail.com")
                .address("")
                .description("wrong address")
                .build();

        logger.info("Test data---> "+contact.toString());

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        logger.info("Assert check is add page still displayed");
    }

    @Test
    public void addNewContactWrongLastName(){
        Random random = new Random();
        int i = random.nextInt(1000);
        Contact contact = Contact.builder()
                .name("Boby")
                .lastName("")
                .phone("6471746178123")
                .email("boby@gmail.com")
                .address("Tel Aviv")
                .description("wrong lastName")
                .build();

        logger.info("Test data---> "+contact.toString());

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen"+ i+ ".png");
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        logger.info("Assert check is add page still displayed");

    }

    @Test
    public void addNewContactWrongPhone(){
        Contact contact = Contact.builder()
                .name("Bob")
                .lastName("Smit")
                .phone("")
                .email("bob@gmail.com")
                .address("Tel Aviv")
                .description("wrong phone")
                .build();

        logger.info("Test data---> "+contact.toString());

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        logger.info("Assert check is add page still displayed");
        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));
        logger.info("Assert check is alert present with error text ' Phone not valid: Phone number must contain only digits! And length min 10, max 15!'");
    }

    @Test
    public void addNewContactWrongEmail(){
        Contact contact = Contact.builder()
                .name("Bob")
                .lastName("Smit")
                .phone("6471746178123")
                .email("bobgmail.com")
                .address("Tel Aviv")
                .description("wrong email")
                .build();

        logger.info("Test data---> "+contact.toString());

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();
        Assert.assertTrue(app.getHelperUser().isAlertPresent2("Email not valid:"));
        logger.info("Assert check is alert present with error text 'Email not valid:'");
    }




}
