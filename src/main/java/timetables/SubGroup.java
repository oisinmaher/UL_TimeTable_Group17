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
    private final String sessionType;
    private final String subGroupId;   // e.g. "CS4004_LAB-G1", "CS2002_TUTORIAL-G1"

    // This will map it so if a module_sessionType has multiple groups you can find which letter
    // to assign for its next groupId,
    // (e.g. when creating a new group for a module that contains CS4004_lab_A, it will return A then you can do 'A'++
    // to get 'B', assign the group the name cs4004B and update map to B
    private static final Map<String, Character> moduleRecentGroupId = new HashMap<>();

    /**
     * Constructs a SubGroup for a given module/year/students/rooms and session type.
     *
     * @param moduleCode     the module this subgroup belongs to
     * @param sessionType the type of session this subgroup is for (LAB or TUTORIAL)
     */
    public SubGroup(String moduleCode, String sessionType) {

        // Call the parent Group constructor
        super(moduleCode);

        if (sessionType == null) {
            throw new IllegalArgumentException("sessionType cannot be null");
        }
        sessionType = sessionType.toLowerCase();
        if(!(sessionType.equals("lab") || sessionType.equals("tutorial"))){
            throw new IllegalArgumentException("This session type doesnt exist, choose either lab or tutorial");
        }

        this.sessionType = sessionType;
        // Subgroup ID must start with the type
        // This gets the correct module code indicator
        // (could be made its own method idk what the convention for constructors is)
        // group indicator (a letter a->z)
        char groupIndicator;
        // check if this module already has a group
        if(moduleRecentGroupId.containsKey(moduleCode + "_" + sessionType)){
            // if it does the map value will be last indicator used (e.g 'a')
            groupIndicator = moduleRecentGroupId.get(moduleCode + "_" + sessionType);
            // increases group indicator (example 'a' to 'b')
            groupIndicator++;
        }
        // else it sets it to first value (which will be 'a')
        else groupIndicator = 'a';
        // makes group id the moduleCode + groupIndicator (e.g. cs4004a)
        this.subGroupId = moduleCode + "_" + sessionType + "_" + groupIndicator;
        // adds / replaces module indicatorCode in the map
        moduleRecentGroupId.put(moduleCode + "_" + sessionType, groupIndicator);
    }

    public String getSessionType() {
        return this.sessionType;
    }
    // DON'T NEED ADD/REMOVE STUDENT METHOD BECAUSE PARENT CLASS HAS IT

    /**
     * Returns the subgroup ID for this specific session type.
     * Example: "LAB-G1", "TUTORIAL-G1"
     */
    public String getSubGroupId() {
        return this.subGroupId;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        List<String> students = getStudentsId();
        for (int i = 0; i < students.size(); i++) {
            sb.append(students.get(i));
            if (i < students.size() - 1) {
                sb.append(", ");
            }
        }

        return "SubGroup{" +
                "subGroupId='" + subGroupId + '\'' +
                ", sessionType=" + sessionType +
                ", module=" + getModuleCode() +
                ", students=[" + sb +
                "], rooms=" + getRooms() +
                '}';
    }
}
