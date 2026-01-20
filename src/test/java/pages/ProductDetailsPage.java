package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class ProductDetailsPage extends BasePage {
    @FindBy(css = "button[name='add-to-cart']") private WebElement addToCart;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCart));
        addToCart.click();
    }
}

