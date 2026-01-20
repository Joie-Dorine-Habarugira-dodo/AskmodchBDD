package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class StorePage extends BasePage {
    @FindBy(className = "astra-shop-thumbnail-wrap") private List<WebElement> products;

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public void selectRandomProduct() {
        Random random = new Random();
        products.get(random.nextInt(products.size())).click();
    }

}
