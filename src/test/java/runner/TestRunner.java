package runner;

import org.junit.runner.RunWith;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features="C:\\Users\\ADMIN\\eclipse-workspace_new\\Cucumber-pro\\src\\test\\resources\\features\\prime.feature",
		glue = {"Stepdefpack","Hooks"},
		plugin= {
		 "pretty",
		 "html:target/cucumber-reports.html",
		 "json:target/cucumber.json"
		},
		monochrome=true,
		dryRun=false
		
		)

public class TestRunner extends AbstractTestNGCucumberTests{

}
