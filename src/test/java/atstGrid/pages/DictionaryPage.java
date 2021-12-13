package atstGrid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.stream.Collectors;

public class DictionaryPage extends Page {

    public DictionaryPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(how = How.NAME, using ="search")
    @CacheLookup
    private WebElement searchTerms;

    @FindBy(how = How.NAME, using ="go")
    @CacheLookup
    private WebElement lookupButton;

    public void enter_keywords(String keyword) {
        searchTerms.sendKeys(keyword);
    }

    public void lookup_terms() {
        lookupButton.click();
    }

    public List<String> getDefinitions() {
        WebElement definitionList = driver.findElement(By.tagName("ol"));
        return definitionList.findElements(By.tagName("li")).stream()
                .map( element -> element.getText() )
                .collect(Collectors.toList());
    }
}
