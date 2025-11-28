package ui.cli1;

import users.Student;
import users.Teacher;

import java.util.HashMap;
import java.util.Map;

public class StudentData {
    /**
     *
     */
    Map<String, Student> students = new HashMap<>();

    /**
     *
     * @return
     */
    Map<String, Student> getStudents() {
        return students;
    }

    /**
     * This will save the state after adding, removing or updating a student
     */
    void save(){

    }
}
