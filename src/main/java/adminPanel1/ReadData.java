package adminPanel1;

import export.dto.*;
import export.io.CsvReaderUtil;
import export.mapper.*;
import modules.CourseModule;
import programCourse.CourseFull;
import programCourse.CourseYear;
import rooms.Room;
import timetables.Group;
import users.Student;
import users.Teacher;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class reads in data from csv files and initialises objects
 * The data are strings so it creates new objects
 * The order of reading in matters
 * for example Student have TimeTable variable, and TimeTable has Student variable
 * So for instance we can Create Student object first and not have it point to a timetable (as there is no available object)
 * Create a timetable and point it to aforementioned student as well as then pointing that student to new instance
 */
public class ReadData {

    public void importAll() throws URISyntaxException {

        //Read in rooms
        Path roomPath = Paths.get(getClass().getClassLoader().getResource("resources/RoomData.csv").toURI());
        CsvReaderUtil<RoomCsvDto> roomReader = new CsvReaderUtil<>(roomPath, RoomCsvDto.class);
        List<RoomCsvDto> roomDtos = roomReader.readAll();
        for (RoomCsvDto roomDto : roomDtos) {
            Room room = RoomMapper.inflate(roomDto);
        }

        //Read in teachers
        Path teacherPath = Paths.get(getClass().getClassLoader().getResource("resources/TeacherData.csv").toURI());
        CsvReaderUtil<TeacherCsvDto> teacherReader = new CsvReaderUtil<>(teacherPath, TeacherCsvDto.class);
        List<TeacherCsvDto> teacherDtos = teacherReader.readAll();
        for (TeacherCsvDto teacherDto : teacherDtos) {
            Teacher teacher = TeacherMapper.inflate(teacherDto);
        }

        //Read in students
        Path studentPath = Paths.get(getClass().getClassLoader().getResource("resources/StudentData.csv").toURI());
        CsvReaderUtil<StudentCsvDto> studentReader = new CsvReaderUtil<>(studentPath, StudentCsvDto.class);
        List<StudentCsvDto> studentCsvDtos = studentReader.readAll();
        for (StudentCsvDto studentCsvDto : studentCsvDtos) {
            Student student = StudentMapper.inflate(studentCsvDto);
        }

        //Read in courseFulls
        Path courseFullPath = Paths.get(getClass().getClassLoader().getResource("resources/CourseFullData.csv").toURI());
        CsvReaderUtil<CourseFullCsvDto> courseFullReader = new CsvReaderUtil<>(courseFullPath, CourseFullCsvDto.class);
        List<CourseFullCsvDto> courseFullDtos = courseFullReader.readAll();
        Map<String, CourseFull> coursesMap = new HashMap<>();
        for (CourseFullCsvDto courseFullDto : courseFullDtos) {
            CourseFull course = CourseFullMapper.inflate(courseFullDto);
            coursesMap.put(course.getName(), course);
        }

        //Read in courseYears
        Path courseYearPath = Paths.get(getClass().getClassLoader().getResource("resources/CourseYearData.csv").toURI());
        CsvReaderUtil<CourseYearCsvDto> courseYearReader = new CsvReaderUtil<>(courseYearPath, CourseYearCsvDto.class);
        List<CourseYearCsvDto> courseYearCsvDtos = courseYearReader.readAll();
        for (CourseYearCsvDto courseYearDto : courseYearCsvDtos) {
            CourseFull course = coursesMap.get(courseYearDto.getYearId().substring(0, 4));
            CourseYearMapper.inflate(course, courseYearDto);
        }

        //Read in courseSemesters
        Path courseSemesterPath = Paths.get(getClass().getClassLoader().getResource("resources/CourseSemesterData.csv").toURI());
        CsvReaderUtil<CourseSemesterCsvDto> courseSemesterReader = new CsvReaderUtil<>(courseSemesterPath, CourseSemesterCsvDto.class);
        List<CourseSemesterCsvDto> semesterCsvDtos = courseSemesterReader.readAll();
        for (CourseSemesterCsvDto courseSemesterDto : semesterCsvDtos) {
            String semesterId = courseSemesterDto.getSemesterId();
            String courseCode = semesterId.substring(0, 5);
            String yearId = semesterId.substring(0, 8);

            CourseFull course = coursesMap.get(courseCode);
            CourseYear year = CourseYear.getCourseYear(yearId);
            CourseSemesterMapper.inflate(year, course, courseSemesterDto);
        }

        //Yet to add...
        //Read in course modules
        Path courseModulesPath = Paths.get(getClass().getClassLoader().getResource("resources/CourseModuleData.csv").toURI());
        CsvReaderUtil<CourseModuleCsvDto> courseModulesReader = new CsvReaderUtil<>(courseModulesPath, CourseModuleCsvDto.class);
        List<CourseModuleCsvDto> courseModulesCsvDtos = courseModulesReader.readAll();
        for (CourseModuleCsvDto courseModuleCsvDto : courseModulesCsvDtos) {
            CourseFull course = coursesMap.get(courseModuleCsvDto.getCode().substring(0, 5));
            CourseModuleMapper.inflate(course, courseModuleCsvDto);
        }

        //Read in groups
        Path groupsPath = Paths.get(getClass().getClassLoader().getResource("resources/GroupData.csv").toURI());
        CsvReaderUtil<GroupCsvDto> groupsReader = new CsvReaderUtil<>(groupsPath, GroupCsvDto.class);
        List<GroupCsvDto> groupsCsvDtos = groupsReader.readAll();
        for (GroupCsvDto groupCsvDto : groupsCsvDtos) {
            Group group = GroupMapper.inflate(groupCsvDto);
        }

        //Read in timeslots
        Path timeSlotsPath = Paths.get(getClass().getClassLoader().getResource("resources/TimeSlotData.csv").toURI());
        CsvReaderUtil<TimeSlotCsvDto> timeSlotReader = new CsvReaderUtil<>(timeSlotsPath, TimeSlotCsvDto.class);
        List<TimeSlotCsvDto> timeSlotCsvDtos = timeSlotReader.readAll();
        for (TimeSlotCsvDto ts : timeSlotCsvDtos){
            Map<String, CourseModule> moduleMap = CourseModule.getModuleMap();
            Map<String, Student> students = Student.getAllStudentsEnrolled();

            TimeSlotMapper.inflate(ts, moduleMap, coursesMap, students);
        }

    }
}
