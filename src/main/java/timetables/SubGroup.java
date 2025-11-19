package timetables;

import modules.CourseModule;
import programCourse.CourseYear;
import rooms.Room;
import users.Student;

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

    /**
     * Constructs a SubGroup for a given module/year/students/rooms and session type.
     *
     * @param module     the module this subgroup belongs to
     * @param year       the course year
     * @param students   the students in this subgroup
     * @param rooms      rooms used by this subgroup
     * @param groupId    the base group id (e.g. "G1")
     * @param sessionType the type of session this subgroup is for (LAB or TUTORIAL)
     */
    public SubGroup(CourseModule module,
                    CourseYear year,
                    List<Student> students,
                    List<Room> rooms,
                    String groupId,
                    SessionType sessionType) {

        // Call the parent Group constructor
        super(module, year, students, rooms, groupId);

        if (sessionType == null) {
            throw new IllegalArgumentException("sessionType cannot be null");
        }

        this.sessionType = sessionType;
        // Subgroup ID must start with the type
        this.subGroupId = sessionType.name() + "-" + groupId;
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
