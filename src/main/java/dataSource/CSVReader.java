package dataSource;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Letter> list;

    public static List<Letter> loadData()
    {
        list = new ArrayList<>();
        CSVParser parser;
        try {
            FileReader Data = new FileReader(ConfProperties.getProperty("csvData_path"));
            //FileReader Data = new FileReader(ConfProperties.getProperty("csvTestData_path"));
            parser = CSVParser.parse(Data, CSVFormat.DEFAULT);
            for (CSVRecord csvCell : parser) {
                String inAddress = csvCell.get(0);
                String inSubject = csvCell.get(1);
                String inBody = csvCell.get(2);
                Letter letter = Letter.builder().address(inAddress).subject(inSubject).body(inBody).build();
                list.add(letter);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
