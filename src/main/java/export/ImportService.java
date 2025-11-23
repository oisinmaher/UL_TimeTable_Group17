package export;

import export.io.CsvReaderUtil;
import java.util.List;

public class ImportService{


    public <T> List<T> read(CsvReaderUtil<T> reader) throws Exception {
        return reader.readAll();
    }
}