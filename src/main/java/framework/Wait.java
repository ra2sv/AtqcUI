package framework;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static framework.PlatformFactory.getDriver;

@Log4j
public class Wait {

    public static boolean waitUntilVisible(WebElement element) {
        try {
            new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(Config.timeout))
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            log.error("Element is not displayed");
            return false;
        }
        log.info("Element is displayed");
        return true;
    }
}
