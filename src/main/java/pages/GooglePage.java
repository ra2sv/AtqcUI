package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static framework.Wait.waitUntilVisible;

@Log4j
public class GooglePage extends BasePage {

    @FindBy(name = "q")
    private WebElement searchField;

    @Step("Input search parameters: {0}")
    public void inputSearchParameters(String searchParams) {
        waitUntilVisible(searchField);
        searchField.click();
        searchField.sendKeys(searchParams);
        log.info("Search parameters was entered");
    }
}
