package runner;


import assertion.TestMethodListener;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import cucumber.api.testng.PickleEventWrapper;
import static utilities.ScreenShot.takeScreenshot;

@CucumberOptions(features = { "src/test/resources/features"}
        ,glue = {"stepDefs","runner"}
        , plugin = {"pretty:target/cucumber-reports/cucumber.txt"
        ,"html:target/cucumber-reports"
        ,"json:target/cucumber.json"}
//        , tags = {"@BackEnd,@FrontEnd"}
        , tags = {"@FrontEnd"}
)

@Listeners(TestMethodListener.class)
public class TestRunner {

    private TestNGCucumberRunner testNGCucumberRunner;
    public Logger log = LogManager.getLogger(TestRunner.class);


    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        bases.BaseTest.initalDriver();
        log.info(" ================================Start Cucumber Suite Class=====================================================");
    }

    @Before
    public void initializeTest(Scenario scenario){
        log.info("------Start Scenario: " + scenario.getName() + " ---------------------");
    }

    @After

    public void embedScreenshot(Scenario scenario) {

        if (scenario.isFailed()) {
            try {
                 scenario.embed(takeScreenshot(),"image/png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.info("------End Scenario: " + scenario.getName() + " was " + scenario.getStatus() + " -----------------------");
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider()
    public Object[][] scenarios() throws Exception {
        return testNGCucumberRunner.provideScenarios();
    }


    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
        bases.BaseTest.closeDriver();
        log.info("=================================End Cucumber Suite Class========================================================");
    }
}
