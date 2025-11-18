package persistence;

import users.Student;

public class StudentCsvStorage extends CsvStorage<Student> {


    public StudentCsvStorage() {
        super("src/main/resources/student.csv", Student.class, new String[]{"name", "userId"});
    }
}
