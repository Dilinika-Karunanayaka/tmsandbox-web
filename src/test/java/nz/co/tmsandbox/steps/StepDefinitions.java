package nz.co.tmsandbox.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import nz.co.tmsandbox.pages.AllCategoriesPage;
import nz.co.tmsandbox.pages.HomePage;
import nz.co.tmsandbox.util.ScenarioContext;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class StepDefinitions {

    private final HomePage homePage;
    private AllCategoriesPage allCategoriesPage;
    private long startTime;
    private long endTime;

    public StepDefinitions(ScenarioContext scenarioContext) {
        homePage = new HomePage(scenarioContext.getDriver());
        allCategoriesPage = new AllCategoriesPage(scenarioContext.getDriver());
    }

    @Given("I'm on the home page")
    public void i_m_on_the_home_page() {
        homePage.navigateToHomePage();
    }

    @When("I try to search for {string}")
    public void i_try_to_search_for(String query) {
        allCategoriesPage = homePage.search(query);
    }

    @Then("the all categories page title should start with {string}")
    public void theAllCategoriesPageTitleShouldStartWith(String titleStartsWith) {
        assertThat(allCategoriesPage.getTitle(), containsString(titleStartsWith));
    }

    @When("I try to search {string}")
    public void i_try_to_search(String searchString) {
        homePage.search(searchString);
    }

    @Then("I can see search results for {string}")
    public void i_can_see_search_results_for(String searchString) {
        assertThat(allCategoriesPage.getHeaderInformation(), containsString(searchString));
    }

    @Then("I can see the search result for {string} and a result count of {string}")
    public void i_can_see_the_search_result_for_and_a_result_count_of(String searchString, String count) {
        assertThat(allCategoriesPage.getHeaderInformation(), containsString(searchString));
        assertThat(allCategoriesPage.getHeaderInformation(), containsString(count));
    }

    @When("I check the search text box")
    public void i_check_the_search_text_box() {
    }

    @Then("I can see {string} as a place holder text")
    public void i_can_see_as_a_place_holder_text(String placeHolderText) {
        assertThat(homePage.getPlaceHolderText(), containsString(placeHolderText));
    }

    @When("I enter {string} and wait for the suggestion list to appear")
    public void i_enter_and_wait_for_the_suggestion_list_to_appear(String searchString) {
        homePage.enterSearchText(searchString);
        homePage.waitForSuggestionsToAppear();
    }

    @Then("I observe suggestions in the dropdown list relevant to {string}")
    public void i_observe_suggestions_in_the_dropdown_list_relevant_to(String searchString) {
        String lowerCaseSearchString = searchString.toLowerCase();
        List<String> suggestionList = homePage.getSuggestionList();

        for (String suggestion : suggestionList) {
            String lowerCaseSuggestion = suggestion.toLowerCase();
            assertThat("Suggestion '" + suggestion + "' does not contain the search string '" + searchString + "'", lowerCaseSuggestion, containsStringIgnoringCase(lowerCaseSearchString));
        }
    }

    @Then("I choose {string} from the suggestion list")
    public void i_choose_from_the_suggestion_list(String suggestedString) {
        homePage.selectSuggestion(suggestedString);
    }

    @Then("I can see the search results that match {string}")
    public void i_can_see_the_search_results_that_match(String suggestedString) {
        assertThat(allCategoriesPage.getHeaderInformation(), containsString(suggestedString));
    }

    @When("I perform a search for {string}")
    public void i_perform_a_search_for(String searchString) {
        startTime = System.currentTimeMillis();
        homePage.search(searchString);
        endTime = System.currentTimeMillis();
    }

    @Then("I should see search results {string}")
    public void i_should_see_search_results(String searchString) {
        assertThat(allCategoriesPage.getHeaderInformation(), containsString(searchString));
        long duration = endTime - startTime;
        log.info("Search results displayed in: " + duration + " milliseconds");
    }

    @When("I type {string} in the search box")
    public void i_type_in_the_search_box(String searchString) {
        startTime = System.currentTimeMillis();
        homePage.enterSearchText(searchString);
        homePage.waitForSuggestionsToAppear();
        List<String> suggestionList = homePage.getSuggestionList();
        assertTrue(homePage.getSuggestionList().size() > 0);
    }

    @Then("I should see search suggestions")
    public void i_should_see_search_suggestions() {
        endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("Search suggestions displayed in: " + duration + " milliseconds");
    }
}