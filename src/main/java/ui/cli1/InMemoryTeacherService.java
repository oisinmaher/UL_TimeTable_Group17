package src.main.java.ui.cli1;

import src.main.java.users.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTeacherService implements TeacherService {

    /**
     * Maps lectureId (as String) to Teacher object
     */
    private final Map<String, Teacher> teachers = new HashMap<>();

    public InMemoryTeacherService() {
        
        addTeacher("123", "Michael");
        addTeacher("455", "Alan");
    }

//    @Override
    public boolean addTeacher(String id, String name) {
        if (teachers.containsKey(id)) {
            return false;
        }
        teachers.put(id, new Teacher(name, id));
        return true;
    }

    @Override
    public boolean updateTeacherField(String id, String field, String newValue) {
        Teacher l = teachers.get(id);
        if (l == null) return false;

        switch(field.toLowerCase()) {
            case "name":
                l.setName(newValue);
                return true;
            default:
                return false;
        }
    }

    @Override
    public Teacher findTeacherById(String id) {
        return teachers.get(id);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return new ArrayList<>(teachers.values());
    }
}
