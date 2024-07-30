package steps;

import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;

public class SignupSteps {
    private WebDriver driver;
    private static String randomEmail;

    @Given("I am on the sign-up page")
    public void i_am_on_the_signup_page() {
    	System.setProperty("webdriver.chrome.driver", "D:/Selenium/chromedriver-win64/chromedriver.exe");
    	driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/"); 
    }

    @When("I enter valid details")
    public void i_enter_valid_details() throws InterruptedException {
    	driver.findElement(By.id("firstname")).sendKeys("Sriram");
        driver.findElement(By.id("lastname")).sendKeys("R");
        randomEmail = "sri" + UUID.randomUUID().toString() + "@sample.com";
        WebElement emailInput = driver.findElement(By.id("email_address"));
        emailInput.sendKeys(randomEmail);
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("password-confirmation")).sendKeys("Password123");
    }

    @When("I try to submit the form")
    public void i_try_to_submit_the_form() {
        driver.findElement(By.cssSelector("button[title='Create an Account']")).click();
    }

    @Then("my account should be created successfully")
    public void my_account_should_be_created_successfully() {
        String expectedMessage = "Thank you for registering with Main Website Store.";
        String actualMessage = driver.findElement(By.cssSelector(".message-success")).getText();
        assert(actualMessage.contains(expectedMessage));
        driver.quit();
    }
    
    @When("I enter details with an existing email")
    public void i_enter_details_with_existing_email() {
        driver.findElement(By.id("firstname")).sendKeys("Sriram");
        driver.findElement(By.id("lastname")).sendKeys("R");
        driver.findElement(By.id("email_address")).sendKeys("info@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("password-confirmation")).sendKeys("Password123");
    }

    @Then("I should get a error as account is already present")
    public void i_should_get_a_error_as_account_is_already_present() {
    	String successMessage = driver.findElement(By.cssSelector(".message-error")).getText();
    	System.out.println("my asser" + successMessage);
        assert(successMessage.contains("There is already an account with this email address. If you are sure that it is your email address, "));
        driver.quit();
    }
    
    @Given("I am trying to sign in into page")
    public void i_am_trying_to_sign_in_into_page() {
    	System.setProperty("webdriver.http.factory", "jdk-http-client");
    	System.setProperty("webdriver.chrome.driver", "D:/Selenium/chromedriver-win64/chromedriver.exe");
    	driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/customer/account/login");
    }
    
    @When("I enter valid credentials")
    public void i_enter_valid_credentials() throws InterruptedException {
    	Thread.sleep(5000);
      	driver.findElement(By.id("email")).sendKeys(randomEmail);
        driver.findElement(By.id("pass")).sendKeys("Password123");
    }

    @And("I try to sign in into the account using valid data")
    public void i_sign_in_into_the_account_using_valid_data() {
        driver.findElement(By.id("send2")).click();
    }
    
    @Then("I should be signed in successfully")
    public void i_should_be_signed_in_successfully() throws InterruptedException {
    	Thread.sleep(5000);
        String expectedMessage = "Welcome, Sriram R!";
        String actualMessage = driver.findElement(By.className("logged-in")).getText();
        assert(actualMessage.contains(expectedMessage));
        driver.quit();
    }        
}