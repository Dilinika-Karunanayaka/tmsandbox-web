package nz.co.tmsandbox.util;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ScenarioContext {

    @Getter @Setter
    private WebDriver driver;

    public ScenarioContext() {
        log.info("ScenarioContext()");
    }
}