package SeleniumFramework.pageObjects;

import SeleniumFramework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css="[placeholder='Select Country']")
    WebElement selectCountry;
    @FindBy(css="button .ng-star-inserted")
    List<WebElement> countryOptions;
    By countryOpt= By.cssSelector("button .ng-star-inserted");
    @FindBy(css=".action__submit")
    WebElement placeOrder;
    public void enterCountry(String country){
        selectCountry.sendKeys(country);
    }
    public List<WebElement> getCountryOptions(){
        return countryOptions;
    }
    public void clickOnDesiredCountry(String country){
        waitForElementToAppear(countryOpt);
        List<WebElement>options=getCountryOptions();
        WebElement desiredOption=options.stream().filter(option->option.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
        desiredOption.click();
    }
    public OrderPage clickOnPlaceOrder(){
        placeOrder.click();
        return new OrderPage(driver);
    }

}
