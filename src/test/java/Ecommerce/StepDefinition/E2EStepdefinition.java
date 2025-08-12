package Ecommerce.StepDefinition;

import Ecommerce.TestComponent.BaseTest;
import SeleniumFramework.pageObjects.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class E2EStepdefinition extends BaseTest {
    LandingPage landingPage;
    ProductCataloguePage productCataloguePage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderPage orderPage;
    @Given("user is on ecommerce page")
    public void userIsOnEcommercePage() throws IOException, InterruptedException {
        landingPage=openApplication();
    }

    @Given("^user enter username (.+) and password (.+)$")
    public void userEnterUsernameAndPassword (String userName,String password){
        productCataloguePage  = landingPage.enterUserNameNPassword(userName, password);
    }
    @When("user get the product list")
    public void user_get_the_product_list(){
        productCataloguePage.getProductsList();
    }
    @And("^add product (.+) to cart$")
    public void add_product_to_cart(String productName){
        productCataloguePage.getProductName(productName);
        productCataloguePage.addToCart(productName);
    }
    @And("move to cart page")
    public void move_to_cart_page(){
        cartPage= productCataloguePage.clickOnCart();
    }

    @Then("^verify cart product (.+)$")
    public void verify_cart_product_name(String productName){
        cartPage.getCartProductNames();
        Boolean match = cartPage.verifyCartProductNames(productName);
        Assert.assertTrue(match);
    }

    @And("click on checkout button")
    public void click_on_checkout_button(){
        checkoutPage = cartPage.clickOnCheckoutBtn();
    }
    @And("^type (.+) name and select desired country$")
    public void select_desired_country(String country){
        checkoutPage.enterCountry("Ind");
        checkoutPage.getCountryOptions();
        checkoutPage.clickOnDesiredCountry(country);
    }

    @And("click on placeorder button")
    public void click_on_placeorder_button(){
        orderPage=checkoutPage.clickOnPlaceOrder();
    }
    @Then("^print order id$")
    public  void print_order_id(){
        System.out.println(orderPage.getOrderId());
    }

    @Then("verify {string} is displayed")
    public void verifyIsDisplayed(String string) {
        String errorMsg=landingPage.getErrorMsg();
        Assert.assertEquals(errorMsg,string);
    }
}
