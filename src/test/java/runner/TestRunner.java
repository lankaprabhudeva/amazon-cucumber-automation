package runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(
		features = {
				
				"src/test/resources/features/prime.feature"
		        
		    },
    glue = {"Stepdefpack", "Hooks"},          
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "json:target/cucumber.json"
        
    },
    monochrome = true,
    dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false) // Set true for parallel execution
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
