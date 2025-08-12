package SeleniumFramework.pageObjects;

import SeleniumFramework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "userEmail")
    WebElement email;
    By emailBox=By.id("userEmail");
    @FindBy(id = "userPassword")
    WebElement password;
    @FindBy(id = "login")
    WebElement submit;
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMsg;
    public void goTo(){

        driver.get("https://rahulshettyacademy.com/client");

    }
    public String getErrorMsg(){
        waitForWebElementToAppear(errorMsg);
        return errorMsg.getText();
    }
    public ProductCataloguePage enterUserNameNPassword(String user, String pass){

        email.sendKeys(user);
        password.sendKeys(pass);
        submit.click();
        return new ProductCataloguePage(driver);
    }

}
