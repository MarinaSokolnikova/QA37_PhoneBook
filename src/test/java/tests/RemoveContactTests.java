package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if (!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("ssa@gmail.com").setPassword("Ssa12345$"));
        }

        app.getHelperContact().provideContacts();
    }


    @Test
    public void removeFirstContact(){
        // Assert size list less than one
        int contactsCountBeforeRemove = app.getHelperContact().getContactsCount();
        app.getHelperContact().openContactsForm();
        app.getHelperContact().clickFirstContact();
        app.getHelperContact().submitRemove();
        app.getHelperContact().pause(1000);//!!!

        int contactsCountAfterRemove = app.getHelperContact().getContactsCount();

        Assert.assertEquals(contactsCountAfterRemove, (contactsCountBeforeRemove-1));
    }

    @Test
    public void removeAllContacts(){
        // "No contacts Here"
        app.getHelperContact().openContactsForm();
        while (app.getHelperContact().getContactsCount() >0)
        {
            app.getHelperContact().removeContact();
            app.getHelperContact().pause(1000);
        }

        Assert.assertTrue(app.getHelperUser().noContactsHere());
    }
}
