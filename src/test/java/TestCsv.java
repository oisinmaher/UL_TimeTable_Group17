import export.ExportService;
import export.ImportService;
import export.dto.CourseFullCsvDto;
import export.dto.StudentCsvDto;
import export.io.CsvReaderUtil;
import export.mapper.CourseFullMapper;
import export.mapper.StudentMapper;
import export.io.CsvWriterUtil;
import modules.CourseModule;
import programCourse.CourseFull;
import programCourse.CourseSemester;
import programCourse.CourseYear;
import users.Student;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestCsv {

    public static void main(String[] args) {

        ExportService exportService = new ExportService();
        ImportService importService = new ImportService();

/*
        CourseFull ec121 = new CourseFull("EC121", "Bcom Econ");
        ec121.addCourseYear("1");
        ec121.addCourseYear("2");
        CourseYear AY25ec121Year1;
        CourseYear AY25ec121Year2;
        AY25ec121Year1 = ec121.getCourseYears().get(0);
        AY25ec121Year2 = ec121.getCourseYears().get(1);
        AY25ec121Year1.addSemester("Autumn");
        AY25ec121Year2.addSemester("Autumn");
        CourseSemester AY25ec121Y1sem1 = AY25ec121Year1.getSemesters().get(0);
        CourseSemester AY25ec121Y2sem1 = AY25ec121Year2.getSemesters().get(0);
        AY25ec121Y1sem1.addNewModule("EC101", "Intro to Econ");
        AY25ec121Y2sem1.addNewModule("EC102", "Foundations of Econ");

        Student student = new Student("123456", "Alex", "EC121", "1");



        StudentCsvDto studentCsvDto = StudentMapper.flatten(student);

 */

        String filepath = "D:\\UL Work\\Yr 2 Sem 1\\CS4013 - OOP\\TimetableProject\\UL_TimeTable_Group17\\src\\test\\resources\\csvstudentstest.csv";
        Path path = Paths.get(filepath);
        String csvcoursefull = "D:\\UL Work\\Yr 2 Sem 1\\CS4013 - OOP\\TimetableProject\\UL_TimeTable_Group17\\src\\test\\resources\\csvcoursefull.csv";
        Path path2 = Paths.get(csvcoursefull);

        /*
        CourseFullCsvDto courseFullCsvDto = CourseFullMapper.flatten(ec121);

        CsvWriterUtil<CourseFullCsvDto> writer = new CsvWriterUtil<>(path2, CourseFullCsvDto.class);

        try{
            exportService.export(courseFullCsvDto, writer);
        }catch (Exception e){
            throw new RuntimeException(e);
        }




        CsvWriterUtil<StudentCsvDto> writer2 = new CsvWriterUtil<>(path, StudentCsvDto.class);
        try {
            exportService.export(studentCsvDto, writer2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


         */

        CsvReaderUtil<CourseFullCsvDto> reader2 = new CsvReaderUtil<>(path2, CourseFullCsvDto.class);

        List<CourseFullCsvDto> courseFullCsvDtoList;

        try{
            courseFullCsvDtoList = importService.read(reader2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<CourseFull> courseFulls = new ArrayList<>();
        for (CourseFullCsvDto dto : courseFullCsvDtoList) {
            CourseFull crs1 = CourseFullMapper.inflate(dto);
            courseFulls.add(crs1);
        }



        CsvReaderUtil<StudentCsvDto> reader = new CsvReaderUtil<>(path, StudentCsvDto.class);


        List<StudentCsvDto> studentCsvDtoList;
        try {
            studentCsvDtoList = importService.read(reader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        List<Student> students = new ArrayList<>();
        for (StudentCsvDto dto : studentCsvDtoList) {
            Student st1 = StudentMapper.inflate(dto);
            students.add(st1);
        }

        for(Student s : students) {
            System.out.println(s);
        }

        for(CourseFull c: courseFulls) {
            System.out.print(c.getName() + "," + c.getCode());
            for(int i =0; i < c.getCourseYears().size(); i++) {

                System.out.print("," + c.getCourseYears().get(i).getYearNumber());
            }
        }

    }

}
