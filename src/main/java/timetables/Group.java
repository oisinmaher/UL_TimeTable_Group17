package src.main.java.timetables;
import src.main.java.modules.CourseModule;
import src.main.java.programCourse.*;
import src.main.java.rooms.LectureRoom;
import src.main.java.rooms.Room;
import src.main.java.users.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Group will be the students in a given module at a specific semester
 */
public class Group {


    private CourseModule module;
    private CourseYear year; 
    private List<Student> students; 
    private List<Room> rooms; 
    private String groupId;


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

public Map<String, String> mapGroupIdsToRoomIds()
{
    Map<String, String> result = new HashMap<>();
    List<Room> rooms = getRooms(); 
    
     if (groupId == null || rooms == null) {
        throw new IllegalArgumentException("Lists and IDs cannot be null.");
    }
    for(int i = 0; i<rooms.size(); i++)
    {
        Room room = rooms.get(i);  
        if (!(room instanceof LectureRoom)) {
                throw new IllegalStateException(
                        "Group " + groupId + " requires LectureRoom but got " + room.getClass().getSimpleName()
                ); 
        }
        if(room.getMaxCapacity()>=students.size())
        {
            result.put(groupId, room.getRoomID()); 
        }

    
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
