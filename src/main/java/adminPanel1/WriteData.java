package adminPanel1;

import export.ExportService;
import export.dto.*;
import export.io.CsvWriterUtil;
import export.mapper.*;
import modules.CourseModule;
import programCourse.CourseFull;
import programCourse.CourseSemester;
import programCourse.CourseYear;
import rooms.Room;
import timetables.Group;
import timetables.TimeSlot;
import users.Student;
import users.Teacher;

import javax.xml.crypto.Data;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Writes all domain objects to CSV files.
 * Mirrors the ReadData class.
 */
public class WriteData {

    public void writeAll(){

        ExportService exportService = new ExportService();

        //Writing students
        Path studentPath = Paths.get("src/main/data/StudentData.csv");
        CsvWriterUtil<StudentCsvDto> studentWriter = new CsvWriterUtil<>(studentPath, StudentCsvDto.class);
        List<Student> domainStudents = new ArrayList<>(DataManager.students.values());
        List<StudentCsvDto> flatStudents = new ArrayList<>(domainStudents.size());
        for (Student student : domainStudents) {
            StudentCsvDto studentCsvDto = StudentMapper.flatten(student);
            flatStudents.add(studentCsvDto);
        }
        try {
            exportService.export(flatStudents, studentWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Writing courseFulls
        Path courseFullPath = Paths.get("src/main/data/CourseFullData.csv");
        CsvWriterUtil<CourseFullCsvDto> courseFullWriter = new CsvWriterUtil<>(courseFullPath, CourseFullCsvDto.class);
        List<CourseFull> domainCourseFulls = new ArrayList<>(DataManager.courseFulls.values());
        List<CourseFullCsvDto> flatCourseFulls = new ArrayList<>(domainCourseFulls.size());
        for (CourseFull courseFull : domainCourseFulls) {
            CourseFullCsvDto csvDto = CourseFullMapper.flatten(courseFull);
            flatCourseFulls.add(csvDto);
        }
        try {
            exportService.export(flatCourseFulls,courseFullWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Writing courseYears
        Path courseYearPath = Paths.get("src/main/data/CourseYearData.csv");
        CsvWriterUtil<CourseYearCsvDto> courseYearWriter = new CsvWriterUtil<>(courseYearPath, CourseYearCsvDto.class);
        List<CourseYear> domainCourseYears = new ArrayList<>(DataManager.courseYears.values());
        List<CourseYearCsvDto> flatCourseYears = new ArrayList<>(domainCourseYears.size());
        for(CourseYear courseYear : domainCourseYears){
            CourseYearCsvDto csvDto = CourseYearMapper.flatten(courseYear);
            flatCourseYears.add(csvDto);
        }
        try{
            exportService.export(flatCourseYears, courseYearWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Writing courseModules
        Path courseModulePath = Paths.get("src/main/data/CourseModuleData.csv");
        CsvWriterUtil<CourseModuleCsvDto> courseModuleWriter = new CsvWriterUtil<>(courseModulePath, CourseModuleCsvDto.class);
        List<CourseModule> domainCourseModules = new ArrayList<>(DataManager.courseModules.values());
        List<CourseModuleCsvDto> flatCourseModules = new ArrayList<>(domainCourseModules.size());
        for(CourseModule courseModule : domainCourseModules){
            CourseModuleCsvDto csvDto = CourseModuleMapper.flatten(courseModule);
            flatCourseModules.add(csvDto);
        }
        try {
            exportService.export(flatCourseModules, courseModuleWriter);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        //Writing courseSemesters
        Path courseSemesterPath = Paths.get("src/main/data/CourseSemesterData.csv");
        CsvWriterUtil<CourseSemesterCsvDto> courseSemesterWriter = new CsvWriterUtil<>(courseSemesterPath, CourseSemesterCsvDto.class);
        List<CourseSemester> domainCourseSemesters = new ArrayList<>(DataManager.courseSemesters.values());
        List<CourseSemesterCsvDto> flatCourseSemesters = new ArrayList<>(domainCourseSemesters.size());
        for(CourseSemester cs : domainCourseSemesters){
            CourseSemesterCsvDto csvDto = CourseSemesterMapper.flatten(cs);
            flatCourseSemesters.add(csvDto);
        }
        try {
            exportService.export(flatCourseSemesters, courseSemesterWriter);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        //Writing teachers
        Path teacherPath = Paths.get("src/main/data/TeacherData.csv");
        CsvWriterUtil<TeacherCsvDto> teacherWriter = new CsvWriterUtil<>(teacherPath, TeacherCsvDto.class);
        List<Teacher> domainTeacher = new ArrayList<>(DataManager.teachers.values());
        List<TeacherCsvDto> flatTeacher = new ArrayList<>(domainTeacher.size());
        for(Teacher t : domainTeacher){
            TeacherCsvDto csvDto = TeacherMapper.flatten(t);
            flatTeacher.add(csvDto);
        }
        try {
            exportService.export(flatTeacher, teacherWriter);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        //Writing groups
        Path groupsPath = Paths.get("src/main/data/GroupData.csv");
        CsvWriterUtil<GroupCsvDto> groupWriter = new CsvWriterUtil<>(groupsPath, GroupCsvDto.class);
        List<Group> domainGroups = new ArrayList<>(DataManager.groups.values());
        List<GroupCsvDto> flatGroups = new ArrayList<>(domainGroups.size());
        for(Group g : domainGroups){
            GroupCsvDto csvDto = GroupMapper.flatten(g);
            flatGroups.add(csvDto);
        }
        try {
            exportService.export(flatGroups, groupWriter);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        //Writing timeslots
        Path timeSlotsPath = Paths.get("src/main/data/TimeSlotsData.csv");
        CsvWriterUtil<TimeSlotCsvDto> timeSlotWriter = new CsvWriterUtil<>(timeSlotsPath, TimeSlotCsvDto.class);
        List<TimeSlot> domainTimeSlots = new ArrayList<>(DataManager.timeSlots);
        List<TimeSlotCsvDto> flatTimeSlots = new ArrayList<>(domainTimeSlots.size());
        for(TimeSlot ts : domainTimeSlots){
            TimeSlotCsvDto csvDto = TimeSlotMapper.flatten(ts);
            flatTimeSlots.add(csvDto);
        }
        try {
            exportService.export(flatTimeSlots,timeSlotWriter);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        //Writing Rooms
        Path roomPath = Paths.get("src/main/data/RoomData.csv");
        CsvWriterUtil<RoomCsvDto> roomWriter = new CsvWriterUtil<>(roomPath, RoomCsvDto.class);
        List<Room> domainRooms = new ArrayList<>(DataManager.rooms.values());
        List<RoomCsvDto> flatRooms = new ArrayList<>(domainRooms.size());
        for(Room r : domainRooms){
            RoomCsvDto csvDto = RoomMapper.flatten(r);
            flatRooms.add(csvDto);
        }
        try {
            exportService.export(flatRooms, roomWriter);
        } catch (Exception e){
            throw new RuntimeException(e);
        }


    }
}