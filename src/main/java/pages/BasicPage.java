package pages;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Utils.getDomainName;

public class BasicPage {
    private String searchFieldLoc = "//input[@name='q']";
    private String submitButtonLoc = "//input[@name='btnK']";
    private String getSearchResults = ".g h3";
    private String searchResultsDomainLoc = ".r > a:first-child";
    private String nexButtonLoc = "#pnnext";

    public void inputText(String text) {
        $x(searchFieldLoc).shouldHave(visible).clear();
        $x(searchFieldLoc).shouldHave(visible).setValue(text);
        $x(submitButtonLoc).shouldHave(visible).click();
    }

    public void openLinkByIndex(int index) {
        $$(getSearchResults).shouldHave(CollectionCondition.sizeGreaterThan(0)).get(index).click();
    }

    public void checkPageTitle(String keyWord) {
        assertThat("Title open page by key word" + keyWord + " not contain it in title", title().toLowerCase(), containsString(keyWord.toLowerCase()));
    }

    public List<String> getDomainNameFromUrl(List<String> urls) {
        List<String> domains = new ArrayList<>();
        for (String url : urls) {
            domains.add(getDomainName(url));
        }
        return domains;
    }

    public List<String> getSearchingResults() {
        ElementsCollection urls = $$(searchResultsDomainLoc).shouldHave(CollectionCondition.sizeGreaterThan(0));
        List<String> result = new ArrayList<>();
        for (WebElement domain : urls) {
            result.add(domain.getAttribute("href"));
        }
        return result;
    }

    public void clickNextPage() {
        $(nexButtonLoc).shouldHave(visible).click();
        //check page reload
    }
}
