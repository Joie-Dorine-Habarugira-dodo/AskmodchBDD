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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ViewCartSteps {
    private final WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    @Given("I have added a product to the cart")
    public void i_have_added_a_product_to_the_cart() {
    driver.get("https://askomdch.com/store/");
    driver.findElement(By.cssSelector("a[data-product_id ='1206']")).click();
    driver.findElement(By.cssSelector("a[data-product_id ='1193']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));



    }
    @When("I hover over the shopping cart icon for seeing added products")
    public void i_hover_over_the_shopping_cart_icon() {

        WebElement viewCartIcon = driver.findElement(By.className("ast-cart-menu-wrap"));
        Actions actions = new Actions(driver);
        actions.moveToElement(viewCartIcon).perform();


    }

    @And("I click on the View cart button in cart preview")
    public void i_click_on_the_link() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement viewCartButton =  driver.findElement(By.cssSelector("a.button.wc-forward"));
        viewCartButton.click();

    }
    @Then("I am redirected to the cart page")
    public void i_am_redirected_to_the_cart_page() {
        WebElement title = driver.findElement(By.cssSelector("h1[class='has-text-align-center']"));
        boolean isDisplayed = title.isDisplayed();
        Assert.assertTrue(isDisplayed);

    }
    @Then("the cart page displays product details")
    public void the_cart_page_displays_product_details() {
        WebElement productName = driver.findElement(By.className("product-name"));
        WebElement productPrice = driver.findElement(By.className("product-price"));
        WebElement total = driver.findElement(By.className("product-subtotal"));

        String nameOfProduct = productName.getText();
        String quantity = productPrice.getText();
        String subTotal = total.getText();
        Assert.assertTrue(nameOfProduct.contains("Product"));
        Assert.assertTrue(quantity.contains("Price"));
        Assert.assertTrue(subTotal.contains("Subtotal"));

    }

}
