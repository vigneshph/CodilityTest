package com.automation.pageObjects;

import com.automation.factory.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.*;

public class CartPage {

    private WebDriver webDriver;
    private Map<Integer, String> dataMap = new HashMap<>();
    private ArrayList<Integer> numberList = new ArrayList<>();

    public CartPage() {
        webDriver = WebDriverFactory.getInstance().getDriver();
    }

    private static String cartListing = "//*[@id=\"post-8\"]/div/div/form/table/tbody/tr";

    private static String cartLink = "//*[@id=\"primary-menu\"]/ul/li[1]/a";

    private static String updateCart = "//*[@id=\"post-8\"]/div/div/form/table/tbody/tr[5]/td/button";

    public void goToCart() {
        WebElement element = webDriver.findElement(By.xpath(cartLink.toString()));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        webDriver.findElement(By.xpath(cartLink.toString())).click();
    }

    public void reloadPage() {
        try {
            webDriver.navigate().refresh();
            Thread.sleep(5000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int returnCartCount() {
        List<WebElement> webElementList = webDriver.findElements(By.xpath(cartListing));
        return webElementList.size() - 1;
    }

    public void searchLowestPricedItem() {
        for (int i = 1; i <= 4; i++) {
            WebElement element = webDriver.findElement(By.xpath("//*[@id=\"post-8\"]/div/div/form/table/tbody/tr[" + i + "]/td[4]/span"));
            numberList.add(Integer.parseInt(element.getText().replaceAll("[\\D]", "")));
            String removeXPath = "//*[@id=\"post-8\"]/div/div/form/table/tbody/tr[" + i + "]/td[5]/div/input";
            dataMap.put(Integer.parseInt(element.getText().replaceAll("[\\D]", "")),removeXPath);
        }
    }

    public void removeLowestPricedItem() {
        try {
            int lowestPrice = Collections.min(numberList);
            String xpath = dataMap.get(lowestPrice);
            WebElement element = webDriver.findElement(By.xpath(xpath));
            element.clear();
            element.sendKeys("0");
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            //executor.executeScript("arguments[0].setAttribute('value', '" + 0 + "')", element);
            Thread.sleep(2000);
            WebElement element1 = webDriver.findElement(By.xpath(updateCart));
            executor.executeScript("arguments[0].click();", element1);
            Thread.sleep(5000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
