package Steps;

import Hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class AddToCartSteps {
    private final WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the product listing page")
    public void i_am_on_the_product_listing_page() {
        driver.get("https://askomdch.com/store/");

    }
    @And("I click on product item for opening product detail page")
    public void click_on_random_product(){
        List<WebElement> products = driver.findElements(By.className("astra-shop-thumbnail-wrap"));
        Random random = new Random();
        products.get(random.nextInt(products.size())).click();
    }


    @When("I click on the Add to Cart button for a product")
    public void i_click_on_the_button_for_a_product() {
     WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='add-to-cart']")));
     addToCart.click();

    }

    @Then("a confirmation message is displayed")
    public void a_confirmation_message_is_displayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("woocommerce-message")));
        String message = driver.findElement(By.className("woocommerce-message")).getText();
        assertTrue(message.contains("has been added to your cart."));
        System.out.println(message);
    }
}
