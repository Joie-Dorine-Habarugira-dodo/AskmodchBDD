package Hooks;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import factory.DriverFactory;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void before() {
        driver = DriverFactory.initializeDriver();
    }
    @After
    public void after(){
        if(driver != null){
            driver.quit();
        }
    }
}