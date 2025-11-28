package ui.cli1;


import users.Student;

//import data.StudentData; 
//class StudentData {
    // Map<String, Student> getStudents();  // returns the in-memory map (loaded from CSV elsewhere)
    //void save();                         // rewrites the CSV using CsvWriterUtil
//}

//This class needs the above from student data for it to work.


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * StudentService implementation that delegates all data access
 * and CSV persistence to {@link StudentData}.
 */
public class CSVStudentService implements StudentService {

    private final StudentData studentData;
    private final Map<String, Student> students;

    /**
     * Creates a new service that uses the provided StudentData object.
     * StudentData is responsible for loading from and saving to CSV.
     *
     * @param studentData the data provider used to access and persist students
     */
    public CSVStudentService(StudentData studentData) {
        this.studentData = studentData;
        this.students = studentData.getStudents(); // already loaded from CSV
    }

    @Override
    public boolean addStudent(String id, String name, String courseCode, String year) {
        if (students.containsKey(id)) {
            return false;
        }
        Student s = new Student(id, name, courseCode, year);
        students.put(id, s);
        studentData.save();  // let StudentData handle CSV rewrite
        return true;
    }

    @Override
    public boolean removeStudent(String id) {
        if (students.remove(id) == null) {
            return false;
        }
        studentData.save();  // persist updated list
        return true;
    }

    @Override
    public boolean updateStudentField(String id, String field, String newValue) {
        Student s = students.get(id);
        if (s == null) {
            return false;
        }

        switch (field.toLowerCase()) {
            case "name":
            case "Name":
                s.setName(newValue);
                break;
            
            default:
                return false; // unknown field
        }

        studentData.save();  // persist updated list
        return true;
    }

    @Override
    public Student findStudentbyID(String id) {
        return students.get(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }
}
