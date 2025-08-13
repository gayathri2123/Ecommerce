package Ecommerce;

import Ecommerce.TestComponent.BaseTest;
import Ecommerce.TestComponent.Retry;
import SeleniumFramework.AbstractComponents.AbstractComponent;
import SeleniumFramework.pageObjects.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
//for jenkins setup
//C:\Users\Dell\Downloads
//set JAVA_HOME=C:\Program Files\Java\jdk-21
//set PATH=%JAVA_HOME%\bin;%PATH%
//java -jar jenkins.war -httpPort=9090


//to run in cmd maven
//move to D:\seleniumFramework using D:
public class LoginTest extends BaseTest{
    ProductCataloguePage productCataloguePage;
    OrderPage orderPage;
    String productNam = "ZARA COAT 3";
    String country = "India";
    @Test(dataProvider = "getData",groups = "purchase",retryAnalyzer = Retry.class)
    public void ecommerceE2E(HashMap<String,String>input) {
        System.out.println("login page");
        ProductCataloguePage productCataloguePage = landingPage.enterUserNameNPassword(input.get("email"), input.get("password"));
        productCataloguePage.getProductsList();
        productCataloguePage.getProductName(input.get("productName"));
        productCataloguePage.addToCart(input.get("productName"));
        CartPage cartPage = productCataloguePage.clickOnCart();

        cartPage.getCartProductNames();
        Boolean match = cartPage.verifyCartProductNames(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.clickOnCheckoutBtn();
        checkoutPage.enterCountry("Ind");
        checkoutPage.getCountryOptions();
        checkoutPage.clickOnDesiredCountry(country);
        orderPage=checkoutPage.clickOnPlaceOrder();
        System.out.println(orderPage.getOrderId());
    }
    @Test(dependsOnMethods = "ecommerceE2E")
    public void verifyOrderHistory() {
        landingPage.enterUserNameNPassword("gayathricseeng@gmail.com", "Gayathri@6");
        orderPage = landingPage.clickOnOrder();
        System.out.println(orderPage.orderProductName());
        Assert.assertTrue(orderPage.verifyOrderProductsNames(productNam));

    }
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data=getDataToMap("src/test/java/Ecommerce/Data/PurchaseOrder.json");
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }


//    @DataProvider
//    public Object[][] getData() {
//     return new Object[][] {{"gayathricseeng@gmail.com", "Gayathri@6", "ZARA COAT 3"}, {"rithesh@gmail.com","Admin@123","ADIDAS ORIGINAL"}};
//        }

//    HashMap<String,String>map=new HashMap<String,String>();
//        map.put("email","gayathricseeng@gmail.com");
//        map.put("password","Gayathri@6");
//        map.put("productName","ZARA COAT 3");
//        HashMap<String,String>map1=new HashMap<String,String>();
//        map1.put("email","rithesh@gmail.com");
//        map1.put("password","Admin@123");
//        map1.put("productName","ADIDAS ORIGINAL");
//        return new Object[][]{{map},{map1}};





    }




