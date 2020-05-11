package pages;

import framework.PlatformFactory;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Log4j
public abstract class BasePage {

    public BasePage() {

        PageFactory.initElements(PlatformFactory.getDriver(), this);

    }

}
