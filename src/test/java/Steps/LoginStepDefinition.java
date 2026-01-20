package Steps;

import Hooks.Hooks;
import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginStepDefinition {
    private final WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
       driver.get("https://askomdch.com/account/");
    }

    @When("I enter valid {string} and {string}")
    public void i_enter_valid_credentials(String username, String password) {

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();

    }
    @When("I enter invalid {string} and {string}")
    public void i_enter_invalid_password(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();

    }


    @Then("I should be taken to the dashboard")
    public void i_should_be_taken_to_the_dashboard() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.woocommerce-MyAccount-content p")));
        String getUserName = driver.findElement(By.cssSelector("div.woocommerce-MyAccount-content p")).getText();
        Assert.assertEquals("Hello dorine_08 (not dorine_08? Log out)",getUserName);
        System.out.println(getUserName);
        //driver.findElement(By.linkText("Logout")).click();
    }
    @Then("I should see the {string} message")
    public void get_error_message(String error){
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.woocommerce-error li")));
     String errorMessage = driver.findElement(By.cssSelector("ul.woocommerce-error li")).getText();
     assertTrue(errorMessage.contains(error));
        System.out.println(errorMessage);
    }
    @And("I should see the option Lost your password? option")
    public void get_forgetPassword_option(){
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Lost your password?")));
      String lostPassword = driver.findElement(By.linkText("Lost your password?")).getText();
      assertTrue(lostPassword.contains("Lost your password?"));
        System.out.println(lostPassword);
    }
}
