package export.io;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;

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
     * @param path the absolute file path for the CSV
     * @param type the class to be written to the file
     */
    public CsvWriterUtil(Path path, Class<T> type) {
        this.path = path;
        this.type = type;
    }

    /**
     * Writes all Java objects to a CSV file
     * @param objects the objects that will be written to the CSV
     * @throws IOException if there is a problem with the i/o of the file
     * @throws CsvRequiredFieldEmptyException if a required field in the bean is missing
     * @throws CsvDataTypeMismatchException if a field cannot be converted to csv
     */

    //Changed to list of generic objects as we now write all at once
    public void writeAll(List<T> objects) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        /*Can use HeaderNameMappingStrategy using CSVBindByName- but this does not take the order into account,
         but headers are then written.
        Can also use the CSVBindByPosition and decides the position of the data in the csv,
         no headers are written - not good.
        */

        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();

        //ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();

        strategy.setType(type);

        try(Writer writer = new FileWriter(path.toString())){
            StatefulBeanToCsv<T> sbc = new StatefulBeanToCsvBuilder<T>(writer)
                    .withMappingStrategy(strategy)
                    .withApplyQuotesToAll(false)
                    .withSeparator(',')
                    .build();

            sbc.write(objects);
        }
    }
}
