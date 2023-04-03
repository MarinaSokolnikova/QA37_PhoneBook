package tests;

import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if (!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().setEmail("ssa@gmail.com").setPassword("Ssa12345$"));
        }

        app.getHelperContact().provideContacts(); // if list contacts < 3 than add 3 contacts
    }


    @Test
    public void removeFirstContact(){
        // Assert size list less than one
    }

    @Test
    public void removeAllContacts(){
        // "No contacts Here"

    }
}
