package Steps;

import Hooks.Hooks;
import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class UpdateProductSteps {
    private WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("the cart is not empty")
    public void the_cart_is_not_empty() {
        driver.get("https://askomdch.com/store");
        WebElement cart=driver.findElement(By.cssSelector("div[class='ast-cart-menu-wrap'] span[class='count']"));
        int items= Integer.parseInt(cart.getText().trim());
        if(items==0){
            List<WebElement> productAddToCartButtons= driver.findElements(By.className("add_to_cart_button"));
            Random random = new Random();
            productAddToCartButtons.get(random.nextInt(productAddToCartButtons.size())).click();

            wait.until(driver -> {
                String text = driver.findElement(
                        By.cssSelector("div.ast-cart-menu-wrap span.count")).getText();
                return Integer.parseInt(text.trim()) > 0;
            });
        }
        String finalCount = driver.findElement(
                By.cssSelector("div.ast-cart-menu-wrap span.count")).getText();

        Assert.assertTrue("Cart is still empty, expected at least 1 product",Integer.parseInt(finalCount.trim()) > 0);

    }
    @And("customer is on the cart page")
    public void customer_is_on_the_cart_page() {
        WebElement cartIcon = driver.findElement(By.className("ast-cart-menu-wrap"));
        cartIcon.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement viewCartButton =  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class='astra-cart-drawer-content'] a[class='button wc-forward']"))));
//        viewCartButton.click();
    }


    @When("customer increases the product quantity in cart")
    public void customer_increases_the_product_quantity_in_cart() {
        WebElement quantityInput = driver.findElement(By.cssSelector(".input-text.qty.text"));
//        int initialQuantity= Integer.parseInt(quantityInput.getAttribute("value"));
        quantityInput.sendKeys(Keys.ARROW_UP);
    }

    @When("customer decreases the product quantity in cart")
    public void customer_decreases_the_product_quantity_in_cart() {
        WebElement quantityInput = driver.findElement(By.cssSelector(".input-text.qty.text"));
        quantityInput.sendKeys(Keys.ARROW_DOWN);
    }

    @When("customer removes a product from the cart")
    public void customer_removes_a_product_from_the_cart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[aria-label='Remove this item']")));
        removeButton.click();
    }

    @Then("the product quantity in the cart should be increased")
    public void product_quantity_in_the_cart_should_be_increased() {
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
