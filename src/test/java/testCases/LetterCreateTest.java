package testCases;

import dataSource.DataProviderSource;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static testCases.LoginTest.mailPage;
import static testCases.LoginTest.userMenu;
import static testCases.LoginTest.wrightLetter;

public class LetterCreateTest {

    @BeforeMethod(
            groups = {"open new letter"},
            onlyForGroups = {"create letter"})
    void openNewLetter() {
        userMenu.openLeftMenuItem(userMenu.getNewLetterBtn());

        boolean isPresent = mailPage.isElementPresent();
        assertTrue(isPresent, "Opening new letter form - works wrong");
    }

    @Test(
            dataProvider = "letter from CSV",
            dataProviderClass = DataProviderSource.class,
            groups = {"create letter"},
            dependsOnGroups = {"enter menu"})

    public void createLetter(String inAddress, String inSubject, String inBody) {
        wrightLetter.wrightLetter(inAddress, inSubject, inBody);

        String saveStatus = wrightLetter.getSaveStatus();
        assertTrue(saveStatus.contains("сохранено"), "The email was not saved");
    }

    @AfterMethod(
            onlyForGroups = {"create letter"})
    void closeLetter() {
        wrightLetter.closeLetter();
    }
}
