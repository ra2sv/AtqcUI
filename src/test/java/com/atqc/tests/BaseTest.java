package com.atqc.tests;

import framework.PlatformFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {

        PlatformFactory.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {

        PlatformFactory.closeDriver();

    }
}
