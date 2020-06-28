package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.MainPage;
import pages.ResultsPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.fail;

public class Hooks {
    ResultsPage resultsPage = new ResultsPage();
    MainPage mainPage = new MainPage();
    final static Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Given("Open {string}")
    public void openUrl(String domainName) {
        open("https://" + domainName);
        logger.info("Open url: https://" + domainName);
    }

    @Then("Input text {string}")
    public void input_text(String string) {
        mainPage.inputText(string);
    }

    @Then("Open link by index {string}")
    public void openLinByIndex(String index) {
        resultsPage.openLinkByIndex(Integer.valueOf(index));
        logger.info("Open result lonk by index" + index);
    }

    @Then("Check that page title contain text {string}")
    public void checkPageTitle(String expectedTitle) {
        resultsPage.checkPageTitle(expectedTitle);
    }

    @Then("Verify that there is {string} domain is present on sears results pages, page 1 - {int}")
    public void verifyDomainNameInResalts(String expectedDomain, int pageToCheck) {
        if (!resultsPage.isSearchSuccessful()) {
            fail("There is no search result, can`t check domains name");
        }

        for (int i = 1; i <= pageToCheck; i++) {
            List<String> urls = resultsPage.getSearchingResults();
            if (resultsPage.getDomainNameFromUrl(urls).contains(expectedDomain)) {
                break;
            } else if (i != pageToCheck) {
                resultsPage.clickNextPage();
            } else {
                fail("Expected domain name '" + expectedDomain + " was not present in search result on pages 1-" + pageToCheck);
            }
        }
    }

}
