package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if (app.getHelperUser().isLogged())
        {
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }
    }
    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password){
        logger.info("Start test with name 'loginSuccess'");
        //logger.info("Test data ---> email: 'ssa@gmail.com' & password: 'Ssa12345$'");
        logger.info("Test data ---> email: "+email+" & password: "+ password);
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();

        //Assert
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
    }



    @Test(dataProvider = "LoginModels", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user){
        logger.info("Test data ---> "+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

        //Assert
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
    }

    @Test
    public void loginWrongEmail(){
        logger.info("Test data ---> email: 'ssagmail.com' & password: 'Ssa12345$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("ssagmail.com", "Ssa12345$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");
    }

    @Test
    public void loginWrongPassword(){
        logger.info("Test data ---> email: 'ssa@gmail.com' & password: 'Ssa'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("ssa@gmail.com", "Ssa");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");
    }

    @Test
    public void loginUnregisteredUser(){
        logger.info("Test data ---> email: 'pop@gmail.com' & password: 'Pop12345$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("pop@gmail.com", "Pop12345$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");
    }
}
