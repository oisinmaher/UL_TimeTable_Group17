package export;

import export.io.CsvWriterUtil;

public class ExportService{

    /**
     * A generic method that initialises a write function to a file
     * @param objectToExport can take any object to write
     * @param writer uses the WriterUtil class to write to the CSV
     * @param <T> can return any type
     * @throws Exception
     */
    public <T> void export(T objectToExport, CsvWriterUtil<T> writer) throws Exception {
        writer.write(objectToExport);
    }
}