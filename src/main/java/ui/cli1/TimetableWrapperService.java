package ui.cli1;

import timetables.TimeSlot;
import timetables.UserTimeTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of {@link TimetableService} that delegates directly to the
 * domain {@link UserTimeTable} object, which already contains all timetables
 * for students and lecturers (using TreeMaps per day).
 */
public class TimetableWrapperService implements TimetableService {

    private final UserTimeTable timetable;

    /**
     * Creates a new timetable service wrapper.
     * @param timetable the domain timetable that holds all sessions
     */
    public TimetableWrapperService(UserTimeTable timetable) {
        this.timetable = timetable;
    }

    /**
     * Gets the students timetable as a string
     * @param studentId the ID of the student
     * @return the students timetable as a string / or if no TimeSlots are found, display
     * "No timetable found" message
     */
    @Override
    public String getStudentTimetableAsString(String studentId) {
        List<TimeSlot> slots = UserTimeTable.getUserTimetable(studentId).getTimeSlots();
        if (slots == null || slots.isEmpty()) {
            return "No timetable found for student " + studentId + ".";
        }
        return formatTimetable(slots, "Timetable for student " + studentId);
    }

    /**
     * Gets the teachers timetable as a string
     * @param TeacherId the ID of the lecturer
     * @return the teachers timetable as a string / or if no TimeSlots are found, display
     * "No timetable found" message
     */
    @Override
    public String getTeacherTimetableAsString(String TeacherId) {
        List<TimeSlot> slots = UserTimeTable.getUserTimetable(TeacherId).getTimeSlots();
        if (slots == null || slots.isEmpty()) {
            return "No timetable found for lecturer " + TeacherId + ".";
        }
        return formatTimetable(slots, "Timetable for lecturer " + TeacherId);
    }

    /**
     * Formats a list of TimeSlots into a readable timetable string.
     * Relies on TimeSlot.toString() and TimeSlot.compareTo().
     * @param slots list of TimeSlots
     * @param title student / lecturer ID number
     * @return list of TimeSlots as a timetable string
     */
    private String formatTimetable(List<TimeSlot> slots, String title) {
        StringBuilder sb = new StringBuilder();
        sb.append(title).append("\n");
        sb.append("---------------------------------\n");

        List<TimeSlot> copy = new ArrayList<>(slots);
//        Collections.sort(copy); // TimeSlot should implement Comparable<TimeSlot>

        for (TimeSlot slot : copy) {
            sb.append(slot).append("\n");
        }

        return sb.toString();
    }
}
