package ui.cli1;
import users.Student;
import users.Teacher;

import java.util.HashMap;
import java.util.Map;

public class TeacherData {
    Map<String, Teacher> teachers = new HashMap<>();
    Map<String, Teacher> getTeachers() {
        return teachers;
    }
    void save(){

    }
}
