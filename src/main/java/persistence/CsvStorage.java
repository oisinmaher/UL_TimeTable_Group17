package persistence;

import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.*;

/**
 * Abstract base class for CSV storage of objects of type T.
 * Handles appending, loading, and header writing.
 */
public abstract class CsvStorage<T> {

    private final String filePath;
    private final Class<T> type;
    private final String[] columns;

    protected CsvStorage(String filePath, Class<T> type, String[] columns) {
        this.filePath = filePath;
        this.type = type;
        this.columns = columns;
    }

    /**
     * Saves a list of objects to the CSV file.
     * Header is written only if file is empty.
     */
    public void saveAll(List<T> objects) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        File file = new File(filePath);
        boolean fileEmpty = !file.exists() || file.length() == 0;

        ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(type);
        strategy.setColumnMapping(columns);

        try (Writer writer = new FileWriter(file, true)) {
            if (fileEmpty) {
                StringBuilder headerBuilder = new StringBuilder();

                for (int i = 0; i < columns.length; i++) {
                    headerBuilder.append(columns[i].toUpperCase());
                    if (i < columns.length - 1) {
                        headerBuilder.append(","); // add comma between columns, but not after last one
                    }
                }
                headerBuilder.append("\n");
                writer.write(headerBuilder.toString());
            }

            StatefulBeanToCsv<T> csv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withMappingStrategy(strategy)
                    .withApplyQuotesToAll(false)
                    .build();

            csv.write(objects);
        }
    }

    /**
     * Loads all objects from the CSV file.
     */
    public List<T> loadAll() throws IOException {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            CsvToBean<T> csv = new CsvToBeanBuilder<T>(reader)
                    .withType(type)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csv.parse();
        }
    }
}
