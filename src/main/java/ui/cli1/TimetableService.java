package ui.cli1;


import timetables.TimeSlot;
import java.util.List;

/**
 * Provides timetable views for students and lecturers.
 * The CLI only needs a string representation, so the service
 * returns pre-formatted timetables as text.
 */
public interface TimetableService {

    /**
     * Returns the timetable for the given student as a formatted string.
     *
     * @param studentId the ID of the student
     * @return a formatted timetable string, or a message if none is found
     */
    String getStudentTimetableAsString(String studentId);

    /**
     * Returns the timetable for the given lecturer as a formatted string.
     *
     * @param TeacherId the ID of the lecturer
     * @return a formatted timetable string, or a message if none is found
     */
    String getTeacherTimetableAsString(String TeacherId);
}
