package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;

public class SigninSteps {
    private WebDriver driver;

    @Given("I am on the sign-in page")
    public void i_am_on_the_signin_page() {
    	System.setProperty("webdriver.http.factory", "jdk-http-client");
    	System.setProperty("webdriver.chrome.driver", "D:/Selenium/chromedriver-win64/chromedriver.exe");
    	driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/customer/account/login");
    }

    @When("I enter invalid credentials")
    public void i_enter_invalid_credentials() {
    	driver.findElement(By.id("email")).sendKeys("invalidmail@sample.com");
        driver.findElement(By.id("pass")).sendKeys("Password123");
        driver.findElement(By.id("send2")).click();
    }
    
    @And("I try to sign in into the account")
    public void i_try_to_sign_in_into_the_account() {
        driver.findElement(By.id("send2")).click();
    } 
    
    @Then("I should get a error message")
    public void i_should_get_a_error_message() throws InterruptedException {
    	Thread.sleep(3000);
        String expectedMessage = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        String actualMessage = driver.findElement(By.cssSelector(".message-error")).getText();
        assert(actualMessage.contains(expectedMessage));
        driver.quit();
    }
}
