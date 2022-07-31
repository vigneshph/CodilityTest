package com.automation.stepDefinitions;

import com.automation.factory.WebDriverFactory;
import com.automation.pageObjects.CartPage;
import com.automation.pageObjects.ShopPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class AutomationSteps {
    private WebDriver driver;
    ShopPage shopPage = new ShopPage();
    CartPage cartPage = new CartPage();

    public AutomationSteps() {
        driver = WebDriverFactory.getInstance().getDriver();
    }

    @Given("I launch the URL")
    public void i_launch_the_url() {
        shopPage.loadURL();
        //shopPage.waitForPageToLoad();
    }

    @Given("I add four random items to my cart")
    public void i_add_four_random_items_to_my_cart() {
        shopPage.addRandomItems();
    }

    @When("I view my cart")
    public void i_view_my_cart() {
        cartPage.goToCart();
        cartPage.reloadPage();
    }

    @Then("I find total four items listed in my cart")
    public void findCartListing() {
        Assert.assertEquals(4, cartPage.returnCartCount());
    }

    @When("I search for lowest price item")
    public void searchLowestPriceItem() {
        cartPage.searchLowestPricedItem();
    }

    @And("I am able to remove the lowest price item from my cart")
    public void removeLowestPriceItem() {
        cartPage.removeLowestPricedItem();
    }

    @Then("I am able to verify three items in my cart")
    public void verifyItemsInCart() {
        cartPage.reloadPage();
        Assert.assertEquals(3, cartPage.returnCartCount());
        WebDriverFactory.getInstance().quitDriver();
    }

}
