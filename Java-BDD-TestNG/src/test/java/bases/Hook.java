package bases;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import runner.TestRunner_Parallel;

import static utilities.ScreenShot.takeScreenshot;

public class Hook {

    private Logger log = LogManager.getLogger(Hook.class);

    @Before
    public void initializeTest(Scenario scenario){
        bases.BaseTest.initalDriver();
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
        bases.BaseTest.closeDriver();
        log.info("------End Scenario: " + scenario.getName() + " -----------------------");
    }
}
