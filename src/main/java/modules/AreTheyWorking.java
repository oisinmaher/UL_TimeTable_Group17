package modules;

import com.opencsv.bean.*;
import users.Lecturer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreTheyWorking {

    public static void main(String[] args) throws Exception {

        Map<String, String> mapping = new HashMap<>();
        mapping.put("name", "name");
        mapping.put("userId", "userId");

        HeaderColumnNameTranslateMappingStrategy<Lecturer> strategy =
                new HeaderColumnNameTranslateMappingStrategy<>();
        strategy.setType(Lecturer.class);
        strategy.setColumnMapping(mapping);

        // Read CSV
        Reader reader = new FileReader("src/main/resources/students.csv");
        CsvToBean<Lecturer> csvToBean =
                new CsvToBeanBuilder<Lecturer>(reader)
                        .withMappingStrategy(strategy)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

        List<Lecturer> modules = csvToBean.parse();
        reader.close();

        // Add new lecturers
        modules.add(new Lecturer("999", "Michael English"));
        modules.add(new Lecturer("852", "Nikola Nikolov"));

        // Write to CSV
        writeStudents(modules);

        // READ BACK AND PRINT
        objectsTrying();
    }

    public static void writeStudents(List<Lecturer> modules) throws Exception {
        try (Writer writer = new FileWriter("src/main/resources/students.csv")) {
            StatefulBeanToCsv<Lecturer> beanToCsv = new StatefulBeanToCsvBuilder<Lecturer>(writer)
                    .withApplyQuotesToAll(false)
                    .build();
            beanToCsv.write(modules);
        }
    }

    public static void objectsTrying() throws Exception {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("name", "name");
        mapping.put("userId", "userId");

        HeaderColumnNameTranslateMappingStrategy<Lecturer> strategy =
                new HeaderColumnNameTranslateMappingStrategy<>();
        strategy.setType(Lecturer.class);
        strategy.setColumnMapping(mapping);

        try (Reader reader = new FileReader("src/main/resources/students.csv")) {
            CsvToBean<Lecturer> csvToBean =
                    new CsvToBeanBuilder<Lecturer>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            List<Lecturer> modules2 = csvToBean.parse();

            System.out.println("\nREAD BACK FROM CSV:");
            for (Lecturer l : modules2) {
                System.out.println(l);
            }
        }
    }
}
