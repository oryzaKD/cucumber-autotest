package com.example.config;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestConfig {
    private static WebDriver driver;

    @Before
    public void setUp() {
        System.out.println("Setting up WebDriver");
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            System.out.println("WebDriver initialized");
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}