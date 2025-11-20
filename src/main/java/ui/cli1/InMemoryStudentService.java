package src.main.java.ui.cli1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.main.java.users.Student;


public class InMemoryStudentService implements StudentService
{
    private final Map<String, Student> students = new HashMap<>(); 
    public InMemoryStudentService() {
        // Sample data – change/remove as you like
        addStudent("24254444", "Yousef", "Abu Al-Salqan");
        addStudent("24254666", "Alex", "Dalla Pria");
    }

    @Override
    public boolean addStudent(String id, String firstName, String lastName)
    {
        if(students.containsKey(id))
        {
            return false; 
        }
        students.put(id, new Student(id, firstName, lastName)); 
        return true; 
    } 

    @Override
     public boolean removeStudent(String id) {
        return students.remove(id) != null;
    }

    @Override
    public boolean updateStudentField(String id, String field, String newValue)
    {
        Student s = students.get(id); 
        if(s==null)
        {
            return false; 
        }
        switch(field.toLowerCase())
        {
            case "student_name":
                s.setName(newValue);
                return true;
            default:
                return false; 
            
        }
    }

    @Override
    public Student findStudentbyID(String id)
    {
        return students.get(id); 
    }

    @Override
    public List<Student> getAllStudents()
    {
        return new ArrayList<>(students.values()); 
    }

}
