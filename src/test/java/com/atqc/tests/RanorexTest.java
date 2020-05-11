package com.atqc.tests;

import enums.RanorexCategory;
import enums.RanorexGender;
import framework.AllureListener;
import framework.DriverActions;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.RanorexPage;

import static enums.RanorexCategory.*;
import static enums.RanorexGender.Female;
import static enums.RanorexGender.Male;
import static org.testng.Assert.assertEquals;


@Epic("Ranorex")
@Feature("User management")
@Listeners(AllureListener.class)
public class RanorexTest extends BaseTest {

    RanorexPage ranorexPage;

    @Test
    @Story("")
    @TmsLink("")
    @Description("The first test: add new user")
    @Issue("")
    public void t1AddNewUser_one() {
        DriverActions.open("https://www.ranorex.com/web-testing-examples/vip/");
        ranorexPage = new RanorexPage();

        ranorexPage.addUser("James","Bond",Movie, Male);
        assertEquals(ranorexPage.getTableLineByFirstName("James"), "BondMovieMale");
        assertEquals(ranorexPage.getVipCount(), "1");
    }

    @Test
    @Story("")
    @TmsLink("")
    @Description("Add two users and delete the first")
    @Issue("")
    public void t2RemoveUser() {
        DriverActions.open("https://www.ranorex.com/web-testing-examples/vip/");
        ranorexPage = new RanorexPage();

        //Add first user
        ranorexPage.addUser("Kevin","Spacey",Other, Male);
        assertEquals(ranorexPage.getTableLineByFirstName("Kevin"), "SpaceyOtherMale");
        assertEquals(ranorexPage.getVipCount(), "1");

        //Add second user
        ranorexPage.addUser("Britney","Spears",Music, Female);
        assertEquals(ranorexPage.getTableLineByFirstName("Britney"), "SpearsMusicFemale");
        assertEquals(ranorexPage.getVipCount(), "2");

        //Delete just added user
        ranorexPage.deleteUserByFirstName("Kevin");
        assertEquals(ranorexPage.getVipCount(), "1");
    }

    @Test (dataProvider = "Beatles")
    @Story("")
    @TmsLink("")
    @Description("Add 'The Beatles'")
    @Issue("")
    public void t3_addMusicalGroup(String firstName, String lastName, RanorexCategory category, RanorexGender gender) {
        DriverActions.open("https://www.ranorex.com/web-testing-examples/vip/");
        ranorexPage = new RanorexPage();

        //Add "The Beatles"
        ranorexPage.addUser(firstName, lastName, category, gender);
        assertEquals(ranorexPage.getTableLineByFirstName(firstName), lastName+category+gender);
        assertEquals(ranorexPage.getVipCount(), "1");
    }

    @DataProvider(name = "Beatles")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "John", "Lennon", Music, Male }, { "Paul", "McCartney", Music, Male }, { "George", "Harrison", Music, Male }, { "Ringo", "Starr", Music, Male } };
    }
}
