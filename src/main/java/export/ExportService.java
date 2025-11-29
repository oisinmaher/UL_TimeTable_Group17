package export;

import export.io.CsvWriterUtil;

import java.util.List;

public class ExportService{

    /**
     * A generic method that initialises a write function to a file
     * @param objectsToExport can take any object to write
     * @param writer uses the WriterUtil class to write to the CSV
     * @param <T> can return any type
     * @throws Exception
     */
    public <T> void export(List<T> objectsToExport, CsvWriterUtil<T> writer) throws Exception {
        writer.writeAll(objectsToExport);
    }
}