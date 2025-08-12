package SeleniumFramework.pageObjects;

import SeleniumFramework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCataloguePage extends AbstractComponent {
    WebDriver driver;

    public ProductCataloguePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By productsBox = By.cssSelector(".mb-3");
    @FindBy(css = ".mb-3")
    List<WebElement> products;
    By productTag = By.tagName("b");
    By addToCart = By.cssSelector(".w-10");
    By toastMsg = By.cssSelector(".toast-message");


    public List<WebElement> getProductsList() {
        waitForElementToAppear(productsBox);
        return products;
    }

    public WebElement getProductName(String productName) {
        WebElement prod = getProductsList().stream().filter(product -> product.findElement(productTag).getText().equals(productName)).findFirst().orElse(null);
        return prod;

    }

    public void addToCart(String productName) {
        WebElement prod = getProductName(productName);
        prod.findElement(addToCart).click();
    }

    public CartPage clickOnCart() {
        waitForElementToAppear(toastMsg);
        goToCart();
        return new CartPage(driver);

    }



}
