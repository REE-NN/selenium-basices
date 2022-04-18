package dataSource;

import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.List;

public class DataProviderSource {
    @DataProvider(name = "letter from CSV")
    public static Object[][] LetterFromCSV() {
        List<Letter> list = CSVReader.loadData();
        int n = CSVReader.list.size(); //количество строк в массиве
        int m = 3;                     //количество колонок - все аттрибуты письма
        Object[][] objects = new Object[n][m];
        for (int i = 0; i < n; i++) {
            Letter letter = list.get(i);
            objects[i][0] = letter.getAddress();
            objects[i][1] = letter.getSubject();
            objects[i][2] = letter.getBody();
        }
        System.out.println(Arrays.deepToString(objects));
        return objects;
    }

    @DataProvider(name = "to subject body")
    public static Object[][] To_Subject_Body() {
        return new Object[][]{
                {"ree.post@yandex.ru", "sport", "sport, sport, sport."},
                {"test@test.com", "test", "test"},
                {"support@mail.ru", "support", "Ben, I need help!"}};
    }

    @DataProvider(name = "letter to send")
    public static Object[][] SendLetter() {
        return new Object[][]{
                {"ree.post@yandex.ru", "send", "this email must be sent"}};
    }

    @DataProvider(name = "letter to delete")
    public static Object[][] LetterToDelete() {
        return new Object[][]{
                {"delete@yandex.ru", "delete", "this massage must be deleted"}};
    }
}