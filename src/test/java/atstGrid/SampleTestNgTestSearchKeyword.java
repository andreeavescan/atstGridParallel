package atstGrid;

import atstGrid.pages.DictionaryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import atstGrid.pages.HomePage;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class SampleTestNgTestSearchKeyword extends TestNgTestBase {

    private DictionaryPage dictionaryPage;
    @BeforeMethod
    public void initPageObjects() {
        dictionaryPage = PageFactory.initElements(driver, DictionaryPage.class);
    }

    //@Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    //@Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    //@Step
    public void should_see_definition(String definition) {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }

    //@Step
    public void is_the_home_page() {
        driver.get("http://en.wiktionary.org/wiki/Wiktionary");
    }

    //@Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }

    @Test
    public void searching_by_keyword_apple_should_display_the_corresponding_article() {
        is_the_home_page();
        looks_for("apple");
        should_see_definition("A common, round fruit produced by the tree Malus domestica, cultivated in temperate climates.");

    }

    @Test
    public void searching_by_keyword_banana_should_display_the_corresponding_article() {
        is_the_home_page();
        looks_for("pear");
        should_see_definition("An edible fruit produced by the pear tree, similar to an apple but elongated towards the stem.");
    }






}
