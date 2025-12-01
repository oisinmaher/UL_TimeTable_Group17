package export.mapper;

import export.dto.TimeSlotCsvDto;
import modules.CourseModule;
import programCourse.CourseFull;
import timetables.Group;
import timetables.TimeSlot;
import users.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TimeSlotMapper {

    public static TimeSlotCsvDto flatten(TimeSlot ts) {
        //String timeSlotId = ts.getTime() + "_" + ts.getModule() + "_" + ts.getClassType();

        String students = String.join(",", ts.getStudents().keySet());

        List<String> courseCodes = new ArrayList<>();
        for (CourseFull courseCode : ts.getCoursesTakingThisModule()) {
            courseCodes.add(courseCode.getCode());
        }
        String cc = String.join(",", courseCodes);

        return new TimeSlotCsvDto(
                ts.getModule().getModuleCode(),
                ts.getClassType(),
                ts.getTime(),
                ts.getRoom().getRoomID(),
                ts.getTeacher().getUserId(),
                students,
                cc
        );
    }


    public static TimeSlot inflate(TimeSlotCsvDto csvDto, Map<String, CourseModule> moduleCodes, Map<String,
            CourseFull> courseFulls, Map<String, Student> studentIds) {

        String[] timeSplit = csvDto.getDayTime().split("_");

        String day = timeSplit[0];
        String time = timeSplit[1];

        String timeLong = TimeSlot.getTimeFromShortened(Integer.parseInt(time));
        String dayWord = TimeSlot.getDayFromNumber(Integer.parseInt(day));

        List<CourseFull> courseFullList = new ArrayList<>();
        String[] courses = csvDto.getCourseCodes().split(",");
        for (String courseCode : courses) {
            CourseFull course = courseFulls.get(courseCode);
            courseFullList.add(course);
        }


        CourseModule module = CourseModule.getModuleFromCode(csvDto.getModuleCode());
        Group group = module.getGroupAssigned();

        TimeSlot timeSlot = new TimeSlot(
                dayWord,
                timeLong,
                module,
                courseFullList,
                csvDto.getClassType(),
                csvDto.getRoomId(),
                csvDto.getTeacherId()
        );

        //Add students from CSV to the timeslot
        String studentIdsCsv = csvDto.getStudentIds();
        if (studentIdsCsv != null && !studentIdsCsv.isBlank()) {
            String[] studentIdArray = studentIdsCsv.split(",");
            for (String studentId : studentIdArray) {
                studentId = studentId.trim();
                if (!studentId.isEmpty()) {
                    timeSlot.addStudent(studentId);
                }
            }
        }
        return timeSlot;

    }
}
