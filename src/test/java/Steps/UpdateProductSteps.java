package Steps;

import Hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class UpdateProductSteps {
    private WebDriver driver = Hooks.driver;

    @Given("I am on the cart page")
    public void i_am_on_the_cart_page() {
        driver.get("https://askomdch.com/store");
        driver.findElement(By.cssSelector("a[data-product_id='1198']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='View cart']")));
        cart.click();
    }


    @When("I increase or decrease the product quantity in cart")
    public void i_increase_or_decrease_the_product_quantity_in_cart() {
        WebElement quantityInput = driver.findElement(By.cssSelector(".input-text.qty.text"));
        quantityInput.sendKeys(Keys.ARROW_UP);
    }

    @When("I remove a product from the cart")
    public void i_remove_a_product_from_the_cart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[aria-label='Remove this item']")));
        removeButton.click();
    }

    @Then("Product quantity in the cart should be updated")
    public void product_quantity_in_the_cart_should_be_updated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("update_cart")));
        updateButton.click();

        WebElement productQuantity = driver.findElement(By.cssSelector(".input-text.qty.text"));
        int quantity = Integer.parseInt(productQuantity.getAttribute("value"));

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-notices-wrapper")));

        assertTrue("Quantity was not increased", quantity > 1);
        assertTrue("Cart update message not shown", message.getText().contains("Cart updated."));
    }

    @Then("the product should be removed from the cart")
    public void the_product_should_be_removed_from_the_cart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-notices-wrapper")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", message);

        assertTrue("Failed to remove item", message.getText().contains("removed"));
    }
}
