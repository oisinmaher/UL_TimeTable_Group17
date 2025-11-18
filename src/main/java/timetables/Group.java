package src.main.java.timetables;
import src.main.java.modules.CourseModule;
import src.main.java.rooms.Room;
import src.main.java.users.Student;

import java.util.List;
import java.util.Set;

/**
 * Group will be the students in a given room for a given module at a given time
 */
public class Group {


    private CourseModule modules;

    public Group(CourseModule modules){
        this.modules = modules;
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

    public Room getRooms() {
        return room;
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
