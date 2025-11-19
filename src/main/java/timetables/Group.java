package timetables;
import modules.CourseModule;
import programCourse.CourseYear;
import rooms.Room;
import users.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Group will be the students in a given module at a specific semester
 */
public class Group {


    private final CourseModule module;
    private final CourseYear year;
    private final List<Student> students;
    private final List<Room> rooms;
    private final String groupId;

    public Group(CourseModule module, CourseYear year, List<Student> students, List<Room> rooms, String groupId){
       if (module == null) {
        throw new IllegalArgumentException("module can't be null");
    }
    if (year == null) {
        throw new IllegalArgumentException("year can't be null");
    }
    if (students == null) {
        throw new IllegalArgumentException("students list can't be null");
    }
    if (rooms == null) {
        throw new IllegalArgumentException("rooms list can't be null");
    }
        this.module = module;
        this.year = year; 
        this.students = students; 
        this.rooms = rooms; 
        this.groupId = groupId; 
    }

    public String getYear() {
        return year.getYearNumber();
    }
    //public int getYear() {          // I think it's better this way 
        //return year.getYearNumber();
   // }

    public List<Student> getStudents() {
        return students;
    }

    public int getNumberOfStudents() {
    return students.size();
    }


    public String getModuleCode() {
        return module.getModuleCode();
    }

    public Map<String, String> mapStudentsToModuleCodes() {
        String moduleCode = module.getModuleCode();
        if (moduleCode == null || moduleCode.isEmpty()) {
            throw new IllegalStateException("Module code cannot be null or empty");
    }

    Map<String, String> result = new HashMap<>();

    for (Student s : students) {
        if (s == null) {
            throw new IllegalStateException("Student list contains null");
        }
        result.put(s.getUserId(), moduleCode);
    }

    return result;
}

    public List<Room> getRooms()
    {
        return this.rooms; 
    }

    public String getGroupId()
    {
        return this.groupId; 
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Build comma-separated list of student IDs
        for (int i = 0; i < students.size(); i++) {
            sb.append(students.get(i).getUserId());
            if (i < students.size() - 1) {
                sb.append(", ");
            }
        }

        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", module=" + module.getModuleCode() +
                ", year=" + year.getYearNumber() +
                ", students=[" + sb +
                "], rooms=" + rooms +
                '}';
    }



}
