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
     *
     * @param timetable the domain timetable that holds all sessions
     */
    public TimetableWrapperService(UserTimeTable timetable) {
        this.timetable = timetable;
    }

    /**
     * Gets the students timetable as a string
     * @param studentId the ID of the student
     * @return the students timetable as a string
     */
    @Override
    public String getStudentTimetableAsString(String studentId) {
        return timetable.toString();
    }

    /**
     * Gets the teachers timetable as a string
     * @param TeacherId the ID of the lecturer
     * @return the teachers timetable as a string
     */
    @Override
    public String getTeacherTimetableAsString(String TeacherId) {
        return timetable.toString();
    }

}