package framework;

import static framework.PropertyLoader.retrieveProperty;

public class Config {

    public static int timeout = Integer.valueOf(PropertyLoader.getProperty("timeout"));

    public static Platform platform = Platform.valueOf(retrieveProperty("webdriver.platform"));

    public static String appiumURL = PropertyLoader.getProperty("appium.url");
    public static String androidAppPath = PropertyLoader.getProperty("android.appPath");
    public static String androidDeviceName = PropertyLoader.getProperty("android.deviceName");

}

