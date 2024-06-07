package nz.co.tmsandbox.pages;

import lombok.extern.log4j.Log4j2;
import nz.co.tmsandbox.util.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class HomePage extends BasePage {

    @FindBy(id = "search")
    private WebElement search;

    @FindBy(xpath = "//button[@aria-label='Search all of Trade Me']")
    private WebElement searchButton;

    @FindBy(xpath = ".//span[contains(@class, 'tm-global-search__search-suggestions-link-text')]")
    private List<WebElement> suggestionsList;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage navigateToHomePage() {
        String url = ConfigManager.getConfig("baseURL");
        log.info("Navigating to Home page: " + url);
        driver.get(url);
        return this;
    }

    public AllCategoriesPage search(String query) {
        log.info("Performing search with query: " + query);
        search.sendKeys(query);
        searchButton.click();
        return new AllCategoriesPage(driver);
    }

    public HomePage enterSearchText(String query) {
        log.info("Enter Search String: " + query);
        search.sendKeys(query);
        return new HomePage(driver);
    }

    public String getPlaceHolderText() {
        log.info("Capture Place holder value in Search");
        return search.getAttribute("placeholder");
    }

    public List<String> getSuggestionList() {
        log.info("Extract the suggestion List ");
        List<String> suggestionsText = new ArrayList<>();
        for (WebElement sugg : suggestionsList) {
            suggestionsText.add(sugg.getText());
        }
        return suggestionsText;
    }

    public void waitForSuggestionsToAppear() {
        log.info("Wait until suggestion list appear");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(suggestionsList));
    }

    public List<String> selectSuggestion(String suggestedString) {
        log.info("Select item from the suggestion List ");
        List<String> suggestionsText = new ArrayList<>();
        for (WebElement suggestion : suggestionsList) {
            log.info(suggestion.getText());
            if (suggestion.getText().equals(suggestedString)) {
                suggestion.click();
                suggestionsText.add(suggestedString);
                break;
            }
        }
        return suggestionsText;
    }
}