package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Utils.getDomainName;

public class ResultsPage {
    final static Logger logger = LoggerFactory.getLogger(ResultsPage.class);

    private String getSearchResults = ".g h3";
    private String searchResultsDomainLoc = ".r > a:first-child";
    private String nexButtonLoc = "#pnnext";
    private String emptyResultLoc = "//div[@id='res']//p[@role='heading']/em";
    private String resultStatsLoc = "//div[@id='result-stats']";

    public void openLinkByIndex(int index) {
        $$(getSearchResults).shouldHave(CollectionCondition.sizeGreaterThan(0)).get(index).click();
        logger.info("Go to the next results page");
    }

    public ResultsPage checkPageTitle(String keyWord) {
        assertThat("Title open page by key word" + keyWord + " not contain it in title", title().toLowerCase(), containsString(keyWord.toLowerCase()));
    return this;
    }

    public List<String> getDomainNameFromUrl(List<String> urls) {
        List<String> domains = new ArrayList<>();
        for (String url : urls) {
            domains.add(getDomainName(url));
        }
        return domains;
    }

    public boolean isSearchSuccessful() {
        // if there is no search results page will be empty
        return !$x(emptyResultLoc).exists();
    }

    public List<String> getSearchingResults() {
        ElementsCollection urls = $$(searchResultsDomainLoc).shouldHave(CollectionCondition.sizeGreaterThan(0));
        List<String> result = new ArrayList<>();
        for (WebElement domain : urls) {
            result.add(domain.getAttribute("href"));
        }
        return result;
    }

    public ResultsPage clickNextPage() {
        String pageStatusText = $x(resultStatsLoc).shouldHave(visible).text();
        $(nexButtonLoc).shouldHave(visible).click();

        //wait for page reload
        $x(resultStatsLoc).shouldNotHave((text(pageStatusText)));
        logger.info("Go to the next results page");
        return this;
    }
}
