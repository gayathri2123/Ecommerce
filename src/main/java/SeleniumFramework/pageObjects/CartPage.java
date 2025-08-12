package SeleniumFramework.pageObjects;

import SeleniumFramework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css=".cartSection h3")
    List<WebElement> cartProductNames;
    By cartProd=By.cssSelector(".cartSection h3");
    @FindBy(css="li button[type='button']")
    WebElement checkout;


    public List<WebElement>getCartProductNames(){
        waitForElementToAppear(cartProd);
        return cartProductNames;
    }
    public Boolean verifyCartProductNames(String productName){
        List<WebElement> cartProd=getCartProductNames();
        Boolean match=cartProd.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
        return match;
    }
    public CheckoutPage clickOnCheckoutBtn(){
        checkout.click();
        return new CheckoutPage(driver);
    }
}
