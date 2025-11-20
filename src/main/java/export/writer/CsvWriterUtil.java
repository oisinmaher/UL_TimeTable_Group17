package export.writer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * Generic writer class for java objects to CSV files - used for persistent storage.
 * Can take any class/object and write to CSV file.
 * Meant to be used in the export package when objects taken from domain and have been
 * flattened in mapper class.
 * @param <T> can take a generic class to write
 */

public class CsvWriterUtil<T> {

    private final Path path;
    private final Class<T> type;

    /**
     * Constructor class for the writer
     * @param path the file path for the CSV
     * @param type the class to be written to the file
     */
    public CsvWriterUtil(Path path, Class<T> type) {
        this.path = path;
        this.type = type;
    }

    /**
     * Writes a single Java object to a CSV file
     * @param object the object that will be written to the CSV
     * @throws IOException if there is a problem with the i/o of the file
     * @throws CsvRequiredFieldEmptyException if a required field in the bean is missing
     * @throws CsvDataTypeMismatchException if a field cannot be converted to csv
     */
    public void write(T object) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        //This takes the CSVBindByName and makes the header
        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(type);

        try(Writer writer = new FileWriter(path.toString())){
            StatefulBeanToCsv<T> sbc = new StatefulBeanToCsvBuilder<T>(writer)
                    .withApplyQuotesToAll(false)
                    .withMappingStrategy(strategy)
                    .withSeparator(',')
                    .build();

            sbc.write(object);
        }
    }
}
