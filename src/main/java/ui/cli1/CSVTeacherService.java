package ui.cli1;

import users.Teacher;
//import data.TeacherData;
//class TeacherData {
    //Map<String, Teacher> getTeachers();   
    //void save();                        
//}
//This class needs the above from TeacherData to run 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TeacherService implementation that delegates all data access and CSV
 * persistence to {@link TeacherData}.
 */
public class CSVTeacherService implements TeacherService {

    private final TeacherData teacherData;
    private final Map<String, Teacher> teachers;

    /**
     * Creates a new CSV-backed teacher service.
     * TeacherData is responsible for loading from and saving to CSV.
     *
     * @param teacherData the data provider used to access and persist teachers
     */
    public CSVTeacherService(TeacherData teacherData) {
        this.teacherData = teacherData;
        this.teachers = teacherData.getTeachers(); // already loaded from CSV
    }

    @Override
    public boolean addTeacher(String id, String name) {
        if (teachers.containsKey(id)) {
            return false;
        }
        Teacher t = new Teacher(id, name);
        teachers.put(id, t);
        teacherData.save();   // persist updated list to CSV
        return true;
    }

    @Override
    public boolean updateTeacherField(String id, String field, String newValue) {
        Teacher t = teachers.get(id);
        if (t == null) {
            return false;
        }

        switch (field.toLowerCase()) {
            case "name":
                t.setName(newValue);
                break;
            default:
                return false; // unknown field
        }

        teacherData.save();   // persist updated list to CSV
        return true;
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
