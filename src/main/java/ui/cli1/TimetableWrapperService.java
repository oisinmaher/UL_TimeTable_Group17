package ui.cli1;



import timetables.TimeSlot;
import timetables.UserTimeTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of {@link TimetableService} that delegates directly to the
 * domain {@link Timetable} object, which already contains all timetables
 * for students and lecturers (using TreeMaps per day).
 */
public class TimetableWrapperService implements TimetableService {

    private final UserTimeTable timetable;

    /**
     * Creates a new timetable service wrapper.
     *
     * @param timetable the domain timetable that holds all sessions
     */
    public TimetableWrapperService(UserTimeTable timetable) {
        this.timetable = timetable;
    }

    @Override
    public String getStudentTimetableAsString(String studentId) {
        List<TimeSlot> slots = UserTimeTable.getStudentTimeSlots(studentId);
        if (slots == null || slots.isEmpty()) {
            return "No timetable found for student " + studentId + ".";
        }
        return formatTimetable(slots, "Timetable for student " + studentId);
    }

    @Override
    public String getTeacherTimetableAsString(String TeacherId) {
        List<TimeSlot> slots = UserTimeTable.getLecturerTimeSlots(TeacherId);
        if (slots == null || slots.isEmpty()) {
            return "No timetable found for lecturer " + TeacherId + ".";
        }
        return formatTimetable(slots, "Timetable for lecturer " + TeacherId);
    }

    /**
     * Formats a list of TimeSlots into a readable timetable string.
     * Relies on TimeSlot.toString() and TimeSlot.compareTo().
     */
    private String formatTimetable(List<TimeSlot> slots, String title) {
        StringBuilder sb = new StringBuilder();
        sb.append(title).append("\n");
        sb.append("---------------------------------\n");

        List<TimeSlot> copy = new ArrayList<>(slots);
        Collections.sort(copy); // TimeSlot should implement Comparable<TimeSlot>

        for (TimeSlot slot : copy) {
            sb.append(slot).append("\n");
        }

        return sb.toString();
    }
}
