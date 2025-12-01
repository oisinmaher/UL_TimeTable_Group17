package ui.cli1;

import adminPanel1.DataManager;
import adminPanel1.WriteData;
import users.Student;
import users.Teacher;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper Class for student data
 */
public class StudentData {

    public Map<String, Student> getStudents(){
        return DataManager.students;
    }

    void save(){
            WriteData writer = new WriteData();
            writer.writeAll();
    }
}
