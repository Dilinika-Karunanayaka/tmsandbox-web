package nz.co.tmsandbox.pages;

import lombok.extern.log4j.Log4j2;
import nz.co.tmsandbox.util.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class HomePage extends BasePage{

    @FindBy(id = "search")
    private WebElement search;

    @FindBy(xpath = "//button[@aria-label='Search all of Trade Me']")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage navigateToHomePage(){
        String url = ConfigManager.getConfig("baseURL");
        log.info("Navigating to Home page: " + url);
        driver.get(url);
        return this;
    }

    public AllCategoriesPage search(String query){
        log.info("Performing search with query: " + query);
        search.sendKeys(query);
        searchButton.click();
        return new AllCategoriesPage(driver);
    }
}