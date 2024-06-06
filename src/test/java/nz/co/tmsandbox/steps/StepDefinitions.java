package nz.co.tmsandbox.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.co.tmsandbox.pages.AllCategoriesPage;
import nz.co.tmsandbox.pages.HomePage;
import nz.co.tmsandbox.util.ScenarioContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class StepDefinitions {

    private final HomePage homePage;
    private AllCategoriesPage allCategoriesPage;

    public StepDefinitions(ScenarioContext scenarioContext) {
        homePage = new HomePage(scenarioContext.getDriver());
    }

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        homePage.navigateToHomePage();
    }

    @When("I search for {string}")
    public void search_for(String query) {
        allCategoriesPage = homePage.search(query);
    }

    @Then("the all categories page title should start with {string}")
    public void theAllCategoriesPageTitleShouldStartWith(String titleStartsWith) {
        assertThat(allCategoriesPage.getTitle(), containsString(titleStartsWith));
    }
}