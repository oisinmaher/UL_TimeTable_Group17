package src.main.java.timetables;

import src.main.java.modules.CourseModule;
import src.main.java.programCourse.CourseYear;
import src.main.java.rooms.Room;
import src.main.java.users.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A SubGroup represents a finer-grained grouping of students for a module,
 * where the grouping is based on the type of session: LAB or TUTORIAL.
 *
 * Each SubGroup has:
 *  - A base groupId inherited from Group (e.g. "G1")
 *  - A SessionType (LAB or TUTORIAL)
 *  - A subGroupId that starts with the session type, e.g. "LAB-G1", "TUTORIAL-G1"
 *
 * If a module offers both labs and tutorials, you typically create:
 *  - one SubGroup for LAB
 *  - one SubGroup for TUTORIAL
 * Each SubGroup can still contain all students in the parent group.
 */
public class SubGroup extends Group {
    public enum SessionType {
        LAB,
        TUTORIAL
    }
    private final SessionType sessionType;
    private final String subGroupId;   // e.g. "LAB-G1", "TUTORIAL-G1"
    private final Map<String, Student> studentsInGroup;

    // This will map it so if a module has multiple groups you can find which letter
    // to assign for its next groupId,
    // (e.g. when creating a new group for a module that contains CS4004A, it will return A then you can do 'A'++
    // to get 'B', assign the group the name cs4004B and update map to B
    private static final Map<String, Character> moduleRecentGroupId = new HashMap<>();

    /**
     * Constructs a SubGroup for a given module/year/students/rooms and session type.
     *
     * @param module     the module this subgroup belongs to
     * @param year       the course year
     * @param sessionType the type of session this subgroup is for (LAB or TUTORIAL)
     */
    public SubGroup(CourseModule module,
                    CourseYear year, SessionType sessionType) {

        // Call the parent Group constructor
        super(module, year);

        if (sessionType == null) {
            throw new IllegalArgumentException("sessionType cannot be null");
        }

        this.sessionType = sessionType;
        // Subgroup ID must start with the type
        // This gets the correct module code indicator
        // (could be made its own method idk what the convention for constructors is)

        // gets module name
        String moduleCode = module.getModuleCode().toLowerCase();
        studentsInGroup = new HashMap<>();
        // group indicator (a letter a->z)
        char groupIndicator;
        // check if this module already has a group
        if(moduleRecentGroupId.containsKey(moduleCode)){
            // if it does the map value will be last indicator used (e.g 'a')
            groupIndicator = moduleRecentGroupId.get(moduleCode);
            // increases group indicator (example 'a' to 'b')
            groupIndicator++;
        }
        // else it sets it to first value (which will be 'a')
        else groupIndicator = 'a';
        // makes group id the moduleCode + groupIndicator (e.g. cs4004a)
        this.subGroupId = moduleCode + groupIndicator;
        // adds / replaces module indicatorCode in the map
        moduleRecentGroupId.put(moduleCode, groupIndicator);
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    /**
     * Returns the subgroup ID for this specific session type.
     * Example: "LAB-G1", "TUTORIAL-G1"
     */
    public String getSubGroupId() {
        return subGroupId;
    }

    /**
     * Map each studentId in this subgroup to this subgroup's ID.
     *
     * Example:
     *  24254444 -> "LAB-G1"
     *  24545667 -> "LAB-G1"
     */
    public Map<String, String> mapStudentIdsToSubGroupId() {
        Map<String, String> result = new HashMap<>();

        for (Student s : getStudents()) {
            if (s == null) {
                throw new IllegalStateException("Student list contains null in SubGroup");
            }
            result.put(s.getUserId(), subGroupId);
        }

        return result;
    }

//    public Map<String,String> mapSubGroupIdToRoomID()// Might have to be it's own class because it has to coordinate with room and its subclasses along with coursemodules
//    {
//
//    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        List<Student> students = getStudents();
        for (int i = 0; i < students.size(); i++) {
            sb.append(students.get(i).getUserId());
            if (i < students.size() - 1) {
                sb.append(", ");
            }
        }

        return "SubGroup{" +
                "subGroupId='" + subGroupId + '\'' +
                ", sessionType=" + sessionType +
                ", module=" + getModuleCode() +
                ", year=" + getYear() +
                ", students=[" + sb +
                "], rooms=" + getRooms() +
                '}';
    }
}
