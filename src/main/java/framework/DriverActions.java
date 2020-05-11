package framework;

import lombok.extern.log4j.Log4j;

@Log4j
public class DriverActions {

    public static void open(String url) {
        PlatformFactory.getDriver().get(url);
    }
}
