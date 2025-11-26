package ui.cli1;

import users.Student;
import users.Teacher;

import java.util.HashMap;
import java.util.Map;

public class StudentData {
    Map<String, Student> students = new HashMap<>();
    Map<String, Student> getStudents() {
        return students;
    }
    void save(){

    }
}
