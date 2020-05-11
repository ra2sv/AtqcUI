package framework;

import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class PlatformFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch(Config.platform) {

            case CHROME:

                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());

                break;

            case ANDROID:

                capabilities.setCapability("deviceName", Config.androidDeviceName);
                capabilities.setCapability("platformName","Android");
                capabilities.setCapability("app", Config.androidAppPath);

                startAppiumDriver(capabilities);

                break;

            case IOS:

                capabilities.setCapability("platformName", "IOS");

                startAppiumDriver(capabilities);

                break;
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        if (driver != null)
            driver.get().quit();
    }

    private static void startAppiumDriver(DesiredCapabilities capabilities) {
        try {
            driver.set(new AppiumDriver<>(new URL(Config.appiumURL), capabilities));
            getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        } catch (MalformedURLException e) {}
    }
}
