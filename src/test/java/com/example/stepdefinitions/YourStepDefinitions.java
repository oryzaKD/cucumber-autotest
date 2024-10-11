package com.example.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

public class YourStepDefinitions {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\seleniumdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://kasirdemo.vercel.app/login");
    }

    @When("I enter valid email and password")
    public void i_enter_email_and_password() {
        System.out.println("1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        element1.sendKeys("kita.jaya@hotmail.com");

        WebElement element2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        element2.sendKeys("123123");

        WebElement element3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div/button")));
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

    @Then("I should be redirected to the homepage")
    public void i_should_be_logged_in_successfully() {
        String expectedDashboardUrl = "https://kasirdemo.vercel.app/dashboard";
        String currentUrl = driver.getCurrentUrl();

        if (!currentUrl.equals(expectedDashboardUrl)) {
            throw new AssertionError(
                    "User tidak diarahkan ke dashboard. Expected: " + expectedDashboardUrl + ", Actual: " + currentUrl);
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dashboardElement = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.id("dashboard-header")));
        if (!dashboardElement.isDisplayed()) {
            throw new AssertionError("Elemen dashboard tidak terlihat");
        }

        System.out.println("Current URL: " + currentUrl);
        System.out.println("Dashboard loaded successfully");
    }
}