package SeleniumFramework.pageObjects;

import SeleniumFramework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
    public OrderPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="label[class='ng-star-inserted']")
    WebElement orderID;
    @FindBy(xpath="//tr/td[2]")
   List<WebElement> orderProductsNames;
    @FindBy(css=".ng-star-inserted")
    WebElement orderPage;
    public String getOrderId(){
        return orderID.getText();

    }
    public String orderProductName(){
        waitForWebElementToAppear(orderPage);
//        orderProductsNames.stream().filter(orderProductName->orderProductName.findElement(By.cssSelector(".ng-star-inserted td:nth-child(3)")).getText()).findFirst().orElse(null)
        String product=orderProductsNames.get(0).getText();
        return product;

    }
   public Boolean verifyOrderProductsNames(String productName){
        waitForWebElementToAppear(orderPage);
        boolean match=orderProductsNames.stream().anyMatch(orderProductName->orderProductName.getText().equalsIgnoreCase(productName));
        return match;
    }

}
