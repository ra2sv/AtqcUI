package pages;

import enums.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import lombok.extern.log4j.Log4j;

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
    public void enterFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    public void selectCategory(RanorexCategory category) {
        Select selectCategory = new Select(categoryField);
        selectCategory.selectByVisibleText(category.toString());
    }
    public void selectGender(RanorexGender gender) {
        switch (gender) {
            case Female:
                genderRadioButtonF.click();
                break;
            default:
                genderRadioButtonM.click();
                break;
        }
    }
    public String getVipCount() {
        return vipCountLabel.getText().replaceAll("[^0-9]", "");
    }
    public RanorexPage clickAddButton(){
        addButton.click();
        return new RanorexPage();
    }
    public RanorexPage clickDeleteButton(){
        deleteButton.click();
        return new RanorexPage();
    }
    public RanorexPage addUser (String firstName, String lastName, RanorexCategory category, RanorexGender gender) {
        enterFirstName(firstName);
        enterLastName(lastName);
        selectCategory(category);
        selectGender(gender);
        return clickAddButton();
    }
    public String getTableLineByFirstName (String firstName) {
        WebElement rootElement = getDriver().findElement(By.xpath(String.format("(//*[@id='VIP'])/following::td[text()='%s']", firstName)));
        String getLastName = rootElement.findElement(By.xpath("./following::td[1]")).getText();
        String getCategory = rootElement.findElement(By.xpath("./following::td[3]")).getText();
        String getGender = rootElement.findElement(By.xpath("./following::td[2]")).getText();
        return (getLastName+getCategory+getGender);
    }
    public RanorexPage deleteUserByFirstName (String firstName) {
        String elementPresented = getDriver().findElement(By.xpath("((//*[@id='VIP'])[1])/following::td[1]")).getText();
        if (elementPresented.equals(firstName)) {
            getDriver().findElement(By.xpath("(//*[@id='VIP'])[1]")).click();
            return clickDeleteButton();
        }
        else {
            log.info("Needed row was not found in the table: can not continue without making sure that the needed element is selected");
            return this;
        }
    }
}
