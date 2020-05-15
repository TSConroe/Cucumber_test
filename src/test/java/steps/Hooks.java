package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasicPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.testng.Assert.fail;

public class Hooks {
    BasicPage basicPage = new BasicPage();
    final static Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void openUrl() {
        open("https://google.com");
        logger.info("Starting browser");
    }

    @Then("Input text {string}")
    public void input_text(String string) {
        basicPage.inputText(string);
    }

    @Then("Open link by index {string}")
    public void openLinByIndex(String index) {
        basicPage.openLinkByIndex(Integer.valueOf(index));
    }

    @Then("Check that page title contain text {string}")
    public void checkPageTitle(String expectedTitle) {
        basicPage.checkPageTitle(expectedTitle);
    }

    @Then("Verify that there is {string} domain is present on sears results pages, page 1 - {int}")
    public void verifyDomainNameInResalts(String expectedDomain, int pageToCheck) {
        List<String> domains = new ArrayList();

        if (!basicPage.isSearchSuccessful()) {
            fail("There is no search result, can`t check domains name");
        }

        for (int i = 0; i < pageToCheck; i++) {
            List<String> urls = basicPage.getSearchingResults();
            domains.addAll(basicPage.getDomainNameFromUrl(urls));
            if (domains.contains(expectedDomain)) {
                break;
            } else {
                basicPage.clickNextPage();
            }
        }
        assertThat("Title open page by key word " + expectedDomain + " not contain it in title", domains, hasItems(expectedDomain));
    }

}
