package SeleniumFramework.AbstractComponents;

import SeleniumFramework.pageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractComponent {
    WebDriver driver;
    public AbstractComponent(WebDriver driver){
        this.driver=driver;
    }
    @FindBy(css = "[routerlink*='cart']")
    WebElement cartBtn;
    @FindBy(css="[routerlink*='myorders']")
    WebElement orderBtn;
    @FindBy(css=".ng-star-inserted td:nth-child(3)")
    List<WebElement> orderProductsNames;
    public void waitForElementToAppear(By findBy){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForWebElementToAppear(WebElement findBy){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }
    public void goToCart(){
        cartBtn.click();
    }
    public OrderPage clickOnOrder(){
        waitForWebElementToAppear(orderBtn);
        orderBtn.click();
        return new OrderPage(driver);
    }

}
