package export;

import export.io.CsvReaderUtil;
import java.util.List;

public class ImportService{

    /**
     * Can take a generic class to import into memory
     * @param reader the reader object that will access the file to import
     * @return the contents of what was read
     * @param <T> what type you want was read to be imported into
     * @throws Exception a general error handler
     */
    public <T> List<T> read(CsvReaderUtil<T> reader) throws Exception {
        return reader.readAll();
    }
}