package Ecommerce.TestComponent;

import SeleniumFramework.pageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest{
    public WebDriver driver;
    public LandingPage landingPage;
public WebDriver initializeDriver() throws IOException {
    Properties prop=new Properties();
    FileInputStream fis=new FileInputStream("src/main/java/SeleniumFramework/Resources/GlobalData.Properties");
    prop.load(fis);
    String browserName=System.getProperty("Browser")!=null?System.getProperty("Browser"):prop.getProperty("Browser");

    if(browserName.contains("chrome")){
        ChromeOptions options=new ChromeOptions();
        if(browserName.contains("headless")) {
            options.addArguments("headless");
        }
        driver=new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1440,900));
    }
    else if(browserName.equalsIgnoreCase("firefox")){
        //geckodriver
        driver=new FirefoxDriver();
    }
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    driver.manage().window().maximize();
    return driver;
}
@BeforeMethod(alwaysRun = true)
public LandingPage openApplication() throws IOException, InterruptedException {
    driver=initializeDriver();
    landingPage=new LandingPage(driver);
    landingPage.goTo();
    return landingPage;

}
@AfterMethod(alwaysRun = true)
    public void closeAppliction(){
    driver.close();
}
public List<HashMap<String, String>> getDataToMap(String filepath) throws IOException {
String jsonContent= FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
    ObjectMapper mapper=new ObjectMapper();
    List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    return data;
}
//
//
//   	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
//	{
//		TakesScreenshot ts = (TakesScreenshot)driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
//		FileUtils.copyFile(source, file);
//		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
//
//
//	}

    public String getBase64Screenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }


//public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
//    TakesScreenshot ts = (TakesScreenshot) driver;
//    File source = ts.getScreenshotAs(OutputType.FILE);
//
//    File file = new File("D:\\SeleniumFramework\\reports\\" + testCaseName + ".png");
//    FileUtils.copyFile(source, file);
//
//    System.out.println("Saved screenshot: " + file.getAbsolutePath());
//    System.out.println("Exists? " + file.exists());
//
//    return file.getAbsolutePath();
//}

}
