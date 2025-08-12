package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/Cucumber",
        glue = "Ecommerce.StepDefinition",
        monochrome = true,
        tags = "@errorValidation",
        plugin = {
                "pretty",
                "html:target/cucumber.html",
                "json:target/cucumber.json"
        },
        publish = true
)
public class TestNGTestCucumberRunner extends AbstractTestNGCucumberTests {
}
