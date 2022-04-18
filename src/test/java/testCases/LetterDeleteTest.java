package testCases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static dataSource.StaticSource.THREAD_SLEEP_4;
import static org.testng.Assert.assertEquals;
import static testCases.LoginTest.mailPage;
import static testCases.LoginTest.userMenu;

public class LetterDeleteTest {

    @Parameters({"draft_Subject"})
    @Test(
            groups = {"check drafts"},
            dependsOnGroups = {"create letter"}
    )
    void checkDraftSubjectTest(String Subject_Body) throws InterruptedException {
        userMenu.openLeftMenuItem(userMenu.getDraftFolder());
        Thread.sleep(THREAD_SLEEP_4);

        String firstDraftSubject = mailPage.getFirstLetterSubject();
        assertEquals(firstDraftSubject, Subject_Body, "The message with subject \"" +
                firstDraftSubject + "\" is not right");
    }

    @Parameters({"draft_Subject"})
    @Test(
            groups = {"delete drafts"},
            dependsOnGroups = {"check drafts"})
    void deleteFirstDraft(String Subject_Body) throws InterruptedException {
        mailPage.dragAndDrop(mailPage.getFirstLetter(), userMenu.getTrashFolder());

        userMenu.openLeftMenuItem(userMenu.getTrashFolder());
        Thread.sleep(THREAD_SLEEP_4);
        String firstDeletedLetterSubject = mailPage.getFirstLetterSubject();
        assertEquals(firstDeletedLetterSubject, Subject_Body, "The message wasn't deleted, " +
                "or it wasn't the right one");
    }
}