package persistence;

import users.Lecturer;

public class LecturerCsvStorage extends CsvStorage<Lecturer> {

    public LecturerCsvStorage() {
        super("src/main/resources/lecturers.csv", Lecturer.class, new String[]{"name", "userId"});
    }
}
