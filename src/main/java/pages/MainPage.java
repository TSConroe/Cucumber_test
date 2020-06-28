package pages;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    private String searchFieldLoc = "//input[@name='q']";

    public ResultsPage inputText(String text) {
        $x(searchFieldLoc).shouldHave(visible).clear();
        $x(searchFieldLoc).shouldHave(visible).setValue(text)
                .pressEnter();
        return page(ResultsPage.class);
    }
}