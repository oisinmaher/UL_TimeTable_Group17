package src.main.java.timetables;
import src.main.java.modules.CourseModule;
import src.main.java.rooms.Room;
import src.main.java.users.Student;

import java.util.List;
import java.util.Set;

public class Group {

    private int year ;
    private List<Student> students;
    private CourseModule modules;
    private Set<Room> rooms;

    public Group(int year, List<Student> students, CourseModule modules, Set<Room> rooms){

        this.year = year;
        this.students = students;
        this.modules = modules;
        this.rooms = rooms;

    }

    public int getYear() {
        return year;
    }

    public List<Student> getStudents() {
        return students;
    }

    public CourseModule getModules() {
        return modules;
    }

    public Set<Room> getRooms() {


        return rooms;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (Student s : students) {
            sb.append(s.getUserId()).append(", ");
        }

        return "Year: " + year + ", Students: " + sb + ", Rooms: " + rooms;
    }

}
