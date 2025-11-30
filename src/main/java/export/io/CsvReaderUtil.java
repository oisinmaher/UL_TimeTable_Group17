package export.io;
import java.io.*;
import java.nio.file.Path;
import java.util.List;

import com.opencsv.bean.*;

/**
 * Generic reader class for CSV files.
 * Can take any csv, read it and create a java object.
 * @param <T> can take a generic class to read
 */

public class CsvReaderUtil<T> {

    private final Path path;
    private final Class<T> type;

    /**
     * Constructor class for the reader
     * @param path the file path of the CSV
     * @param type the class to be read
     */
    public CsvReaderUtil(Path path, Class<T> type) {
        this.path = path;
        this.type = type;
    }

    /**
     * Reads an entire file and creates a list of that file's objects
     * @return a list of objects
     */
    public List<T> readAll() {
        try {
            Reader reader = new FileReader(path.toFile());
            HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
                    strategy.setType(type);

            CsvToBean<T> result = new CsvToBeanBuilder<T>(reader)
                    .withType(type)
                    .withMappingStrategy(strategy)
                    .build();

            return result.parse();


        } catch (FileNotFoundException | IllegalStateException e) {
            throw new RuntimeException(e);
        }

    }
}
