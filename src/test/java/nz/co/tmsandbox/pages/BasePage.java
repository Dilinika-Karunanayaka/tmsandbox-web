package nz.co.tmsandbox.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        if (driver == null) {
            log.error("WebDriver instance cannot be null");
            throw new IllegalArgumentException("WebDriver instance cannot be null");
        }
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getTitle(String titleContains){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.titleContains(titleContains));
        return driver.getTitle().toLowerCase();
    }
}