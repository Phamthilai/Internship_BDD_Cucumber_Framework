package runner;

import assertion.TestMethodListener;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

@CucumberOptions(features = { "src/test/resources/features"}
        ,glue = {"stepDefs", "bases"}
        , plugin = {"pretty:target/cucumber-reports/cucumber.txt"
        ,"html:target/cucumber-reports","json:target/cucumber.json"}
        , tags = {"@BackEnd,@FrontEnd"}
)


@Listeners(TestMethodListener.class)
public class TestRunner_Parallel extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }


}
