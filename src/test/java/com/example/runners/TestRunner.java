package com.example.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/resources/features", // Path to your feature files
    glue = "com.example.stepdefinitions",      // Path to your step definitions
    plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner {
}
