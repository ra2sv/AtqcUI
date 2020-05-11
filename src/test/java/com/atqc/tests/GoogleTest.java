package com.atqc.tests;

import framework.AllureListener;
import framework.DriverActions;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.GooglePage;

@Epic("Search")
@Feature("Google Search Test")
@Listeners(AllureListener.class)
public class GoogleTest extends BaseTest {

    GooglePage googlePage;

    @Test
    @Story("STORY-23")
    @TmsLink("25363")
    @Description("Test for search")
    @Issue("JIRA-111")
    public void openGooglePage() {

        DriverActions.open("https://www.google.com/");
        googlePage = new GooglePage();
        googlePage.inputSearchParameters("fff");

    }

}
