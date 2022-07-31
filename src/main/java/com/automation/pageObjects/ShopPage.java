package com.automation.pageObjects;

import com.automation.factory.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ShopPage {
    private WebDriver webDriver;

    public ShopPage() {
        webDriver = WebDriverFactory.getInstance().getDriver();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"page\"]/header/h1")
    private static WebElement shopBanner;

    @FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/div[2]/ul/li")
    private static WebElement itemsList;

    private static String addCartBtn = "[1]/div/a[2]";

    @FindBy(how = How.XPATH, using = "//*[@id=\"primary-menu\"]/ul/li[1]/a")
    private static WebElement cartButton;

    public void loadURL() {
        webDriver.get("https://cms.demo.katalon.com");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void waitForPageToLoad() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOf(shopBanner));
    }

    public void addRandomItems() {
        try {
            int count = 0;
            List<WebElement> elementsList = webDriver.findElements(By.xpath("//*[@id=\"main\"]/div[2]/ul/li"));
            final int[] randNum = new Random().ints(1, elementsList.size()).distinct().limit(10).toArray();
            for (int num : randNum) {
                String xpath = "//*[@id=\"main\"]/div[2]/ul/li[" + num + "]/div/a[2]";
                WebElement element = webDriver.findElement(By.xpath(xpath));
                if (element.getAttribute("aria-label").contains("to your cart")) {
                    Thread.sleep(5000);
                    JavascriptExecutor executor = (JavascriptExecutor)webDriver;
                    executor.executeScript("arguments[0].click();", element);
                    count++;
                }
                if (count == 4) {
                    break;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            webDriver.quit();
        }
    }
}
