package com.example.stepdefinitions;

import com.example.config.TestConfig;
import com.example.stepdefinitions.FileCounter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

// Atau untuk TestNG
// import org.testng.Assert;

import java.time.Duration;

public class YourStepDefinitions {

    private static WebDriver driver;

    @Before
    public void setUp() {
        if (driver == null) { // Prevent opening a new browser for every scenario
            System.setProperty("webdriver.chrome.driver", "C:\\seleniumdrivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    @After
    public void tearDown() {
        // if (driver != null) {
        // driver.quit();
        // driver = null;
        // }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://kasirdemo.vercel.app/login");
    }

    @When("I enter valid email and password")
    public void i_enter_valid_email_and_password() {
        System.out.println("1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        element1.sendKeys("kita.jaya@hotmail.com");

        WebElement element2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        element2.sendKeys("123123");

        WebElement element3 = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div/button")));
        element3.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(webDriver -> {
                    try {
                        Thread.sleep(5000);
                        return true;
                    } catch (InterruptedException e) {
                        return false;
                    }
                });
    }

    @Then("I should be redirected to the dashboard page")
    public void i_should_be_redirected_to_the_dashboard_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String expectedUrl = "https://kasirdemo.vercel.app/dashboard";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        assertTrue("URL does not match dashboard URL", actualUrl.equals(expectedUrl));
    }

    //CATEGORY
    @Given("I am on the category page")
    public void i_am_on_the_category_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div[1]/div/a[5]/div")));
        element.click();

        String expectedUrl = "https://kasirdemo.vercel.app/categories";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        assertTrue("URL does not match category URL", actualUrl.equals(expectedUrl));
    }

    @When("I click the create category button")
    public void i_click_the_create_category_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/a")));
        element.click();

        String expectedUrl = "https://kasirdemo.vercel.app/categories/create";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        assertTrue("URL does not match category create URL", actualUrl.equals(expectedUrl));
    }

    @And("I click button simpan category without fill the form")
    public void i_click_button_simpan_category_without_fill_the_form() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/button")));
        element.click();

        assertTrue("\"name\" is not allowed to be empty",
                driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/div[1]")).isDisplayed());

        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(webDriver -> {
                    try {
                        Thread.sleep(5000);
                        return true;
                    } catch (InterruptedException e) {
                        return false;
                    }
                });
    }

    @And("I fill the category form")
    public void i_fill_the_category_form() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Fill in the category name
        WebElement categoryNameInput = driver.findElement(By.id("nama"));
        int counterValue = FileCounter.incrementAndSaveCounter();
        categoryNameInput.sendKeys("Category" + counterValue);

        WebElement categoryDescriptionInput = driver.findElement(By.id("deskripsi"));
        categoryDescriptionInput.sendKeys("This is a test category description");

        // Submit the form
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/button"));
        submitButton.click();

        // Wait for submission to complete (you might want to wait for a success message
        // or redirection)
        wait.until(ExpectedConditions.urlContains("https://kasirdemo.vercel.app/categories"));
    }

    @Then("I should see new category in the category list")
    public void i_should_see_new_category_in_the_category_list() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String expectedUrl = "https://kasirdemo.vercel.app/categories";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        assertTrue("URL does not match category URL", actualUrl.equals(expectedUrl));
        System.out.println("Category form filled and submitted successfully");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(webDriver -> {
                    try {
                        Thread.sleep(5000);
                        return true;
                    } catch (InterruptedException e) {
                        return false;
                    }
                });
    }

    @When("I update one of the category value")
    public void i_update_one_of_the_category_value() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element1 = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/td[3]/button")));
        element1.click();

        WebElement element2 = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/td[3]/div/div/a")));
        element2.click();

        WebElement categoryNameInputUpdate = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.id("nama")));
        categoryNameInputUpdate.sendKeys(" " + "Edit");

        WebElement categoryDescriptionInputUpdate = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.id("deskripsi")));
        categoryDescriptionInputUpdate.sendKeys(", and have been updated");

        // Submit the form
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/button"));
        submitButton.click();

        // Wait for submission to complete (you might want to wait for a success message
        // or redirection)
        wait.until(ExpectedConditions.urlContains("https://kasirdemo.vercel.app/categories"));
    }

    @When("I should see updated category in the category list")
    public void i_should_see_updated_category_in_the_category_list() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String expectedUrl = "https://kasirdemo.vercel.app/categories";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        assertTrue("URL does not match login URL", actualUrl.equals(expectedUrl));
        System.out.println("Category form updated successfully");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(webDriver -> {
                    try {
                        Thread.sleep(5000);
                        return true;
                    } catch (InterruptedException e) {
                        return false;
                    }
                });
    }

    @When("I delete one of the category")
    public void i_deleted_one_of_the_category() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element1 = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/td[3]/button")));
        element1.click();

        WebElement element2 = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/td[3]/div/div/button")));
        element2.click();

        WebElement element3 = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.xpath("/html/body/div[4]/div/div[3]/div/section/footer/button[2]")));
        element3.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(webDriver -> {
                    try {
                        Thread.sleep(5000);
                        return true;
                    } catch (InterruptedException e) {
                        return false;
                    }
                });
    }

    //USER
    @Given("I am on the user page")
    public void i_am_on_the_user_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div[1]/div/a[7]/div/div/div")));
        element.click();

        String expectedUrl = "https://kasirdemo.vercel.app/users";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        assertTrue("URL does not match user URL", actualUrl.equals(expectedUrl));
    }

    @When("I click the create user button")
    public void i_click_the_create_user_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/a")));
        element.click();

        String expectedUrl = "https://kasirdemo.vercel.app/users/create";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        assertTrue("URL does not match user create URL", actualUrl.equals(expectedUrl));
    }

    @And("I click button simpan user without fill the form")
    public void i_click_button_simpan_user_without_fill_the_form() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/button")));
        element.click();

        assertTrue("\"name\" is not allowed to be empty",
                driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/div[1]")).isDisplayed());

        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(webDriver -> {
                    try {
                        Thread.sleep(5000);
                        return true;
                    } catch (InterruptedException e) {
                        return false;
                    }
                });
    }

    @And("I fill the user form")
    public void i_fill_the_user_form() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Fill in the category name
        WebElement userNameInput = driver.findElement(By.id("nama"));
        int counterValue = FileCounter.incrementAndSaveCounter();
        userNameInput.sendKeys("Name" + counterValue);
        
        WebElement userEmailInput = driver.findElement(By.id("email"));
        userEmailInput.sendKeys("name.email" + counterValue + "@hotmail.com");

        WebElement userPasswordInput = driver.findElement(By.id("password"));
        userPasswordInput.sendKeys("123123");

        // Submit the form
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/button"));
        submitButton.click();

        // Wait for submission to complete (you might want to wait for a success message
        // or redirection)
        wait.until(ExpectedConditions.urlContains("https://kasirdemo.vercel.app/users"));
    }

    @Then("I should see new user in the user list")
    public void i_should_see_new_user_in_the_user_list() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String expectedUrl = "https://kasirdemo.vercel.app/users";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        assertTrue("URL does not match user URL", actualUrl.equals(expectedUrl));
        System.out.println("User form filled and submitted successfully");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(webDriver -> {
                    try {
                        Thread.sleep(5000);
                        return true;
                    } catch (InterruptedException e) {
                        return false;
                    }
                });
    }

    @When("I update one of the user value")
    public void i_update_one_of_the_user_value() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element1 = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[4]/button")));
        element1.click();

        WebElement element2 = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[4]/div/div/a")));
        element2.click();

        WebElement userNameInputUpdate = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.id("nama")));
        userNameInputUpdate.sendKeys(" " + "Edit");

        // Submit the form
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/button"));
        submitButton.click();

        // Wait for submission to complete (you might want to wait for a success message
        // or redirection)
        wait.until(ExpectedConditions.urlContains("https://kasirdemo.vercel.app/users"));
    }

    @When("I should see updated user in the user list")
    public void i_should_see_updated_user_in_the_user_list() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String expectedUrl = "https://kasirdemo.vercel.app/users";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        assertTrue("URL does not match user URL", actualUrl.equals(expectedUrl));
        System.out.println("User form updated successfully");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(webDriver -> {
                    try {
                        Thread.sleep(5000);
                        return true;
                    } catch (InterruptedException e) {
                        return false;
                    }
                });
    }

    @When("I delete one of the user")
    public void i_deleted_one_of_the_user() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element1 = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[4]/button")));
        element1.click();

        WebElement element2 = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.xpath(
                                "/html/body/div[1]/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[4]/div/div/button")));
        element2.click();

        WebElement element3 = wait.until(
                ExpectedConditions
                        .elementToBeClickable(By.xpath("/html/body/div[4]/div/div[3]/div/section/footer/button[2]")));
        element3.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(webDriver -> {
                    try {
                        Thread.sleep(5000);
                        return true;
                    } catch (InterruptedException e) {
                        return false;
                    }
                });
    }

    @When("I click button logout")
    public void i_click_button_logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element1 = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/div[1]/div[2]/button")));
        element1.click();

        WebElement element2 = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("/html/body/div/div/div/div[2]/div[1]/div[2]/div/div/button[2]")));
        element2.click();
    }

    @Then("I should be redirected to the login page")
    public void i_should_be_redirected_to_the_login_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String expectedUrl = "https://kasirdemo.vercel.app/login";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        assertTrue("URL does not match login URL", actualUrl.equals(expectedUrl));
        driver.quit();
    }

}