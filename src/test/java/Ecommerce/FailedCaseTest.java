package Ecommerce;

import Ecommerce.TestComponent.BaseTest;
import Ecommerce.TestComponent.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FailedCaseTest extends BaseTest {
    @Test(groups = {"errorHandling"},retryAnalyzer = Retry.class)
    public void ecommerceLoginCheck()  {
     landingPage.enterUserNameNPassword("gayathricseeng@gmail.com","43253");
    String errorMsg=landingPage.getErrorMsg();
    Assert.assertEquals(errorMsg,"Incorrect email or password.");
    }

}
