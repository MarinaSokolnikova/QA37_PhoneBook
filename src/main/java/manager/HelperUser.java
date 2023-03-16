package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm(){
//        WebElement loginTab = wd.findElement(By.cssSelector("[href='/login']"));
//        // xpath //a[text()='LOGIN']
//
//        loginTab.click();

        click(By.cssSelector("[href='/login']"));
    }

    public void fillLoginRegistrationForm(String email, String password){
//        WebElement emailInput = wd.findElement(By.name("email"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys(email);
        type(By.name("email"), email);

//        WebElement passwordInput = wd.findElement(By.xpath("//input[@name='password']"));
//        passwordInput.click();
//        passwordInput.clear();
//        passwordInput.sendKeys(password);
        type(By.xpath("//input[@name='password']"), password);

    }

    public void submitLogin(){
        click(By.xpath("//button[@name='login']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public void logout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }
}
