package com.automation.factory;

import io.cucumber.java.AfterAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.automation.constants.Constants.CHROME_DRIVER_PROPERTY;

public class WebDriverFactory {
    private static final WebDriverFactory INSTANCE = new WebDriverFactory();
    private ConfigFactory configFactory = new ConfigFactory();
    private WebDriver driver;

    private WebDriverFactory() {
        driver = createWebDriverInstance();
    }

    public static WebDriverFactory getInstance() {
        if(INSTANCE == null) {
            return new WebDriverFactory();
        } else {
            return INSTANCE;
        }
    }

    public WebDriver getDriver() {
        if(driver == null) {
            driver = createWebDriverInstance();
        }
        return driver;
    }

    private WebDriver createWebDriverInstance() {
        System.setProperty(CHROME_DRIVER_PROPERTY, ConfigFactory.getPathToDriver());
        driver = new ChromeDriver();
        return driver;
    }

    public void quitDriver() {
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
