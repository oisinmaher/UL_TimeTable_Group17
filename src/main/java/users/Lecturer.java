package src.main.java.users;
import src.main.java.programCourse.CourseSemester;
import src.main.java.programCourse.CourseYear;
import src.main.java.timetables.TimeTable;

public class Lecturer extends User{
    String name;
    int id;
    public Lecturer(String name, int id){
        super(name, id);
    }
    public void setName(String name){
        this.name = name;
    }
//    void findLecturerById(){
//      return id;
//    }
}
