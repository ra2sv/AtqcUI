package pages;

import enums.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import lombok.extern.log4j.Log4j;

import static enums.RanorexGender.*;
import static framework.PlatformFactory.getDriver;

@Log4j
public class RanorexPage extends BasePage {

    @FindBy (id = ("FirstName"))
    private WebElement firstNameField;

    @FindBy (id = "LastName")
    private WebElement lastNameField;

    @FindBy (id = "Category")
    private WebElement categoryField;

    @FindBy (id = "Add")
    private WebElement addButton;

    @FindBy (id = "Delete")
    private WebElement deleteButton;

    @FindBy (id = "count")
    private WebElement vipCountLabel;

    @FindBy (xpath = "//*[@id='Gender' and @value='male']")
    private WebElement genderRadioButtonM;

    @FindBy (xpath = "//*[@id='Gender' and @value='female']")
    private WebElement genderRadioButtonF;

    //Methods
    @Step("Find field: {0}")
    public void enterFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        log.info("Clear and type in firstName field");
    }

    @Step("Find field: {0}")
    public void enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        log.info("Clear and type in lastName field");
    }

    @Step("Find field: {0}")
    public void selectCategory(RanorexCategory category) {
        Select selectCategory = new Select(categoryField);
        selectCategory.selectByVisibleText(category.getValue());
        log.info("Select Category");
    }

    @Step("Find checkbox: {0}")
    public void selectGender(RanorexGender gender) {
        if (gender.equals(MALE)) {
            genderRadioButtonM.click();
        } else {
            genderRadioButtonF.click();
        }
        log.info("Select gender");
    }

    @Step("Find VIP counter")
    public String getVipCount() {
        log.info("Get number from 'VIP count' filed");
        return vipCountLabel.getText().replaceAll("[^0-9]", "");
    }

    @Step("Find [Add] button")
    public RanorexPage clickAddButton(){
        addButton.click();
        log.info("Click on the 'Add' button");
        return new RanorexPage();
    }

    @Step("Find [Delete] button")
    public RanorexPage clickDeleteButton(){
        deleteButton.click();
        log.info("Click on the 'Delete' button");
        return new RanorexPage();
    }

    @Step("Add user: {0}, {1}, {2}, {3}")
    public RanorexPage addUser (String firstName, String lastName, RanorexCategory category, RanorexGender gender) {
        enterFirstName(firstName);
        enterLastName(lastName);
        selectCategory(category);
        selectGender(gender);
        return clickAddButton();
    }

    @Step("Find table row by firstName: {0}")
    public String getTableLineByFirstName (String firstName) {
        WebElement rootElement = getDriver().findElement(By.xpath(String.format("(//*[@id='VIP'])/following::td[text()='%s']", firstName)));
        String getLastName = rootElement.findElement(By.xpath("./following::td[1]")).getText();
        String getCategory = rootElement.findElement(By.xpath("./following::td[3]")).getText();
        String getGender = rootElement.findElement(By.xpath("./following::td[2]")).getText();
        log.info("Try to find needed row in the table by firstName");
        return (getLastName+getCategory+getGender);
    }

    @Step("Delete user by firstName: {0}")
    public RanorexPage deleteUserByFirstName (String firstName) {
        String elementPresented = getDriver().findElement(By.xpath("((//*[@id='VIP'])[1])/following::td[1]")).getText();
        if (elementPresented.equals(firstName)) {
            getDriver().findElement(By.xpath("(//*[@id='VIP'])[1]")).click();
            log.info("Needed row was found in the table");
            return clickDeleteButton();
        }
        else {
            log.info("Needed row was not found in the table: can not continue without making sure that the needed element is selected");
            return this;
        }
    }

}
