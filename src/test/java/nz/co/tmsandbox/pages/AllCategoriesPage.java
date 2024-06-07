package nz.co.tmsandbox.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class AllCategoriesPage extends BasePage{

    @FindBy(id = "search")
    private WebElement search;

    @FindBy(xpath = "//button[@aria-label='Search all of Trade Me']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[contains(concat(' ', @class, ' '), ' tm-search-header-result-count__heading ng-star-inserted ')]")
    private WebElement headerInformation;

    public AllCategoriesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return getTitle("for sale | Trade Me");
    }

    public  String getHeaderInformation(){
        return headerInformation.getText();
    }
}