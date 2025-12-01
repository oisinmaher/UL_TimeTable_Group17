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
        return timetable.toString();
    }

    @Override
    public String getTeacherTimetableAsString(String TeacherId) {
        return timetable.toString();
    }

}
