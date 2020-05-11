package framework;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static framework.PlatformFactory.getDriver;

@Log4j
public class Utilities {

    public static WebElement find(By locator) {
        return getDriver().findElement(locator);
    }
}
