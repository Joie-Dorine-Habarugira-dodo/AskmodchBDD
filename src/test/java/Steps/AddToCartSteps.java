package Steps;

import Hooks.Hooks;
import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ProductDetailsPage;
import pages.StorePage;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class AddToCartSteps {
    private final WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the product listing page")
    public void i_am_on_the_product_listing_page() {

        new StorePage(driver).load("https://askomdch.com/store/");

    }
    @And("I click on product item for opening product detail page")
    public void click_on_random_product(){
        new StorePage(driver).selectRandomProduct();
    }


    @When("I click on the Add to Cart button for a product")
    public void i_click_on_the_button_for_a_product() {
     new ProductDetailsPage(driver).addToCart();
    }

    @Then("a confirmation message is displayed")
    public void a_confirmation_message_is_displayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("woocommerce-message")));
        String message = driver.findElement(By.className("woocommerce-message")).getText();
        assertTrue(message.contains("has been added to your cart."));
        System.out.println(message);
    }
}
