package src.main.java.ui.cli;
import java.util.List;
public interface StudentService 
{
    boolean addStudent(String id, String firstName, String lastName); 
    boolean removeStudent(String id); 
    boolean updateStudentField(String id, String field, String newValue); 
    Student findStudentbyID(String id); 
    List<Student> getallStudents(); 
}
