package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if (app.getHelperUser().isLogged())
        {
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }
    }

    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000);

//        int z = (int)System.currentTimeMillis()/1000;

        User user = new User().setEmail("ssa"+i+"@gmail.com").setPassword("Ssa12345$");
        logger.info("Test data ---> email: ssa"+i+"@gmail.com, password: Ssa12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        //Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().contacts());
        logger.info("Assert check is contact page displayed");
        Assert.assertTrue(app.getHelperUser().noContactsHere());
        logger.info("Assert check is element with text 'No Contacts Here' present");
    }

    @Test
    public void registrationWrongEmail(){
        logger.info("Test data ---> email: 'ssagmail.com', password: 'Ssa12345$'");
        User user = new User().setEmail("ssagmail.com").setPassword("Ssa12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent2("Wrong email or password format"));
        logger.info("Assert check is alert present with error text 'Wrong email or password format'");
    }

    @Test
    public void registrationWrongPassword(){
        logger.info("Test data ---> email: 'don@gmail.com', password: 'ssa12345$'");
        User user = new User().setEmail("don@gmail.com").setPassword("ssa12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent2("Wrong email or password format"));
        logger.info("Assert check is alert present with error text 'Wrong email or password format'");
    }

    @Test
    public void registrationUserRegistered(){
        logger.info("Test data ---> email: 'ssa@gmail.com', password: 'Ssa12345$'");
        User user = new User().setEmail("ssa@gmail.com").setPassword("Ssa12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
        logger.info("Assert check is alert present with error text 'User already exist'");
    }
}
