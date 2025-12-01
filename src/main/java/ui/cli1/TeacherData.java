package ui.cli1;
import adminPanel1.DataManager;
import adminPanel1.WriteData;
import users.Student;
import users.Teacher;

import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper class for teacher data
 */
public class TeacherData {
    public Map<String, Teacher> getTeachers() {
        return DataManager.teachers;
    }
    void save(){
        WriteData writer = new WriteData();
        writer.writeAll();
    }
}
