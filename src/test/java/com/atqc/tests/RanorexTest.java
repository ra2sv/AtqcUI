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
import static enums.RanorexGender.*;
import static org.testng.Assert.assertEquals;


@Epic("Ranorex")
@Feature("User management")
@Listeners(AllureListener.class)
public class RanorexTest extends BaseTest {

    RanorexPage ranorexPage;

    @Test
    @Description("The first test: add new user")
    public void t1AddNewUser_one() {
        DriverActions.open("https://www.ranorex.com/web-testing-examples/vip/");
        ranorexPage = new RanorexPage();

        ranorexPage.addUser("James","Bond",MOVIE, MALE);
        assertEquals(ranorexPage.getTableLineByFirstName("James"), "BondMovieMale");
        assertEquals(ranorexPage.getVipCount(), "1");
    }

    @Test
    @Description("Add two users and delete the first")
    public void t2RemoveUser() {
        DriverActions.open("https://www.ranorex.com/web-testing-examples/vip/");
        ranorexPage = new RanorexPage();

        //Add first user
        ranorexPage.addUser("Kevin","Spacey",OTHER, MALE);
        assertEquals(ranorexPage.getTableLineByFirstName("Kevin"), "SpaceyOtherMale");
        assertEquals(ranorexPage.getVipCount(), "1");

        //Add second user
        ranorexPage.addUser("Britney","Spears",MUSIC, FEMALE);
        assertEquals(ranorexPage.getTableLineByFirstName("Britney"), "SpearsMusicFemale");
        assertEquals(ranorexPage.getVipCount(), "2");

        //Delete just added user
        ranorexPage.deleteUserByFirstName("Kevin");
        assertEquals(ranorexPage.getVipCount(), "1");
    }

    @Test (dataProvider = "Beatles")
    @Description("Add 'The Beatles'")
    public void t3_addMusicalGroup(String firstName, String lastName, RanorexCategory category, RanorexGender gender) {
        DriverActions.open("https://www.ranorex.com/web-testing-examples/vip/");
        ranorexPage = new RanorexPage();

        //Add "The Beatles"
        ranorexPage.addUser(firstName, lastName, category, gender);
        assertEquals(ranorexPage.getTableLineByFirstName(firstName), lastName+category.getValue()+gender.getValue());
        assertEquals(ranorexPage.getVipCount(), "1");
    }

    @DataProvider(name = "Beatles")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "John", "Lennon", MUSIC, MALE }, { "Paul", "McCartney", MUSIC, MALE }, { "George", "Harrison", MUSIC, MALE }, { "Ringo", "Starr", MUSIC, MALE } };
    }
}
