package nz.co.tmsandbox.steps;

import io.cucumber.java.*;
import lombok.extern.log4j.Log4j2;
import nz.co.tmsandbox.util.ScenarioContext;
import nz.co.tmsandbox.util.WebDriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CucumberHooks {

    private WebDriver driver;
    private final ScenarioContext scenarioContext;

    public CucumberHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @BeforeAll
    public static void beforeAll() {
        log.info("CucumberHooks.BeforeAll");
    }

    @Before()
    public void openBrowser() {
        log.info("CucumberHooks.Before");
        driver = WebDriverFactory.createWebDriver();
        scenarioContext.setDriver(driver);
    }

    @AfterStep
    public void attachScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName() + " => "+ scenarioContext.getDriver().getCurrentUrl());
        }
    }

    @After()
    public void closeBrowser() {
        log.info("CucumberHooks.After");
        scenarioContext.getDriver().quit();
    }

    @AfterAll
    public static void afterAll() {
        log.info("CucumberHooks.AfterAll");
    }
}