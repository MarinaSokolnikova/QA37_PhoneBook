package manager;

import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    public void fillLoginRegistrationForm(User user){
        type(By.name("email"), user.getEmail());
        type(By.xpath("//input[@name='password']"), user.getPassword());

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



    public boolean isAlertPresent2(String message) {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        if(alert != null && alert.getText().contains(message)){
            //click ok
            alert.accept();
            //click cancel ---> alert.dismiss();
            //type into alert ---> alert.sendKeys("hello");


            return true;
        }
        return false;
    }

    public void submitRegistration() {
        click(By.name("registration"));
    }

    public boolean contacts() {
        return isElementPresent(By.cssSelector("[href='/contacts']"));
    }

    public boolean noContactsHere() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.textToBePresentInElement(wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")), "No Contacts here!"));

    }

    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }
}
